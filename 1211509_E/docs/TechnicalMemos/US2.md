# Technical Memo — Asynchronous Reader and User Creation(US2)

## Problem

The functional requirement is:

> **As a librarian, I want to create a Reader and the respective User in the same request.**

The original system was implemented as a **monolithic architecture**, where Readers and Users were managed within the same codebase and database. This approach simplified synchronous persistence, however it did not align with the new architectural goal of **reengineering the system into microservices**.

During the reengineering process, Readers and Users(Auth and Users)  were separated into **independent microservices**, each owning its own database and persistence logic. This architectural shift introduced several challenges that were not present in the monolithic design:

- **Distributed data consistency**: Creating a Reader and a User now spans multiple services and databases, making atomic transactions impossible.
- **Service decoupling**: What was previously a local operation became a distributed workflow requiring coordination across service boundaries.
- **Performance and scalability concerns**: Synchronous communication between microservices would block requests and reduce system performance.
- **Data integrity risks**: Direct persistence of final entities across services could lead to partial failures, inconsistent states, and transient entity errors.
- **Lack of coordination mechanisms**: The monolithic system did not provide patterns such as event-driven communication or eventual consistency, which are required in a distributed architecture.

As a result, the original monolithic persistence strategy could not be reused, and a new approach was required to ensure **reliable, scalable, and consistent creation of Readers and Users across microservices**.

## Summary of Solution

In order to implement this requirement, the following approach was adopted:

- **Temporary Data Models**: Temporary data models were created to store incoming request data before final persistence.

- **Asynchronous Communication with RabbitMQ**: The Reader and User microservices are coordinated using RabbitMQ. Each service performs a local transaction and publishes domain events to signal state changes, allowing other services to react asynchronously as part of the saga workflow.

- **Outbox Event Processor**: The Outbox pattern was implemented to reliably publish events originating from the persistence layer. Each event is first stored in the database and later processed by a scheduled worker.

- **DTOs for Asynchronous Messaging**: DTos is used to transfer user-related data asynchronously between the Reader and User microservices.

- **Final Persistence**: Once the temporary entities have been successfully processed by both microservices, `Reader` and `ReaderDetails` are persisted in the final tables, as well as the `User`, that is persisted in the User microservice’s final table.

## Factors

This solution addresses several key factors:

- Microservices must handle requests efficiently without blocking, especially when operations span multiple services. Performance is a critical requirement.

- Distributed systems cannot rely on traditional ACID transactions across service boundaries. Eventual consistency and coordination mechanisms are required to ensure data integrity across microservices.


- In a distributed environment, operations can fail partially or messages can be delayed. Mechanisms for auditing and monitoring are essential to track the state of operations and recover from failures.

- Each microservice owns its own data and business logic. Coordinating multiple services without tightly coupling them is necessary to maintain scalability, deployability, and independent evolution of services.


## Solution Breakdown
The solution is implemented in the following steps:

1. **Create Temporary Entities and Repositories**  
   When a librarian sends a create request, temporary entities are created and saved using dedicated temporary repositories.  
   These temporary repositories hold the incoming data for validation, transformation, and asynchronous processing before final persistence.

2. **Publish Events to RabbitMQ via Outbox**  
   After saving temporary entities, domain events are created and stored in the Outbox.  
   The Outbox processor reliably publishes these events to RabbitMQ.

3. **Process User Creation Asynchronously**  
   Microservices listen for events published via the Outbox and attempt to persist temporary data in their respective temporary repositories.  
   The User microservice listens when a temporary reader is created so it can safely create a temporary user, and listens when a reader is persisted to create the respective final user.  
   Similarly, the Reader microservice listens when a temporary user is created, ensuring it has the necessary information to persist the respective reader safely.  
   Upon successful processing, services emit completion events.

4. **Maintain Event Reliability**  
   The Outbox pattern ensures that even if RabbitMQ fails, events are stored in the database and retried.  
   Status flags such as "NEW" and "SENT" track event processing.

5. **Logging and Monitoring**  
   Each stage logs the entity ID, status, and event payload for debugging and auditing purposes.


## Motivation

This solution increases architectural resilience by decoupling services and coordinating workflows via asynchronous events, allowing microservices to evolve independently without blocking or introducing inconsistent states. It directly supports several key software quality attributes:

- **Maintainability**  
  
  - Separation of temporary and final entities, along with dedicated repositories, makes the system easier to debug, extend, and evolve independently.

- **Reliability**

    - Using temporary entities prevents inconsistent data and transient reference errors when persisting across multiple services.

- **Performance**

    - Asynchronous messaging avoids blocking API requests while persistence and cross-service operations are processed in the background.

## Alternatives

**Synchronous Creation**

This approach consists of directly calling one microservice from another (e.g., via HTTP) to create dependent entities in real time, instead of using asynchronous messaging.

- **Pros:**

  - Simpler to implement.

  - Immediate feedback to the client.

- **Cons:**

  - Performance issues under high load.

  - Risk of failures if either microservice fails.

## Pending Issues

- **Event Cleanup**  
  Outbox events should be archived or removed periodically to prevent table growth and maintain performance.

- **End-to-End Testing**  
  Automated tests spanning both microservices and RabbitMQ are needed to ensure consistent and reliable behavior in the distributed workflow.
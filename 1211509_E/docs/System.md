# Library Management Microservice Backend Project Documentation

## 1. Introduction

### 1.1 Project Overview
In the previous phase, the Library Management System (LMS) was developed as a REST-based backend application following a centralized architectural approach. The system exposes endpoints for managing the core entities of a library domain, including:

- **Books**
- **Genres**
- **Authors**
- **Readers**
- **Lendings**

The application supports CRUD (Create, Read, Update, Delete) operations and encapsulates the primary business capabilities required for library management. In the previous sprint, configurability concerns were addressed by improving modularity and flexibility within the backend.

## 2. Problem Statement

The current Library Management System is implemented as a centralized backend application that consolidates multiple business capabilities into a single deployment unit. While this architecture is functionally correct and supports the required business operations, it exhibits several limitations when evaluated against new operational and organizational requirements.

Specifically, the system presents the following critical challenges:

- **Availability**: A failure in any subsystem may impact the availability of the entire application, as all capabilities share the same runtime and deployment context.
- **Performance**: The centralized architecture does not scale efficiently during peak demand periods.
- **Scalability and Elasticity**  
  In the current centralized deployment model, scaling the system requires replicating the entire backend application, even when increased demand affects only specific business functionalities. This leads to inefficient use of hardware resources, especially given that workload peaks occur infrequently.
- **Modifiability**  
  Architectural changes risk impacting existing software clients, as the backend exposes a single, tightly coupled API. This limits the system’s ability to evolve internally while preserving external contracts.
- **Misalignment with SOA Strategy**  
  The current design does not fully adhere to the organization’s Service-Oriented Architecture (SOA) strategy.

## 3. Architecturally Significant Requirements (ASRs)

Architecturally Significant Requirements (ASRs) represent the key quality attributes and constraints that have a decisive impact on the system’s architecture. These requirements guide the selection of architectural styles, decomposition strategies, and communication mechanisms, ensuring that the system can meet its non-functional goals under varying operational conditions.

In this project, the ASRs focus on improving availability, scalability and elasticity, performance, modifiability and maintainability through the adoption of a decentralized, service-oriented architecture, while preserving compatibility with existing clients and adhering to the organization’s SOA strategy.

### 1. Availability

The system must remain operational despite failures of individual components. Architectural decisions must minimize the impact of partial failures and prevent a single fault from compromising the entire system. This requirement drives the decomposition of the system into autonomous services with isolated runtimes and independent deployment lifecycles, improving overall system availability.

### 2. Scalability and Elasticity

The system must support elastic scaling to handle workload variations, achieving at least a 25% performance improvement during high-demand periods. Scalability must be fine-grained, allowing individual business capabilities to scale independently, rather than requiring replication of the entire system. This ensures efficient handling of peak loads while avoiding unnecessary resource consumption during normal operation.

### 3. Performance

The architecture must support high-throughput and low-latency interactions, particularly under peak demand. Performance improvements should be achieved by reducing contention between unrelated functionalities, optimizing read-heavy workloads, and enabling asynchronous processing where appropriate. Architectural decisions must ensure that performance gains are achieved without compromising data consistency or reliability.

### 4. Modifiability

The system must be able to evolve over time with minimal impact on existing software clients. Internal architectural changes, such as service decomposition, refactoring, or technology evolution, should not require modifications to client-facing APIs except in extreme cases. This emphasizes preserving stable external contracts and supporting incremental evolution.

### 5. Maintainability

The architecture must support ease of understanding, development, and operation over time. By decomposing the system into smaller, autonomous services aligned with distinct business capabilities, internal changes can be localized, reducing complexity, risk of side effects, and operational overhead. This improves developer productivity, simplifies testing and deployment, and facilitates long-term system maintenance.

### 6. Resource Efficiency

The system must use hardware resources parsimoniously, scaling only the components that require additional capacity. Given that high-demand peaks occur infrequently, the architecture must avoid over-provisioning and support dynamic allocation of resources based on actual runtime needs. This requirement influences deployment and scaling strategies to ensure cost-effective operation.

### 7. SOA Compliance

The system must adhere to the organization’s Service-Oriented Architecture (SOA) strategy of API-led connectivity. Services must expose well-defined APIs aligned with business capabilities and support interaction with other services and external systems through standardized communication mechanisms.

## 4. ADD

### Architectural Drivers

### Use Cases

| **ID** | **Use Case** | **Description** | **Priority** | **Category** |
|:------:|---------------|-----------------|---------------|---------------|
| **UC1** | Bootstrap user credential data | Initialize librarian credential data | High | Bootstrap |
| **UC2** | Bootstrap genre data | Initialize genre data | Medium | Bootstrap |
| **UC3** | Register author | Register an author (name, short bio) | High | Authors |
| **UC4** | Update author data | Update an author’s information | High | Authors |
| **UC5** | Get author detail | Get author details by author number | High | Authors |
| **UC6** | Search author by name | Search authors by name | High | Authors |
| **UC7** | Register book | Register a book (ISBN, title, genre, etc.) | High | Books |
| **UC8** | Update book data | Update book information | High | Books |
| **UC9** | Get book detail | Get book details by ISBN | High | Books |
| **UC10** | Search books by genre | Search books by genre | Medium | Books |
| **UC11** | Register reader | Register a reader (name, email, GDPR consent) | High | Readers |
| **UC12** | Update reader data | Update reader information | Medium | Readers |
| **UC13** | Get reader detail | Get reader details by reader number | High | Readers |
| **UC14** | Search readers by name | Search readers by name | Medium | Readers |
| **UC15** | Lend book | Lend a book (with validations and constraints) | High | Lendings |
| **UC16** | Return book | Register a book return (apply fine if overdue) | High | Lendings |
| **UC17** | Get lending detail | Get lending details by lending number | High | Lendings |
| **UC18** | Register author with photo | Register an author with an optional photo | Medium | Authors |
| **UC19** | Get books of an author | Get all books of a given author | Medium | Authors |
| **UC20** | Get co-authors | List co-authors and their respective books | Medium | Authors |
| **UC21** | Top 5 authors | Retrieve the top 5 authors | Low | Authors |
| **UC22** | Register book with cover | Register a book with an optional cover image | Medium | Books |
| **UC23** | Search books by title | Search books by title | High | Books |
| **UC24** | Top 5 books lent | Retrieve the top 5 most borrowed books | Low | Books |
| **UC25** | Top 5 genres | Retrieve the top 5 genres | Low | Books |
| **UC26** | Top 5 readers | Retrieve the top 5 readers | Low | Readers |
| **UC27** | Register reader with photo/interests | Register reader with optional photo and list of interests | Medium | Readers |
| **UC28** | Book suggestions | Recommend books based on a reader’s interests | High | Readers |
| **UC29** | List overdue lendings | List overdue lendings sorted by lateness | High | Lendings |
| **UC30** | Avg lendings per genre | Calculate the average number of lendings per genre per month | Medium | Lendings |
| **UC31** | Avg lending duration | Calculate average lending duration | Medium | Lendings |
| **UC32** | Lendings per month by genre | Retrieve lendings per month broken down by genre | Medium | Reporting |
| **UC33** | Top readers per genre | Retrieve top 5 readers per genre over a period | Medium | Reporting |
| **UC34** | Monthly lending per reader | Retrieve monthly lending count per reader | Medium | Reporting |
| **UC35** | Avg lending duration per genre | Retrieve average lending duration per genre per month | Medium | Reporting |
| **UC36** | Bootstrap author/book data | Initialize author/book data for analytics | High | Bootstrap |
| **UC37** | Bootstrap lending data | Initialize lending data for analytics | High | Bootstrap |

### Quality Attributes (QA)

| **QA ID** | **Quality Attribute**           | **Description / Rationale**                                                                                                                        |
| ---------- | ------------------------------- | -------------------------------------------------------------------------------------------------------------------------------------------------- |
| **QA1**   | **Availability**                | The system must remain operational despite partial failures, ensuring service continuity even if one or more microservices fail.                   |
| **QA2**   | **Scalability & Elasticity**    | Services must scale independently to handle peak loads efficiently, improving performance by at least 25% during high-demand periods.              |
| **QA3**   | **Performance**                 | The system must provide low-latency responses and support high throughput, particularly during peak load, while avoiding contention between services. |
| **QA4**   | **Modifiability**               | Internal changes, including service decomposition or refactoring, should not impact existing clients except in extreme cases.                       |
| **QA5**   | **Maintainability**             | Smaller, autonomous services aligned to business capabilities reduce complexity, simplify deployment, and facilitate long-term development and testing. |
| **QA6**   | **Interoperability**            | Services must communicate seamlessly via standardized APIs, events, or messaging, supporting API-led connectivity and integration with external systems. |
| **QA7**   | **Reliability / Data Consistency** | The system must ensure reliable persistence and eventual consistency across distributed services, using patterns like Saga and Outbox when needed. |



### Constraints (CON)

| **CON ID** | **Constraint**               | **Description / Rationale**                                                                                 |
| ---------- | ---------------------------- | ----------------------------------------------------------------------------------------------------------- |
| **CON1**   | **Microservices Architecture** | The system must be decomposed into at least three collaborating microservices (e.g., Books, Readers, Lendings). |
| **CON2**   | **Database-per-Service**       | Each microservice must manage its own database or data store, supporting polyglot persistence where required. |
| **CON3**   | **Messaging / Event Broker**   | Services must communicate asynchronously using a message broker (e.g., RabbitMQ) to decouple dependencies and support events. |
| **CON4**   | **CQRS and Event-Driven Patterns** | Command and Query responsibilities should be separated where needed to optimize performance and scalability. |
| **CON5**   | **Backward Compatibility**     | Changes to internal architecture should preserve API contracts for clients, except in extreme cases. |
| **CON6**   | **Deployment Scalability**     | Each microservice must support multiple instances (containers or VMs) to handle demand peaks efficiently. |



### Concerns (CRN)

| **CRN ID** | **Concern**                        | **Description / Rationale**                                                                                     |
| ---------- | ---------------------------------- | --------------------------------------------------------------------------------------------------------------- |
| **CRN1**   | **Service Decomposition**           | How to partition the monolith into autonomous microservices while minimizing inter-service coupling.          |
| **CRN2**   | **Data Consistency Across Services** | Ensuring eventual consistency and correct coordination of business transactions in a distributed environment. ||
| **CRN3**   | **Scalability and Resource Efficiency** | Designing services to scale independently to meet peak demand without over-provisioning hardware.              |
| **CRN4**   | **Incremental Migration**            | Migrating from the existing monolith to microservices progressively using the pattern Strangler Fig.       ||

## Iteration 1

### Step 2: Establish Goals by the Inputs

| **Goal ID** | **Goal**                       | **Rationale / Impact**                                                                                     |
| ----------- | ------------------------------ | ---------------------------------------------------------------------------------------------------------- |
| **G1**      | **Availability (QA1)**         | Design microservices to remain operational despite failures, ensuring continuity even if one service fails. |
| **G2**      | **Scalability & Elasticity (QA2)** | Enable independent scaling of services to efficiently handle peak loads, improving performance by at least 25%. |
| **G3**      | **Performance (QA3)**          | Optimize throughput and latency under high-demand scenarios through asynchronous processing, CQRS, and event-driven patterns. |
| **G4**      | **Modifiability (QA4)**        | Internal service refactoring or decomposition should not impact client-facing APIs except in extreme cases. |
| **G5**      | **Maintainability (QA5)**      | Smaller, autonomous services aligned with business capabilities reduce complexity, simplify testing, deployment, and long-term maintenance. |
| **G6**      | **Interoperability (QA6)**     | Ensure seamless communication via APIs, messaging, or events, supporting API-led connectivity and SOA compliance. |
| **G7**      | **Reliability / Data Consistency (QA7)** | Ensure reliable persistence and eventual consistency across services using patterns like Saga and Outbox. |
| **G8**      | **Microservices Architecture (CON1)** | Decompose the system into at least three collaborating services (Books, Readers, Lendings) to isolate responsibilities. |
| **G9**      | **Database-per-Service (CON2)** | Each service must manage its own data store, supporting polyglot persistence where required. |
| **G10**     | **Messaging / Event Broker (CON3)** | Enable asynchronous inter-service communication via a message broker (e.g., RabbitMQ) to decouple dependencies. |
| **G11**     | **CQRS & Event-Driven Patterns (CON4)** | Separate commands and queries where appropriate to optimize performance and allow event-driven workflows. |
| **G12**     | **Deployment Scalability (CON6)** | Support multiple instances per service (containers or VMs) to efficiently handle demand peaks. |
| **G13**     | **Incremental Migration (CRN4)** | Migrate progressively from the monolith to microservices using the Strangler Fig pattern to reduce risk. |


### Step 3 : Choose one or more elements to refine

For this iteration of the Library Management system, all architectural and functional changes will be made exclusively in the backend of the project.

### Step 4: Choose One or More Design Concepts that Satisfy the Selected Inputs

| **Design Decision and Location**                     | **Description to be Done**                                                                                                                                                      | **Quality Attributes / Constraints Addressed**                                         |
| ---------------------------------------------------- | -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | -------------------------------------------------------------------------------------- |
| **Microservice Decomposition**                       | Break the monolith into at least three autonomous services (Books, Readers, Lendings), aligned with business capabilities to reduce coupling and improve maintainability.       | QA4 (Modifiability), QA5 (Maintainability), CON1 (Microservices Architecture)          |
| **Database-per-Service / Polyglot Persistence**      | Each microservice manages its own database or data store, allowing selection of the optimal persistence technology per service.                                                 | CON2 (Database-per-Service), QA3 (Performance), QA7 (Reliability / Data Consistency)   |
| **Messaging via Event Broker**                        | Use a message broker (e.g., RabbitMQ) to enable asynchronous communication between services, decoupling dependencies and supporting domain events.                               | CON3 (Messaging / Event Broker), QA6 (Interoperability), QA2 (Scalability & Elasticity)|
| **CQRS (Command-Query Responsibility Segregation)**  | Separate command (write) and query (read) responsibilities where appropriate to improve performance and scalability of high-load operations.                                    | CON4 (CQRS & Event-Driven Patterns), QA3 (Performance), QA2 (Scalability & Elasticity) |
| **Domain Events & Outbox Pattern**                   | Implement domain events to propagate changes across services; use the Outbox pattern to guarantee reliable event delivery and eventual consistency.                               | QA7 (Reliability / Data Consistency), CON3 (Messaging / Event Broker)                   |
| **Saga Pattern for Distributed Transactions**        | Manage multi-service business transactions using Saga choreography or orchestration to ensure consistency without locking.                                                     | QA7 (Reliability / Data Consistency), QA1 (Availability)                                |
| **Strangler Fig Migration**                           | Gradually replace monolith functionality with microservices, minimizing disruption to existing clients.                                                                       | CRN4 (Incremental Migration), QA4 (Modifiability)                                       |
| **Service Scalability**                 | Deploy multiple instances of each service (containers or VMs) to efficiently handle demand peaks and improve resource utilization.                                            | CON6 (Deployment Scalability), QA2 (Scalability & Elasticity)                           |
| **Autonomous Services with Clear APIs**              | Define stable, versioned APIs for each service to ensure modifiability and backward compatibility, minimizing impact on clients during internal changes.                        | QA4 (Modifiability), CON5 (Backward Compatibility), QA6 (Interoperability)              |

### Step 5 & 6 : Technical Memos

For more details, refer to the following technical memos:

- [Asynchronous Reader and User Creation(US2)](./TechnicalMemos/US2.md)

### Step 7 : Perform analysis of current design and review iteration goal and achievements of design purposes

### Use Cases

All Use cases were addressed

### Quality Attributes (QA)

| **QA ID** | **Quality Attribute**  | **Addressed** | **Partially Addressed** | **Not Addressed** |
|----------|------------------------|---------------|-------------------------|-------------------|
| **QA1**  | Availability           |              |X                         |                   |
| **QA2**  | Scalability & Elasticity         |             |X                         |                   |
| **QA3**  | Performance             |              |X                         |                   |
| **QA4**  | Modifiability             | X             |                         |                   |
| **QA5**  | Maintainability         | X             |                         |                   |
| **QA6**  |       Interoperability       |X               |                        |                   |
| **QA7**  | Reliability / Data Consistency        | X             |                         |                   |

---

### Constraints (CON)

| **CON ID** | **Constraint**         | **Addressed** | **Partially Addressed** | **Not Addressed** |
|------------|------------------------|---------------|-------------------------|-------------------|
| **CON1**   | Microservices Architecture         |X                |                        |                   |
| **CON2**   | Database-per-Service     | X             |                         |                   |
| **CON3**   | Messaging / Event Broker           | X             |                         |                   |
| **CON4**   | CQRS and Event-Driven Patterns        |              | X                        |                   |
| **CON5**   | Backward Compatibility         |              |           X              |                   |
| **CON6**   | Deployment Scalability         |              |                         |X                   |

---

### Concerns (CRN)

| **CRN ID** | **Concern**                      | **Addressed** | **Partially Addressed** | **Not Addressed** |
|------------|----------------------------------|---------------|-------------------------|-------------------|
| **CRN1**   | Service Decomposition         |              |X                         |                   |
| **CRN2**   | Data Consistency Across Services      | X             |                         |                   |
| **CRN3**   | Scalability and Resource Efficiency    |              |X                         |                   |
| **CRN4**   | Incremental Migration                 |X                |                         |                   |



## 5 Architecture and Patterns to Adopt

### Microservices Adoption

The system was refactored from a monolithic architecture into microservices to improve **scalability, maintainability, and deployment flexibility**. Microservices allow independent evolution of each domain, easier testing, and focused responsibility per module.  

The work was divided into the following microservices:

- **Books**: Contains entities related to books, including authors and genres. Since authors and genres are tightly related to books, they are kept together in a single service.
- **Auth and User Management**: Manages user accounts, authentication, and authorization.
- **Readers**: Manages reader entities and reader details.
- **Lendings**: Handles the lendings of books by readers.

This separation makes sense in the context of the implemented User Story (US2), which requires creating a Reader and its associated User. Splitting Readers from User Management and Auth allows independent orchestration and asynchronous coordination, while books, authors, and genres remain cohesive within a single service.

### Patterns to Adopt

The system design considers multiple architectural patterns to ensure robust and maintainable microservices:

#### Strangler Fig Pattern

The **Strangler Fig Pattern** supports the incremental migration of a monolithic system into microservices by gradually replacing functionality without disrupting the overall system.

This pattern was applied during the reengineering process by migrating the system step by step, instead of performing a full rewrite. The migration began with the Books microservice (including Authors and Genres), followed by Authentication and User Management, then Readers, and finally Lendings.  
Each bounded context was extracted and stabilized before moving on to the next, ensuring continuous system operability and reducing migration risk.

#### Command-Query Responsibility Segregation (CQRS)

Command-Query Responsibility Segregation (CQRS)  
separates commands (writes) from queries (reads) to allow optimized handling of each operation. 

A full CQRS implementation, with separate command and query models or distinct microservices, was not adopted in this system. However, several CQRS principles were applied at a logical level. Write operations are handled through a controlled, multi-step workflow using temporary data models and repositories. These temporary models are responsible for processing and validating command operations before any final entities are persisted. Only after successful coordination between microservices are the final Reader and User entities created and made available for querying.

#### Database-per-Service
Each microservice owns its own database, preventing tight coupling and enabling independent scaling. 

This pattern was implemented by assigning a dedicated PostgreSQL database to each microservice. Each database is accessed exclusively by its owning microservice, ensuring strict data ownership, preventing direct data coupling between services, and allowing independent deployment and scaling.

#### Polyglot Persistence  
Supports using different types of databases (relational, document, or cache) depending on the service's needs. This approach allows each service to choose the optimal storage technology. 

This pattern was not implemented in the current solution. All microservices use PostgreSQL as their persistence technology. While this ensures consistency and simplifies operations, it also means that different storage technologies were not explored.

#### Messaging through Message Broker (e.g., RabbitMQ)  
Asynchronous communication between microservices decouples services and enables reliable event-driven workflows. 

This pattern was fully implemented in User Story 2. Asynchronous communication via RabbitMQ is used to coordinate the Reader and User microservices through domain events.  

#### Outbox Pattern
Ensures that messages (events) are persisted in the database and reliably sent to the message broker, preventing message loss during failures. 

This pattern was fully implemented in both the Reader and User microservices. Domain events are first persisted in an Outbox table within each service’s database, with events marked using status flags such as "NEW" and "SENT".  A scheduled Outbox publisher worker processes these events and reliably publishes them to the message broker.  This guarantees message delivery even in the presence of temporary failures, preventing message loss and ensuring consistency between the database and the messaging system.

#### Domain Events  
Used to signal important state changes within a service to other microservices, enabling coordination and eventual consistency.

Domain events were implemented in the Books, Reader, and User microservices to signal important state changes. These events are published to RabbitMQ whenever significant actions occur, such as creating temporary entities, persisting final entities. Other microservices listen for these events to coordinate operations and maintain eventual consistency across the distributed system.

#### Saga Pattern  
Orchestrates long-running transactions across multiple services, ensuring that each step completes successfully or compensates in case of failure. 

The saga pattern was implemented across the Reader and User microservices to coordinate long-running workflows.  It manages the creation of temporary entities and their promotion to final entities, ensuring that each step completes successfully before proceeding. If any step fails, the saga prevents the final persistence, maintaining data consistency across services.

## 6. Diagrams

The system architecture diagrams in this section have been created using the 4+1 architectural model in conjunction with the C4 model. This approach allows for a comprehensive view of the system at different levels of granularity, from high-level overviews to detailed component and code-level diagrams. By combining both models, we ensure that the architecture is clearly communicated to various stakeholders, covering both strategic and technical perspectives.

### Domain Model


### 3.1 Level 1

#### 3.1.1 Logical View

![Logical View Level1](Diagramas\VistaLógicaNível1.svg)


### 3.2 Level 2

#### 3.2.1 Logical View

![Logical View Level2](Diagramas\VistaLógicaNível2.svg)

#### 3.2.3 Physical View

![Physical View Level2](Diagramas\VistaFísicaNível2.svg)

#### 3.2.4 Implementation View

![Implementation View Nível2](Diagramas\VistaImplementaçãoNível2.svg)

### 3.3 Level 3

#### 3.3.1 Logical View

![Logical View Level3](Diagramas\VistaLógicaNível3.svg)

#### 3.3.2 Process View

![Process View Level3](Diagramas\VistaProcessosNivel3-US2.svg)

#### 3.3.3 Implementation View

![Implementation View Nível3](Diagramas\VistaImplementaçãoNível3.svg)
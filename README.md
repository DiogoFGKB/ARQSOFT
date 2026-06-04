# Software Architecture

## Overview

This project was developed for the **Software Architecture (ARQSOFT)** course and consisted of two major phases:

1. **Software Architecture Analysis and Reengineering**

   * Reverse engineering of an existing Library Management System (LMS).
   * Architectural documentation and analysis of the existing solution.
   * Refactoring the application to improve configurability and extensibility.

2. **Distributed Microservices Migration**

   * Migration of a modular monolith into a distributed microservices architecture.
   * Adoption of event-driven communication and service-oriented architectural patterns.
   * Application of Onion Architecture principles within each microservice.

## Project Goals

The original system managed:

* Books
* Authors
* Genres
* Readers
* Lendings

The objective was to improve the system's:

* Extensibility
* Configurability
* Availability
* Scalability
* Reliability


## Phase 1 – Architecture Reengineering

### Reverse Engineering & Documentation

The first phase focused on understanding and documenting the existing system architecture, including:

* Architectural decomposition
* Requirements analysis
* Architectural drivers and quality attributes
* Design decisions and trade-offs
* Architecture documentation using architectural views

### Configurability Improvements

Several architectural improvements were introduced:

#### Multiple Database Support

The persistence layer was redesigned to support different database technologies through configuration. Database selection could be configured at startup without requiring code changes.

#### External ISBN Providers

A gateway abstraction was introduced to support multiple external ISBN providers. Dependency Injection was used to dynamically select the provider implementation through configuration.

#### Configurable ID Generation

Entity identifiers were decoupled from the domain model and generated through interchangeable strategies.

Different ID formats could be selected through application configuration, allowing runtime flexibility without modifying business logic.

#### Layer Decoupling

The system was refactored following Onion Architecture principles:

* Domain layer isolated from infrastructure concerns
* Persistence abstractions introduced through repositories
* External services accessed through gateway interfaces
* Improved testability and maintainability



## Phase 2 – Migration to Microservices

### Architecture Transformation

The modular monolith was decomposed into **4 independent microservices**, each owning its own database.

Key architectural goals included:

* Independent deployment
* Improved scalability
* Higher availability
* Better fault isolation

### Event-Driven Communication

Microservices communicate asynchronously using:

* RabbitMQ

This enabled:

* Loose coupling
* Event-driven workflows
* Improved resilience
* Better scalability under load

### Service Design

Each microservice follows Onion Architecture principles:

* Domain Layer
* Application Layer
* Infrastructure Layer
* API Layer

This separation ensures business logic remains independent from external technologies and frameworks.

### Patterns and Architectural Practices

Implemented architectural patterns include:

* Microservices Architecture
* Event-Driven Architecture
* Dependency Injection
* Repository Pattern
* Gateway Pattern
* Strategy Pattern
* Onion Architecture



## Technologies

* Java
* Spring Boot
* Spring Data
* RabbitMQ
* Docker


## Key Achievements

* Reverse engineered and documented an existing software architecture.
* Refactored a monolithic application into a distributed microservices ecosystem.
* Added support for multiple databases through configuration.
* Integrated multiple external APIs through interchangeable gateway implementations.
* Implemented configurable ID generation strategies using Dependency Injection.
* Applied Onion Architecture principles across all services.
* Established asynchronous communication using RabbitMQ.
* Improved system extensibility, scalability, and maintainability.

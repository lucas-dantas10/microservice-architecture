# Microservice Architecture

Welcome to the **Microservice Architecture** repository! This project serves as a collection of microservices working together to implement a complete microservice-based system using **Spring Boot** and **Spring Cloud**. The architecture includes service discovery, an API gateway, user authentication, and a payment service.

## Table of Contents

1. [Overview](#overview)
2. [Services](#services)
3. [Technologies](#technologies)
4. [Getting Started](#getting-started)
5. [Architecture Diagram](#architecture-diagram)
6. [Endpoints](#endpoints)
7. [License](#license)

---

## Overview

This project demonstrates a microservice architecture that includes multiple components working together seamlessly. The system is built using Spring Boot and utilizes Spring Cloud features such as Eureka for service discovery and Spring Cloud Gateway for API routing.

The architecture includes:

- **Service Registry** for dynamic service discovery
- **API Gateway** to route requests
- **User Authentication Service** for secure login and JWT management
- **Payment Service** to handle payment operations

---

## Services

The project is divided into multiple microservices, each serving a specific purpose:

| Service Name          | Description                               | Port |
| --------------------- | ----------------------------------------- | ---- |
| **registry-service**     | Service registry for dynamic discovery    | 8761 |
| **gateway-service**       | Gateway to route incoming requests        | 8080 |
| **auth-service** | Manages user login and JWT authentication | 8081 |
| **payment-service**   | Handles payment-related operations        | 8082 |

Each service runs independently and communicates via HTTP with other services.

---

## Technologies

The following technologies are used to build the microservices:

- **Spring Boot**: Framework for building standalone microservices
- **Spring Cloud**: Tools for building distributed systems
- **Spring Cloud Eureka**: Service discovery and registration
- **Spring Cloud Gateway**: API Gateway for routing requests
- **Spring Security**: Security for authentication and authorization
- **JWT (JSON Web Token)**: Token-based authentication
- **Docker**: Containerization for microservices
- **Maven**: Build and dependency management
- **PostgreSQL**: Relational database for persistent storage

---

## Getting Started

Follow these steps to set up the project locally:

### Prerequisites

- **Java 21+**
- **Maven**
- **Docker** (optional for containerization)

### Steps to Run the Project

1. **Clone the Repository**:

   ```bash
   git clone https://github.com/your-username/microservice-architecture.git
   cd microservice-architecture
   ```

2. **Start the Eureka Server**:

   - Navigate to the `eureka-server` folder and run:
     ```bash
     mvn spring-boot:run
     ```
   - Eureka dashboard will be available at: `http://localhost:8761`

3. **Run the API Gateway**:

   - Navigate to the `api-gateway` folder and run:
     ```bash
     mvn spring-boot:run
     ```
   - API Gateway runs on port `8080`.

4. **Run the User Authentication Service**:

   - Navigate to `user-auth-service` and run:
     ```bash
     mvn spring-boot:run
     ```
   - Exposed endpoints available at `http://localhost:8081`.

5. **Run the Payment Service**:

   - Navigate to `payment-service` and run:
     ```bash
     mvn spring-boot:run
     ```
   - Payment endpoints available at `http://localhost:8082`.

6. **Test the Services**:

   - Use Postman or CURL to test endpoints through the API Gateway.

---

## Architecture Diagram

Below is a simplified diagram representing the microservice architecture:

```
                        +-----------------------+
                        |     Gateway Service    |  (Port: 8080)
                        +-----------------------+
                                   |
                +-------------------+-------------------+
                |                                       |
+---------------v--------------+          +-------------v-------------+
|    Auth Service              |          |      Payment Service     |
|       Service (8081)         |          |         (8082)           |
+------------------------------+          +--------------------------+
                                   
                        +-----------------------+
                        |     Registry Service     |  (Port: 8761)
                        +-----------------------+
```

---

## Endpoints

### 1. **User Authentication Service** (`http://localhost:8081`)

| Endpoint      | Method | Description                   |
| ------------- | ------ | ----------------------------- |
| `/auth/login` | POST   | Authenticate user and get JWT |

### 2. **Payment Service** (`http://localhost:8082`)

| Endpoint            | Method | Description                  |
| ------------------- | ------ | ---------------------------- |
| `/payment/transfer` | GET    | Simulates a payment transfer |

### 3. **API Gateway** (`http://localhost:8080`)

- All requests should go through the gateway.
- Example:
  - Login: `http://localhost:8080/auth/login`
  - Transfer Payment: `http://localhost:8080/payment/transfer`

---

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.


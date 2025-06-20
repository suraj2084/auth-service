﻿# auth-service
This repository contains the Auth Service for the Expense Tracker application. It is responsible for user registration, login, JWT token issuance and validation, and refresh token handling. Built using Spring Boot, Spring Security, JWT, Kafka, and Docker, this service is designed for stateless authentication, scalability, and secure token-based communication between microservices.
# 🧾 Expense Tracker — Auth Service

The **Expense Tracker** is a scalable, modular, event-driven application designed to help users manage and understand their expenses. This repository contains the **Auth Service**, which handles authentication, token generation, and secure communication between services.

---

## ✅ Features (Auth Service)

- ✅ User signup and login via REST APIs
- ✅ JWT-based access and refresh tokens
- ✅ Secure password storage (BCrypt)
- ✅ Stateless token-based authentication
- ✅ Token refresh support to avoid frequent re-login
- ✅ Event publishing to UserService via Kafka (asynchronously)
- ✅ Fault-tolerant and recoverable event publishing
- ✅ Custom validation (email, password strength, first/last name checks)

---

## 🧱 Architecture (Microservices)

- **API Gateway (Kong)** handles routing and token verification
- **Auth Service** (you are here) handles authentication and token management
- **User Service** will handle user profile and metadata
- **Templatisation, Ledger, Billing, Reporting Services** (future scope)
- **Kafka** used for asynchronous communication between services
- **Docker** for containerization
- **Redis, RabbitMQ, PostgreSQL** for caching, messaging, and persistence

---

## 📌 Functional Requirements (Overall System)

- Users can register and log in
- Add/remove/view categorized expenses
- View weekly/monthly/yearly reports
- Statistics and trends on user spending

---

## 🔐 Auth Service Functional Requirements

- Sign-up and login via tokenized REST APIs
- Access and refresh token generation and validation
- Token sent over HTTPS
- Automatic refresh of access tokens
- One-time login with token reuse
- Recoverable event publishing to downstream services (like User Service)

---

## 🚀 Non-Functional Requirements

- Latency under 100ms
- Scalable and fault-tolerant architecture
- Auth requests optimized to reduce app startup lag
- Database design avoids LRQs and unnecessary joins

---

## 🛠️ Technologies Used

- Java 17
- Spring Boot 3.x
- Spring Security, JWT, OAuth2
- Docker
- Kafka
- PostgreSQL
- RabbitMQ (for notification service)
- Kubernetes (for deployment)
- Redis (for caching)
- Kong Gateway
- LLM integration planned for smart insights

---

## 🧩 Component Breakdown (LLD Highlights)

- **SecurityConfig**: Applies custom filters, configures AuthenticationProvider & other beans.
- **JWTFilter**: Validates all incoming requests (except login/signup/refreshToken).
- **JwtService**: Creates, validates, and extracts data from JWT tokens.
- **RefreshTokenService**: Generates and verifies refresh tokens.
- **UserDetailsServiceImpl**: Integrates Spring Security for loading and saving users.
- **AuthenticationManager**: Authenticates user credentials via Spring Security.

---

## 🗂️ Database Design (ERD Overview)

- `users`: Stores user credentials
- `roles`: Defines user roles (ADMIN, USER, etc.)
- `users_roles`: Many-to-many mapping between users and roles
- `tokens`: Stores refresh tokens mapped to users

---

## 🔮 Future Scope

- Expense prediction using ML or LLM
- Smart notifications via WhatsApp & SMS (risk alerts, suggestions)
- Auto-expense entry from SMS parsing
- Personalized financial coaching based on patterns

---

## 🧪 How to Run (Locally)

```bash
# Run using Maven & Docker
mvn clean install
docker-compose up --build

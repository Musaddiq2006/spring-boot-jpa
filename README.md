# spring-boot-jpa
[README (1).md](https://github.com/user-attachments/files/34922918/README.1.md)
<div align="center">

# 🍃 Spring Boot JPA — User Management REST API

**A clean, layered REST API built with Spring Boot, Spring Data JPA and MySQL.**

![Java](https://img.shields.io/badge/Java-25-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-4.1.1-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![Spring Data JPA](https://img.shields.io/badge/Spring_Data_JPA-Hibernate-59666C?style=for-the-badge&logo=hibernate&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-8.x-4479A1?style=for-the-badge&logo=mysql&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-Wrapper-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)

</div>

---

## 📖 Table of Contents

- [About the Project](#-about-the-project)
- [Features](#-features)
- [Tech Stack](#-tech-stack)
- [Architecture](#-architecture)
- [Project Structure](#-project-structure)
- [Getting Started](#-getting-started)
- [API Reference](#-api-reference)
- [Running Tests](#-running-tests)
- [Roadmap](#-roadmap)
- [Author](#-author)

---

## 🎯 About the Project

**spring-boot-jpa** is a beginner-friendly yet production-style backend project that demonstrates how to persist data with **Spring Data JPA** and expose it over a **REST API** using **Spring Boot**.

It follows the classic **Controller → Service → Repository** layering, maps a Java class to a MySQL table using **JPA/Hibernate**, and lets Spring Data generate all the CRUD SQL — so there is no hand-written SQL in the codebase.

**What you can learn from this project:**

- How an `@Entity` maps to a relational table
- How `JpaRepository` removes boilerplate data-access code
- How to structure a Spring Boot app into clean, single-responsibility layers
- How to connect Spring Boot to a MySQL database

---

## ✨ Features

- ✅ **Create users** via a `POST` request with a JSON body
- ✅ **Fetch all users** via a `GET` request
- ✅ **Automatic schema generation** — Hibernate creates/updates the table on startup (`ddl-auto=update`)
- ✅ **Auto-generated primary keys** using MySQL `IDENTITY` (auto-increment)
- ✅ **SQL logging** enabled for easy learning and debugging
- ✅ **Maven Wrapper included** — no need to install Maven

---

## 🛠 Tech Stack

| Layer            | Technology                                   |
| ---------------- | -------------------------------------------- |
| Language         | Java 25                                      |
| Framework        | Spring Boot 4.1.1                            |
| Web              | Spring Web (`spring-boot-starter-web`)       |
| Persistence      | Spring Data JPA + Hibernate                  |
| Database         | MySQL (`mysql-connector-j`)                  |
| Build Tool       | Maven (with Maven Wrapper)                   |
| Testing          | Spring Boot Starter Test, Data JPA Test      |

---

## 🏗 Architecture

The app is split into three focused layers:

```
   HTTP Request
        │
        ▼
┌───────────────────┐
│  UserController   │   ← REST endpoints (@RestController, /users)
└─────────┬─────────┘
          ▼
┌───────────────────┐
│   UserService     │   ← Business logic (@Service)
└─────────┬─────────┘
          ▼
┌───────────────────┐
│  UserRepository   │   ← Data access (JpaRepository)
└─────────┬─────────┘
          ▼
┌───────────────────┐
│  MySQL Database   │   ← jpademo_db
└───────────────────┘
```

| Class                  | Role                                                                 |
| ---------------------- | -------------------------------------------------------------------- |
| `JpademoApplication`   | Application entry point (`@SpringBootApplication`)                   |
| `UserClassSpringBoot`  | JPA entity with `id` (auto-generated) and `name`                     |
| `UserRepository`       | Extends `JpaRepository<UserClassSpringBoot, Long>` for CRUD          |
| `UserService`          | Calls the repository to save and list users (constructor injection)  |
| `UserController`       | Exposes `POST /users` and `GET /users`                               |

---

## 📂 Project Structure

```
spring-boot-jpa
├── .mvn/wrapper/                     # Maven Wrapper config
├── src
│   ├── main
│   │   ├── java/com/example/jpademo
│   │   │   ├── JpademoApplication.java
│   │   │   ├── UserClassSpringBoot.java   # Entity
│   │   │   ├── UserRepository.java        # Repository
│   │   │   ├── UserService.java           # Service
│   │   │   └── UserController.java        # REST Controller
│   │   └── resources
│   │       └── application.properties     # DB & JPA configuration
│   └── test/java/com/example/jpademo
│       └── JpademoApplicationTests.java
├── mvnw / mvnw.cmd                   # Maven Wrapper scripts
└── pom.xml
```

---

## 🚀 Getting Started

### Prerequisites

Make sure you have the following installed:

- **JDK 25** (the project targets Java 25)
- **MySQL Server** running locally on port `3306`
- **Git**

> 💡 Maven is **not** required — use the bundled `mvnw` wrapper.

### 1. Clone the repository

```bash
git clone https://github.com/Musaddiq2006/spring-boot-jpa.git
cd spring-boot-jpa
```

### 2. Create the database

Hibernate creates the **tables** for you, but the **database** itself must exist:

```sql
CREATE DATABASE jpademo_db;
```

### 3. Configure your database credentials

Open `src/main/resources/application.properties` and set your own MySQL username and password:

```properties
spring.application.name=jpademo

# Database URL, Username, and Password
spring.datasource.url=jdbc:mysql://localhost:3306/jpademo_db
spring.datasource.username=<your-mysql-username>
spring.datasource.password=<your-mysql-password>

spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

> 🔐 **Never commit real passwords.** Prefer environment variables, for example:
> `spring.datasource.password=${DB_PASSWORD}` and then run with `DB_PASSWORD=yourpassword`.

### 4. Run the application

**Linux / macOS**

```bash
./mvnw spring-boot:run
```

**Windows**

```bash
mvnw.cmd spring-boot:run
```

The server starts on **http://localhost:8080**.

---

## 📡 API Reference

**Base URL:** `http://localhost:8080/users`

| Method | Endpoint | Description        | Request Body | Response            |
| ------ | -------- | ------------------ | ------------ | ------------------- |
| `POST` | `/users` | Create a new user  | JSON user    | The saved user      |
| `GET`  | `/users` | Get all users      | —            | JSON array of users |

### ➕ Create a user

**Request**

```bash
curl -X POST http://localhost:8080/users \
  -H "Content-Type: application/json" \
  -d '{"name": "Musaddiq"}'
```

**Response**

```json
{
  "id": 1,
  "name": "Musaddiq"
}
```

### 📋 Get all users

**Request**

```bash
curl http://localhost:8080/users
```

**Response**

```json
[
  { "id": 1, "name": "Musaddiq" },
  { "id": 2, "name": "Ahmed" }
]
```

### 🗃 Database table

Hibernate generates a table for the entity with this shape:

| Column | Type                         | Notes                      |
| ------ | ---------------------------- | -------------------------- |
| `id`   | `BIGINT`                     | Primary key, auto-increment |
| `name` | `VARCHAR(255)`               | User's name                |

---

## 🧪 Running Tests

```bash
./mvnw test
```

> Note: the included context-load test starts the full Spring context, so it needs a reachable MySQL database with the credentials from `application.properties`.

---

## 🗺 Roadmap

Ideas for taking this project further:

- [ ] `GET /users/{id}` — fetch a single user
- [ ] `PUT /users/{id}` — update a user
- [ ] `DELETE /users/{id}` — delete a user
- [ ] Input validation with `spring-boot-starter-validation`
- [ ] Global exception handling with `@RestControllerAdvice`
- [ ] DTOs to decouple the API from the entity
- [ ] Externalized config using environment variables / Spring profiles
- [ ] Swagger / OpenAPI documentation
- [ ] Dockerfile + `docker-compose` with MySQL
- [ ] More unit and integration tests

---

## 👨‍💻 Author

**Syed Musaddiq Ahmed**

- GitHub: [@Musaddiq2006](https://github.com/Musaddiq2006)

---

<div align="center">

⭐ If you found this project helpful, consider giving it a star!

</div>

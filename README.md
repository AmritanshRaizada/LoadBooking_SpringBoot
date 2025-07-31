
# 📦 Load & Booking Management System

A backend system built with Spring Boot and PostgreSQL to manage load (freight) postings and booking operations efficiently. Designed for performance, scalability, and clear business rules.

---

## 🚀 Features

- Normalized database schema with foreign key constraints
- REST APIs with proper DTO–Service–Repository layering
- Status transitions for load and bookings (POSTED → BOOKED → CANCELLED)
- Filtering and pagination support
- Global exception handling with `@ControllerAdvice`
- Input validation via `@Valid`, `@NotNull`, etc.
- Swagger UI documentation
- PostgreSQL integration with auto schema generation

---

## 📁 Tech Stack

- **Spring Boot 3**
- **Spring Data JPA**
- **PostgreSQL**
- **Swagger / OpenAPI**
- **JUnit 5 + Mockito**
- **Maven**
- **Java 17**

---

## 🧠 Database Schema Overview

### `Load`
```json
{
  "id": "UUID",
  "shipperId": "String",
  "facility": {
    "loadingPoint": "String",
    "unloadingPoint": "String",
    "loadingDate": "Timestamp",
    "unloadingDate": "Timestamp"
  },
  "productType": "String",
  "truckType": "String",
  "noOfTrucks": "int",
  "weight": "double",
  "comment": "String",
  "datePosted": "Timestamp",
  "status": "POSTED | BOOKED | CANCELLED"
}
```

### `Booking`
```json
{
  "id": "UUID",
  "loadId": "UUID",
  "transporterId": "String",
  "proposedRate": "double",
  "comment": "String",
  "status": "PENDING | ACCEPTED | REJECTED",
  "requestedAt": "Timestamp"
}
```

---

## ✅ Business Rules

- When a **load** is created, `status = POSTED` by default.
- When a **booking is accepted**, load's status becomes `BOOKED`.
- If a **booking is deleted**, load status becomes `CANCELLED`.
- A **booking** cannot be created for a cancelled load.
- If **all bookings are deleted or rejected**, load status reverts to `POSTED`.

---

## 🔗 API Endpoints

### Load Management
| Method | Endpoint                     | Description                       |
|--------|------------------------------|-----------------------------------|
| POST   | `/api/loads`                 | Create a new load                 |
| GET    | `/api/loads`                 | Get all loads (filterable)        |
| GET    | `/api/loads/{id}`            | Get load by ID                    |
| PUT    | `/api/loads/{id}`            | Update load                       |
| DELETE | `/api/loads/{id}`            | Delete load                       |

### Booking Management
| Method | Endpoint                     | Description                       |
|--------|------------------------------|-----------------------------------|
| POST   | `/api/bookings`              | Create a new booking              |
| GET    | `/api/bookings`              | Get bookings (filterable)         |
| GET    | `/api/bookings/{id}`         | Get booking by ID                 |
| PUT    | `/api/bookings/{id}`         | Update booking                    |
| DELETE | `/api/bookings/{id}`         | Delete booking                    |

---

## 📄 API Documentation

- Swagger UI:  
  👉 [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

---

## ⚙️ Getting Started

### 1. Clone the Repo
```bash
git clone https://github.com/AmritanshRaizada/loadbooking-system.git
cd loadbooking-system
```

### 2. Configure PostgreSQL
Ensure PostgreSQL is running and create a database:

```sql
CREATE DATABASE loadbooking;
```

Update your `src/main/resources/application.yml` with:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/loadbooking
    username: your_user
    password: your_password
```

### 3. Run the App
```bash
./mvnw spring-boot:run
```

---

## ✅ Testing

Run all tests using:

```bash
./mvnw test
```

---

## 📬 Submission Info

- **Candidate:** Amritansh Raizada
- **Role:** Software Development Internship
- **Email:** amritanshspc@gmail.com

---

## 📜 License

This project is for demonstration and assessment purposes only.

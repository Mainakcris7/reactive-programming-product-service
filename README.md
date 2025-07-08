# Reactive Product Service

This is a **Reactive Product Management Service** built using **Spring WebFlux**.  
I created this project to practice **Reactive Programming** concepts using non-blocking and asynchronous APIs in a real-world CRUD-based application.

---

## 📌 Project Description

This application exposes a set of RESTful APIs to manage product data using a reactive programming paradigm.  
It leverages **Project Reactor**, **Spring WebFlux**, and **R2DBC** for end-to-end non-blocking I/O and data access.

---

## 🛠️ Tech Stack

- **Spring Boot** with **Spring WebFlux**
- **Reactive MySQL Driver**
- **R2DBC (Reactive Relational Database Connectivity)**
- **Lombok**
- **Gradle** (Build Tool)
- **MySQL** (as the database)

---

## 📂 API Endpoints

All endpoints are prefixed with `/products`. Below is the list of available APIs:

| Method | Endpoint             | Description               |
|--------|----------------------|---------------------------|
| GET    | `/products`          | Retrieve all products     |
| GET    | `/products/{id}`     | Get a product by ID       |
| POST   | `/products`          | Create a new product      |
| PUT    | `/products`          | Update an existing product|
| DELETE | `/products/{id}`     | Delete a product by ID    |

- Each endpoint returns either a `Mono<T>` or `Flux<T>` as appropriate to follow the reactive design.

- I have attached `Postman` collection document, which can be imported directly in `Postman` for API testing.

---

## ⚙️ Getting Started
### Prerequisites

- Java 21
- Gradle installed or use the Gradle wrapper (`./gradlew`)
- Running MySQL instance (reactive-compatible)

---

## 🧩 Project Setup

### Clone the Repository

```bash
git clone https://github.com/Mainakcris7/reactive-programming-product-service.git
cd reactive-programming
```

## ▶️ Running the Application
### You can start the application using the Gradle wrapper:

```bash
./gradlew bootRun
```

## 📖 Learning Outcomes
- Built a reactive REST API using Spring WebFlux
- Incorporated Global Exception Handling
- Implemented non-blocking CRUD operations using R2DBC with MySQL
- Practiced working with Mono and Flux for reactive data handling
- Understood the basics of reactive streams and backpressure
- 
## 🚀 Future Improvements
- Add validation using `Hibernate Validator`
- Create DTO package and implement DTO classes
- Dockerize the application with `docker compose`.
- Integrate `Swagger`/`OpenAPI` for better API documentation

## 📧 Contact
For any further queries, feel free to reach out to me via email at mainakcr72002@gmail.com.

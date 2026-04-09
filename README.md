# student-management-api
Student REST API built with Spring Boot, PostgreSQL and Maven

# 🎓 Student Management REST API

A beginner-friendly, production-ready **REST API** built with **Spring Boot** and **PostgreSQL** for managing student records. This project demonstrates core backend development concepts including CRUD operations, layered architecture, input validation, and database integration.

---

## 📌 Project Name Suggestion

```
student-management-api
```

> Clean, professional, and clearly describes what the project does.

---

## 🛠️ Tech Stack

| Technology | Version | Purpose |
|---|---|---|
| Java | 17 | Programming Language |
| Spring Boot | 3.5.13 | Backend Framework |
| Spring Data JPA | 3.5.13 | Database ORM |
| Spring Validation | 3.5.13 | Input Validation |
| PostgreSQL | 18.x | Production Database |
| Lombok | 1.18.x | Boilerplate Reduction |
| Maven | 3.x | Build & Dependency Management |
| Postman | Latest | API Testing |

---

## ✨ Features

- ✅ Full **CRUD** — Create, Read, Update, Delete students
- ✅ **Input Validation** — @NotBlank, @Email, @Min, @Max, @Pattern
- ✅ **Custom Queries** — Filter by age, course, exact age
- ✅ **Multiple Delete** — Delete students by list of IDs
- ✅ **Layered Architecture** — Controller → Service → Repository → Model
- ✅ **PostgreSQL Integration** — Permanent data storage
- ✅ **Seed Data** — 20 pre-loaded students via data.sql
- ✅ **Beautiful Error Messages** — Field-level validation errors

---

## 📁 Project Structure

```
src/main/java/com/example/student_api/
├── controller/
│   └── StudentController.java     # REST endpoints
├── model/
│   └── Student.java               # Entity / Data model
├── repository/
│   └── StudentRepository.java     # Database access layer
├── service/
│   └── StudentService.java        # Business logic layer
└── StudentApiApplication.java     # Main entry point

src/main/resources/
├── application.properties         # App configuration
└── data.sql                       # Seed data — 20 students
```

---

## 🗃️ Student Entity

```java
Student {
    id           Long      // Auto-generated primary key
    name         String    // @NotBlank
    email        String    // @NotBlank + @Email
    age          int       // @Min(1) + @Max(100)
    address      String    // @NotBlank
    phoneNumber  String    // @Pattern — 10 digits
    course       String    // @NotBlank
    gender       String    // Male / Female / Other
    dateOfBirth  LocalDate // @NotNull
}
```

---

## 🔗 API Endpoints

### Student CRUD

| Method | Endpoint | Description |
|---|---|---|
| `GET` | `/api/students` | Get all students |
| `GET` | `/api/students/{id}` | Get student by ID |
| `POST` | `/api/students` | Add new student |
| `PUT` | `/api/students/{id}` | Update student |
| `DELETE` | `/api/students/{id}` | Delete student |
| `DELETE` | `/api/students/delete-multiple` | Delete multiple by IDs |

### Filter & Search

| Method | Endpoint | Description |
|---|---|---|
| `GET` | `/api/students/age/{age}` | Students with age less than given value |
| `GET` | `/api/students/age/exact/{age}` | Students with exact age |
| `GET` | `/api/students/course/{course}` | Students by course name |

### Update Operations

| Method | Endpoint | Description |
|---|---|---|
| `PUT` | `/api/students/age/increment` | Increment age for students below a threshold |

---

## 📦 Sample Request & Response

### POST — Add Student

**Request:**
```json
{
  "name": "Priya Sharma",
  "email": "priya.sharma@gmail.com",
  "age": 21,
  "address": "Delhi",
  "phoneNumber": "9876543210",
  "course": "Computer Science",
  "gender": "Female",
  "dateOfBirth": "2003-05-15"
}
```

**Response (200 OK):**
```json
{
  "id": 21,
  "name": "Priya Sharma",
  "email": "priya.sharma@gmail.com",
  "age": 21,
  "address": "Delhi",
  "phoneNumber": "9876543210",
  "course": "Computer Science",
  "gender": "Female",
  "dateOfBirth": "2003-05-15"
}
```

### Validation Error Example

**Request (invalid data):**
```json
{
  "name": "",
  "email": "not-an-email",
  "age": 150
}
```

**Response (400 Bad Request):**
```json
{
  "name": "Name cannot be empty!",
  "email": "Please enter a valid email!",
  "age": "Age cannot be more than 100!"
}
```

### DELETE Multiple

**Request:**
```json
[3, 9, 12]
```

**Response:**
```
Students deleted successfully!
```

---

## ⚙️ Setup & Installation

### Prerequisites

- Java 17+
- Maven 3.x
- PostgreSQL 14+
- Postman (for API testing)
- IntelliJ IDEA (recommended)

### Step 1 — Clone the Repository

```bash
git clone https://github.com/YOUR_USERNAME/student-management-api.git
cd student-management-api
```

### Step 2 — Create PostgreSQL Database

```sql
CREATE DATABASE student_db;
```

Or open **PgAdmin** → right click Databases → Create → name it `student_db`

### Step 3 — Configure application.properties

Open `src/main/resources/application.properties` and update:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/student_db
spring.datasource.username=postgres
spring.datasource.password=YOUR_PASSWORD
```

### Step 4 — Run the Application

```bash
mvn clean install
mvn spring-boot:run
```

App will start at: `http://localhost:8080`

### Step 5 — Test with Postman

Import the API collection or manually test:
```
GET http://localhost:8080/api/students
```

You should see 20 pre-loaded students! ✅

---

## 🏗️ Architecture

```
Request (Postman/Browser)
         ↓
   Controller Layer       ← Receives HTTP requests, returns JSON
         ↓
   Service Layer          ← Business logic
         ↓
   Repository Layer       ← Database operations (no SQL needed!)
         ↓
   PostgreSQL Database    ← Permanent data storage
```

### Why Layered Architecture?

- **Maintainability** — Change one layer without affecting others
- **Testability** — Each layer can be tested independently
- **Separation of Concerns** — Each class has one clear responsibility

---

## 🧠 Key Concepts Demonstrated

| Concept | Implementation |
|---|---|
| REST API | HTTP methods — GET, POST, PUT, DELETE |
| Spring Data JPA | `JpaRepository` — zero SQL CRUD |
| Bean Validation | `@Valid`, `@NotBlank`, `@Email`, `@Min`, `@Max` |
| Dependency Injection | `@Autowired` |
| Custom Queries | `@Query` with JPQL |
| Lombok | `@Data`, `@NoArgsConstructor`, `@AllArgsConstructor` |
| Exception Handling | `@ExceptionHandler` for validation errors |
| Transactions | `@Transactional` for data integrity |

---

## 🔮 Future Enhancements

- [ ] Spring Security — JWT Authentication
- [ ] Swagger UI — Auto API Documentation
- [ ] Docker — Containerization
- [ ] Unit Tests — JUnit + Mockito
- [ ] Pagination — Large data handling
- [ ] Course Entity — Separate course management
- [ ] React Frontend — UI for the API

---

## 👩‍💻 Author

**Malti Pathak**
- Built as a beginner Spring Boot learning project
- Demonstrates REST API development with Java

---

## 📄 License

This project is open source and available under the [MIT License](LICENSE).

---

> *"Every expert was once a beginner."* 🚀

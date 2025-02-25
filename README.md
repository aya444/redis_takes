# **Simple Redis Spring Boot Application - Student Management**

## **Overview**
This project is a **Spring Boot** application for learning purposes, it integrates **Redis** as an in-memory datastore for managing student records. It demonstrates how to use **Redis as a high-speed key-value store** for caching and data retrieval without relying on a traditional database.

## **Features**
- **CRUD Operations on Student Data**
  - Add a new student
  - Retrieve student details
  - Update student information
  - Delete a student record
- **Fast and Efficient Data Retrieval** using Redis Hashes
- **REST API Endpoints** for managing students

## **Technologies Used**
- **Java 17**
- **Spring Boot**
- **Spring Data Redis**
- **Redis** (as an in-memory datastore)
- **Maven** (for dependency management)

## **Installation & Setup**
### **Prerequisites**
Ensure you have the following installed:
- **JDK 17**
- **Maven**
- **Redis Server** (running on `localhost:6379` by default)

### **Steps to Run the Application**
1. **Clone the repository:**
   ```sh
   git clone https://github.com/aya444/redis_takes.git
   cd redis-spring-boot
   ```

2. **Start Redis Server** (if not already running):
   ```sh
   redis-server
   ```

3. **Build and Run the Application:**
   ```sh
   mvn spring-boot:run
   ```

The application should now be running on `http://localhost:8080/`

## **API Endpoints**
| HTTP Method | Endpoint | Description |
|------------|---------|-------------|
| **POST** | `/student/create` | Add a new student |
| **GET** | `/student/getAll` | Retrieve all students |
| **GET** | `/student/get/{id}` | Retrieve student details |
| **PUT** | `/student/update` | Update student information |
| **DELETE** | `/student/delete/{id}` | Delete a student record |

## **Project Structure**
```
redis-spring-boot/
│── src/
│   ├── main/java/com/example/redis/
│   │   ├── SpringBootRedisApplication.java  # Main Application
│   │   ├── config/RedisConfig.java         # Redis Configuration
│   │   ├── entity/Student.java              # Student Model
│   │   ├── service/StudentService.java     # Service Layer
│   │   ├── controller/RedisController.java # REST Controller
│   ├── resources/
│   │   ├── application.properties         # Redis Configurations
│── pom.xml
│── README.md
```

## **Configuration**
The Redis connection properties are defined in `application.properties`:
```properties
spring.application.name=learnRedis
spring.data.redis.host=localhost
spring.data.redis.port=6379
```

## **Why Use Redis?**
- **Fast Performance**: Redis operates in-memory, making it extremely fast.
- **Simple Data Storage**: Uses **key-value pairs** with **hashes** for structured data.
- **High Availability & Scalability**: Redis supports **replication, clustering, and persistence**.

## **Future Improvements**
Add Redis Documentation


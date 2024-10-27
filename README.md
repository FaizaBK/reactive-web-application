# Reactive Web Application

This is a Spring Boot reactive web application that utilizes WebFlux and MongoDB to manage tasks asynchronously. The application allows users to start counting tasks and track their progress.

## Technologies
- **Java**: 11
- **Spring Boot**: 3.3.5
- **Spring WebFlux**: Reactive programming support
- **Spring Data MongoDB**: Reactive MongoDB access

## Features
- Start a task that counts from 0 to 1000 with a 1-second delay.
- Create a new task with a status and timestamps.
- Reactive data access with MongoDB.

## Setup
1. **Clone the repository**:
   ```bash
   git clone https://github.com/FaizaBK/reactive-web-application.git
   cd reactive-web-application
2. **Configure MongoDB**:
   Spring.data.mongodb.uri=mongodb://localhost:27017/reactive-web
3. **Build and Run the Application**:
   The main entry point for this Spring Boot application is in the Application class
## API Endpoints

Start Task:

- POST /api/receiver/start/{taskId}   : Starts a counting task with a specified task ID.

- Create Task:
-POST /api/receiver/create   :Creates a new task with an initial status.

## Project Structure
```bash
reactive-web-application/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── react_web_app/
│   │   │           ├── Application.java
│   │   │           ├── controllers/
│   │   │           │   └── ReceiverController.java
│   │   │           ├── models/
│   │   │           │   └── Task.java
│   │   │           ├── repositories/
│   │   │           │   └── TaskRepository.java
│   │   │           └── services/
│   │   │               └── CounterService.java
│   │   └── resources/
│   │       └── application.properties
└── pom.xml
```


## Dependencies
The pom.xml file includes the following key dependencies:
- Spring Boot Starter WebFlux: For building reactive web applications.
- Spring Boot Starter Data MongoDB Reactive: For reactive database access with MongoDB.
- Springdoc OpenAPI: For generating API documentation.

## IDE Setup
This project was created using IntelliJ IDEA. You can import the project as a Maven project and start working with the various components directly in the IDE.
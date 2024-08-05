
# Task Management Application

## Table of Contents
- [Introduction](#introduction)
- [Features](#features)
- [Technology Stack](#technology-stack)
- [Installation](#installation)
- [API Documentation](#api-documentation)
- [Usage](#usage)
- [Security](#security)
## Introduction
This is a Task Management Application designed to manage tasks and users. It provides RESTful APIs to create, read, update, and delete tasks and users, as well as user authentication with JWT.

## Features
- User Registration and Authentication
- Task Creation, Update, and Deletion
- Task Assignment to Users
- Task Prioritization and Status Management
- Role-Based Access Control (Admin and User roles)

## Technology Stack
- **Backend:** Spring Boot
- **Security:** Spring Security, JWT
- **Database:** MySQL
- **Documentation:** Swagger (OpenAPI)
- **Caching**

## Installation
1. **Clone the repository:**
    ```bash
    git clone https://github.com/sahil-1208/Woro-Media-Assignment.git
    cd task-management
    ```

2. **Configure the application:**
   - Update the `application.yml` file with your database configuration and JWT secret key.

3. **Build and run the application:**
    
## API Documentation
API documentation is available via Swagger UI. Once the application is running, you can access it at:
```
http://localhost:8080/swagger-ui.html
```

## Usage
### User Authentication
- **Login:**
    ```
    POST /api/v1/auth/login
    Body: { "email": "user@example.com", "password": "password" }
    ```
    Response: `{ "token": "jwt-token" }`

### User Management
- **Add User:**
    ```
    POST /api/v1/user/addUser
    Body: { "email": "user@example.com", "name": "User Name", "password": "password" }
    ```

- **Get User by ID:**
    ```
    GET /api/v1/user/{id}
    ```

- **Update User:**
    ```
    PUT /api/v1/user/{id}
    Body: { "email": "updated@example.com", "name": "Updated Name", "password": "newpassword" }
    ```

- **Delete User:**
    ```
    DELETE /api/v1/user/{id}
    ```

### Task Management
- **Create Task:**
    ```
    POST /api/v1/task/createTask
    Body: {
      "title": "Task Title",
      "description": "Task Description",
      "dueDate": "2023-12-31",
      "priority": "HIGH",
      "userId": 1,
      "taskStatus": "PENDING"
    }
    ```

- **Get Task by ID:**
    ```
    GET /api/v1/task/{id}
    ```

- **Get All Tasks:**
    ```
    GET /api/v1/task/all
    ```

- **Get Tasks by Priority:**
    ```
    GET /api/v1/task/priority/{taskPriority}
    ```

- **Update Task:**
    ```
    PUT /api/v1/task/{id}
    Body: {
      "title": "Updated Title",
      "description": "Updated Description",
      "dueDate": "2024-01-01",
      "priority": "MEDIUM",
      "userId": 1,
      "taskStatus": "IN_PROGRESS"
    }
    ```

- **Delete Task:**
    ```
    DELETE /api/v1/task/{id}
    ```

## Security
- User authentication is managed using JWT.
- Role-based access control is implemented to ensure that only authorized users can access certain endpoints.

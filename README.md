# Spring Boot URL Shortener

A robust, production-ready URL Shortener API built with Spring Boot and PostgreSQL. This application allows users to shorten long URLs, optionally use custom short links, track click analytics, and seamlessly redirect users to the original destination.

## Features

- **Shorten URLs**: Convert long URLs into concise short links.
- **Custom Short Links**: Provide your own custom alias for your shortened URL (e.g., `mysite.com/my-custom-link`).
- **Redirection**: Fast HTTP 301 Permanent Redirects to original URLs.
- **Click Analytics**: Track how many times a short URL has been clicked/visited.
- **Data Persistence**: Uses PostgreSQL for reliable and scalable data storage.
- **Timestamps**: Automatically tracks `createdAt` and `updatedAt` for all short URLs.
- **Validation**: Strict validation of incoming requests to ensure data integrity.

## Tech Stack

- **Java 17**
- **Spring Boot 3.x**
  - Spring Web (Web MVC for RESTful APIs)
  - Spring Data JPA (Hibernate for ORM)
  - Spring Validation (Jakarta constraints)
- **PostgreSQL** (Database)
- **Lombok** (Boilerplate code reduction)
- **Maven** (Build Tool)

---

## Getting Started

### Prerequisites

- [Java Development Kit (JDK) 17](https://jdk.java.net/17/) or higher.
- [Maven](https://maven.apache.org/) installed (or you can use the included `mvnw` wrapper).
- A running instance of **PostgreSQL** (local or cloud-based like Neon DB/AWS RDS).

### 1. Clone the Repository

```bash
git clone <your-repository-url>
cd urlshortener
```

### 2. Configure the Database (`application.properties`)

To run this application, you need to configure your database connection.

1. Navigate to the `src/main/resources/` directory.
2. Ensure you have an `application.properties` file. If it doesn't exist, create it.
3. Open `application.properties` and add your PostgreSQL credentials:

```properties
# Spring Application Name
spring.application.name=urlshortener

# Database Connection Settings (Update with your PostgreSQL details)
# Example for local PostgreSQL: jdbc:postgresql://localhost:5432/your_db_name
spring.datasource.url=jdbc:postgresql://<your-db-host>:<your-db-port>/<your-db-name>?sslmode=require
spring.datasource.username=<your-db-username>
spring.datasource.password=<your-db-password>

# Hibernate / JPA Settings
# Use 'update' for development. Use 'validate' or 'none' for production.
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

*(Note: The project is already set up to work with cloud-managed PostgreSQL like Neon DB by providing the appropriate connection string in the `spring.datasource.url`)*

### 3. Build the Project

Use Maven to resolve dependencies and build the application:

```bash
# Using Maven wrapper
./mvnw clean install

# Or if you have Maven installed locally
mvn clean install
```

### 4. Run the Application

You can start the Spring Boot application using the following command:

```bash
./mvnw spring-boot:run
```

By default, the application will start on `http://localhost:8080`.

---

## API Endpoints

### 1. Create a Short URL
**POST** `/api/urls`

**Request Body:**
```json
{
  "originalUrl": "https://www.example.com/some/very/long/path",
  "customLink": "my-alias" // Optional
}
```
**Response (201 Created):**
```json
{
  "success": true,
  "shortUrl": "http://localhost:8080/my-alias"
}
```

### 2. Redirect to Original URL
**GET** `/{shortcode}`
- Performs an HTTP 301 Redirect to the `originalUrl`.
- Increments the `clickCount` by 1.

### 3. Get URL Analytics
**GET** `/api/{shortcode}/analytics`
- Returns the number of times the short URL has been clicked.

**Response (200 OK):**
```json
{
  "success": true,
  "clickCount": 42
}
```

### 4. Get URL Details
**GET** `/api/{shortcode}`
- Retrieves the full details of the shortened URL.

**Response (200 OK):**
```json
{
  "success": true,
  "shortcode": "my-alias",
  "originalUrl": "https://www.example.com/some/very/long/path",
  "clickCount": 42
}
```

## Database Schema

The application automatically manages the following schema for the `short_urls` table via Hibernate:
- `id` (Primary Key, Auto Increment)
- `original_url` (String, Not Null)
- `short_url` (String, Not Null, Unique)
- `click_count` (Integer, Default 0)
- `created_at` (Timestamp)
- `updated_at` (Timestamp)
- `deleted_at` (Timestamp, Nullable)

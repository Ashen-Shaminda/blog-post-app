# Blog Application

A Spring Boot blog application with PostgreSQL database.

## Prerequisites

- Docker
- Docker Compose

## Running with Docker

This application is containerized and can be run using Docker and Docker Compose.

### Using Docker Compose (Recommended)

To start the entire application stack (Spring Boot app, PostgreSQL, and Adminer):

```bash
docker-compose up -d
```

This will:
- Build the Spring Boot application from the Dockerfile
- Start a PostgreSQL database container
- Start an Adminer container for database management
- Configure all services to work together

To stop all containers:

```bash
docker-compose down
```

To stop all containers and remove volumes (this will delete all data):

```bash
docker-compose down -v
```

### Using Docker Directly

If you want to build and run just the Spring Boot application:

1. Build the Docker image:
   ```bash
   docker build -t blog-app .
   ```

2. Run the container:
   ```bash
   docker run -p 8080:8080 -e SPRING_DATASOURCE_URL=jdbc:postgresql://your-db-host:5432/blog -e SPRING_DATASOURCE_USERNAME=root -e SPRING_DATASOURCE_PASSWORD=changemeinprod blog-app
   ```

## Accessing the Application

- Spring Boot application: http://localhost:8080
- Adminer (database management): http://localhost:8888
  - System: PostgreSQL
  - Server: postgres
  - Username: root
  - Password: changemeinprod
  - Database: blog

## Troubleshooting

- If the application fails to connect to the database, ensure the PostgreSQL container is running:
  ```bash
  docker ps
  ```

- To view logs from the application:
  ```bash
  docker-compose logs app
  ```

- To view logs from the database:
  ```bash
  docker-compose logs postgres
  ```
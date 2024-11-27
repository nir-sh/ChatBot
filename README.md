# Chatbot Application

This is a Spring Boot chatbot application that provides integration with multiple services, including Amazon product lookup and Chuck Norris jokes. It includes a REST API layer, Swagger documentation, and can be easily Dockerized for deployment.

## Features

- **Amazon Product Lookup**: Uses `AmazonService` to provide product-related information.
- **Chuck Norris Jokes**: Uses `ChuckNorrisService` to fetch and deliver random Chuck Norris jokes.
- **Swagger UI**: Interactive API documentation using Swagger.

## Prerequisites

- **Java 8**: Ensure you have Java 8 installed.
- **Maven**: Used for building the project.
- **Docker**: Optional, but recommended for containerized deployment.

## Installation

### Clone the Repository

```sh
git clone <repository-url>
cd nirsh-chatbot-main
```

### Build the Project Locally

To build the project, use Maven:

```sh
mvn clean package
```

This will generate the JAR file in the `target` directory.

## Running the Application

### Option 1: Run from Docker Hub

To run the pre-built Docker image directly from Docker Hub:

1. Pull the image:
   ```sh
   docker pull nirshaulian/chatbot:001
   ```

2. Run the container:
   ```sh
   docker run -p 8080:8080 nirshaulian/chatbot:001
   ```

The application will be accessible on `http://localhost:8080`.

### Option 2: Run Locally

To run the application locally after building it:

```sh
java -jar target/chatbot-0.0.1-SNAPSHOT.jar
```

The application will be accessible on `http://localhost:8080`.

## Configuration

- Copy the `application.properties.template` file to `application.properties`:
  ```sh
  cp application.properties.template application.properties
  ```
- Edit `application.properties` to add your credentials:
  ```properties
  amazon.cookie=YOUR_AMAZON_COOKIE_HERE
  ```

## Swagger API Documentation

Swagger UI is available to explore the API:

- Once the application is running, go to: `http://localhost:8080/swagger-ui.html`

## API Endpoints

### `/bot/chuckNorris` (GET)

- **Description**: Fetches a random Chuck Norris joke.

### `/bot/amazon` (GET)

- **Description**: Provides Amazon product-related data.

## Development

### Configurations

- **Swagger Configuration**: Configured via `SwaggerConfig.java` to enable easy API exploration.
- **Controllers and Services**:
  - `BotController.java`: Handles REST requests for Chuck Norris jokes and Amazon services.
  - `AmazonService.java` and `ChuckNorrisService.java`: Service classes that encapsulate business logic.

## License

This project is open-source and available under the [MIT License](LICENSE).

## Contact

For any questions, feel free to reach out via GitHub issues.


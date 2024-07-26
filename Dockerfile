# Use an official OpenJDK runtime as a parent image
FROM openjdk:17

# Set the working directory in the container
WORKDIR /app

# Copy the current directory contents into the container at /app
COPY . /app

# Environment variables
ENV SPRING_DATASOURCE_URL=jdbc:h2:mem:testdb
ENV SPRING_DATASOURCE_USERNAME=sa
ENV SPRING_DATASOURCE_PASSWORD=password
ENV SPRING_JPA_HIBERNATE_DDL_AUTO=update

# Make port 8080 available to the world outside this container
EXPOSE 8080

# Run the Spring Boot application
CMD ["java", "-jar", "/app/target/ecommerce-0.0.1-SNAPSHOT.jar"]


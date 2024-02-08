# Use the official Open JDK base image
FROM openjdk:17

# Set the working directory inside the container
WORKDIR /tmp

# Copy the packaged JAR file into the container at /tmp
COPY ./target/TicTacToeApp.jar /tmp/TicTacToeApp.jar

# Expose the default Spring Boot port
EXPOSE 8080

#Command to run application
CMD ["java","-jar","TicTacToeApp.jar"]
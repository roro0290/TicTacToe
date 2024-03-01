# Tic Tac Toe Web App

## Overview

This project started as a simple Tic Tac Toe game inspired by a YouTube tutorial. The initial version allowed users to input positions in the console while the computer randomly selected available positions and evaluated the game's outcome (win, lose, or tie).

## Features

- Front End:
  - Simple HTML and Bootstrap interface for a user-friendly experience.

- Middle Layer:
  - Thymeleaf and JavaScript to capture user input.
  - Ajax and jQuery to send user input to the Java Spring backend.

- Backend:
  - Java Spring controller with methods to evaluate game outcomes.
  - Successive evaluations and responses sent back to the front end.

## Deployment

The project has been containerized using Docker. The Docker image is deployed on Amazon ECS Fargate, ensuring scalability and efficient resource utilization.

## Usage

1. Clone the repository:

```bash
git clone https://github.com/your-username/tic-tac-toe-web-app.git
cd tic-tac-toe-web-app

2. Build and run the Docker image:

```bash
docker build -t tic-tac-toe-app .
docker run -p 8080:8080 tic-tac-toe-app

3. Access the web app in your browser: http://localhost:8080


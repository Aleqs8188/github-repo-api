<<<<<<< HEAD
# GitHub Repositories API

This is a simple API that allows users to list all their GitHub repositories that are not forks. The API fetches information about the repositories, including the repository name, owner login, and for each branch, its name and last commit SHA.

## Requirements

- JDK 17 or higher
- Quarkus 3.x
- GitHub API

## Project Setup

To build and run the project locally, follow these steps:

### 1. Clone the repository

```bash
git clone https://github.com/your-username/your-repository.git
```

### 2. Build the project

Make sure you have Maven installed, then run the following command in the project root directory:

```bash
./mvnw clean package
```
### 3. Run the application

You can run the application with the following command:

```bash
./mvnw quarkus:dev
```

This will start the application locally on http://localhost:8080.

### 4. Testing the API

Once the application is running, you can use the following endpoints:

Get all GitHub repositories (non-forks)
Endpoint: /github/repositories

Method: GET

Query Parameter:

    username: GitHub username of the user whose repositories are to be fetched.

Example Request:
```bash
curl -X GET "http://localhost:8080/github/repositories?username=octocat"
```
Response (Success - 200 OK):

```bash
[
  {
    "name": "example",
    "ownerLogin": "usereExample",
    "branches": [
      {
        "name": "main",
        "commitSha": "a1b2c3d4"
      }
    ],
    "fork": false
  }
]
```

Response (Not Found - 404):

```bash
{
  "status": 404,
  "message": "User not found"
}
```

### Error Handling

404 Not Found: If the user does not exist or has no repositories.

500 Internal Server Error: If there is a server issue or API failure.

### Dependencies

Quarkus 3.x

RESTEasy Reactive

GitHub API

### License

This project is licensed under the MIT License - see the [LICENSE](.) file for details.
=======
# github-repo-api
Project for test task.
>>>>>>> 0f41f83028f48b62b10a32e850e80d5c0ae304bc

# SchoolPaz Backend

This is the backend service for SchoolPaz, a school management system.

## Features

- Docker Compose integration with MongoDB, PostgreSQL, MinIO, and MailHog
- Environment variables configuration with .env file
- Mail service with Thymeleaf and Mustache template processing
- File service for uploading, updating, and deleting files (with focus on images)
- REST API endpoints for mail and file operations

## Prerequisites

- Java 21
- Docker and Docker Compose
- Gradle

## Getting Started

### 1. Clone the repository

```bash
git clone https://github.com/yourusername/SchoolPaz-be.git
cd SchoolPaz-be
```

### 2. Configure environment variables

The project uses a `.env` file for configuration. A default `.env` file is provided with the project, but you can modify it according to your needs.

### 3. Start the Docker services

```bash
docker-compose up -d
```

This will start the following services:
- PostgreSQL (port 5432)
- MongoDB (port 27017)
- MinIO (API port 9000, Console port 9001)
- MailHog (SMTP port 1025, UI port 8025)

### 4. Build and run the application

```bash
./gradlew bootRun
```

The application will start on port 8080.

## Docker Services

### PostgreSQL

- **URL**: jdbc:postgresql://localhost:5432/schoolpaz
- **Username**: schoolpaz
- **Password**: schoolpaz

### MongoDB

- **Host**: localhost
- **Port**: 27017
- **Database**: schoolpaz
- **Username**: schoolpaz
- **Password**: schoolpaz

### MinIO (S3-compatible object storage)

- **Endpoint**: http://localhost:9000
- **Console**: http://localhost:9001
- **Access Key**: schoolpaz
- **Secret Key**: schoolpaz
- **Bucket**: schoolpaz

### MailHog (Email testing)

- **SMTP Server**: localhost
- **SMTP Port**: 1025
- **Web UI**: http://localhost:8025

## API Endpoints

### File Operations

- **Upload a file**: `POST /api/files/upload`
  - Parameters: `file` (MultipartFile), `fileName` (String)
  - Returns: URL of the uploaded file

- **Upload an image**: `POST /api/files/upload/image`
  - Parameters: `image` (MultipartFile), `fileName` (String)
  - Returns: URL of the uploaded image

- **Update a file**: `PUT /api/files/update`
  - Parameters: `file` (MultipartFile), `fileName` (String)
  - Returns: URL of the updated file

- **Update an image**: `PUT /api/files/update/image`
  - Parameters: `image` (MultipartFile), `fileName` (String)
  - Returns: URL of the updated image

- **Delete a file**: `DELETE /api/files/{fileName}`
  - Returns: Success message

- **Get a file**: `GET /api/files/{fileName}`
  - Returns: The file content

- **List all files**: `GET /api/files`
  - Returns: List of file names

### Mail Operations

- **Send a simple email**: `POST /api/mail/simple`
  - Parameters: `to` (String), `subject` (String), `text` (String)
  - Returns: Success message

- **Send a Thymeleaf email**: `POST /api/mail/thymeleaf`
  - Parameters: `to` (String), `subject` (String), `templateString` (String)
  - Body: JSON object with template variables
  - Returns: Success message

- **Send a Thymeleaf template email**: `POST /api/mail/thymeleaf/template`
  - Parameters: `to` (String), `subject` (String), `templateName` (String)
  - Body: JSON object with template variables
  - Returns: Success message

- **Send a Mustache email**: `POST /api/mail/mustache`
  - Parameters: `to` (String), `subject` (String), `templateString` (String)
  - Body: JSON object with template variables
  - Returns: Success message

- **Send a Mustache template email**: `POST /api/mail/mustache/template`
  - Parameters: `to` (String), `subject` (String), `templateName` (String)
  - Body: JSON object with template variables
  - Returns: Success message

- **Test welcome email**: `GET /api/mail/test/welcome?to=email@example.com`
  - Returns: Success message

- **Test notification email**: `GET /api/mail/test/notification?to=email@example.com`
  - Returns: Success message

## Testing

### Testing Mail Service

1. Start the application and Docker services
2. Open MailHog UI at http://localhost:8025
3. Send a test email using one of the test endpoints:
   - `GET /api/mail/test/welcome?to=test@example.com`
   - `GET /api/mail/test/notification?to=test@example.com`
4. Check MailHog UI to see the received email

### Testing File Service

1. Start the application and Docker services
2. Use the file API endpoints to upload, update, and delete files
3. Access MinIO console at http://localhost:9001 to see the uploaded files
   - Username: schoolpaz
   - Password: schoolpaz

## License

This project is licensed under the MIT License - see the LICENSE file for details.
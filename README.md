# Direct JWT Token Generation

A Spring Boot REST API that accepts a JSON payload (`username`, `role`) and returns a signed JWT using the JJWT library.

## Requirements
- Java 17+
- Maven 3.8+
- Internet access on first build so Maven can download dependencies

## Run in STS
1. Extract this ZIP.
2. Open Spring Tool Suite.
3. Select **File → Import → Maven → Existing Maven Projects**.
4. Select the extracted `jwt-token-generation` folder and click **Finish**.
5. Right-click the project → **Run As → Spring Boot App**.

Or run from a terminal in the project directory:

```bash
mvn spring-boot:run
```

## Swagger UI
Open: http://localhost:8080/swagger-ui/index.html

## Generate a token with Postman
- Method: `POST`
- URL: `http://localhost:8080/api/jwt/generate`
- Header: `Content-Type: application/json`
- Body → raw → JSON:

```json
{
  "username": "charan",
  "role": "USER"
}
```

Example response:

```json
{
  "message": "JWT generated successfully",
  "token": "eyJ...<three-part-token>...",
  "tokenType": "Bearer"
}
```

The actual token is generated at runtime. It has the standard `header.payload.signature` format. The payload contains `sub` (username), `role`, `iat` (issued-at), and `exp` (expiry).

## Important security note
The included secret is for local demonstration only. In real deployments, use a long random secret supplied through an environment variable or a secrets manager, and never commit production signing keys. A JWT is signed, not encrypted, so do not put passwords or other secrets in its payload.

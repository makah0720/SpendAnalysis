## Deployment Guide

### Build
- With Maven Wrapper:
  - Linux/macOS: `./mvnw -DskipTests package`
  - Windows: `mvnw.cmd -DskipTests package`

### Run
- `java -jar target/spend-analytics-0.0.1-SNAPSHOT.jar`

### Configuration
- See `src/main/resources/application.yml`
- Override via environment variables:
  - `APP_SECURITY_ADMIN-USER`, `APP_SECURITY_ADMIN-PASS`
  - `APP_SECURITY_ANALYST-USER`, `APP_SECURITY_ANALYST-PASS`
  - `SPRING_DATASOURCE_URL`, `SPRING_DATASOURCE_USERNAME`, `SPRING_DATASOURCE_PASSWORD`

### Production Hardening
- Replace H2 with a managed DB (PostgreSQL/MySQL)
- Add HTTPS termination (reverse proxy or embedded)
- Externalize credentials to a secret manager
- Configure logging and monitoring (Actuator, OpenTelemetry)


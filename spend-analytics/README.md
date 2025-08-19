## Spend Analytics Module

Production-ready Spring Boot module for ERP Procurement spend visibility, analytics, savings tracking, reporting, and compliance.

### Tech Stack
- Spring Boot 3, Java 17
- Spring MVC, Thymeleaf
- Spring Security (RBAC)
- Spring Data JPA, H2 (in-memory demo)
- Lombok
- OpenPDF, Apache POI (exports)

### Quick Start
1. Build: `./mvnw -DskipTests package` (if Maven wrapper is available) or install Maven and run `mvn -DskipTests package`.
2. Run: `java -jar target/spend-analytics-0.0.1-SNAPSHOT.jar`
3. Open: `http://localhost:8080`

### Demo Credentials
- Admin: `admin` / `admin123`
- Analyst: `analyst` / `analyst123`

### API Endpoints
- GET `/analysis/dashboard` — dashboard view with KPIs and charts
- GET `/analysis/suppliers` — supplier spend analysis view
- GET `/analysis/categories` — category spend analysis view
- GET `/analysis/trends` — trend analysis view
- GET `/analysis/maverick` — maverick spend page; POST `/analysis/maverick/detect`
- POST `/cube/rebuild` — rebuild spend cube (ADMIN)
- GET `/savings` — list savings initiatives
- POST `/savings` — create savings
- GET `/report` — view consolidated report
- GET `/report/export/csv` — export report CSV
- GET `/report/export/pdf` — export PDF
- GET `/report/export/xlsx` — export Excel

### Architecture
- `entities` — JPA entities (SpendTransaction, SpendCategory, SpendCube, SpendAnalytics, MaverickSpend, SavingsTracking, SpendDashboard, CategoryAnalysis, SupplierSpendAnalysis, TrendAnalysis)
- `dto` — DTOs (SpendTransactionDTO, SpendAnalyticsDTO, SavingsTrackingDTO, SpendReportDTO)
- `repositories` — Spring Data repositories
- `services` — business logic (SpendAnalysisService, SpendCubeService, SavingsTrackingService, SpendReportingService)
- `controllers` — MVC controllers & RBAC
- `templates` — Thymeleaf views (dashboard, savings, report)
- `config` — SecurityConfig

### Testing
- Run all tests: `./mvnw test`
- Add integration tests under `src/test/java`

### Deployment
- Build jar: `./mvnw -DskipTests package`
- Environment variables can override credentials: see `application.yml` under `app.security.*`
- Use a real DB (PostgreSQL, MySQL) by replacing `spring.datasource.*` and adding driver dependency.


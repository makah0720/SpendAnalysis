## Testing & Verification

### Local Smoke Test
1. Build: `./mvnw -DskipTests package`
2. Run: `java -jar target/spend-analytics-0.0.1-SNAPSHOT.jar`
3. Login at `http://localhost:8080` with:
   - Admin: `admin` / `admin123`
   - Analyst: `analyst` / `analyst123`
4. Validate pages:
   - Dashboard: `/analysis/dashboard`
   - Savings: `/savings`
   - Report: `/report`
5. Export:
   - CSV: `/report/export/csv`
   - PDF: `/report/export/pdf`
   - Excel: `/report/export/xlsx`

### Automated Tests
- Run: `./mvnw test`
- Add more tests under `src/test/java` (service-level unit tests and controller MVC tests).


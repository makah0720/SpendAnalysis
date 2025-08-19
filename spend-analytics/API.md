## API & UI Reference

### Authentication
- Form login (RBAC):
  - Admin: `admin` / `admin123`
  - Analyst: `analyst` / `analyst123`

### UI Routes
- GET `/analysis/dashboard` — KPIs, Spend by Supplier/Category, Monthly Trend
- GET `/savings` — Savings tracking list and create form
- POST `/savings` — Create savings initiative (ADMIN only)
- POST `/cube/rebuild` — Rebuild cube (ADMIN only)
- GET `/report` — Consolidated analytics + savings report view

### Export Endpoints
- GET `/report/export/csv` — text/csv
- GET `/report/export/pdf` — application/pdf
- GET `/report/export/xlsx` — application/vnd.openxmlformats-officedocument.spreadsheetml.sheet

### Notes
- All endpoints require role `ADMIN` or `ANALYST` unless otherwise stated.
- For API automation, send session cookie after login or integrate Spring Security stateless auth for production (JWT/OAuth2).


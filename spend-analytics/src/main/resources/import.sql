INSERT INTO spend_transaction (transaction_id, supplier_name, category_code, category_name, cost_center, currency, transaction_date, amount, approved, contracted)
VALUES
 ('T-1001','Acme Supplies','IT-HW','IT Hardware','CC-100','USD','2024-11-01',15000.00, true, true),
 ('T-1002','Globex Corp','MKT','Marketing','CC-200','USD','2024-11-10',8000.00, false, true),
 ('T-1003','Initech','FAC','Facilities','CC-300','USD','2024-12-05',12000.00, true, false),
 ('T-1004','Acme Supplies','IT-SW','Software','CC-100','USD','2025-01-15',20000.00, true, true),
 ('T-1005','Umbrella Co','TRV','Travel','CC-400','USD','2025-01-20',5000.00, true, true);

INSERT INTO savings_tracking (initiative_name, category_code, supplier_name, baseline_spend, realized_spend, period)
VALUES
 ('Software Consolidation','IT-SW','Acme Supplies',30000.00,20000.00,'2025-01-01');


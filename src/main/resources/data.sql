-- CUST001 - Transactions
INSERT INTO customer_transaction (customer_id, amount, transaction_date)
VALUES ('CUST001', 120.0, '2025-04-10'),
('CUST001', 120.0, '2025-05-15'),
('CUST001', 225.0, '2024-12-22');

-- CUST002 - Transactions
INSERT INTO customer_transaction (customer_id, amount, transaction_date)
VALUES ('CUST002', 120.0, '2025-04-17'),
       ('CUST002', 120.0, '2025-01-10');

-- CUST003 and CUST004 - Transactions
INSERT INTO customer_transaction (customer_id, amount, transaction_date)
VALUES ('CUST003', 120.0, '2025-03-10'),
       ('CUST004', 120.0, '2025-03-25'),
       ('CUST005', 120.0, '2025-03-25'),
       ('CUST005', 120.0, '2025-04-25');
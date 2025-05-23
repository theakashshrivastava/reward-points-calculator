CREATE TABLE customer_transaction (
                              id BIGINT AUTO_INCREMENT PRIMARY KEY,
                              customer_id VARCHAR(50) NOT NULL,
                              amount DOUBLE NOT NULL,
                              transaction_date DATE NOT NULL
);
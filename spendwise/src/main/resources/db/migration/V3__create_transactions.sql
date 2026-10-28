-- Lets a transaction point to a category AND its type together
ALTER TABLE categories
    ADD CONSTRAINT uq_categories_id_type UNIQUE (id, type);

CREATE TABLE transactions (
                              id             BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                              user_id        BIGINT        NOT NULL,
                              category_id    BIGINT        NOT NULL,
                              type           VARCHAR(10)   NOT NULL,
                              amount         NUMERIC(14,2) NOT NULL,
                              txn_date       DATE          NOT NULL,
                              payment_method VARCHAR(20),
                              description    VARCHAR(255),
                              created_at     TIMESTAMPTZ   NOT NULL DEFAULT now(),
                              updated_at     TIMESTAMPTZ   NOT NULL DEFAULT now(),

                              CONSTRAINT fk_transactions_user
                                  FOREIGN KEY (user_id) REFERENCES users (id),
                              CONSTRAINT fk_transactions_category_type
                                  FOREIGN KEY (category_id, type) REFERENCES categories (id, type),
                              CONSTRAINT ck_transactions_type
                                  CHECK (type IN ('EXPENSE', 'INCOME')),
                              CONSTRAINT ck_transactions_amount_positive
                                  CHECK (amount > 0),
                              CONSTRAINT ck_transactions_payment_method
                                  CHECK (payment_method IN ('CASH', 'CREDIT_CARD')),
                              CONSTRAINT ck_transactions_payment_by_type
                                  CHECK (
                                      (type = 'EXPENSE' AND payment_method IS NOT NULL) OR
                                      (type = 'INCOME'  AND payment_method IS NULL)
                                      )
);

CREATE INDEX idx_transactions_user_date
    ON transactions (user_id, txn_date DESC);
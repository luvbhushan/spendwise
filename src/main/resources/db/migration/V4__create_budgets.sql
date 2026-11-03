CREATE TABLE budgets (
                         id           BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                         user_id      BIGINT        NOT NULL,
                         category_id  BIGINT,
                         type         VARCHAR(10)   NOT NULL DEFAULT 'EXPENSE',
                         budget_month DATE          NOT NULL,
                         limit_amount NUMERIC(14,2) NOT NULL,
                         created_at   TIMESTAMPTZ   NOT NULL DEFAULT now(),
                         updated_at   TIMESTAMPTZ   NOT NULL DEFAULT now(),

                         CONSTRAINT fk_budgets_user
                             FOREIGN KEY (user_id) REFERENCES users (id),
                         CONSTRAINT fk_budgets_category_type
                             FOREIGN KEY (category_id, type) REFERENCES categories (id, type),
                         CONSTRAINT ck_budgets_type
                             CHECK (type = 'EXPENSE'),
                         CONSTRAINT ck_budgets_amount_positive
                             CHECK (limit_amount > 0),
                         CONSTRAINT ck_budgets_first_of_month
                             CHECK (EXTRACT(DAY FROM budget_month) = 1),
                         CONSTRAINT uq_budgets_user_month_category
                             UNIQUE NULLS NOT DISTINCT (user_id, budget_month, category_id)
);
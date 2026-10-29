CREATE TABLE categories (
                            id   BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                            name VARCHAR(50) NOT NULL,
                            type VARCHAR(10) NOT NULL,

                            CONSTRAINT ck_categories_type CHECK (type IN ('EXPENSE', 'INCOME')),
                            CONSTRAINT uq_categories_type_name UNIQUE (type, name)
);

INSERT INTO categories (name, type) VALUES
                                        ('Food and Dining',         'EXPENSE'),
                                        ('Groceries',               'EXPENSE'),
                                        ('Shopping',                'EXPENSE'),
                                        ('Petrol and Fuel',         'EXPENSE'),
                                        ('Travel',                  'EXPENSE'),
                                        ('Rent',                    'EXPENSE'),
                                        ('Bills and Utilities',     'EXPENSE'),
                                        ('Health',                  'EXPENSE'),
                                        ('Entertainment',           'EXPENSE'),
                                        ('Education',               'EXPENSE'),
                                        ('EMI and Loans',           'EXPENSE'),
                                        ('Other',                   'EXPENSE'),
                                        ('Salary',                  'INCOME'),
                                        ('Freelance',               'INCOME'),
                                        ('Interest and Investments','INCOME'),
                                        ('Gift',                    'INCOME'),
                                        ('Other',                   'INCOME');
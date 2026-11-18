CREATE TABLE alerts (
                        id            BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                        user_id       BIGINT      NOT NULL,
                        budget_id     BIGINT      NOT NULL,
                        threshold_pct SMALLINT    NOT NULL,
                        triggered_at  TIMESTAMPTZ NOT NULL DEFAULT now(),
                        read_at       TIMESTAMPTZ,
                        created_at    TIMESTAMPTZ NOT NULL DEFAULT now(),

                        CONSTRAINT fk_alerts_user
                            FOREIGN KEY (user_id) REFERENCES users (id),
                        CONSTRAINT fk_alerts_budget
                            FOREIGN KEY (budget_id) REFERENCES budgets (id) ON DELETE CASCADE,
                        CONSTRAINT ck_alerts_threshold
                            CHECK (threshold_pct IN (80, 100)),
                        CONSTRAINT uq_alerts_budget_threshold
                            UNIQUE (budget_id, threshold_pct)
);

CREATE INDEX idx_alerts_user_unread
    ON alerts (user_id, triggered_at DESC)
    WHERE read_at IS NULL;

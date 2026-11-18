package com.luvbhushan.spendwise.transaction.domain;

/**
 * How an expense was paid. Required for expenses; must be null for incomes.
 * Stored in the database as the VARCHAR values CASH and CREDIT_CARD.
 */
public enum PaymentMethod {
	CASH,
	CREDIT_CARD
}

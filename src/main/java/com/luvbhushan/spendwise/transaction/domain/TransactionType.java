package com.luvbhushan.spendwise.transaction.domain;

/**
 * Whether a transaction (or related row) is money out or money in.
 * Stored in the database as the VARCHAR values EXPENSE and INCOME.
 */
public enum TransactionType {
	EXPENSE,
	INCOME
}

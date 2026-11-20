package com.luvbhushan.spendwise.budget.domain;

import com.luvbhushan.spendwise.category.domain.Category;
import com.luvbhushan.spendwise.identity.domain.User;
import com.luvbhushan.spendwise.transaction.domain.TransactionType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

/**
 * A monthly spending limit for one user: either for one expense category,
 * or overall when {@link #category} is null. Maps to budgets (V4).
 * Spent-vs-limit is computed live from transactions later; not stored here.
 */
@Entity
@Table(name = "budgets")
public class Budget {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	/** Null means an overall monthly limit (all expense categories). */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id")
	private Category category;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 10)
	private TransactionType type;

	@Column(name = "budget_month", nullable = false)
	private LocalDate budgetMonth;

	@Column(name = "limit_amount", nullable = false, precision = 14, scale = 2)
	private BigDecimal limitAmount;

	@Column(name = "created_at", nullable = false)
	private Instant createdAt;

	@Column(name = "updated_at", nullable = false)
	private Instant updatedAt;

	/** Required by JPA; not used by application code. */
	protected Budget() {
	}

	/**
	 * @param category    expense category, or null for overall limit
	 * @param budgetMonth must be the first day of a month (enforced by DB + service later)
	 */
	public Budget(User user, Category category, LocalDate budgetMonth, BigDecimal limitAmount) {
		this.user = user;
		this.category = category;
		this.type = TransactionType.EXPENSE;
		this.budgetMonth = budgetMonth;
		this.limitAmount = limitAmount;
		Instant now = Instant.now();
		this.createdAt = now;
		this.updatedAt = now;
	}

	public Long getId() {
		return id;
	}

	public User getUser() {
		return user;
	}

	public Category getCategory() {
		return category;
	}

	public TransactionType getType() {
		return type;
	}

	public LocalDate getBudgetMonth() {
		return budgetMonth;
	}

	public BigDecimal getLimitAmount() {
		return limitAmount;
	}

	public Instant getCreatedAt() {
		return createdAt;
	}

	public Instant getUpdatedAt() {
		return updatedAt;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public void setBudgetMonth(LocalDate budgetMonth) {
		this.budgetMonth = budgetMonth;
	}

	public void setLimitAmount(BigDecimal limitAmount) {
		this.limitAmount = limitAmount;
	}

	public void setUpdatedAt(Instant updatedAt) {
		this.updatedAt = updatedAt;
	}
}

package com.luvbhushan.spendwise.transaction.domain;

import com.luvbhushan.spendwise.category.domain.Category;
import com.luvbhushan.spendwise.identity.domain.User;
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
 * One money movement: either an expense or an income (see {@link #type}).
 * Maps to the shared transactions table (V3). Business rules (date range,
 * payment method, income lock) live in the service layer later.
 */
@Entity
@Table(name = "transactions")
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id", nullable = false)
	private Category category;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 10)
	private TransactionType type;

	@Column(nullable = false, precision = 14, scale = 2)
	private BigDecimal amount;

	@Column(name = "txn_date", nullable = false)
	private LocalDate txnDate;

	@Enumerated(EnumType.STRING)
	@Column(name = "payment_method", length = 20)
	private PaymentMethod paymentMethod;

	@Column(length = 255)
	private String description;

	@Column(name = "created_at", nullable = false)
	private Instant createdAt;

	@Column(name = "updated_at", nullable = false)
	private Instant updatedAt;

	/** Required by JPA; not used by application code. */
	protected Transaction() {
	}

	public Transaction(
			User user,
			Category category,
			TransactionType type,
			BigDecimal amount,
			LocalDate txnDate,
			PaymentMethod paymentMethod,
			String description) {
		this.user = user;
		this.category = category;
		this.type = type;
		this.amount = amount;
		this.txnDate = txnDate;
		this.paymentMethod = paymentMethod;
		this.description = description;
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

	public BigDecimal getAmount() {
		return amount;
	}

	public LocalDate getTxnDate() {
		return txnDate;
	}

	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}

	public String getDescription() {
		return description;
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

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public void setTxnDate(LocalDate txnDate) {
		this.txnDate = txnDate;
	}

	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setUpdatedAt(Instant updatedAt) {
		this.updatedAt = updatedAt;
	}
}

package com.luvbhushan.spendwise.identity.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.time.Instant;

/**
 * A person who owns expenses, incomes, and budgets.
 * Maps to the users table (V1). Auth rules come later in the identity service.
 */
@Entity
@Table(
		name = "users",
		uniqueConstraints = {
				@UniqueConstraint(name = "uq_users_email", columnNames = "email")
		}
)
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 255)
	private String email;

	@Column(name = "password_hash", nullable = false, length = 255)
	private String passwordHash;

	@Column(nullable = false, length = 100)
	private String name;

	@Column(name = "created_at", nullable = false)
	private Instant createdAt;

	@Column(name = "updated_at", nullable = false)
	private Instant updatedAt;

	/** Required by JPA; not used by application code. */
	protected User() {
	}

	/**
	 * Creates a new user. Caller must pass an already-lowercased email
	 * and an already-hashed password (hashing belongs in the service later).
	 */
	public User(String email, String passwordHash, String name) {
		this.email = email;
		this.passwordHash = passwordHash;
		this.name = name;
		Instant now = Instant.now();
		this.createdAt = now;
		this.updatedAt = now;
	}

	public Long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public String getName() {
		return name;
	}

	public Instant getCreatedAt() {
		return createdAt;
	}

	public Instant getUpdatedAt() {
		return updatedAt;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public void setUpdatedAt(Instant updatedAt) {
		this.updatedAt = updatedAt;
	}
}

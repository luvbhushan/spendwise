package com.luvbhushan.spendwise.budget.domain;

import com.luvbhushan.spendwise.identity.domain.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.Instant;

/**
 * A notice that a budget crossed 80% or 100% of its limit.
 * Maps to alerts (V5). Rows are not deleted by the user; mark as read instead.
 * Deleting the parent budget removes its alerts (DB ON DELETE CASCADE).
 */
@Entity
@Table(name = "alerts")
public class Alert {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "budget_id", nullable = false)
	private Budget budget;

	/** 80 or 100; enforced by the database check constraint. */
	@Column(name = "threshold_pct", nullable = false)
	private short thresholdPct;

	@Column(name = "triggered_at", nullable = false)
	private Instant triggeredAt;

	/** Null means unread. */
	@Column(name = "read_at")
	private Instant readAt;

	@Column(name = "created_at", nullable = false)
	private Instant createdAt;

	/** Required by JPA; not used by application code. */
	protected Alert() {
	}

	public Alert(User user, Budget budget, short thresholdPct) {
		this.user = user;
		this.budget = budget;
		this.thresholdPct = thresholdPct;
		Instant now = Instant.now();
		this.triggeredAt = now;
		this.createdAt = now;
	}

	public Long getId() {
		return id;
	}

	public User getUser() {
		return user;
	}

	public Budget getBudget() {
		return budget;
	}

	public short getThresholdPct() {
		return thresholdPct;
	}

	public Instant getTriggeredAt() {
		return triggeredAt;
	}

	public Instant getReadAt() {
		return readAt;
	}

	public Instant getCreatedAt() {
		return createdAt;
	}

	public boolean isUnread() {
		return readAt == null;
	}

	public void markRead(Instant readAt) {
		this.readAt = readAt;
	}
}

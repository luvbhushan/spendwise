package com.luvbhushan.spendwise.category.domain;

import com.luvbhushan.spendwise.transaction.domain.TransactionType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

/**
 * A predefined expense or income label (Food, Salary, etc.).
 * Rows are seeded by Flyway; the app never creates, updates, or deletes them.
 */
@Entity
@Table(
		name = "categories",
		uniqueConstraints = {
				@UniqueConstraint(name = "uq_categories_type_name", columnNames = {"type", "name"}),
				@UniqueConstraint(name = "uq_categories_id_type", columnNames = {"id", "type"})
		}
)
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 50)
	private String name;

	/**
	 * EXPENSE or INCOME. Stored as the enum name in the VARCHAR type column.
	 */
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 10)
	private TransactionType type;

	/** Required by JPA; not used by application code. */
	protected Category() {
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public TransactionType getType() {
		return type;
	}
}

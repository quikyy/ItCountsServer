package com.itcounts.model.dao.expense;

import com.itcounts.model.dao.account.AccountDAO;
import com.itcounts.model.dao.user.UserDAO;
import java.math.BigInteger;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "expense_scheduled")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ExpenseScheduledDAO {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private BigInteger id;

	@Column(name = "amount")
	private double amount;

	@Column(name = "day_of_month")
	private int dayOfMonth;

	@Column(name = "inserted_date")
	private Timestamp insertedDate;

	@Column(name = "is_active")
	private boolean isActive;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "account_id")
	private AccountDAO accountDao;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private UserDAO userDao;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id")
	private ExpenseCategoryDAO expenseCategoryDao;


}

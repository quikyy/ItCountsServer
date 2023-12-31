package com.itcounts.model.dao.expense;

import com.itcounts.model.dao.account.AccountDAO;
import com.itcounts.model.dao.user.UserDAO;
import java.math.BigInteger;
import java.sql.Date;
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
@Table(name = "accounts_expenses")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ExpenseDAO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", columnDefinition = "BIGINT")
	private BigInteger id;

	@Column(name = "amount")
	private double amount;

	@Column(name = "inserted_date")
	private Timestamp insertedDate;

	@Column(name = "spend_date")
	private Date spendDate;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "account_id")
	private AccountDAO accountDao;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id")
	private ExpenseCategoryDAO expenseCategoryDao;

	@Column(name = "is_deleted")
	private boolean isDeleted;

	@Column(name = "is_scheduled")
	private boolean isScheduled;

	@Column(name = "info")
	private String info;

}

package com.itcounts.requestbody;

import java.math.BigInteger;
import java.sql.Date;
import lombok.Getter;

@Getter
public class ExpenseBodyDTO {
	private BigInteger accountId;
	private BigInteger expenseCategoryId;
	private double amount;
	private Date spendDate;

}

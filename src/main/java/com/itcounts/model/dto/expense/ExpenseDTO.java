package com.itcounts.model.dto.expense;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ExpenseDTO {

	private BigInteger id;
	private BigInteger expenseCategoryId;
	private double amount;
	private Timestamp insertedDate;
	private Date spendDate;
	private boolean isScheduled;

}

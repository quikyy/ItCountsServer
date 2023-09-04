package com.itcounts.requestbody;

import java.math.BigDecimal;
import java.math.BigInteger;
import lombok.Getter;
import lombok.Setter;

@Getter
public class ExpenseBodyDTO {
	private BigInteger accountId;
	private BigInteger expenseCategoryId;
	private BigDecimal amount;

}

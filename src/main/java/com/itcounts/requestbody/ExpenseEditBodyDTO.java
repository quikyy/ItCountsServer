package com.itcounts.requestbody;

import java.math.BigInteger;
import lombok.Getter;

@Getter
public class ExpenseEditBodyDTO {

	private double amount;
	private BigInteger expenseCategoryId;

}

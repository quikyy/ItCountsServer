package com.itcounts.requestbody;

import java.math.BigInteger;
import lombok.Getter;

@Getter
public class ExpenseScheduledBodyDTO {

	private double amount;
	private int dayOfMonth;
	private BigInteger categoryId;

}

package com.itcounts.model.dto.expense;

import java.math.BigInteger;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ExpenseScheduledDTO {

	private BigInteger id;
	private double amount;
	private int dayOfMonth;
	private BigInteger userId;
	private BigInteger categoryId;
	private boolean isActive;
}

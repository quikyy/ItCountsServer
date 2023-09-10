package com.itcounts.model.dto.expense;

import java.math.BigInteger;
import java.sql.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ExpenseDTOBucket {

	private Date startDate;
	private Date endDate;
	private BigInteger categoryId;
	private int expensesAmount;
	private List<ExpenseDTO> expenses;



}

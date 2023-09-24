package com.itcounts.model.dto.summary;

import com.itcounts.model.dto.expense.ExpenseDTO;
import java.math.BigInteger;
import java.sql.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SummaryDTO {

	private BigInteger userId;
	private Date startDate;
	private Date endDate;
	private double totalSpendAmount;
	private int totalExpensesAmount;
	private List<ExpenseDTO> expenses;

}

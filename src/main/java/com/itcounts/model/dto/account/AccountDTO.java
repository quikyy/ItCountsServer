package com.itcounts.model.dto.account;

import com.itcounts.model.dto.expense.ExpenseDTO;
import java.math.BigInteger;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AccountDTO {

	private BigInteger id;
	private BigInteger ownerId;
	List<ExpenseDTO> expenses;

}

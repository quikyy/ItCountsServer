package com.itcounts.model.dto.user;

import java.math.BigInteger;
import java.sql.Timestamp;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDTO {

	private BigInteger id;
	private String email;
	private String firstName;
	private String lastName;
	private Timestamp registerDate;
	private BigInteger ownerAccountId;
	private BigInteger guestAccountId;
}

package com.itcounts.model.dto.auth.request;

import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RegisterRequestDTO {

	private String email;
	private String password;
	private String firstName;
	private String lastName;
	private Date date;
	private String gender;
}

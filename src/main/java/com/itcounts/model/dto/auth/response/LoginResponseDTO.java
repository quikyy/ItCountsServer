package com.itcounts.model.dto.auth.response;

import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LoginResponseDTO {
	private String type = "Bearer ";
	private String token;
	private Timestamp expiresAt;

}

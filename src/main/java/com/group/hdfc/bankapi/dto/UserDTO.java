package com.group.hdfc.bankapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

	
	private String firstName;
	private String lastName;
	private String otherName;
	private String gender;
	private String address;
	private String stateOfOrigin;
	//private String accountNumber;
	//private BigDecimal accountBalance;
	private String email;
	private String phone;
	private String alternativePhone;
//	private String status;
	
}

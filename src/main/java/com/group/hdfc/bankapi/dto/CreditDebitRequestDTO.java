package com.group.hdfc.bankapi.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CreditDebitRequestDTO {

	private String accountNumber;
	private BigDecimal amount;
	
}

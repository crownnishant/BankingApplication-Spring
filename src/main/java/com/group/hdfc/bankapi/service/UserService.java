package com.group.hdfc.bankapi.service;

import com.group.hdfc.bankapi.dto.BankResponse;
import com.group.hdfc.bankapi.dto.CreditDebitRequestDTO;
import com.group.hdfc.bankapi.dto.EnquiryRequestDTO;
import com.group.hdfc.bankapi.dto.UserDTO;

public interface UserService {

	BankResponse createAccount(UserDTO userDto);
//bankresponse is the return type
	
	BankResponse balanceEnquiry(EnquiryRequestDTO enquiryRequestDto);
	
	String nameEnquiry(EnquiryRequestDTO enquiryRequestDTO);
	
	BankResponse creditAccount(CreditDebitRequestDTO request);
	
	/*BankResponse debitAccount(CreditDebitRequestDTO request);
	 * 
	 * */
	
	
}

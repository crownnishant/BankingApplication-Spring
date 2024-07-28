package com.group.hdfc.bankapi.service.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group.hdfc.bankapi.dto.AccountInfo;
import com.group.hdfc.bankapi.dto.BankResponse;
import com.group.hdfc.bankapi.dto.CreditDebitRequestDTO;
import com.group.hdfc.bankapi.dto.EmailDTO;
import com.group.hdfc.bankapi.dto.EnquiryRequestDTO;
import com.group.hdfc.bankapi.dto.UserDTO;
import com.group.hdfc.bankapi.entity.User;
import com.group.hdfc.bankapi.repository.UserRepository;
import com.group.hdfc.bankapi.service.EmailService;
import com.group.hdfc.bankapi.service.UserService;
import com.group.hdfc.bankapi.util.AccountUtil;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private EmailService emailService;
	
	@Override
	public BankResponse createAccount(UserDTO userDto) {
		/** Creating new account --saving it into DB
		*   check if user already has an account
		*
		*/
		if(userRepository.existsByEmail(userDto.getEmail())) {
			return BankResponse.builder()
					.responseCode(AccountUtil.ACCOUNT_EXISTS_CODE)
					.responseMessage(AccountUtil.ACCOUNT_EXISTS_MESSAGE)
					.accountInfo(null)
					.build();
		}
		User newUser=User.builder()
				
				.firstName(userDto.getFirstName())
				.lastName(userDto.getLastName())
				.otherName(userDto.getOtherName())
				.gender(userDto.getGender())
				.address(userDto.getAddress())
				.stateOfOrigin(userDto.getStateOfOrigin())
				.accountNumber(AccountUtil.generateAccountNumber())
				.accountBalance(BigDecimal.ZERO)
				.email(userDto.getEmail())
				.phone(userDto.getPhone())
				.alternativePhone(userDto.getAlternativePhone())
				.status("ACTIVE")
				
				.build();
		
		User saveUser=userRepository.save(newUser);
	//send email alert
	//object of EmailDTO
		
		EmailDTO emailDto=EmailDTO.builder()
				
				.recipient(saveUser.getEmail())
				.subject("ACCOUNT CREATION")
				.messageBody("CONGRATULATION ! YOUR ACCOUNT HAS BEEN SUCCESFULLY CREATED. \n Your Account details: \n"
						+ "Account Name: " +saveUser.getFirstName() + " "+saveUser.getLastName() + " "+ saveUser.getOtherName())
				
				.build();
		emailService.sendEmailAlert(emailDto);
		
		return BankResponse.builder()
				.responseCode(AccountUtil.ACCOUNT_CREATION_SUCCESS)
				.responseMessage(AccountUtil.ACCOUNT_CREATION_MESSAGE)
				.accountInfo(AccountInfo.builder()
						.accountBalance(saveUser.getAccountBalance())
						.accountNumber(saveUser.getAccountNumber())
						.accountName(saveUser.getFirstName() + " " +saveUser.getLastName() + " "+saveUser.getOtherName())
						.build())
				.build();
		
	}

//balance enquiry, name enquiry, credit, debit, transfer
	
	@Override
	public BankResponse balanceEnquiry(EnquiryRequestDTO enquiryRequestDto) {
		// check if provided account number exists in DB
		boolean isAccountExist=userRepository.existsByAccountNumber(enquiryRequestDto.getAccountNumber());
		if(!isAccountExist) {
			return BankResponse.builder()
					.responseCode(AccountUtil.ACCOUNT_DOES_NOT_EXIST_CODE)
					.responseMessage(AccountUtil.ACCOUNT_FAILED_MESSAGE)
					.accountInfo(null)
					.build();
		}
		User foundUser=userRepository.findByAccountNumber(enquiryRequestDto.getAccountNumber());
		return BankResponse.builder()
				.responseCode(AccountUtil.ACCOUNT_EXISTS_CODE)
				.responseMessage(AccountUtil.ACCOUNT_FOUND_MESSAGE)
				.accountInfo(AccountInfo.builder()
						.accountBalance(foundUser.getAccountBalance())
						.accountNumber(enquiryRequestDto.getAccountNumber())
						.accountName(foundUser.getFirstName() +" "+ foundUser.getLastName() + " "+foundUser.getOtherName())
						.build())
				
				.build();
		
	}

	@Override
	public String nameEnquiry(EnquiryRequestDTO enquiryRequestDTO) {
		// TODO Auto-generated method stub
		boolean isAccountExist=userRepository.existsByAccountNumber(enquiryRequestDTO.getAccountNumber());
		if(!isAccountExist) {
			return AccountUtil.ACCOUNT_FAILED_MESSAGE;
					
		}
		User foundUSer=userRepository.findByAccountNumber(enquiryRequestDTO.getAccountNumber());
		return foundUSer.getFirstName()+ " "+ foundUSer.getLastName()+ " " +foundUSer.getOtherName();
				
	}

	@Override
	public BankResponse creditAccount(CreditDebitRequestDTO request) {
		// check if the account exists
		boolean isAccountExist=userRepository.existsByAccountNumber(request.getAccountNumber());
		if(!isAccountExist) {
			return BankResponse.builder()
					.responseCode(AccountUtil.ACCOUNT_DOES_NOT_EXIST_CODE)
					.responseMessage(AccountUtil.ACCOUNT_FAILED_MESSAGE)
					.accountInfo(null)
					.build();
			
		}
		User userToCredit=userRepository.findByAccountNumber(request.getAccountNumber());
		userToCredit.setAccountBalance(userToCredit.getAccountBalance().add(request.getAmount()));
		userRepository.save(userToCredit);
		
		return BankResponse.builder()
				.responseCode(AccountUtil.ACCOUNT_CREDIT_SUCCESS_CODE)
				.responseMessage(AccountUtil.ACCOUNT_CREDIT_SUCCESS_MESSAGE)
				.accountInfo(AccountInfo.builder()
						.accountName(userToCredit.getFirstName()+ " "+userToCredit.getLastName()+ " "+userToCredit.getOtherName())
						.accountBalance(userToCredit.getAccountBalance())
						.accountNumber(request.getAccountNumber())
						.build())
				 
				.build();
	}

	/*@Override
	public BankResponse debitAccount(CreditDebitRequestDTO request) {
		// TODO Auto-generated method stub
		boolean isAccountExist=userRepository.existsByAccountNumber(request.getAccountNumber());
		if(!isAccountExist) {
			BankResponse.builder()
			.responseCode(AccountUtil.ACCOUNT_DOES_NOT_EXIST_CODE)
			.responseCode(AccountUtil.ACCOUNT_FAILED_MESSAGE)
			.accountInfo(null)
			.build();
		}
		
		User userToDebit=userRepository.findByAccountNumber(request.getAccountNumber());
		
	}*/
	

	

}

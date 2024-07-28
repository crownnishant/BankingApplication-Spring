package com.group.hdfc.bankapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group.hdfc.bankapi.dto.BankResponse;
import com.group.hdfc.bankapi.dto.CreditDebitRequestDTO;
import com.group.hdfc.bankapi.dto.EnquiryRequestDTO;
import com.group.hdfc.bankapi.dto.UserDTO;
import com.group.hdfc.bankapi.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/user")
@Tag(name="User Account Management APIs")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Operation(summary="create new user account")
	@PostMapping
	public BankResponse createAccount(@RequestBody UserDTO userDTO) {
		return userService.createAccount(userDTO);
	}
	
	@Operation(summary="get balance detail")
	@GetMapping("/balanceEnquiry")
	public BankResponse balanceEnquiry(@RequestBody EnquiryRequestDTO enquiryRequestDTO) {
		return userService.balanceEnquiry(enquiryRequestDTO);
	}
	
	@Operation(summary="get name detail from account number")
	@GetMapping("/nameEnquiry")
	public String nameEnquiry(@RequestBody EnquiryRequestDTO enquiryRequestDTO) {
		return userService.nameEnquiry(enquiryRequestDTO);
	}
	
	@Operation(summary="credit amount")
	@PostMapping("credit")
	public BankResponse creditAccount(@RequestBody CreditDebitRequestDTO creditDebitRequestDTO) {
		return userService.creditAccount(creditDebitRequestDTO);
	}
}

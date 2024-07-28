package com.group.hdfc.bankapi.util;

import java.time.Year;
import java.util.*;

public class AccountUtil {
	
//hardcoded value
	
	public static final String ACCOUNT_EXISTS_CODE="001";
	public static final String ACCOUNT_EXISTS_MESSAGE="This User already has an account created";
	public static final String ACCOUNT_CREATION_SUCCESS="002";
	public static final String ACCOUNT_CREATION_MESSAGE="account has been created successfully";
	public static final String ACCOUNT_DOES_NOT_EXIST_CODE="003";
	public static final String ACCOUNT_FAILED_MESSAGE="user with given account number does not exist";
	public static final String ACCOUNT_FOUND_MESSAGE="user found with the given account number";
	public static final String ACCOUNT_CREDIT_SUCCESS_CODE="005";
	public static final String ACCOUNT_CREDIT_SUCCESS_MESSAGE="amount has been credit successfully";
	
	public static String generateAccountNumber() {
	
		/**
		 * 2024 + randomSixdDigit
		 */

		Year currentYear=Year.now();
		
		int min=100000;
		int max=999999;
		
	//generate random number  between min and max
		
		int randomNumber=(int)Math.floor(Math.random() * (max-min+1)+min);
		
	//convert the current and randomNumber to Strings, then contatenate
		
		String year=String.valueOf(currentYear);
		String randNumber=String.valueOf(randomNumber);
		StringBuilder accountNumber= new StringBuilder();
		
		return accountNumber.append(year).append(randNumber).toString();
	}
}

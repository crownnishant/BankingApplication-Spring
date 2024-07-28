package com.group.hdfc.bankapi.service;

import com.group.hdfc.bankapi.dto.EmailDTO;

public interface EmailService {

	void sendEmailAlert(EmailDTO emailDto);
}

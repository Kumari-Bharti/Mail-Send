package com.example.Emailxyz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.mail.MessagingException;
@RestController
public class EmailController {
	  	@Autowired
	    private EmailSenderService emailSenderService;

	    @PostMapping("/sendEmails")
	    public String sendEmail(
	            @RequestParam int id
//	            @RequestParam String subject,
//	            @RequestParam String body,
//	            @RequestParam String fileName
	    ) {
	        try {
	            emailSenderService.sendMail(id);
	            return "Email sent successfully!";
	        } catch (MessagingException e) {
	            e.printStackTrace();
	            return "Failed to send email.";
	        }
	    }
	}

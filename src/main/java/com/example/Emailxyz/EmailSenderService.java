package com.example.Emailxyz;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailSenderService {
	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	private Userrepo userrepo;

	public void sendMail(int id) throws MessagingException {
		User data = userrepo.findById(id).get();
		String fileName = data.getPhoneno() + ".pdf";
		String subject = "Cagtering servie";
		String body = "Cagtering servie";
		String attachmentPath = "C:/Users/DeLL/Downloads/" + fileName;
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setTo(data.getEmail());
		helper.setSubject(subject);
		helper.setText(body);

		// Add attachment if attachment path is provided
		if (attachmentPath != null && !attachmentPath.isEmpty()) {
			File file = new File(attachmentPath);
			if (file.exists()) {
				helper.addAttachment(file.getName(), file);
			} else {
				throw new MessagingException("Attachment file not found at the specified path: " + attachmentPath);
			}
		}

		javaMailSender.send(message);
	}
}


package in.uday.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class EmailUtils {

	private static final Logger logger = LoggerFactory.getLogger(EmailUtils.class);

	@Autowired
	private JavaMailSender mailsender;

	public boolean sendEmail(String subject, String body, String to) {

		boolean isSent = true;

		try {

			MimeMessage mimeMessage = mailsender.createMimeMessage();

			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);

			helper.setSubject(subject);
			helper.setText(body, true);
			helper.setTo(to);
			mailsender.send(mimeMessage);
			isSent = true;

		} catch (Exception e) {
			logger.error("Error sending email", e);
		}

		return isSent;
	}
}

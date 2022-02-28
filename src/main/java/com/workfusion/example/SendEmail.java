package com.workfusion.example;

import java.io.IOException;
import java.util.Collections;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import com.workfusion.component.mail.smtp.EmailSmtpSender;
import com.workfusion.component.mail.smtp.EmailSmtpSenderConfig;

public class SendEmail {

    public static void main(String[] args) {
        SendEmail test = new SendEmail();
        test.run();
    }

    public void run(){
        EmailSmtpSenderConfig config = new EmailSmtpSenderConfig();
        config.setSmtpHost("host");
        config.setUser("username@email.xom");
        config.setPassword("pass");
        config.setSmtpPort("587");

        EmailSmtpSender emailSmtpSender = new EmailSmtpSender(config);
        try {
            MimeMessage email = emailSmtpSender.createEmail("from@email.com",
                    Collections.singletonList("to@email.com"),
                    null,
                    null,
                    "Test email subject",
                    "Body for <b>test</b> email",
                    null);

            emailSmtpSender.send(email);
        } catch (MessagingException | IOException e) {
            throw new ExampleException("Can't send email", e);
        }
    }
}

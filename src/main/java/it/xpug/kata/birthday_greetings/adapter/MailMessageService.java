package it.xpug.kata.birthday_greetings.adapter;

import it.xpug.kata.birthday_greetings.domain.model.Message;
import it.xpug.kata.birthday_greetings.domain.ports.IMessageService;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailMessageService implements IMessageService {

    private final String smtpHost;
    private final int smtpPort;

    public MailMessageService(String smtpHost, int smtpPort) {
        this.smtpHost = smtpHost;
        this.smtpPort = smtpPort;
    }

    @Override
    public void send(Message message) throws Exception {
        // Create a mail session
        java.util.Properties props = new java.util.Properties();
        props.put("mail.smtp.host", smtpHost);
        props.put("mail.smtp.port", "" + smtpPort);
        Session session = Session.getInstance(props, null);

        // Construct the message
        javax.mail.Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(message.getSender()));
        msg.setRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(message.getRecipient()));
        msg.setSubject(message.getSubject());
        msg.setText(message.getBody());

        // Send the message
        Transport.send(msg);
    }
}

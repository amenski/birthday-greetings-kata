package it.xpug.kata.birthday_greetings.domain.model;

import java.util.Objects;

public class Message {

    private final String sender;
    private final String recipient;
    private final String subject;
    private final String body;

    public Message(String sender, String recipient, String subject, String body) {
        this.sender = sender;
        this.recipient = recipient;
        this.subject = subject;
        this.body = body;
    }

    public String getSender() {
        return sender;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getSubject() {
        return subject;
    }

    public String getBody() {
        return body;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return Objects.equals(sender, message.sender) && Objects.equals(recipient, message.recipient) && Objects.equals(subject, message.subject) && Objects.equals(body, message.body);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sender, recipient, subject, body);
    }
}

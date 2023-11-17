package it.xpug.kata.birthday_greetings.adapter;

import it.xpug.kata.birthday_greetings.domain.model.Message;

public interface IMessageService {

    void send(Message message) throws Exception;
}

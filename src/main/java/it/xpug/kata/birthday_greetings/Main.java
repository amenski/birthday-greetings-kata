package it.xpug.kata.birthday_greetings;

import it.xpug.kata.birthday_greetings.adapter.IMessageService;
import it.xpug.kata.birthday_greetings.adapter.MailMessageService;
import it.xpug.kata.birthday_greetings.domain.model.XDate;

public class Main {

	public static void main(String[] args) throws Exception {
		IMessageService messageService = new MailMessageService("localhost", 25);
		BirthdayService service = new BirthdayService(messageService);
		service.sendGreetings("employee_data.txt", new XDate("2008/10/08"));
	}

}

package it.xpug.kata.birthday_greetings;

import it.xpug.kata.birthday_greetings.adapter.FileEmployeeRepository;
import it.xpug.kata.birthday_greetings.domain.ports.IEmployeeRepository;
import it.xpug.kata.birthday_greetings.domain.ports.IMessageService;
import it.xpug.kata.birthday_greetings.adapter.MailMessageService;
import it.xpug.kata.birthday_greetings.domain.model.XDate;

public class Main {

	public static void main(String[] args) throws Exception {
		IMessageService messageService = new MailMessageService("localhost", 25);
		IEmployeeRepository employeeRepository = new FileEmployeeRepository( new FileParser("employee_data.txt"));
		BirthdayService service = new BirthdayService(messageService, employeeRepository);
		service.sendGreetings( new XDate("2008/10/08"));
	}

}

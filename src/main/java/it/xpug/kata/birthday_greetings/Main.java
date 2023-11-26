package it.xpug.kata.birthday_greetings;

import it.xpug.kata.birthday_greetings.adapter.EmployeeFileRepository;
import it.xpug.kata.birthday_greetings.domain.ports.IEmployeeRepository;
import it.xpug.kata.birthday_greetings.domain.ports.IMessageService;
import it.xpug.kata.birthday_greetings.adapter.EmailMessageService;
import it.xpug.kata.birthday_greetings.domain.model.XDate;
import it.xpug.kata.birthday_greetings.domain.usecase.BirthDayGreetingsUseCase;

public class Main {

	public static void main(String[] args) throws Exception {
		IMessageService messageService = new EmailMessageService("localhost", 25);
		IEmployeeRepository employeeRepository = new EmployeeFileRepository( new FileParser("employee_data.txt"));
		BirthDayGreetingsUseCase service = new BirthDayGreetingsUseCase(messageService, employeeRepository);
		service.sendGreetings( new XDate("2008/10/08"));
	}
}

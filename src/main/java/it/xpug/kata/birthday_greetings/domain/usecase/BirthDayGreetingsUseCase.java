package it.xpug.kata.birthday_greetings.domain.usecase;

import it.xpug.kata.birthday_greetings.domain.model.Employee;
import it.xpug.kata.birthday_greetings.domain.model.Message;
import it.xpug.kata.birthday_greetings.domain.model.XDate;
import it.xpug.kata.birthday_greetings.domain.ports.IEmployeeRepository;
import it.xpug.kata.birthday_greetings.domain.ports.IMessageService;

import java.util.List;

public class BirthDayGreetingsUseCase {

	private final IMessageService messageService;
	private final IEmployeeRepository employeeRepository;

	public BirthDayGreetingsUseCase(IMessageService messageService, IEmployeeRepository employeeRepository) {
		this.messageService = messageService;
		this.employeeRepository = employeeRepository;
	}

	public void sendGreetings(XDate xDate) throws Exception {
		List<Employee> employeeList = employeeRepository.findEmployeesBornOn(xDate);
		for(Employee employee: employeeList) {
			String body = String.format("Happy Birthday, dear %s!", employee.getFirstName());
			this.messageService.send(new Message("sender@here.com",	employee.getEmail(), "Happy Birthday!", body));
		}
	}
}
package it.xpug.kata.birthday_greetings;

import it.xpug.kata.birthday_greetings.domain.ports.IMessageService;
import it.xpug.kata.birthday_greetings.domain.model.Employee;
import it.xpug.kata.birthday_greetings.domain.model.Message;
import it.xpug.kata.birthday_greetings.domain.model.XDate;

import java.io.BufferedReader;
import java.io.FileReader;

public class BirthdayService {

	private final IMessageService messageService;

	public BirthdayService(IMessageService messageService) {
		this.messageService = messageService;
	}

	public void sendGreetings(String fileName, XDate xDate) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader(fileName));
		String str = "";
		str = in.readLine(); // skip header
		while ((str = in.readLine()) != null) {
			String[] employeeData = str.split(", ");
			Employee employee = new Employee(employeeData[1], employeeData[0], employeeData[2], employeeData[3]);
			if (employee.isBirthday(xDate)) {
				String recipient = employee.getEmail();
				String body = String.format("Happy Birthday, dear %s!", employee.getFirstName());
				String subject = "Happy Birthday!";
				Message message = new Message("sender@here.com", recipient, subject, body);
				this.messageService.send(message);
			}
		}
	}
}
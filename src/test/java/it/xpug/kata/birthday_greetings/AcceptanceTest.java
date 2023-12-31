package it.xpug.kata.birthday_greetings;

import static org.junit.Assert.*;

import it.xpug.kata.birthday_greetings.adapter.EmployeeFileRepository;
import it.xpug.kata.birthday_greetings.adapter.EmailMessageService;
import it.xpug.kata.birthday_greetings.domain.model.XDate;
import it.xpug.kata.birthday_greetings.domain.usecase.BirthDayGreetingsUseCase;
import org.junit.*;

import com.dumbster.smtp.*;


public class AcceptanceTest {

	private static final int NONSTANDARD_PORT = 9999;
	private BirthDayGreetingsUseCase birthDayGreetingsUseCase;
	private SimpleSmtpServer mailServer;

	@Before
	public void setUp() {
		mailServer = SimpleSmtpServer.start(NONSTANDARD_PORT);
		birthDayGreetingsUseCase = new BirthDayGreetingsUseCase(new EmailMessageService("", NONSTANDARD_PORT), new EmployeeFileRepository( new FileParser("employee_data.txt")));
	}

	@After
	public void tearDown() throws Exception {
		mailServer.stop();
		Thread.sleep(200);
	}

	@Test
	public void willSendGreetings_whenItsSomebodysBirthday() throws Exception {

		birthDayGreetingsUseCase.sendGreetings(new XDate("2008/10/08"));

		assertEquals("message not sent?", 1, mailServer.getReceivedEmailSize());
		SmtpMessage message = (SmtpMessage) mailServer.getReceivedEmail().next();
		assertEquals("Happy Birthday, dear John!", message.getBody());
		assertEquals("Happy Birthday!", message.getHeaderValue("Subject"));
		String[] recipients = message.getHeaderValues("To");
		assertEquals(1, recipients.length);
		assertEquals("john.doe@foobar.com", recipients[0].toString());
	}

	@Test
	public void willNotSendEmailsWhenNobodysBirthday() throws Exception {
		birthDayGreetingsUseCase.sendGreetings(new XDate("2008/01/01"));

		assertEquals("what? messages?", 0, mailServer.getReceivedEmailSize());
	}
}

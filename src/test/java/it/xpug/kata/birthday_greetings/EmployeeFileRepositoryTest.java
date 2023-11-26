package it.xpug.kata.birthday_greetings;

import it.xpug.kata.birthday_greetings.adapter.EmployeeFileRepository;
import it.xpug.kata.birthday_greetings.domain.model.XDate;
import it.xpug.kata.birthday_greetings.domain.ports.IEmployeeRepository;
import it.xpug.kata.birthday_greetings.domain.model.Employee;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.*;

public class EmployeeFileRepositoryTest {

    private final FileParser fileParser = Mockito.mock(FileParser.class);
    private final IEmployeeRepository employeeRepository = new EmployeeFileRepository(fileParser);

    @Test
    public void findEmployeesBornOn_empty() {
        List<Employee> employees = employeeRepository.findEmployeesBornOn(null);

        assertTrue(employees.isEmpty());
    }

    @Test
    public void findEmployeesBornOn_verify_fileParser() throws Exception {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("John", "Doe", "1982/10/08", "mock@mock.com"));
        when(fileParser.getEmployeeList()).thenReturn(employees);

        employeeRepository.findEmployeesBornOn(new XDate());

        verify(fileParser, times(1)).getEmployeeList();
    }

    @Test
    public void findEmployeesBornOn_notEmpty() throws Exception {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("John", "Doe", "1982/10/08", "mock@mock.com"));
        when(fileParser.getEmployeeList()).thenReturn(employees);

        List<Employee> result = employeeRepository.findEmployeesBornOn(new XDate("1982/10/08"));

        verify(fileParser, times(1)).getEmployeeList();
        assertFalse(result.isEmpty());
    }
}

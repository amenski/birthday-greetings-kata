package it.xpug.kata.birthday_greetings.adapter;

import it.xpug.kata.birthday_greetings.FileParser;
import it.xpug.kata.birthday_greetings.domain.model.Employee;
import it.xpug.kata.birthday_greetings.domain.model.XDate;
import it.xpug.kata.birthday_greetings.domain.ports.IEmployeeRepository;

import java.util.List;
import java.util.stream.Collectors;

public class EmployeeFileRepository implements IEmployeeRepository {

    private final FileParser fileParser;

    public EmployeeFileRepository(FileParser fileParser) {
        this.fileParser = fileParser;
    }

    @Override
    public List<Employee> findEmployeesBornOn(XDate xDate) {
       List<Employee> employeeList = fileParser.getEmployeeList();
       return employeeList.stream()
               .filter(employee -> employee.isBirthday(xDate))
               .collect(Collectors.toList());
    }
}

package it.xpug.kata.birthday_greetings.domain.ports;

import it.xpug.kata.birthday_greetings.domain.model.Employee;
import it.xpug.kata.birthday_greetings.domain.model.XDate;

import java.util.List;

/**
 * The IEmployeeRepository interface would be part of the Use Cases layer. <br>
 * <p>
 * This layer defines the application-specific business rules and use cases.
 * The repository interface is an abstraction that defines how the application
 * interacts with external data sources without specifying the details of the implementation.
 */
public interface IEmployeeRepository {

    List<Employee> findEmployeesBornOn(XDate xDate);

}

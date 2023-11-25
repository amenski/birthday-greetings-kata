package it.xpug.kata.birthday_greetings;

import it.xpug.kata.birthday_greetings.domain.model.Employee;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class FileParser {

    private final String fileName;

    public FileParser(String fileName) {
        this.fileName = fileName;
    }

    public List<Employee> getEmployeeList() {
        List<Employee> employeeList = new ArrayList<>();
        try {
            BufferedReader in = new BufferedReader(new FileReader(fileName));
            String str = "";
            str = in.readLine(); // skip header
            while ((str = in.readLine()) != null) {
                String[] employeeData = str.split(", ");
                employeeList.add(new Employee(employeeData[1], employeeData[0], employeeData[2], employeeData[3]));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return employeeList;
    }
}

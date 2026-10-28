package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Pattern;

public class PayrollCalculatorApp {

    public static void main(String[] args) {
        String fileName = "src/main/resources/employees.csv";

        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line = bufferedReader.readLine(); // throw away header
            line = bufferedReader.readLine();
            while (line != null) {
                Employee employee = parseEmployee(line);

                System.out.printf("%d  %s  %f\n", employee.getEmployeeId(), employee.getName(), employee.getGrossPay());

                line = bufferedReader.readLine();
            }

        } catch (FileNotFoundException e) {
            System.out.println("Sorry, mate, that file doesn't exist: " + fileName);
        } catch (IOException e) {
            System.out.println("Not sure what's up, some kinda I/O problem 🤷🏼‍♂️");
        }

    }

    /**
     * Convert a CSV of employee info into an object of type Employee
     *
     * @param employeeCsv line like: "20|Ezra Aiden|17|16.75"
     * @return An employee object constructed from the line
     */
    private static Employee parseEmployee(String employeeCsv) {
        String[] lineParts = employeeCsv.split(Pattern.quote("|"));

        int id = Integer.parseInt(lineParts[0]);
        String name = lineParts[1];
        double hoursWorked = Double.parseDouble(lineParts[2]);
        double payRate = Double.parseDouble(lineParts[3]);

        Employee employee = new Employee(id, name, hoursWorked, payRate);
        return employee;
    }
}

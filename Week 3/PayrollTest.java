import java.util.List;
import java.util.Scanner;

public class PayrollTest {

    public static int getValidIntInput(Scanner scanner, String prompt) {
        int input;
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextInt()) {
                input = scanner.nextInt();
                if (input < 0) { // Ensure input is greater than 0
                    System.out.println("Huyyy! The number must be greater than zero. Try again.");
                } else {
                    break;
                }
            } else {
                System.out.println("Huyyy! That’s not a valid number. Please enter a positive integer.");
                scanner.next();
            }
        }
        return input;
    }

    public static double getValidDoubInput(Scanner scanner, String prompt) {
        double input;
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextDouble()) {
                input = scanner.nextDouble();
                if (input < 0) { // Ensure input is greater than 0
                    System.out.println("Huyyy! The number must be greater than zero. Try again.");
                } else {
                    break;
                }
            } else {
                System.out.println("Huyyy! That’s not a valid number. Please enter a positive decimal number.");
                scanner.next();
            }
        }
        return input;
    }

    public static String getValidEmployeeType(Scanner scanner) {
        String type;
        while (true) {
            System.out.print("Enter Employee Type (fulltime/parttime/contractual): ");
            type = scanner.nextLine().toLowerCase();
            if (type.equals("fulltime") || type.equals("parttime") || type.equals("contractual")) {
                break;
            } else {
                System.out.println("Error... Please enter a valid employee type (fulltime, parttime, or contractual) :)");
            }
        }
        return type;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PayrollSystem payrollSystem = new PayrollSystem();

        while (true) {
            System.out.println("\n*******************************");
            System.out.println("Heyts Heyt You Payroll System Menu");
            System.out.println("*******************************");
            System.out.println("1. Add Employee");
            System.out.println("2. Display Employees");
            System.out.println("3. Update Employee");
            System.out.println("4. Remove Employee");
            System.out.println("5. Search Employee by Name");
            System.out.println("6. Filter Employees by Type");
            System.out.println("7. Exit");
            int choice = getValidIntInput(scanner, "Enter your choice (1-7): ");
            scanner.nextLine(); // Clear the newline character

            switch (choice) {
                case 1: // Adding Employees
                    System.out.println("\n*******************************");
                    String type = getValidEmployeeType(scanner);
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();
                    int hoursWorked = getValidIntInput(scanner, "Enter Hours Worked: ");
                    double hourlyRate = getValidDoubInput(scanner, "Enter Hourly Rate: ");

                    if (type.equalsIgnoreCase("fulltime")) {
                        double overtimeHours;
                        do {
                            overtimeHours = getValidDoubInput(scanner, "Enter Overtime Hours: ");
                            if (overtimeHours < 0) {
                                System.out.println("Huyyy! Overtime hours cannot be negative. Try again.");
                            }
                        } while (overtimeHours < 0);
                        payrollSystem.addEmployee(new FullTimeEmployee(name, hoursWorked, hourlyRate, overtimeHours));
                    } else if (type.equalsIgnoreCase("parttime")) {
                        payrollSystem.addEmployee(new PartTimeEmployee(name, hoursWorked, hourlyRate));
                    } else {
                        payrollSystem.addEmployee(new ContractualEmployee(name, hoursWorked, hourlyRate));
                    }
                    System.out.println("Employee added successfully!");
                    System.out.println("*******************************\n");
                    break;

                case 2:
                    System.out.println("\n*******************************");
                    System.out.println("Employee Payroll");
                    System.out.println("*******************************");
                    payrollSystem.displayEmployees();
                    System.out.println("*******************************\n");
                    break;

                case 3: // Update Employee
                    System.out.println("\n*******************************");
                
                    int indexToUpdate;
                    while (true) {
                        indexToUpdate = getValidIntInput(scanner, "Enter Employee Index to Update (Employee 1 is equal to 0 index): ");
                        if (indexToUpdate < 0 || indexToUpdate >= payrollSystem.getEmployeeCount()) {
                            System.out.println("Invalid index. Please enter a valid index.");
                        } else {
                            break; // Valid index, break the loop
                        }
                    }
                    scanner.nextLine(); // Clear the buffer
                    System.out.print("Enter New Name: ");
                    String newName = scanner.nextLine();
                    int newHoursWorked;
                    do {
                        newHoursWorked = getValidIntInput(scanner, "Enter New Hours Worked: ");
                        if (newHoursWorked < 0) {
                            System.out.println("Huyyy! The number must be greater than zero. Try again.");
                        }
                    } while (newHoursWorked < 0);
                    double newHourlyRate;
                    do {
                        newHourlyRate = getValidDoubInput(scanner, "Enter New Hourly Rate: ");
                        if (newHourlyRate < 0) {
                            System.out.println("Huyyy! The number must be greater than zero. Try again.");
                        }
                    } while (newHourlyRate < 0);
                    double newOvertimeHours = 0;
                    if (payrollSystem.getEmployees().get(indexToUpdate) instanceof FullTimeEmployee) {
                        do {
                            newOvertimeHours = getValidDoubInput(scanner, "Overtime Hours (if full-time): ");
                            if (newOvertimeHours < 0) {
                                System.out.println("Huyyy! Overtime hours cannot be negative. Try again.");
                            }
                        } while (newOvertimeHours < 0);
                    }
                    payrollSystem.updateEmployee(indexToUpdate, newName, newHoursWorked, newHourlyRate, newOvertimeHours);
                    System.out.println("Employee updated successfully!");
                    System.out.println("*******************************\n");
                    break;

                case 4: // Removing Employees
                    System.out.println("\n*******************************");
                    int indexToRemove = getValidIntInput(scanner, "Enter Employee Index to Remove (Employee 1 is equal to 0 index): ");
                    if (indexToRemove < 0 || indexToRemove >= payrollSystem.getEmployeeCount()) { // Check for valid index
                        System.out.println("Invalid index. Please enter a valid index.");
                        break;
                    }
                    payrollSystem.removeEmployee(indexToRemove);
                    System.out.println("*******************************\n");
                    break;

                case 5: // Searching Employees
                    System.out.println("\n*******************************");
                    String searchName;
                    while (true) {
                        System.out.print("Enter Employee Name to Search: ");
                        searchName = scanner.nextLine().trim();
                        if (searchName.isEmpty()) {
                            System.out.println("Invalid input. Please enter a name to search.");
                        } else {
                            break; // Valid input, break the loop
                        }
                    }
                    Employee foundEmployee = payrollSystem.searchEmployeeByName(searchName);
                    if (foundEmployee != null) {
                        System.out.println(foundEmployee.getName() + ": " + foundEmployee.calculatePay());
                    } else {
                        System.out.println("Employee not found! Please try again :)");
                    }
                    System.out.println("*******************************\n");
                    break;

                case 6: // Filtering Employees
                    System.out.println("\n*******************************");
                    String filterType = getValidEmployeeType(scanner); // Get valid employee type (fulltime, parttime, or contractual)
                    
                    // Filter the employees by the selected type
                    List<Employee> filteredEmployees = payrollSystem.filterEmployeesByType(filterType);

                    System.out.println("Filtered Employees:");
                    if (filteredEmployees.isEmpty()) {
                        // If no employees of the selected type are found, show this message
                        System.out.println("No employees found for type: " + filterType);
                    } else {
                        // If employees of the selected type are found, display them
                        for (Employee employee : filteredEmployees) {
                            System.out.println(employee.getName() + ": " + employee.calculatePay());
                        }
                    }
                    
                    System.out.println("*******************************\n");
                    break;

                case 7: // Exiting
                    System.out.println("Exiting... Thank you for using Heyts Hey You Payroll System!!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please select a valid option :)");
            }
        }
    }
}
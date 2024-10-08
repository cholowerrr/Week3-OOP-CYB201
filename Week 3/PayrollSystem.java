import java.util.ArrayList;
import java.util.List;

public class PayrollSystem {
    private List<Employee> employees;

    public PayrollSystem() {
        employees = new ArrayList<>();
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public int getEmployeeCount() {
        return employees.size();
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void displayEmployees() {
        for (Employee emp : employees) {
            System.out.println(emp.getName() + ": " + emp.calculatePay());
        }
    }
    
    public void updateEmployee(int index, String name, int hoursWorked, double hourlyRate, double overtimeHours) {
        if (index >= 0 && index < employees.size()) {
            Employee emp = employees.get(index);
            emp.setName(name);
            emp.setHoursWorked(hoursWorked);
            emp.setHourlyRate(hourlyRate);
            if (emp instanceof FullTimeEmployee) {
                ((FullTimeEmployee) emp).setOvertimeHours(overtimeHours);
            }
        } else {
            System.out.println("Error: Employee index out of bounds.");
        }
    }

    public void removeEmployee(int index) {
        if (index >= 0 && index < employees.size()) {
            employees.remove(index);
            System.out.println("Employee removed successfully.");
        } else {
            System.out.println("Error: Employee index out of bounds.");
        }
    }

    public Employee searchEmployeeByName(String name) {
        for (Employee emp : employees) {
            if (emp.getName().equalsIgnoreCase(name)) {
                return emp;
            }
        }
        return null;
    }

    public List<Employee> filterEmployeesByType(String type) {
        List<Employee> filteredEmployees = new ArrayList<>();
        for (Employee employee : employees) {
            if (type.equalsIgnoreCase("fulltime") && employee instanceof FullTimeEmployee) {
                filteredEmployees.add(employee);
            } else if (type.equalsIgnoreCase("parttime") && employee instanceof PartTimeEmployee) {
                filteredEmployees.add(employee);
            } else if (type.equalsIgnoreCase("contractual") && employee instanceof ContractualEmployee) {
                filteredEmployees.add(employee);
            }
        }
        return filteredEmployees;
    }
}
public class PartTimeEmployee extends Employee {
    public PartTimeEmployee(String name, int hoursWorked, double hourlyRate) {
        super(name, hoursWorked, hourlyRate);
    }

    @Override
    public double calculatePay() {
        return hoursWorked * hourlyRate; 
    }
}
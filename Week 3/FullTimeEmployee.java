public class FullTimeEmployee extends Employee {
    private double overtimeHours;

    public FullTimeEmployee(String name, int hoursWorked, double hourlyRate, double overtimeHours) {
        super(name, hoursWorked, hourlyRate);
        this.overtimeHours = overtimeHours;
    }

    @Override
    public double calculatePay() {
        double basePay = hoursWorked * hourlyRate;
        double overtimePay = overtimeHours * hourlyRate * 1.5;
        return basePay + overtimePay;
    }

    public double getOvertimeHours() {
        return overtimeHours;
    }

    public void setOvertimeHours(double overtimeHours) {
        this.overtimeHours = overtimeHours;
    }
}
public class Employee {
    private String name;
    private double hours;
    private Position position;
    private int employeeId;

    public Employee(String name, double hours, Position position, int employeeId) {
        this.name = name;
        this.hours = hours;
        this.position = position;
        this.employeeId = employeeId;
    }

    public double getHours() {
        return hours;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public Position getPosition() {
        return position;
    }

    public String getName() {
        return name;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public void setHours(double hours) {
        this.hours = hours;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}

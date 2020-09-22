import java.time.LocalTime;

public class Shift {
    private LocalTime startTime;
    private LocalTime endTime;
    private Employee employee;
    private Position position;

    public Shift(LocalTime startTime, LocalTime endTime, Position position){
        this.startTime = startTime;
        this.endTime = endTime;
        this.position = position;
    }

    public void assignEmployee(Employee employee){
        this.employee = employee;
        employee.incrementHours(startTime.getHour() - endTime.getHour());
    }

    public Employee getEmployee() {
        return employee;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public Position getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return position + ": " + startTime;
    }
}

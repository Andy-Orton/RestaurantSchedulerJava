import java.util.LinkedList;
import java.util.List;

public class Restaurant {
    private List<Employee> employeeList;
    private Schedule schedule;

    public Restaurant(){
        employeeList = new LinkedList<>();
    }

    public void createSchedule(){
        schedule = new Schedule();
    }
}

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Stream;

public class Restaurant {
    private List<Employee> employeeList;
    private Schedule schedule;
    private List<Shift> standardWeekDayShifts;
    private List<Shift> standardWeekendShifts;

    public Restaurant(){
        standardWeekDayShifts = new LinkedList<>();
        standardWeekendShifts = new LinkedList<>();
        employeeList = new LinkedList<>();
        LocalTime StartingTime = LocalTime.of(9, 0, 0);
        LocalTime ClosingTime = LocalTime.of(22, 0, 0);
        setStartingData(StartingTime, ClosingTime);
        for(int i = 0; i < employeeList.size(); i++){
            System.out.println(employeeList.get(i).getName() +" " + employeeList.get(i).getHours());
        }
    }

    private void setStartingData(LocalTime startingTime, LocalTime closingTime) {
        Shift startingCook = new Shift(startingTime, LocalTime.of(3, 0, 0), Position.COOK);
        Shift startingServer = new Shift(startingTime, LocalTime.of(3, 0, 0), Position.SERVER);
        Shift startingManager = new Shift(startingTime, LocalTime.of(3, 0, 0), Position.MANAGER);
        Shift endingCook = new Shift(LocalTime.of(3, 0, 0), closingTime, Position.COOK);
        Shift endingServer = new Shift(LocalTime.of(3, 0, 0), closingTime, Position.SERVER);
        Shift endingManager = new Shift(LocalTime.of(3, 0, 0), closingTime, Position.MANAGER);
        Shift endingDish = new Shift(LocalTime.of(3, 0, 0), closingTime, Position.DISHWASHER);
        standardWeekDayShifts.add(startingCook);
        standardWeekDayShifts.add(startingCook);
        standardWeekDayShifts.add(startingServer);
        standardWeekDayShifts.add(startingManager);
        standardWeekDayShifts.add(endingManager);
        standardWeekDayShifts.add(endingServer);
        standardWeekDayShifts.add(endingCook);
        standardWeekDayShifts.add(endingCook);
        standardWeekDayShifts.add(endingDish);
        standardWeekendShifts.add(startingCook);
        standardWeekendShifts.add(startingCook);
        standardWeekendShifts.add(startingServer);
        standardWeekendShifts.add(startingManager);
        standardWeekendShifts.add(endingManager);
        standardWeekendShifts.add(endingServer);
        standardWeekendShifts.add(endingCook);
        standardWeekendShifts.add(endingCook);
        standardWeekendShifts.add(endingCook);
        standardWeekendShifts.add(endingDish);
        Employee startingManagerEMP = new Employee("Amie", 0, Position.MANAGER, 0);
        Employee startingCookEMP = new Employee("Andy", 0, Position.COOK, 1);
        Employee secondStartingCookEMP = new Employee("Haley", 0, Position.COOK, 2);
        Employee startingServerEMP = new Employee("Heather", 0, Position.SERVER, 3);
        Employee endingCookEMP = new Employee("Nick", 0, Position.COOK, 4);
        Employee endingDishEMP = new Employee("X", 0, Position.DISHWASHER, 5);
        Employee endingManagerEMP = new Employee("Shaun", 0, Position.MANAGER, 6);
        Employee endingServerEMP = new Employee("Carrie", 0, Position.SERVER, 7);
        employeeList.add(startingCookEMP);
        employeeList.add(startingManagerEMP);
        employeeList.add(secondStartingCookEMP);
        employeeList.add(startingServerEMP);
        employeeList.add(endingCookEMP);
        employeeList.add(endingDishEMP);
        employeeList.add(endingManagerEMP);
        employeeList.add(endingServerEMP);
    }

    public List<Shift> getStandardWeekDayShifts() {
        return standardWeekDayShifts;
    }

    public List<Shift> getStandardWeekendShifts() {
        return standardWeekendShifts;
    }

    public void createSchedule(){
        schedule = new Schedule(LocalDate.now(), standardWeekDayShifts, standardWeekendShifts);
        fillSchedule();
    }

    public Schedule getSchedule(){
        return schedule;
    }

    public void fillSchedule(){
        for(int i = 0; i < schedule.days.size(); i++){
            for(int s = 0; s < schedule.days.get(i).getShifts().size(); s++){
                List<Employee> possibleEmployees = GetEmployeeByType(employeeList, schedule.days.get(i).getShifts().get(s).getPosition());
                Collections.sort(possibleEmployees);
                schedule.days.get(i).getShifts().get(s).assignEmployee(possibleEmployees.get(0));
            }
        }
    }




    private List<Employee> GetEmployeeByType(List<Employee> employeeList, Position position) {
        List<Employee> possible = new LinkedList<>();
        for(int i = 0; i < employeeList.size(); i++){
            if(employeeList.get(i).getPosition() == position){
                possible.add(employeeList.get(i));
            }
        }
        return possible;
    }
}

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Stream;

public class Restaurant {
    private List<Employee> employeeList;
    private Schedule schedule;
    private List<Shift> standardWeekDayShifts;
    private List<Shift> standardWeekendShifts;
    private LocalTime startingTime;
    private LocalTime closingTime;
    private Employee NoPossibleEmployee;

    public List<Shift> getStandardWeekendShifts() {
        return standardWeekendShifts;
    }

    public List<Shift> getStandardWeekDayShifts() {
        return standardWeekDayShifts;
    }

    public void setClosingTime(LocalTime closingTime) {
        this.closingTime = closingTime;
    }

    public void setStartingTime(LocalTime startingTime) {
        this.startingTime = startingTime;
    }

    public void addWeekDayShift(Shift s){
        standardWeekDayShifts.add(s);
    }

    public void addWeekendShift(Shift s){
        standardWeekendShifts.add(s);
    }

    public Restaurant(){
        standardWeekDayShifts = new LinkedList<>();
        standardWeekendShifts = new LinkedList<>();
        employeeList = new LinkedList<>();
        NoPossibleEmployee = new Employee("No Employee Found", 0, Position.MANAGER, -1);
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void displaySchedule() {
        for(int i = 0; i < schedule.days.size(); i++){
            System.out.println(schedule.days.get(i).getDay().getDayOfWeek());
            for(int j = 0; j < schedule.days.get(i).getShifts().size(); j++){
                var x = schedule.days.get(i).getShifts().get(j);
                System.out.println(x.getStartTime().toString() + ": " + x.getEmployee());
            }
        }
    }

    private void setStartingData() {
        startingTime = LocalTime.of(9,0,0);
        closingTime = LocalTime.of(15,0,0);
        Shift startingCook = new Shift(startingTime, LocalTime.of(3, 0, 0), Position.COOK);
        Shift startingServer = new Shift(startingTime, LocalTime.of(3, 0, 0), Position.SERVER);
        Shift startingManager = new Shift(startingTime, LocalTime.of(3, 0, 0), Position.MANAGER);
        Shift endingCook = new Shift(LocalTime.of(15, 0, 0), closingTime, Position.COOK);
        Shift endingServer = new Shift(LocalTime.of(15, 0, 0), closingTime, Position.SERVER);
        Shift endingManager = new Shift(LocalTime.of(15, 0, 0), closingTime, Position.MANAGER);
        Shift endingDish = new Shift(LocalTime.of(15, 0, 0), closingTime, Position.DISHWASHER);
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

    public void createSchedule(LocalDate startingDate, LocalDate endingDate){
        schedule = new Schedule(startingDate, endingDate,standardWeekDayShifts, standardWeekendShifts);
    }

    public Schedule getSchedule(){
        return schedule;
    }

    public void fillSchedule(){
        for(int i = 0; i < schedule.days.size(); i++){
            for(int s = 0; s < schedule.days.get(i).getShifts().size(); s++){
                int EmployeeChosen = 0;
                List<Employee> possibleEmployees = getEmployeeByType(employeeList, schedule.days.get(i).getShifts().get(s).getPosition());
                sortEmployees(possibleEmployees);
                if(possibleEmployees.size() == 0){
                    schedule.days.get(i).getShifts().get(s).assignEmployee(NoPossibleEmployee);
                    break;
                }
                schedule.days.get(i).getShifts().get(s).assignEmployee(possibleEmployees.get(0));
            }
        }
    }

    private void sortEmployees(List<Employee> possibleEmployees) {
        int i, j, min_idx;
        for(i = 0; i < possibleEmployees.size(); i++){
            min_idx = i;
            for(j = i+1; j < possibleEmployees.size(); j++){
                if(possibleEmployees.get(j).getHours() < possibleEmployees.get(min_idx).getHours()){
                    min_idx = j;
                }
            }
            Employee temp = possibleEmployees.get(min_idx);
            possibleEmployees.set(min_idx, possibleEmployees.get(i));
            possibleEmployees.set(i, temp);
        }
    }


    private List<Employee> getEmployeeByType(List<Employee> employeeList, Position position) {
        List<Employee> possible = new LinkedList<>();
        for(int i = 0; i < employeeList.size(); i++){
            if(employeeList.get(i).getPosition() == position){
                possible.add(employeeList.get(i));
            }
        }
        return possible;
    }

    public void addEmployee(Employee e){
        employeeList.add(e);
    }
}

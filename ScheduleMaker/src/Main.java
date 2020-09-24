import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class Main {

    private static Restaurant restaurant;

    public static void main(String[] args) {
        System.out.println("Welcome to Restaurant Schedule Maker");
        restaurant = new Restaurant();
        int choice = -1;
        Scanner choiceScanner = new Scanner(System.in);
        while(choice!=0){
            System.out.println("1. Create Schedule\n2. View Employees\n3. Create new employee\n4. Set working days\n5. Set restaurant hours\n6. Create Shift\n7. View Shifts\n0. Quit");
            choice = choiceScanner.nextInt();
            switch(choice){
                case 0: break;
                case 1:
                    System.out.println("What day do you want to start on?");
                    System.out.print("Input Day(DD/MM/YYYY) : ");
                    var x = choiceScanner.next();
                    LocalDate start = LocalDate.of(Integer.parseInt(x.substring(6,10)),Integer.parseInt(x.substring(3,5)),Integer.parseInt(x.substring(0,2)));
                    System.out.println("What day do you want to end on?");
                    System.out.print("Input Day(DD/MM/YYYY) : ");
                    x = choiceScanner.next();
                    LocalDate end = LocalDate.of(Integer.parseInt(x.substring(6,10)),Integer.parseInt(x.substring(3,5)),Integer.parseInt(x.substring(0,2)));
                    if(restaurant.getStandardWeekDayShifts().size() == 0 && restaurant.getStandardWeekendShifts().size() == 0){
                        System.out.println("No shifts are specified, first create a shift");
                        break;
                    }
                    restaurant.createSchedule(start, end);

                    break;
                case 6:
                    System.out.print("What position is this shift for?");
                    Position position;
                    String y = choiceScanner.next();
                    if(y.equals("Manager") || y.equals("manager")){
                        position = Position.MANAGER;
                    }
                    else if(y.equals("Server") || y.equals("server")){
                        position = Position.SERVER;
                    }
                    else if(y.equals("Dishwasher") || y.equals("dishwasher")){
                        position = Position.DISHWASHER;
                    }
                    else if(y.equals("Cook") || y.equals("cook")){
                        position = Position.COOK;
                    }
                    else{
                        position = Position.MANAGER;
                    }
                    System.out.print("What hour does this shift start?(0-24) ");
                    var startHour = choiceScanner.nextInt();
                    System.out.print("What hour does this shift end?(0-24)  ");
                    var endHour = choiceScanner.nextInt();
                    Shift s = new Shift(LocalTime.of(startHour, 0), LocalTime.of(endHour, 0), position);
                    restaurant.addWeekDayShift(s);
                    restaurant.addWeekendShift(s);
                    break;
                case 7:
                    for(int i = 0; i < restaurant.getStandardWeekendShifts().size(); i++){
                        System.out.println(restaurant.getStandardWeekDayShifts().get(i).toString());
                    }
                default:
                    System.out.println("input correct choice ##");
            }
        }
        System.out.println("Thanks for using schedule maker");
    }
}

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        Restaurant restaurant = new Restaurant();
        restaurant.createSchedule();
        System.out.println(restaurant.getSchedule().days.get(0).getShifts().get(0).getEmployee().toString());
    }
}

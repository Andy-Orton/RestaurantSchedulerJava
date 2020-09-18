import java.util.List;

public class WorkDays {
    private String day;
    private List<Shift> shifts;


    public WorkDays(String day, List<Shift> shifts) {
        this.day = day;
        this.shifts = shifts;
    }

    public String getDay() {
        return day;
    }

    public List<Shift> getShifts() {
        return shifts;
    }

    public void setShifts(List<Shift> shifts) {
        this.shifts = shifts;
    }
}

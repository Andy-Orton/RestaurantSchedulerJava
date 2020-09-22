import java.time.LocalDate;
import java.util.List;

public class WorkDay {
    private LocalDate day;
    private List<Shift> shifts;


    public WorkDay(LocalDate day, List<Shift> shifts) {
        this.day = day;
        this.shifts = shifts;
    }

    public LocalDate getDay() {
        return day;
    }

    public List<Shift> getShifts() {
        return shifts;
    }

    public void setShifts(List<Shift> shifts) {
        this.shifts = shifts;
    }
}

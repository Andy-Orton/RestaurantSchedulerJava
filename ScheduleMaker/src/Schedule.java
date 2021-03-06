import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class Schedule {
    public LocalDate startDay;
    public LocalDate endDay;
    public List<WorkDay> days;
    public Schedule(LocalDate startDay, LocalDate endDay, List<Shift> weekday, List<Shift> weekend){
        days = new LinkedList<>();
        this.startDay = startDay;
        this.endDay = endDay;
        for(int i = 0; i < startDay.datesUntil(endDay).count(); i++){
            if(isWeekday(startDay.plusDays(i))){
              WorkDay w = new WorkDay(startDay.plusDays(i), weekday);
              days.add(w);
            }
            else{
                WorkDay w = new WorkDay(startDay.plusDays(i), weekend);
                days.add(w);
            }
        }
    }

    private boolean isWeekday(LocalDate day) {
        if(day.getDayOfWeek().toString() != "SATURDAY" || day.getDayOfWeek().toString() != "FRIDAY" ){
            return true;
        }
        else{
            return false;
        }
    }

}

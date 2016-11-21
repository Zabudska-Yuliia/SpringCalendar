package app;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.*;

public class Calendar {
    private YearMonth month;
    private LocalDate today;
    private DayOfWeek weekStart;
    private Set<DayOfWeek> weekend;
    private Locale locale;
    private List<LocalDate> days = new ArrayList<LocalDate>();

    public Calendar() {
        this(YearMonth.now());
    }

    public Calendar(YearMonth month) {
        this.month = month;
    }

    public Calendar(YearMonth month, LocalDate today){
        this.today = today;
        this.month = month;
        setWeekStart(DayOfWeek.WEDNESDAY);
        weekend = new LinkedHashSet<DayOfWeek>();
        setWeekend(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY);
        locale = Locale.getDefault();
        daysOfMonth();
    }

    public void setWeekStart(DayOfWeek day){
        this.weekStart = day;
    }

    public void setWeekend(DayOfWeek ... weekend) {
        this.weekend.clear();
        Collections.addAll(this.weekend, weekend);
    }

    public Set<DayOfWeek> getWeekend(){
        return weekend;
    }

    public int getWeekStart(){
        return weekStart.getValue();
    }

    public Locale getLocale(){ return locale; }

    public List<LocalDate> daysOfMonth(){
        for (int i = 1; i < month.lengthOfMonth()+1; i++){
            days.add(today.withDayOfMonth(i));
        }
        return days;
    }

    public List<LocalDate> getDaysInMonth(){
        return days;
    }

    public LocalDate getToday(){
        return today;
    }

    public void setToday(LocalDate today){
        this.today = today;
    }

    public void setMonth(YearMonth month){
        this.month = month;
    }

    public YearMonth getMonth(){
        return month;
    }
}
package app;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Set;

abstract class Printer {
    private int countDaysInWeek = 7;
    private Calendar calendar;
    private String shortStyleOfName;

    protected String renderCalendar(Calendar calendar){
        int positionOfFirstDayOfMonth = (calendar.getToday().getDayOfWeek().minus(calendar.getToday().getDayOfMonth())).getValue();
        String output = "";
        this.calendar = calendar;
        output += printNamesOfTheDays();
        output += printTabsForFirstDay(positionOfFirstDayOfMonth);
        output += printDates(positionOfFirstDayOfMonth);
        return  output;
    }

    private String printDates(int positionOfFirstDayOfMonth){
        String Dates = "";
        for (int i = 0; i < calendar.getDaysInMonth().size(); i++) {
            Dates += setColorForDay(calendar.getDaysInMonth().get(i));
            if (checkLastDayOfWeek(calendar.getDaysInMonth().get(i), positionOfFirstDayOfMonth)) {
                Dates += startNewWeek();
            }
        }
        return Dates;
    }

    private String printNamesOfTheDays() {
        String namesOfDays = "";
        if (calendar.getWeekStart() == 1) {
            namesOfDays += printNamesOfTheDaysIfWeekStartAtMonday();
        } else {
            namesOfDays += printNamesOfTheDaysIfWeekStartNotAtMonday();
        }
        return printNamesOfDays(namesOfDays);
    }

    private String printTabsForFirstDay(int positionOfFirstDayOfMonth) {
        String tabForFirstDay = "";
        if (positionOfFirstDayOfMonth == countDaysInWeek) {
            tabForFirstDay += printTabsForFirstDayIfIsMonday();
        } else {
            tabForFirstDay += printTabsForFirstDayIfIsNotMonday(positionOfFirstDayOfMonth);
        }
        return tabForFirstDay;
    }

    private String printTabsForFirstDayIfIsMonday() {
        String tabsForMonday = "";
        if (calendar.getWeekStart() == 1) {
            tabsForMonday += printTableIfFirstDayNotMonday();
        } else {
            for (int j = 0; j < countDaysInWeek - calendar.getWeekStart() + 1; j++) {
                tabsForMonday += printTableIfFirstDayIsMonday();
            }
        }
        return tabsForMonday;
    }

    private String printTabsForFirstDayIfIsNotMonday(int positionOfFirstDayOfMonth) {
        String tabForMonday = "";
        if (positionOfFirstDayOfMonth + 1 > calendar.getWeekStart()) {
            tabForMonday += printTabsForPositionOfFirstDayOfMonthIfEqualsFirstDayOfWeek();
        } else if (positionOfFirstDayOfMonth + 1 == calendar.getWeekStart()) {
            tabForMonday += printTableIfFirstDayNotMonday();
        } else {
            tabForMonday += printTabsForPositionOfFirstDayOfMonthIfLessThanFirstDayOfWeek(positionOfFirstDayOfMonth);
        }
        return tabForMonday;
    }

    private String printTabsForPositionOfFirstDayOfMonthIfEqualsFirstDayOfWeek() {
        String tabForMonday = "";
        for (int j = 0; j < calendar.getWeekStart(); j++) {
            tabForMonday += printTableIfFirstDayEqualFirstDayOfWeek();
        }
        return tabForMonday;
    }

    private String printTabsForPositionOfFirstDayOfMonthIfLessThanFirstDayOfWeek(int positionOfFirstDayOfMonth) {
        String tabForMonday = "";
        for (int j = 0; j <= countDaysInWeek - calendar.getWeekStart() + positionOfFirstDayOfMonth; j++) {
            tabForMonday += printTableIfFirstDayNotMonday();
        }
        return tabForMonday;
    }

    private String printNamesOfTheDaysIfWeekStartAtMonday() {
        String namesOfDays = "";
        for (int i = calendar.getWeekStart(); i <= countDaysInWeek; i++) {
            shortStyleOfName += DayOfWeek.of(i).getDisplayName(TextStyle.SHORT, calendar.getLocale());
            namesOfDays += printNameOfDay(shortStyleOfName);
        }
        return namesOfDays;
    }

    private String printNamesOfTheDaysIfWeekStartNotAtMonday() {
        String names = "";
        names += printNamesFromFirstDayToDayWeekBegings();
        names += printNamesFromWeekBeginsDayToLastDay();
        return names;
    }

    private String printNamesFromWeekBeginsDayToLastDay() {
        String namesOfDays = "";
        for (int i = 1; i <= calendar.getWeekStart() - 1; i++) {
            shortStyleOfName = DayOfWeek.of(i).getDisplayName(TextStyle.SHORT, calendar.getLocale());
            namesOfDays += printNameOfDay(shortStyleOfName);
        }
        return namesOfDays;
    }

    private  String printNamesFromFirstDayToDayWeekBegings() {
        String namesOfDays = "";
        for (int i = calendar.getWeekStart(); i <= countDaysInWeek; i++) {
            shortStyleOfName = DayOfWeek.of(i).getDisplayName(TextStyle.SHORT, calendar.getLocale());
            namesOfDays += printNameOfDay(shortStyleOfName);
        }
        return namesOfDays;
    }

    private boolean checkLastDayOfWeek(LocalDate getDayOfMonth, int positionOfFirstDayOfMonth) {
        int countOfDays = getDayOfMonth.getDayOfMonth() + countDaysInWeek + 1 - calendar.getWeekStart() + positionOfFirstDayOfMonth;
        return (countOfDays % countDaysInWeek == 0);
    }

    private String setColorForDay(LocalDate numberOfDay) {
        DayOfWeek dayOfWeek = DayOfWeek.from(calendar.getToday().withDayOfMonth(numberOfDay.getDayOfMonth()));
        if (checkCurrentDay(numberOfDay.getDayOfMonth(), calendar.getToday())) {
            return setColorForCurrentDay(numberOfDay);
        } else if (checkWeekend(dayOfWeek, calendar.getWeekend())) {
            return setColorForWeekend(numberOfDay);
        } else {
            return setColorForWorkDay(numberOfDay);
        }
    }

    private static boolean checkCurrentDay(int numberOfDay, LocalDate date) {
        return date.getDayOfMonth() == numberOfDay;
    }

    private static boolean checkWeekend(DayOfWeek getDayOfMonth, Set weekendDay) {
        boolean isWeekend = false;
        for (Object i : weekendDay.toArray()) {
            isWeekend = (DayOfWeek.valueOf(i.toString()).getValue() == getDayOfMonth.getValue());
            if (isWeekend) break;
        }
        return isWeekend;
    }

    public abstract String printTableIfFirstDayNotMonday();
    public abstract String printTableIfFirstDayIsMonday();
    public abstract String printTableIfFirstDayEqualFirstDayOfWeek();
    public abstract String printNameOfDay(String names);
    public abstract String startNewWeek();
    public abstract String setColorForCurrentDay(LocalDate numberOfDay);
    public abstract String setColorForWeekend(LocalDate numberOfDay);
    public abstract String setColorForWorkDay(LocalDate numberOfDay);
    public abstract String printNamesOfDays(String namesOdDays);
}
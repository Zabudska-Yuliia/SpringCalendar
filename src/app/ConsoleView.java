package app;

import java.time.LocalDate;

class ConsoleView extends Printer{

    private String indent = "\t";
    private String colorForCurrentDay = "\033[36m";
    private String colorForWeekend = "\033[31m";
    private String colorForWorkDays = "\033[37m";
    private Calendar calendar;

    public ConsoleView(Calendar calendar){
        this.calendar = calendar;
    }
    @Override
    public String printTableIfFirstDayNotMonday(){
        return indent;
    }

    @Override
    public String printTableIfFirstDayIsMonday(){
        return indent;
    }

    @Override
    public String printTableIfFirstDayEqualFirstDayOfWeek(){
        return indent;
    }

    @Override
    public String printNameOfDay(String name){
        return  name + indent;
    }

    @Override
    public String printNamesOfDays(String namesOfDays){
        return namesOfDays + "\n";
    }

    @Override
    public String setColorForCurrentDay(LocalDate numberOfDay){
        return colorForCurrentDay + numberOfDay.getDayOfMonth() + indent;
    }

    @Override
    public String setColorForWeekend(LocalDate numberOfDay){
        return colorForWeekend + numberOfDay.getDayOfMonth() + indent;
    }

    @Override
    public String setColorForWorkDay(LocalDate numberOfDay){
        return colorForWorkDays + numberOfDay.getDayOfMonth() + indent;
    }

    @Override
    public String startNewWeek(){
        return indent + "\n";
    }

}
package app;

import java.io.*;
import java.time.*;

class HtmlView extends Printer {
    private  File html = new File("/home/employee/Calendar.html");
    private  File styles = new File("/home/employee/style.css");
    private  FileWriter htmlWriter = null;
    private  FileWriter styleWriter = null;
    private  String htmlContent = "";
    private  String styleContent = "";
    private  String TAG_TD = "<td>";
    private  String TAG_TD_END = "</td>";
    private  String TAGS_TR= "<tr></tr>";
    private  String TAGS_TD= "<td></td>";
    private  String colorForWeekend = "<td class=\"colorForWeekend\">";
    private  String colorForCurrentDay = "<td class=\"colorForCurrentDay\">";
    private  String colorForWorkDays = "<td>";
    private Calendar calendar;

    public HtmlView(Calendar calendar){
        this.calendar = calendar;
    }

    @Override
    public String printTableIfFirstDayNotMonday(){
        return TAGS_TD;
    }

    @Override
    public String printTableIfFirstDayIsMonday(){
        return TAGS_TD;
    }

    @Override
    public String printTableIfFirstDayEqualFirstDayOfWeek(){
        return TAGS_TD;
    }

    @Override
    public String printNameOfDay(String names){
        return  TAG_TD + names + TAG_TD_END;
    }

    @Override
    public String setColorForCurrentDay(LocalDate numberOfDay){
        return colorForCurrentDay + numberOfDay.getDayOfMonth();
    }

    @Override
    public String printNamesOfDays(String namesOfDays){
        return namesOfDays + TAGS_TR;
    }

    @Override
    public String setColorForWeekend(LocalDate numberOfDay){
        return colorForWeekend + numberOfDay.getDayOfMonth();
    }

    @Override
    public String setColorForWorkDay(LocalDate numberOfDay){
        return  colorForWorkDays + numberOfDay.getDayOfMonth();
    }

    @Override
    public String startNewWeek(){
        return TAGS_TR;
    }

    public void addContentToFile(Calendar calendar) {
        htmlContent += "<html> <head> <title>Calendar</title><link href=\"style.css\" rel=\"stylesheet\"></head> <body> <table>";
        htmlContent += renderCalendar(calendar);
        htmlContent += "</table></body></html>";
        styleContent += " .colorForCurrentDay {color: #66CDAA;} .colorForWeekend{color: #CD5C5C;} td{ width: 30px;}";
        addContent();
    }

    private void addContent() {
        try {
            htmlWriter = new FileWriter(html);
            htmlWriter.write(htmlContent);
            styleWriter = new FileWriter(styles);
            styleWriter.write(styleContent);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeFile();
        }
    }

    private void closeFile(){
        try {
            htmlWriter.close();
            styleWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
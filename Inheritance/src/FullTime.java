import java.util.ArrayList;

public class FullTime extends Employee{
    public void daysOfWork(FullTime [] fullTimers, int wagePerDay) //calculating pay based on days of work of full timers
    {
        for (FullTime fullTime : fullTimers)
            fullTime.totalSalary+=20.0*wagePerDay;
    }
    public void overWork (ArrayList<String> week, int newSize, FullTime [] fullTimes, int maxHours, int wageForOverWork) //calculating over work of full timers
    {
        for (int j = 0; j < newSize; j++) {
            for (String lines : week) {
                if (lines.split("\\t")[0].equals(fullTimes[j].regNum)) {
                    for (int m = 1; m < 5; m++) {
                        if (Integer.parseInt(lines.split("\\t")[m]) - 40 > 0) {
                            if(Integer.parseInt(lines.split("\\t")[m])-40>=maxHours)
                                fullTimes[j].totalSalary+=maxHours*wageForOverWork*1.0;
                            else
                                fullTimes[j].totalSalary+=(Integer.parseInt(lines.split("\\t")[m])-40)*wageForOverWork*1.0;
                        }
                    }
                }
            }
        }
    }
}

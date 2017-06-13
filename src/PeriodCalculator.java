import java.util.Calendar;
import java.util.Date;

import org.joda.time.LocalDate;
import org.joda.time.Years;

public class PeriodCalculator {

	public static int calculateYears(Date date1, Date date2){
		int years = -1;
		LocalDate  today = new LocalDate(date1);
		LocalDate  birth = new LocalDate(date2);
		years = Years.yearsBetween(birth, today).getYears();
		return years;
	}
}

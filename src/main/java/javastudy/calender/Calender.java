package javastudy.calender;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import static java.time.temporal.TemporalAdjusters.firstDayOfMonth;
import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;

/**
 * 설명작성란
 *
 * @author : 유호철
 * @see
 * @since 1.0
 */
public class Calender {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int year = scanner.nextInt();
        int month = scanner.nextInt();

        Month months = Month.of(month);
        LocalDate localDate = LocalDate.of(year, month, months.maxLength());
        //첫째일자
        LocalDate firstDate = localDate.with(firstDayOfMonth());
        //마지막 일자
        LocalDate lastDate = localDate.with(months).with(lastDayOfMonth());

        DayOfWeek dayOfWeek = firstDate.getDayOfWeek();
        String dayOfKorean = dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.KOREA);
        List<String> days = List.of("일", "월", "화", "수", "목", "금", "토");

        System.out.println(year + "년 " + month + "월");
        System.out.println("일  월  화  수  목  금  토");
        int count = 0;
        String value = "";
        for (int i = firstDate.getDayOfYear(); i <= lastDate.getDayOfYear(); i++, count++) {
            LocalDate tempDate = firstDate.plusDays(count);
            String displayName = tempDate.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.KOREA);
            boolean flag = true;
            for (String day : days) {
                if (displayName.equals(day)) {
                    value += tempDate.getDayOfMonth();
                    if(displayName.equals("토")){
                        value += "\n";
                        break;
                    }
                }  else {
                    if(flag){
                        value +="\t";
                        flag = false;
                    }
                }
            }
        }
        System.out.println(value);

    }
}

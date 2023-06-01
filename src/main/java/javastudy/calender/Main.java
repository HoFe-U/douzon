package javastudy.calender;

import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int year = scanner.nextInt();
        int month = scanner.nextInt();

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month-1, 1);
        int i = calendar.get(Calendar.DAY_OF_WEEK); // 시작요일
        int i1 = calendar.getActualMinimum(Calendar.WEEK_OF_MONTH);
        int actualMaximum = calendar.getActualMaximum(Calendar.DATE);
        int arr[][] = new int[i1][7];

        int cnt = 0;
        String value = "";
        List<String> days = List.of("일", "월", "화", "수", "목", "금", "토");
        for (String day : days) {
            value += day + "\t";
        }
        boolean flag = true;
        for (int j = 0; j < i1; j++) {
            if(cnt == actualMaximum) break;
            for (int k = 0; k < 7; k++) {
                calendar.add(Calendar.DATE, 1);
                cnt++;
            }
        }
    }
}

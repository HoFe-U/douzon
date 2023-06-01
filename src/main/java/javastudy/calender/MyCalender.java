package javastudy.calender;

/**
 * 설명작성란
 *
 * @author : 유호철
 * @see
 * @since 1.0
 */
public class MyCalender {
    private CalenderService service;
    private Integer year;
    private Integer month;

    public CalenderService getService() {
        return service;
    }

    public void setService(CalenderService service) {
        this.service = service;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }
}

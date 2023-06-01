package javastudy.book.entity;

/**
 * 설명작성란
 *
 * @author : 유호철
 * @see
 * @since 1.0
 */
public class RentalHistory {
    private int historyNo;
    private int bookNo;
    private int userNo;
    private String rentalDate;
    private String expectReturnDate;

    public int getHistoryNo() {
        return historyNo;
    }

    public int getBookNo() {
        return bookNo;
    }

    public int getUserNo() {
        return userNo;
    }

    public String getRentalDate() {
        return rentalDate;
    }

    public String getExpectReturnDate() {
        return expectReturnDate;
    }
}

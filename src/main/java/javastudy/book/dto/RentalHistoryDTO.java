package javastudy.book.dto;

/**
 * 설명작성란
 *
 * @author : 유호철
 * @see
 * @since 1.0
 */
public class RentalHistoryDTO {
    private int historyNo;
    private int bookNo;
    private int userNo;
    private String rentalDate;
    private String expectReturnDate;

    public RentalHistoryDTO(int historyNo, int bookNo, int userNo, String rentalDate, String expectReturnDate) {
        this.historyNo = historyNo;
        this.bookNo = bookNo;
        this.userNo = userNo;
        this.rentalDate = rentalDate;
        this.expectReturnDate = expectReturnDate;
    }


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

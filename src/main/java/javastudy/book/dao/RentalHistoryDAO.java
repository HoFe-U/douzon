package javastudy.book.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javastudy.book.BookSystem;
import javastudy.book.dto.ReturnDTO;
import javastudy.book.utils.ConnectionManager;

/**
 * 설명작성란
 *
 * @author : 유호철
 * @see
 * @since 1.0
 */
public class RentalHistoryDAO {
    private static final String INSERT_RENTAL_HISTORY_QUERY = "INSERT INTO rentalHistory (book_no, user_no, rental_date, expect_return_date) VALUES (?, ?, CURDATE(), DATE_ADD(CURDATE(), INTERVAL 7 DAY))";
    private static final String UPDATE_BOOK_RENTAL_CHECK_QUERY = "UPDATE book SET rental_check = 'T' WHERE book_no = ?";
//    private static final String UPDATE_BOOK_RETURN_QUERY = "UPDATE book SET rental_check = 'F' WHERE book_no = ?";
    private static final String SELECT_RENTAL_HISTORIES_QUERY = "SELECT b.book_no, author, rental_date, expect_return_date, history_no, return_date FROM book AS b JOIN rentalHistory AS r WHERE b.book_no = r.book_no AND user_no = ? AND return_date IS NULL";
    private static final String UPDATE_RENTAL_HISTORY_RETURN_DATE_QUERY = "UPDATE rentalHistory SET return_date = sysdate() WHERE history_no = ?";
    private static final String UPDATE_BOOK_RENTAL_CHECK_RETURN_QUERY = "UPDATE book SET rental_check ='F' WHERE book_no = ?";
    private static final String SELECT_EXPECT_RETURN_DATE_QUERY = "SELECT expect_return_date, return_date FROM rentalHistory WHERE history_no = ?";
    private static final String SELECT_OVERDUE_DATE_QUERY = "SELECT overdue_date FROM user WHERE user_no = ?";
    private static final String UPDATE_USER_OVERDUE_DATE_QUERY = "UPDATE user SET overdue_date = ? WHERE user_no = ?";

    //
//    public static void returnBook(int bookNo) throws SQLException {
//        try (Connection conn = ConnectionManager.getConnection()) {
//            conn.setAutoCommit(false);
//
//            try (PreparedStatement pstmt = conn.prepareStatement(UPDATE_BOOK_RETURN_QUERY)) {
//                pstmt.setInt(1, bookNo);
//                pstmt.executeUpdate();
//            }
//
//            conn.commit();
//        }
//    }
    private ReturnDTO makeReturnDTO(ResultSet rs) throws SQLException {
        ReturnDTO dto = new ReturnDTO();
        dto.setBookNo(rs.getInt("book_no"));
        dto.setAuthor(rs.getString("author"));
        dto.setRentalDate(rs.getDate("rental_date").toLocalDate());
        dto.setExpectedReturnDate(rs.getDate("expect_return_date").toLocalDate());
        dto.setHistoryNo(rs.getInt("history_no"));
        return dto;
    }

    private boolean isRentCntOver(Connection conn, int userNo) throws SQLException {
        String query = "SELECT g.rental_count as 'gradeCnt', count(rH.user_no) as 'rentalCnt' " +
            "FROM user as u " +
            "INNER JOIN grade g" +
            " ON g.grade_code = u.grade_code" +
            " LEFT JOIN rentalHistory rH " +
            "on u.user_no = rH.user_no" +
            " WHERE u.user_no = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, userNo);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    int gradeCnt = rs.getInt("gradeCnt");
                    int rentalCnt = rs.getInt("rentalCnt");
                    return gradeCnt <= rentalCnt;
                }
            }
        }
        return false;
    }

    public boolean rentBook(int bookNo) throws SQLException {
        Integer userNo = BookSystem.THREAD_LOCAL_USER_NO.get();
        try (Connection conn = ConnectionManager.getConnection()) {
            conn.setAutoCommit(false);

            // 이미 대출한 책인지 확인
            if (isBookRented(conn, bookNo)) {
                System.out.println("해당 도서는 이미 대출된 상태입니다.");
                conn.rollback();
                return false;
            }

            if (isRentCntOver(conn, userNo)) {
                System.out.println("도서 대출량을 초과하였습니다.");
                conn.rollback();
                return false;
            }

            // 대출 내역 작성
            try (PreparedStatement pstmt = conn.prepareStatement(INSERT_RENTAL_HISTORY_QUERY)) {
                pstmt.setInt(1, bookNo);
                pstmt.setInt(2, userNo);
                pstmt.executeUpdate();
            }

            // 대출한 책의 상태 변경
            try (PreparedStatement pstmt = conn.prepareStatement(UPDATE_BOOK_RENTAL_CHECK_QUERY)) {
                pstmt.setInt(1, bookNo);
                pstmt.executeUpdate();
            }
            conn.commit();
            System.out.println("대출에 성공하셨습니다.");
            return true;
        }
    }

    public List<ReturnDTO> selectRentalHistories(int userNo) throws SQLException {
        List<ReturnDTO> list = new ArrayList<>();

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SELECT_RENTAL_HISTORIES_QUERY)) {
            pstmt.setInt(1, userNo);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    ReturnDTO dto = makeReturnDTO(rs);

                    list.add(dto);
                }
            }
        }
        return list;
    }

    public boolean updateRentalHistories(int historyNo, int bookNo) throws SQLException {
        boolean flag = true;

        try (Connection conn = ConnectionManager.getConnection()) {
            //TODO 1: auto commit 이있을이유가 있나??? 원래어디였지... 기억이안나
            conn.setAutoCommit(false);

            try (PreparedStatement pstmt = conn.prepareStatement(UPDATE_RENTAL_HISTORY_RETURN_DATE_QUERY)) {
                pstmt.setInt(1, historyNo);
                pstmt.executeUpdate();
            }

            try (PreparedStatement pstmt = conn.prepareStatement(UPDATE_BOOK_RENTAL_CHECK_RETURN_QUERY)) {
                pstmt.setInt(1, bookNo);
                pstmt.executeUpdate();
            }

            conn.commit();
        }
        return flag;
    }

    public int checkOverDue(int historyNo) throws SQLException {
        int count = 0;

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SELECT_EXPECT_RETURN_DATE_QUERY)) {
            pstmt.setInt(1, historyNo);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    LocalDate expectDate = rs.getDate("expect_return_date").toLocalDate();
                    LocalDate returnDate = rs.getDate("return_date").toLocalDate();

                    int year = returnDate.getYear() - expectDate.getYear();
                    if (year > 0) {
                        count = (year * 365);
                    }
                    count += (returnDate.getDayOfYear() - expectDate.getDayOfYear());
                }
            }
        }
        return count;
    }

    public void insertOverDue(int count, int userNo) throws SQLException {
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SELECT_OVERDUE_DATE_QUERY)) {
            pstmt.setInt(1, userNo);
            try (ResultSet rs = pstmt.executeQuery()) {
                LocalDate date = null;
                if (rs.next()) {
                    if (date == null) {
                        date = LocalDate.now();
                    } else {
                        date = rs.getDate("overdue_date").toLocalDate();
                    }
                }

                int year = LocalDate.now().getYear();
                int overDueDate = (LocalDate.now().getDayOfYear() + count);

                if (overDueDate > 365) {
                    year += overDueDate / 365;
                    overDueDate %= 365;
                }

                if (date.getDayOfYear() < overDueDate && date.getYear() <= year) {
                    try (PreparedStatement updatePstmt = conn.prepareStatement(UPDATE_USER_OVERDUE_DATE_QUERY)) {
                        updatePstmt.setDate(1, Date.valueOf(LocalDate.ofYearDay(year, overDueDate)));
                        updatePstmt.setInt(2, userNo);
                        int updateCount = updatePstmt.executeUpdate();
                        if (updateCount == 0) {
                            System.out.println("연체일자 입력 실패");
                        }
                    }
                }
            }
        }
    }

    private boolean isBookRented(Connection conn, int bookNo) throws SQLException {
        String query = "SELECT rental_check FROM book WHERE book_no = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, bookNo);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String rentalCheck = rs.getString("rental_check");
                    return rentalCheck.equals("T");
                }
            }
        }
        return false;
    }

}

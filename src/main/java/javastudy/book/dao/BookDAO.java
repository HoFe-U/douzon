package javastudy.book.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javastudy.book.dto.BookDTO;
import javastudy.book.utils.ConnectionManager;

/**
 * 설명작성란
 *
 * @author : 유호철
 * @see
 * @since 1.0
 */
public class BookDAO {
    private static final String SELECT_CATEGORIES_QUERY = "SELECT DISTINCT category_name FROM book";
    private static final String SELECT_BOOK_BY_BOOK_NO = "SELECT * FROM book WHERE book_no = ?";
    private static final String SEARCH_BOOKS_QUERY = "SELECT * FROM book WHERE display_check = 'T' ";

    public List<String> selectCategories() throws SQLException {
        List<String> categories = new ArrayList<>();
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SELECT_CATEGORIES_QUERY);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                String category = rs.getString("category_name");
                categories.add(category);
            }
        }
        return categories;
    }

    public BookDTO selectBookByBookNo(int bookNo) throws SQLException {
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SELECT_BOOK_BY_BOOK_NO)) {
            pstmt.setInt(1, bookNo);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return createBookDTO(rs);
                }
            }
        }
        return null;
    }

    public List<BookDTO> selectSearchList(int menuNo, String word) throws SQLException {
        List<BookDTO> books = new ArrayList<>();
        StringBuilder sql = new StringBuilder(SEARCH_BOOKS_QUERY);

        if (word == null) {
            menuNo = -1;
        }

        switch (menuNo) {
            case 0:
                sql.append("AND category_name LIKE CONCAT('%', ?, '%')");
                break;
            case 1:
                sql.append("AND title LIKE CONCAT('%', ?, '%')");
                break;
            case 2:
                sql.append("AND content LIKE CONCAT('%', ?, '%')");
                break;
            case 3:
                sql.append("AND author LIKE CONCAT('%', ?, '%')");
                break;
            case 4:
                sql.append("AND rental_check = ?");
                break;
            default:
                break;
        }

        sql.append(" ORDER BY FIELD(rental_check, 'F', 'T'), title ASC, author ASC");

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql.toString())) {
            if (word != null) {
                pstmt.setString(1, word);
            }

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    books.add(createBookDTO(rs));
                }
            }
        }
        return books;
    }

    private BookDTO createBookDTO(ResultSet rs) throws SQLException {
        int bookNo = rs.getInt("book_no");
        String title = rs.getString("title");
        String content = rs.getString("content");
        String author = rs.getString("author");
        String category = rs.getString("category_name");
        return new BookDTO(bookNo, title, content, author, category);
    }
}

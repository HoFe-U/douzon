package javastudy.book.dto;

/**
 * 설명작성란
 *
 * @author : 유호철
 * @see
 * @since 1.0
 */
public class BookDTO {
    private int bookNo;
    private String title;
    private String content;
    private String author;
    private String category;

    public BookDTO(int bookNo, String title, String content, String author, String category) {
        this.bookNo = bookNo;
        this.title = title;
        this.content = content;
        this.author = author;
        this.category = category;
    }

    public BookDTO(int bookNo, String title, String author) {
        this.bookNo = bookNo;
        this.title = title;
        this.author = author;
    }

    public int getBookNo() {
        return bookNo;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getAuthor() {
        return author;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "--------책 정보--------" + "\n" +
            "책 번호 : " + bookNo + "\n" +
            "책 제목 : " + title + "\n" +
            "책 내용 : " + content + "\n" +
            "책 저자 : " + author + "\n" +
            "책 카테고리 : " + category + "\n";
    }
}

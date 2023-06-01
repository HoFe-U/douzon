package javastudy.book.entity;

/**
 * 설명작성란
 *
 * @author : 유호철
 * @see
 * @since 1.0
 */
public class Book {
    private int bookNo;
    private String title;
    private String content;
    private String author;
    private String category;

    public Book(int bookNo, String title, String content, String author, String category) {
        this.bookNo = bookNo;
        this.title = title;
        this.content = content;
        this.author = author;
        this.category = category;
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
}

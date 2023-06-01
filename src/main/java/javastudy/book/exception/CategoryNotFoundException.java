package javastudy.book.exception;

/**
 * 설명작성란
 *
 * @author : 유호철
 * @see
 * @since 1.0
 */
public class CategoryNotFoundException extends RuntimeException {
    public static final String MESSAGE = "카테고리를 찾을 수 없습니다.";

    public CategoryNotFoundException() {
        super(MESSAGE);
    }
}

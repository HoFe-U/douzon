package javastudy.lotto.correct;

/**
 * 데이터클래스(DTO, VO)
 *
 * @author : 유호철
 * @see
 * @since 1.0
 */
public class LottoBall {
    private Integer number;

    public LottoBall(Integer number) {
        this.number = number;
    }

    public Integer getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return "LottoBall{" +
            "number=" + number +
            '}';
    }
}

package javastudy.numberbaseball;

/**
 * 설명작성란
 *
 * @author : 유호철
 * @see
 * @since 1.0
 */
public class Result {
    private Integer strike;
    private Integer ball;
    private Integer out;

    public Result(Integer strike, Integer ball, Integer out) {
        this.strike = strike;
        this.ball = ball;
        this.out = out;
    }

    @Override
    public String toString() {
        return "Result{" +
            "strike=" + strike +
            ", ball=" + ball +
            ", out=" + out +
            '}';
    }
}

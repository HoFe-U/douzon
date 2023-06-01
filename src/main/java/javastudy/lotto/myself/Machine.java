package javastudy.lotto.myself;

import java.util.ArrayList;
import java.util.List;

/**
 * 설명작성란
 *
 * @author : 유호철
 * @see
 * @since 1.0
 */
public class Machine  {
    private List<Ball> balls = new ArrayList<>();

    public void pickAndPrint() {
        for (int i = 0; i < 6; i++) {
            Ball ball = new Ball();
            System.out.println(ball);
            balls.add(ball);
        }
    }

    public List<Ball> getBalls() {
        return balls;
    }
}

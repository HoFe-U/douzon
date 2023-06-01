package javastudy.lotto.myself;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 설명작성란
 *
 * @author : 유호철
 * @see
 * @since 1.0
 */
public class Ball {
    public static List<Integer> countBall = new ArrayList<>();
    private static Random random = new Random();
    private int ballNumber;

    public Ball() {
        List<Ball> balls = new ArrayList<>();
        this.ballNumber = randomBall();
    }

    private static int randomBall() {
        int i = random.nextInt(45) + 1;
        for (Integer integer : countBall) {
            if (integer.equals(i)) {
                return randomBall();
            }
        }
        countBall.add(i);
        return i;
    }

    @Override
    public String toString() {
        return "Ball{" +
            "ballNumber=" + ballNumber +
            '}';
    }

}

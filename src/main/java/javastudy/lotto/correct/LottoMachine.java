package javastudy.lotto.correct;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 설명작성란
 *
 * @author : 유호철
 * @see
 * @since 1.0d
 */
//@Service
public class LottoMachine {
    private List<LottoBall> balls = new ArrayList<>();

    public LottoBall[] startMachine() {
        LottoBall[] balls = new LottoBall[6];
        Random random = new Random();
        for (int i = 0; i < 45; i++) {
            this.balls.add(new LottoBall(i));
        }
        for (int i = 0; i < 6; i++) {
            int rndValue = random.nextInt(45);
            balls[i] = this.balls.remove(rndValue);
        }
        return balls;
    }

}

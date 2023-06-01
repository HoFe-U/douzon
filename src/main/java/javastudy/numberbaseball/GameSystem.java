package javastudy.numberbaseball;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.Set;

/**
 * 설명작성란
 *
 * @author : 유호철
 * @see
 * @since 1.0
 */
public class GameSystem {
    private static List<Integer> numbers = new ArrayList<>();
    private static Set<Integer> check = new HashSet<>();
    private final Random random = new Random();
    private boolean flag;

    public GameSystem() {
        this.ready();
    }

    public Result gameStart(Client client) {
        int[] play = client.play();
        int strike = 0;
        int ball = 0;
        int out = 0;
        List<Integer> array = new ArrayList<>(numbers);

        for (int i = 0; i < play.length; i++) {
            flag = true;
            for (int j = 0; j < play.length; j++) {
                if (play[i] == array.get(i) && !check.contains(play[i])) {
                    strike++;
                    check.add(play[i]);
                    flag = false;
                    break;
                } else if (play[i] == array.get(j) && !check.contains(play[i])) {
                    ball++;
                    check.add(play[i]);
                    flag = false;
                    break;
                }
            }
            if (flag) {
                out++;
            }
        }
        return new Result(strike, ball, out);
    }

    private void ready() {
        System.out.print("Computer : ");
        List<Integer> countValue = new ArrayList<>();
        while (numbers.size() < 4) {
            int value = random.nextInt(9);
            if (countValue.contains(value)) {
                continue;
            }
            if (numbers.isEmpty() && value == 0) {
                continue;
            }
            countValue.add(value);
            numbers.add(value);
        }
        for (Integer number : numbers) {
            System.out.print(number);
        }
        System.out.println();
    }
}

package javastudy.numberbaseball;

import java.util.Scanner;

/**
 * 설명작성란
 *
 * @author : 유호철
 * @since 1.0
 */
public class Client {
    private final Scanner scanner = new Scanner(System.in);
    private boolean flag;
    public int[] play() {
        int[] values = new int[4];
        String inputValue = scanner.next();
        for (int i = 0; i < inputValue.length(); i++) {
            values[i] = Integer.parseInt(String.valueOf(inputValue.charAt(i)));
        }

        return values;
    }

    public boolean isFlag() {
        return flag;
    }
}

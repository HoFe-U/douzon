package javastudy.basic;

import java.util.ArrayList;

/**
 * 설명작성란
 *
 * @author : 유호철
 * @see
 * @since 1.0
 */
public class Main {
    public static void main(String[] args) {
        Main m = new Main();
        m.test();
    }

    public void test() {
        ArrayList list = new ArrayList<>();
        list.add("한글");
        list.add(2);

        for (Object o : list) {
            String value = (String) o;
            System.out.println(value);
        }
    }
}

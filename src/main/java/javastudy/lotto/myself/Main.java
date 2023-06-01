package javastudy.lotto.myself;

/**
 * 1 ~45 까지의 공을 가진 기계에서 무작위로 6개의 공을 꺼내서 출력
 * 해당 기계는 한번 꺼낸 공은 다시 안으로 집어 넣지 않습니다.
 *
 * @author : 유호철
 * @see
 * @since 1.0
 */
public class Main {
    public static void main(String[] args) {
        Machine machine = new Machine();
        machine.pickAndPrint();
    }
}

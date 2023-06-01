package javastudy.numberbaseball;

/**
 * 설명작성란
 *
 * @author : 유호철
 * @see
 * @since 1.0
 */
public class Main {
    public static void main(String[] args) {
        GameSystem gameSystem = new GameSystem();
        Client client = new Client();
        Result result = gameSystem.gameStart(client);
        System.out.println(result.toString());
    }
}
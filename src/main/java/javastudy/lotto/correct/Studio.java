package javastudy.lotto.correct;

/**
 * 설명작성란
 *
 * @author : 유호철
 * @see
 * @since 1.0
 */
public class Studio {
    public static void main(String[] args) {
      /*
        1. Lotto Machine 을 생성한다.
        2. Lotto Ball 을 생성해서 LottoMachine 에 주입한다.
        3. Lotto Machine 에서 6개의공들을 뽑고 출력한다. 공을 뽑을때 List 에서 하나씩 제거한다.
       */
        Studio studio = new Studio();
        studio.onAir();
    }

    public LottoBall[] ready() {
        LottoMachine machine = new LottoMachine();

        return machine.startMachine();
    }

    public void onAir() {
        LottoBall[] balls = this.ready();
        LottoBall temp = null;
        for (int i = 0; i < balls.length -1; i++) {
            temp = balls[i];
            for (int j = i+1; j < balls.length; j++) {
                if(temp.getNumber() > balls[j].getNumber()){
                    temp =balls[j];
                    balls[j] = balls[i];
                    balls[i] = temp;
                }
            }
        }
        for (LottoBall ball : balls) {
            System.out.println(ball);
        }
    }
}

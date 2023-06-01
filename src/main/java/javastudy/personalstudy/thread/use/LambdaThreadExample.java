package javastudy.personalstudy.thread.use;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class LambdaThreadExample {
    public static void main(String[] args) {
        Runnable runnable = () -> {
            // 스레드 동작을 구현
        };

        Thread thread1 = new Thread(runnable);
        thread1.start();

        Thread thread2 = new Thread(()->{
            System.out.println(Thread.currentThread());
        });
        thread2.start();
    }
}

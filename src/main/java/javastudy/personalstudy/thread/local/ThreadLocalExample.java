
package javastudy.personalstudy.thread.local;

import java.util.concurrent.TimeUnit;

public class ThreadLocalExample {
    private static ThreadLocal<Integer> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {
        // 스레드 1
        Thread thread1 = new Thread(() -> {
            threadLocal.set(1);
            try {
                TimeUnit.SECONDS.sleep(2); // 2초 대기
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread 1: " + threadLocal.get()); // 1 출력
        });

        // 스레드 2
        Thread thread2 = new Thread(() -> {
            threadLocal.set(2);
            System.out.println("Thread 2: " + threadLocal.get()); // 2 출력
        });

        // 스레드 3
        Thread thread3 = new Thread(() -> {
            threadLocal.set(3);
            System.out.println("Thread 3: " + threadLocal.get());
        });

        // 각 스레드 시작
        thread1.run();
        thread2.start();
        thread3.start();

        try {
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 메인 스레드에서의 값 출력
        System.out.println("Main Thread: " + threadLocal.get()); // 1 출력

        thread1.run(); // remove 해야하는 예시
//
    }
}

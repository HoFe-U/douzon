package javastudy.personalstudy.thread.syncronizedways.semaphore;

import java.util.concurrent.Semaphore;

public class SemaphoreExample {
    private static int count = 0;
    private static Semaphore semaphore = new Semaphore(1); // 동시에 접근할수 있는 Thread 개수를 1개로 선정한것

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            try {
                semaphore.acquire(); // Semaphore 의 값을 1감소 처음에 1할당한것을 감소시키니까 0 이겠죠?
                for (int i = 0; i < 1000; i++) {
                    count++;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release(); // 종료됬으니까 Semaphore 의 cnt 값을 1증가
            }
        });

        Thread thread2 = new Thread(() -> {
            try {
                semaphore.acquire();
                for (int i = 0; i < 1000; i++) {
                    count++;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release();
            }
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Count: " + count);
    }
}

package javastudy.personalstudy.thread.syncronizedways.mutex;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MutexExample {
    private static int count = 0;
    private static Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        // 앞서 설명했듯이 thread 에서 runnable 을 lambda 로 구현
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                lock.lock(); //<-- 해당자원이 임계영역에 진입하기전 lock 을검
                try {
                    count++;
                } finally {
                    lock.unlock(); // <-- 다 끝났을 경우 unlock 으로 해제
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                lock.lock();
                try {
                    count++;
                } finally {
                    lock.unlock();
                }
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

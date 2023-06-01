package javastudy.personalstudy.thread.syncronizedways.monitor;

import java.util.Comparator;

public class ThreadMonitorExample {

    private static int count = 0;

    public static void main(String[] args) {
        // 주요 스레드 생성
        Thread mainThread = new Thread(() -> {
            System.out.println("주요 스레드 시작");

            // 보조 스레드 생성
            for (int i = 1; i <= 5; i++) {
                Thread subThread = new Thread(() -> {
                    for (int j = 0; j < 400; j++) {
                        incrementCount();
                    }
                    System.out.println("보조 스레드 " + Thread.currentThread().getId() + " 종료");
                    System.out.println("보조 스레드 상태 :  " + Thread.currentThread().getState());
                });
                subThread.start();
            }

            // 보조 스레드들이 모두 종료될 때까지 대기
            while (count < 2000) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println("주요 스레드 종료");
        });

        // 주요 스레드 시작
        mainThread.start();

        // 주요 스레드와 보조 스레드 모니터링
        while (mainThread.isAlive()) {
            System.out.println("주요 스레드 상태: " + mainThread.getState());
            System.out.println("현재 count 값: " + count);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        try {
            System.out.println("프로그램 종료");
            mainThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }finally {
            System.out.println("메인 스레드 상태 : "+ mainThread.getState());
        }

    }

    private static synchronized void incrementCount() {
        count++;
    }
}

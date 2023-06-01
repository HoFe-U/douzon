package javastudy.personalstudy.thread.use;

public class MyRunnable implements Runnable {
    public void run() {
        // 스레드 동작을 구현
    }

    public static void main(String[] args) {
        MyRunnable myRunnable = new MyRunnable();
        Thread thread = new Thread(myRunnable);
        thread.start();
    }
}

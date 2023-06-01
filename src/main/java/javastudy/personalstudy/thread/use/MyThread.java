package javastudy.personalstudy.thread.use;

public class MyThread extends Thread {
    public void run() {
        // 스레드 동작을 구현
    }

    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.start();
    }
}

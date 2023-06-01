package javastudy.personalstudy.thread.different;

/**
 * 설명작성란
 *
 * @author : 유호철
 * @see
 * @since 1.0
 */

class MyThread4 extends Thread {
    public void run()
    {
        System.out.println("Current thread name: "
            + Thread.currentThread().getName());
        System.out.println("run() method called");
    }
}

class Main4 {
    public static void main(String[] args)
    {
        MyThread t = new MyThread();
        t.run();
        t.run();
    }
}

package javastudy.personalstudy.thread.different;

/**
 * 설명작성란
 *
 * @author : 유호철
 * @see
 * @since 1.0
 */
class MyThread3 extends Thread {
    public void run()
    {
        System.out.println("Current thread name: "
            + Thread.currentThread().getName());

        System.out.println("run() method called");
    }
}

class Main3 {
    public static void main(String[] args)
    {
        MyThread3 t = new MyThread3();
        t.start();
        t.start();
    }
}


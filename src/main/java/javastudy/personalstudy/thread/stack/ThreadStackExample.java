package javastudy.personalstudy.thread.stack;

public class ThreadStackExample {
    // FIXME : 설명하기 애매한 예제를만든것같다.. 추후수정
    public static void main(String[] args) {
        Thread mainThread = Thread.currentThread();
        System.out.println("Main Thread: " + mainThread.getName());

        Thread childThread = new Thread(() -> {
            Thread currentThread = Thread.currentThread();
            System.out.println("Child Thread: " + currentThread.getName());
            printStackTrace(currentThread);
        }, "ChildThread");

        childThread.start();

        try {
            childThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Main Thread Stack Trace:");
        printStackTrace(mainThread);
    }

    private static void printStackTrace(Thread thread) {
        StackTraceElement[] stackTrace = thread.getStackTrace();
        System.out.println("Stack Trace for " + thread.getName());
        for (StackTraceElement element : stackTrace) {
            System.out.println(element);
        }
        System.out.println();
    }
}

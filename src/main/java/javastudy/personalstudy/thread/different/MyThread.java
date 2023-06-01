package javastudy.personalstudy.thread.different;

class MyThread extends Thread {
	public void run()
	{
		System.out.println("Current thread name: "
						+ Thread.currentThread().getName());
		System.out.println("run() method called");
	}
}

class Main {
	public static void main(String[] args)
	{
		MyThread t = new MyThread();
		t.start();
	}
}

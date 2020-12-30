package concurrent;

public class TestThread extends Thread{
    private static int count = 0;

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            count++;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int counts = 1000;
        Thread[] threads = new Thread[counts];

        for (int i = 0; i < counts; i++) {
            threads[i] = new TestThread();
            threads[i].start();
        }

        for (int i = 0; i < counts; i++) {
            threads[i].join();
        }

        System.out.println(count);
    }
}

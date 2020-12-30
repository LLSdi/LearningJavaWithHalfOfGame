package concurrent;

public class VisibilityDemo {
    private static boolean shutdown = false;

    static class HelloThread extends Thread {
        @Override
        public void run() {
            while (!shutdown) {

            }

            System.out.println("HelloThread shutdown");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new HelloThread().start();
//        Thread.sleep(1000);
        shutdown = true;
        System.out.println("main shutdown");
    }
}

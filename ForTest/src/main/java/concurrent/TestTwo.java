package concurrent;

public class TestTwo implements Runnable {
    @Override
    public void run() {
        welcome();
    }

    private void welcome() {
        System.out.println("1");
        System.out.println("2");
        System.out.println("3");
        System.out.println("4");
        System.out.println("5");
    }

    private synchronized void MonitorWelcome() {
        System.out.println("1");
        System.out.println("2");
        System.out.println("3");
        System.out.println("4");
        System.out.println("5");
    }

    public static void main(String[] args) {
        Runnable runnable = new TestTwo();
        new Thread(runnable).start();
        new Thread(runnable).start();
    }
}

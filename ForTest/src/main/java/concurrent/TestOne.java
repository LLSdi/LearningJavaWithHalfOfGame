package concurrent;

public class TestOne{

    static class Concurent extends Thread {
        @Override
        public void run() {
            welcome();
        }

        public static synchronized void welcome() {
            System.out.println("1");
            System.out.println("2");
            System.out.println("3");
            System.out.println("4");
            System.out.println("5");
        }

        public synchronized void welcomeTwo() {
            System.out.println("a");
            System.out.println("b");
            System.out.println("c");
            System.out.println("d");
            System.out.println("e");
        }
    }

    class TestTwo implements Runnable {
        @Override
        public void run() {
            welcome();
        }

        private synchronized void welcome() {
            System.out.println("1");
            System.out.println("2");
            System.out.println("3");
            System.out.println("4");
            System.out.println("5");
        }
    }

    interface TestThree extends Runnable {
        @Override
        void run();
    }


    public static void main(String[] args) {
        TestOne test = new TestOne();

    }
}

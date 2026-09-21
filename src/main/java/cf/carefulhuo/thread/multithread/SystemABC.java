package cf.carefulhuo.thread.multithread;

public class SystemABC {

    private static int state = 0;

    private static final Object lock = new Object();

    public static void main(String[] args) {
        Thread a = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    try {
                        synchronized (lock) {
                            while (state % 3 != 0) {
                                lock.wait();
                            }
                            System.out.println("A");
                            state++;
                            lock.notifyAll();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread b = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 10; i++) {
                        synchronized (lock) {
                            while (state % 3 != 1) {
                                lock.wait();
                            }
                            System.out.println("B");
                            state++;
                            lock.notifyAll();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        Thread c = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 10; i++) {
                        synchronized (lock) {
                            while (state % 3 != 2) {
                                lock.wait();
                            }
                            System.out.println("C");
                            state++;
                            lock.notifyAll();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        a.start();
        b.start();
        c.start();
    }
}

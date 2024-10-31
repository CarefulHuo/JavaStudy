package cf.carefulhuo.thread.countdownlatch;

import java.util.concurrent.CountDownLatch;

public class DecRunable implements Runnable {

    private CountDownLatch countDownLatch;

    public DecRunable(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try {
            for (long i = this.countDownLatch.getCount(); i > 0; i--) {
                System.out.println("countDownLatch count: " + i);
                // countDown 每次减一时，释放一条线程，减为0 时，唤醒等待的线程
                this.countDownLatch.countDown();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

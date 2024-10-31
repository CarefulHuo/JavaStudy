package cf.carefulhuo.thread.countdownlatch;

import java.util.concurrent.CountDownLatch;

public class InrRunable implements Runnable{

    private CountDownLatch countDownLatch;

    public InrRunable(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try {
            System.out.println("await");
            // 等待 countDownLatch 计数器为 0 时的唤醒
            countDownLatch.await();
            System.out.println("runnable run");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

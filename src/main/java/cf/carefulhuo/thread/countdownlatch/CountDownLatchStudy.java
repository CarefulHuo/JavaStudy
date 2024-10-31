package cf.carefulhuo.thread.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch 的学习
 */
public class CountDownLatchStudy {

    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(5);
        DecRunable decRunable = new DecRunable(countDownLatch);
        InrRunable inrRunable = new InrRunable(countDownLatch);

        new Thread(decRunable).start();
        new Thread(inrRunable).start();
    }

}

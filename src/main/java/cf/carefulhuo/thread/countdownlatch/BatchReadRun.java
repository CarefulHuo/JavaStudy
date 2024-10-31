package cf.carefulhuo.thread.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BatchReadRun {

    CountDownLatch readCount = new CountDownLatch(10);

    public void read() throws Exception {
        System.out.println(Thread.currentThread().getName() + "读取文件开始");
        Thread.sleep((long) (Math.random() * 1000));
        System.out.println(Thread.currentThread().getName() + "读取文件结束");
        // 任务完成，readCount 减 1
        readCount.countDown();
    }

    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        // 创建任务
        BatchReadRun batchReadRun = new BatchReadRun();
        for (int i = 0; i < 10; i++) {
            Runnable runnable = () -> {
                try {
                    // 分 10个批次执行任务
                    batchReadRun.read();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            };
            service.submit(runnable);
        }
        try {
            System.out.println(Thread.currentThread().getName() + "等待文件读取结束");
            // 主线程等待，readCount 减为 0 后，被唤醒
            batchReadRun.readCount.await();
            System.out.println(Thread.currentThread().getName() + "文件读取结束");
        } catch (Exception e) {
            e.printStackTrace();
        }
        service.shutdown();
    }
}

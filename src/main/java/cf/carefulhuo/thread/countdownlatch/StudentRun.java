package cf.carefulhuo.thread.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 通过学生与裁判的故事，体现出来线程之间的协作，以及 CountDownLatch 的使用
 */
public class StudentRun {

    CountDownLatch trial = new CountDownLatch(1);
    CountDownLatch student = new CountDownLatch(10);

    public void studentRun() throws Exception {
        System.out.println("选手" + Thread.currentThread().getName() + "正在等待裁判发布命令");
        // 等待裁判发布命令，当 trial 计数器为0时，裁判发布命令，唤醒等待的选手线程
        trial.await();
        System.out.println("选手" + Thread.currentThread().getName() + "接收到裁判发布的命令");
        // 某个选手到达终点，student 的计数器减一，释放一条选手线程
        Thread.sleep((long) (Math.random() * 1000));
        student.countDown();
        System.out.println("选手" + Thread.currentThread().getName() + "跑步完成");
    }

    public void trialRun() throws Exception {
        Thread.sleep((long) (Math.random() * 1000));
        System.out.println("裁判" + Thread.currentThread().getName() + "正在等待所有选手准备就绪");
        // 裁判发布命令，即 trial 计数器为0时，发布命令任务完成，唤醒等待的学生线程
        trial.countDown();
        System.out.println("裁判" + Thread.currentThread().getName() + "发布命令，所有选手开始跑步");
        // 等待学生到达终点，当 student 计数器为0时，学生全部到底终点，唤醒等待的 trial 线程
        student.await();
        System.out.println("所有学生到达终点");
        System.out.println("裁判" + Thread.currentThread().getName() + "汇总成绩");
    }

    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        StudentRun studentRun = new StudentRun();
        for (int i = 0; i < 10; i++) {
            Runnable runnable = ()->{
                try {
                    studentRun.studentRun();
                }catch (Exception e){
                    e.printStackTrace();
                }
            };
            service.submit(runnable);
        }
        try {
            studentRun.trialRun();
        }catch (Exception e){
            e.printStackTrace();
        }

        service.shutdown();
    }


}

package cf.carefulhuo.thread.multithread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockABC {

    // 共享变量，决定打印顺序
    private static int state = 0;

    // 可重入锁
    private static final ReentrantLock lock = new ReentrantLock();

    // 三个条件对象，分别绑定 A B C 线程
    private static final Condition A = lock.newCondition();
    private static final Condition B = lock.newCondition();
    private static final Condition C = lock.newCondition();

    public static void main(String[] args) {
        Thread a = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    for (int i = 0; i < 10; i++) {
                        lock.lock();
                        while(state%3 != 0){
                            A.await();
                        }
                        System.out.println("A");
                        state++;
                        B.signal();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }
            }
        });

        Thread b = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    for (int i = 0; i < 10; i++) {
                        lock.lock();
                        while(state %3 != 1){
                            B.await();
                        }
                        System.out.println("B");
                        state++;
                        C.signal();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }
            }
        });

        Thread c = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    for (int i = 0; i < 10; i++) {
                        lock.lock();
                        while(state %3 != 2){
                            C.await();
                        }
                        System.out.println("C");
                        state++;
                        A.signal();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }
            }
        });

        a.start();
        b.start();
        c.start();
    }
}

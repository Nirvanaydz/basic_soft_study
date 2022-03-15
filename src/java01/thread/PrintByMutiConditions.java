package java01.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 通过多个condition完成对线程执行顺序的操作
 * 线程A执行打印a
 * 线程B执行打印b
 * 线程C执行打印c
 * @author yudazhi
 */
public class PrintByMutiConditions {
    private static ReentrantLock lock = new ReentrantLock();
    volatile private static int nextPrintWho = 1;
    private final static Condition CONDITION_A = lock.newCondition();
    private final static Condition CONDITION_B = lock.newCondition();
    private final static Condition CONDITION_C = lock.newCondition();

    public static void main(String[] args) {
        Thread threadA = new Thread() {
            @Override
            public void run() {
                lock.lock();
                try {
                    while (nextPrintWho != 1) {
                        CONDITION_A.await();
                    }
                    System.out.println("thread-a working");
                    nextPrintWho = 2;
                    //  let thread b work
                    CONDITION_B.signalAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        };
        Thread threadB = new Thread() {
            @Override
            public void run() {
                lock.lock();
                try {
                    while (nextPrintWho != 2) {
                        CONDITION_B.await();
                    }
                    System.out.println("thread-b working");
                    nextPrintWho = 3;
                    //  let thread c work
                    CONDITION_C.signalAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        };
        Thread threadC = new Thread() {
            @Override
            public void run() {
                lock.lock();
                try {
                    while (nextPrintWho != 3) {
                        //  waiting
                        CONDITION_C.await();
                    }
                    System.out.println("thread-c working");
                    nextPrintWho = 1;
                    //  let thread a work
                    CONDITION_A.signalAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        };
        Thread[] aArray = new Thread[5];
        Thread[] bArray = new Thread[5];
        Thread[] cArray = new Thread[5];
        for (int i = 0; i < 5; i++) {
             aArray[i] = new Thread(threadA);
             bArray[i] = new Thread(threadB);
             cArray[i] = new Thread(threadC);
             aArray[i].start();
             bArray[i].start();
             cArray[i].start();
        }
    }
}

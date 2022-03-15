package java01.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 多线程通信，交替打印abc，100次
 * 线程间通信
 * @author yudazhi
 */
public class MyPrint {
    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    private int flag = 0;
    public void printA() {
        lock.lock();
        try {
            while (flag % 3 != 0) {
                condition.await();
            }
            System.out.print("a");
            flag = flag + 1;
            //  一对一时，使用signal方法通知一个
            condition.signalAll();
            lock.getWaitQueueLength(condition);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public void printB() {
        lock.lock();
        try {
            while (flag % 3 != 1) {
                condition.await();
            }
            System.out.print("b");
            flag = flag + 1;
            //  一对一时，使用signal方法通知一个
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public void printC() {
        lock.lock();
        try {
            while (flag % 3 != 2) {
                condition.await();
            }
            System.out.print("c");
            System.out.println();
            flag = flag + 1;
            //  一对一时，使用signal方法通知一个
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


    private static class MyThreadA extends Thread {
        private MyPrint myService;

        public MyThreadA(MyPrint myService) {
            super();
            this.myService = myService;
        }

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                 myService.printA();
            }
        }
    }

    private static class MyThreadB extends Thread {
        private MyPrint myService;

        public MyThreadB(MyPrint myService) {
            super();
            this.myService = myService;
        }

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                 myService.printB();
            }
        }
    }

    private static class MyThreadC extends Thread {
        private MyPrint myService;

        public MyThreadC(MyPrint myService) {
            super();
            this.myService = myService;
        }

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                 myService.printC();
            }
        }
    }

    public static void main(String[] args) {
        MyPrint myService = new MyPrint();
        MyThreadA a = new MyThreadA(myService);
        MyThreadB b = new MyThreadB(myService);
        MyThreadC c = new MyThreadC(myService);
        a.start();
        b.start();
        c.start();
    }
}

package java01.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 单个消费者和单个生产者模式，交替执行打印❤️和🌟
 * 线程间通信
 * @author yudazhi
 */
public class MyService {
    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    private boolean hasValue = false;
    public void set() {
        lock.lock();
        try {
            while (hasValue == true) {
                System.out.println("有可能时🌟🌟连续");
                condition.await();
            }
            System.out.println("🌟");
            hasValue = true;
            //  一对一时，使用signal方法通知一个
            condition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public void get() {
        lock.lock();
        try {
            while (hasValue == false) {
//                System.out.println("有可能时❤️❤️连续");
                condition.await();
            }
            System.out.println("❤️");
            hasValue = false;
            //  一对一时，使用signal方法通知一个
            condition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    private static class MyThreadA extends Thread {
        private MyService myService;

        public MyThreadA(MyService myService) {
            super();
            this.myService = myService;
        }

        @Override
        public void run() {
            for (int i = 0; i < Integer.MAX_VALUE; i++) {
                 myService.set();
            }
        }
    }

    private static class MyThreadB extends Thread {
        private MyService myService;

        public MyThreadB(MyService myService) {
            super();
            this.myService = myService;
        }

        @Override
        public void run() {
            for (int i = 0; i < Integer.MAX_VALUE; i++) {
                 myService.get();
            }
        }
    }

    public static void main(String[] args) {
        MyService myService = new MyService();
        MyThreadA a = new MyThreadA(myService);
        MyThreadB b = new MyThreadB(myService);
        a.start();
        b.start();
    }
}

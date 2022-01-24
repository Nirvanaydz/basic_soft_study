package java01.concurrentstudy.chapter01;

/**
 * 在并发累计操作数低于百万级别时，并发的耗时大于单线程
 * 原因：并发时，线程有创建和上下文切换的开销
 * @author yudazhi
 */
public class ConcurrencyTest {
    /**
     *              serial  concurrency
     * 100000000    105ms   58ms
     * 10000000     14ms    14ms
     * 1000000      8ms     6ms
     * 100000       3ms     3ms
     * 10000        0ms     2ms
     */
    private static final long COUNT = 10000L;
    public static void main(String[] args) throws InterruptedException {
        concurrency();
        serial();
    }

    /**
     * 单线程运行
     */
    private static void serial() {
        long start = System.currentTimeMillis();
        int a = 0;
        for (int i = 0; i < COUNT; i++) {
            a += 5;
        }
        int b = 0;
        for (int i = 0; i < COUNT; i++) {
            b --;
        }
        long time = System.currentTimeMillis() - start;
        System.out.println("serial cost " + time + "ms,b = " + b);
    }

    /**
     * 多线程运行
     * @throws InterruptedException
     */
    private static void concurrency() throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int a = 0;
                for (int i = 0; i < COUNT; i++) {
                    a += 5;
                }
            }
        });
        thread.start();
        int b = 0;
        for (int i = 0; i < COUNT; i++) {
            b --;
        }
        thread.join();
        long time = System.currentTimeMillis() - start;
        System.out.println("concurrency cost " + time + "ms,b = " + b);
    }


}

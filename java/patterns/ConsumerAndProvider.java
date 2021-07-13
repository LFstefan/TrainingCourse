/**
 * 可以基于不同的锁实现
 * synchronized
 * reedtrantlock，需要手动释放锁，try{}finally{... release lock ...}
 * 
 */
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConsumerAndProvider {
    public static void main(String[] args) {
        ConsumerAndProvider test = new ConsumerAndProvider();
        ConsumerThread consumer = test.new ConsumerThread();
        ProviderThread provider = test.new ProviderThread();
        test.pool.add(1);
        test.pool.add(1);
        new Thread(consumer).start();
        new Thread(provider).start();
        System.out.println("演示结束");
    }

    class ConsumerThread implements Runnable {
        @Override
        public void run() {
            consumer();
        }
    }

    class ProviderThread implements Runnable {
        @Override
        public void run() {
            provider();
        }
    }

    /* ------------------------------ synchronized 加锁实现 ----------------------------- */

    public List<Integer> pool = new ArrayList<>();
    public int cap = 30;
    public boolean isFull = false;
    public boolean isNotFull = true;
    public boolean isEmpty = false;
    public boolean isNotEmpty =true;
    public int consumerLimit = 1;
    public int providerLimit = 1;

    /**
     * 消费者
     */
    public void consumer () {
        while (isNotEmpty){
            if (consumerLimit >= 100) break;
            consumerLimit++;

            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (pool){
                if (!isEmpty){
                    pool.remove(0);
                    System.out.println("consumer has consumed one resource");
                    isEmpty = pool.size() <= 0;
                    pool.notifyAll(); // notify other thread get the resource which has been released
                    continue;
                }
                try {
                    pool.wait(); // current thread waiting resource been released
//                Thread.sleep(1000L);
                } catch (InterruptedException e){}
            }
        }
    }

    /**
     * 生产者
     */
    public void provider () {
        while (isNotFull){
            if (providerLimit >= 100) break;
            providerLimit++;

            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (pool){
                if (!isFull){
                    pool.add(1);
                    System.out.println("provider has provided one resource");
                    isFull = pool.size() >= cap;
                    pool.notifyAll();
                    continue;
                }
                try {
                    pool.wait();
//                Thread.sleep(1000L);
                } catch (InterruptedException e){}
            }
        }
    }

    /* ------------------------------ ReentrantLock 加锁实现 ----------------------------- */

    // 自定义公平锁/非公平锁
//    ReentrantLock lock = new ReentrantLock();
    public Lock lock = new ReentrantLock(true);
    // 资源索取条件
    public Condition take = lock.newCondition();
    // 资源填充条件
    public Condition fill = lock.newCondition();

    /**
     * 消费者
     */
    public void consumer1 () {
        while (isNotEmpty){
            if (consumerLimit >= 100) break;
            consumerLimit++;

            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            lock.lock();
            if (!isEmpty){
                pool.remove(0);
                System.out.println("consumer has consumed one resource");
                isEmpty = pool.size() <= 0;
                fill.signal(); // notify other thread get the resource which has been released
                continue;
            }
            try {
                take.wait(); // 类比object对象的wait方法，挂起当前线程到等待队列中，等待资源释放
//                Thread.sleep(1000L);
            } catch (InterruptedException e){}
            lock.unlock();
        }
    }

    /**
     * 生产者
     */
    public void provider1 () {
        while (isNotFull){
            if (providerLimit >= 100) break;
            providerLimit++;

            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            lock.lock();
            if (!isFull){
                pool.add(1);
                System.out.println("provider has provided one resource");
                isFull = pool.size() >= cap;
                take.signal();
                continue;
            }
            try {
                fill.wait();
//                Thread.sleep(1000L);
            } catch (InterruptedException e){}
            lock.unlock();
        }
    }

    /* ------------------------------ BlockingQueue 阻塞队列实现 ----------------------------- */

    // 阻塞队列分为有界/无界，队空会阻塞消费线程，队满会阻塞生产线程，由此衍生出不同的阻塞策略
    BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(100);

    /**
     * 消费者
     */
    public void consumer2 () {
        while (isNotEmpty){
            if (consumerLimit >= 100) break;
            consumerLimit++;

            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                // 阻塞等待资源，还是有限期内等待，还是直接返回null
//                queue.take();
                queue.poll(3, TimeUnit.SECONDS);
//                queue.poll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("consumer has consumed one resource");
        }
    }

    /**
     * 生产者
     */
    public void provider2 () {
        while (isNotFull){
            if (providerLimit >= 100) break;
            providerLimit++;

            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                // 资源的填充，阻塞等待填充，还是直接返回false
//                queue.offer(1);
                queue.offer(1, 3, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("provider has provided one resource");
        }
    }

    /* ------------------------------ BlockingQueue 阻塞队列实现 ----------------------------- */

    Semaphore mutex = new Semaphore(1);

    /**
     * 消费者
     */
    public void consumer3 () {
        while (isNotEmpty){
            if (consumerLimit >= 100) break;
            consumerLimit++;

            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                mutex.acquire();
                if (!isEmpty)
                    pool.remove(0);
                isEmpty = pool.size() <= 0;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mutex.release();
            System.out.println("consumer has consumed one resource");
        }
    }

    /**
     * 生产者
     */
    public void provider3 () {
        while (isNotFull){
            if (providerLimit >= 100) break;
            providerLimit++;

            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                mutex.acquire();
                if (!isFull)
                    pool.add(1);
                isFull = pool.size() >= cap;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mutex.release();
            System.out.println("provider has provided one resource");
        }
    }
}
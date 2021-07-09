/**
 * 可以基于不同的锁实现
 * synchronized
 * reedtrantlock，需要手动释放锁，try{}finally{... release lock ...}
 * 
 */
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
}

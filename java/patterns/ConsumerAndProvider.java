public class ConsumerAndProvider {
    public List<Integer> pool = new ArrayList<>();
    public int cap = 30;
    public boolean isFull = false;
    public boolean isNotFull = true;
    public boolean isEmpty = true;
    public boolean isNotEmpty =false;

    /**
     * 消费者
     */
    public void consumer () {
        while (isNotEmpty){
            synchronized (pool){
                if (!isEmpty){
                    pool.remove(0);
                    isEmpty = pool.size() <= 0;
                    continue;
                }
            }
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e){}
        }
    }

    /**
     * 生产者
     */
    public void provider () {
        while (isNotFull){
            synchronized (pool){
                if (!isFull){
                    pool.add(1);
                    isFull = pool.size() >= cap;
                    continue;
                }
            }
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e){}
        }
    }
}
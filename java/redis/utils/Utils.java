package dcits.liufein.utils;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;

/**
 * @author liufei
 * @date 10/20/2019
 */
public class Utils {
    private static final Timer TIMER = new Timer();
    // TODO 线程池核心线程数的大小如何根据实际需求指定
    private static final int CPU_NUM = Runtime.getRuntime().availableProcessors();

    public static ScheduledExecutorService createThreadPool() {
        /*  Executors.newScheduledThreadPool(CPU_NUM);
            不建议使用Executors来创建线程池，原因如下
            最大请求队列容量为Integer的最大值，请求创建太多容易造成oom
            最大线程容量同样也是Integer的最大值，线程创建太多同样会造成oom
        */
        ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(CPU_NUM,
                new BasicThreadFactory
                        .Builder()
                        .namingPattern("liufei")
                        .daemon(true)
                        .build());
        return executorService;
    }

    public static <T> void schedule(Consumer<T> comsumer, long delay, T t) {
        TIMER.schedule(new TimerTask() {
            @Override
            public void run() {
                comsumer.accept(t);
            }
        }, delay);
    }

    public static <T> void schedule(Consumer<T> comsumer, Date date, T t) {
        TIMER.schedule(new TimerTask() {
            @Override
            public void run() {
                comsumer.accept(t);
            }
        }, date);
    }

    public static <T> void schedule(Consumer<T> comsumer, long delay, long period, T t) {
        TIMER.schedule(new TimerTask() {
            @Override
            public void run() {
                comsumer.accept(t);
            }
        }, delay, period);
    }

    public static <T> void schedule(Consumer<T> comsumer, Date firstTime, long period, T t) {
        TIMER.schedule(new TimerTask() {
            @Override
            public void run() {
                comsumer.accept(t);
            }
        }, firstTime, period);
    }


    public static void main(String[] args) {
        /*schedule(System.out::println, 5000L, "5秒钟后输出");
        schedule(System.out::println, new Date(), "现在时间输出");
        schedule(System.out::println, 5000L, 2000L, "5秒钟后输出，之后每隔两秒输出一次");
        schedule(System.out::println, new Date(), 2000L, "现在时间输出,之后每隔两秒输出一次");*/

        System.out.println(LocalDateTime.now());
        AtomicBoolean flag = new AtomicBoolean(true);

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                System.out.println(LocalDateTime.now());
                if (flag.get()) {
                    try {
                        Thread.sleep(8000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    flag.set(false);
                }
            }
        };

        // schedule方法：下一次执行时间相对于 上一次 实际执行完成的时间点 ，因此执行时间会不断延后
        TIMER.schedule(task, 2000L, 3000L);
        // scheduleAtFixedRate方法：下一次执行时间相对于上一次开始的 时间点 ，因此执行时间不会延后
        TIMER.scheduleAtFixedRate(task, 2000L, 3000L);

        // 使用线程池执行定时任务，避免线程创建过多，导致OOM; TODO TimeUnit为delay和period两个时间的时间单位
        ScheduledFuture<?> resultRunnable = createThreadPool().schedule(() -> System.out.println("定时执行"), 2000L, TimeUnit.SECONDS);
        if (!resultRunnable.isDone()){
            if (!resultRunnable.isCancelled()){
                resultRunnable.cancel(true);
            }
        }

        ScheduledFuture<String> resultCallable = createThreadPool().schedule(()->{
            System.out.println("定时执行并返回结果");
            return "Hello World!";
        }, 2000L, TimeUnit.SECONDS);
        if (resultCallable.isDone()){
            try {
                System.out.println(resultCallable.get());
            } catch (InterruptedException|ExecutionException e) {
                e.printStackTrace();
            }
        }
        // 下一次执行时间相对于上一次开始的 时间点 ，因此执行时间不会延后
        ScheduledFuture<?> fixRateResult = createThreadPool().scheduleAtFixedRate(()-> System.out.println("定时周期执行"),2000L,3000L,TimeUnit.SECONDS);
        if (fixRateResult.isDone()){
            System.out.println("定时任务已经执行完毕");
        }else if (fixRateResult.isCancelled()){
            System.out.println("定时任务已取消");
        }else {
            fixRateResult.cancel(true);
        }

        //下一次执行时间相对于 上一次 实际执行完成的时间点 ，因此执行时间会不断延后;并且如果某一次的定时任务出现异常，后续任务将都会被阻塞不被执行
        ScheduledFuture<?> fixDelayResult = createThreadPool().scheduleWithFixedDelay(()-> System.out.println("定时延后执行"),2000L,3000L,TimeUnit.SECONDS);
        if (!fixDelayResult.isDone()){
            fixDelayResult.cancel(false);
        }
    }

}

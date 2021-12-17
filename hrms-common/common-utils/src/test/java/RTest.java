import com.alex.common.util.R;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author _Alexzinv_
 * @date 2021/12/17
 * @description
 */
public class RTest {

    /**
     *  结论: R对象如果写成单例，其data属性会有线程安全问题，目前已修改
     **/
    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 10, 60L,
                TimeUnit.SECONDS, new ArrayBlockingQueue<>(10));

        String str = "a";
        for (int i = 0; i < 10; i++) {
            int code = i;
            executor.execute(() -> {
                Thread.currentThread().setName(str + code);
                R r = R.ok().data("aaa", code);
                System.out.println(Thread.currentThread().getName() + " ===> " + r.getData().get("aaa"));
            });
        }

        executor.shutdown();
    }
}

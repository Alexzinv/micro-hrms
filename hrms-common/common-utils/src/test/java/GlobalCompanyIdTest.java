import com.alex.common.consant.GlobalCompanyId;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author _Alexzinv_
 * @date 2021/12/10
 * @description
 */
public class GlobalCompanyIdTest {

    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 10, 60L,
                TimeUnit.SECONDS, new ArrayBlockingQueue<>(10));

        executor.execute(() -> {
            System.out.println(GlobalCompanyId.getCompanyId());
            Thread.currentThread().setName("bbbb");
            GlobalCompanyId.setCompanyId(111L);
            System.out.println(GlobalCompanyId.getCompanyId());
        });
        executor.execute(() -> {
            Thread.currentThread().setName("aaaa");
            GlobalCompanyId.setCompanyId(222L);
            System.out.println(GlobalCompanyId.getCompanyId());
            GlobalCompanyId.remove();
        });
        executor.execute(() -> {
            Long companyId = GlobalCompanyId.getCompanyId();
            System.out.println(companyId == null);
        });

        executor.shutdown();
    }
}

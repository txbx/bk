package backup;

import cn.hutool.cron.CronUtil;
import cn.hutool.cron.task.Task;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Map;

/**
 * @author : txb
 * @date: 2023/1/9 10:41
 * @description :
 */
@SpringBootApplication
public class SpringTest {
    public static void main(String[] args) {
        System.out.println("springboot 启动。。。");
        SpringApplication.run(SpringTest.class, args);

        Map<String, String> getenv = System.getenv();
        System.out.println("环境变量:" + getenv);

        // 启动就执行一次
        doBackUp();

        // 定时任务
        CronUtil.schedule("0 0 1 * * ?", (Task) App::doBackUp);
        CronUtil.setMatchSecond(true);
        CronUtil.start();
    }

    private static void doBackUp(){
        System.out.println("开始备份");
        try {
            Backup service = new Backup();
            service.doBackup();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("备份失败：" + e.getMessage());
        }
    }
}

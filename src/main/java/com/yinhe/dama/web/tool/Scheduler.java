package com.yinhe.dama.web.tool;

import com.yinhe.dama.aop.Encapsulation;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @ClassName Scheduler
 * @Description
 * @Author lc
 * @Date 2020/4/3 0003 9:24
 * @Version 1.0
 */
@Component
public class Scheduler {

   /* @Scheduled(fixedRate = 2000)*/
    public void testTasks() {
    }

    //每天3：05执行
   /* @Scheduled(cron = "0 05 03 ? * *")*/
    public void testTaskss() {
    }

}

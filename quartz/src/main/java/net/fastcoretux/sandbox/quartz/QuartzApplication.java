package net.fastcoretux.sandbox.quartz;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class QuartzApplication {


    public static void main(String[] args) {
        SpringApplication.run(QuartzApplication.class, args);
    }

    @Bean
    public JobDetail helloJobDetail() {
        return JobBuilder.newJob(HelloJob.class).withIdentity("helloJob")
                .usingJobData("name", "Hello").storeDurably().build();
    }

    @Bean
    public Trigger helloJobTrigger() {
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(2).repeatForever();

        return TriggerBuilder.newTrigger().forJob(helloJobDetail())
                .withIdentity("helloTrigger").withSchedule(scheduleBuilder).build();
    }

    @Bean
    public JobDetail goodByeJobDetail() {
        return JobBuilder.newJob(GoodByeJob.class).withIdentity("goodByeJob")
                .usingJobData("name", "GoodBye").storeDurably().build();
    }

    @Bean
    public Trigger goodByeJobTrigger() {
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(2).repeatForever();

        return TriggerBuilder.newTrigger().forJob(goodByeJobDetail())
                .withIdentity("goodByeTrigger").withSchedule(scheduleBuilder).build();
    }
}

package net.fastcoretux.sandbox.quartz.actuate;

import java.time.LocalDateTime;

import lombok.Data;

/**
 * @author fastcoretux
 * @since 17.04.2018
 */
@Data
public class JobInfo {

    private String schedulerName;
    private String triggerName;
    private String triggerGroup;
    private String jobName;
    private String jobGroup;
    private String calendarName;
    private boolean recovering;
    private int refireCount;
    private LocalDateTime fireTime;
    private LocalDateTime scheduledFireTime;
    private LocalDateTime previousFireTime;
    private LocalDateTime nextFireTime;
    private long jobRunTime;
    private String fireInstanceId;


}

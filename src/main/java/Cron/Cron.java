package Cron;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;


public class Cron {

    static final String DEFAULT_TIME = "0 12 * * WED";


    public static void ejecutarCron() throws Exception {
        //ejecutarCron(DEFAULT_TIME);
    }

    public static void ejecutarCron(Integer tiempo) {
        SchedulerFactory schedFactory = new StdSchedulerFactory();
        try {
            org.quartz.Scheduler sched = schedFactory.getScheduler();
            JobDetail job = JobBuilder.newJob(RecorrerPublicaciones.class)
                    .withIdentity("RecorrerYNotificar", "group6").build();

            Trigger trigger = TriggerBuilder
                    .newTrigger()
                    .withIdentity("Triggergrupo6", "group6")
                    .startNow()
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(tiempo).repeatForever())
                    .build();

            sched.scheduleJob(job, trigger);
            sched.start();
        }
        catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}

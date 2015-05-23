package service;

import entity.lot.Lot;

import org.apache.log4j.Logger;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Andriy on 21.05.2015.
 */
public class SoldLotJob implements Job {

    private static Logger log = Logger.getLogger(SoldLotJob.class);

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        SchedulerContext schedulerContext = null;
        try {
            schedulerContext = context.getScheduler().getContext();
        } catch (SchedulerException e1) {
           log.error(e1);
        }
        Lot soldLot = (Lot)schedulerContext.get("lot");
        soldLot.setState("SOLD");

    }
}

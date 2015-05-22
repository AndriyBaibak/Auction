package service;

import buisnessLogic.ActionWithLotImpl;
import entity.lot.Status;
import entity.lot.Lot;
import org.apache.log4j.Logger;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Inject;
import javax.jws.WebService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Andriy on 20.05.2015.
 */
@WebService(endpointInterface = "service.Lots")
public class LotsImpl implements Lots {
    private static Logger log = Logger.getLogger(LotsImpl.class);
    @Autowired
    private ActionWithLotImpl actionWithLot;


    @Override
    public Lot getLotById(int id) {
        return actionWithLot.getLotById(id);
    }

    @Override
    public void addLot(String lotName, String finishDate, double startPrice, String description, String owner, Status state, int ownerId) throws ParseException, SchedulerException {
        actionWithLot.addLot(lotName, finishDate, startPrice, description, owner, state, ownerId);
        setDeadlineLot(new Lot(lotName, finishDate, startPrice, description, owner, state, ownerId));
    }

    @Override
    public void deleteLot(int id) {
        actionWithLot.deleteLot(id);
    }

    @Override
    public ArrayList<Lot> getAllLots() {
        System.out.print(actionWithLot.getAllLots().get(0).toString());
        return actionWithLot.getAllLots();
    }

    private void setDeadlineLot(Lot lot) throws ParseException, SchedulerException {
        JobDetail job = JobBuilder.newJob(SoldLotJob.class)
                .withIdentity("soldLot", "group1").build();

        Trigger trigger = TriggerBuilder
                .newTrigger()
                .withIdentity("soldLot", "group1")
                .startAt(lot.getFinishDate())
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()).build();
        Scheduler scheduler = new StdSchedulerFactory().getScheduler();
        scheduler.getContext().put("lot", lot);
        scheduler.start();
        scheduler.scheduleJob(job, trigger);
    }

}
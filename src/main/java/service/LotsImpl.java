package service;

import buisnessLogic.ActionWithLotImpl;

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
import java.util.Vector;

/**
 * Created by Andriy on 20.05.2015.
 */
@WebService(endpointInterface = "service.Lots")
public class LotsImpl implements Lots {
    private static Logger log = Logger.getLogger(LotsImpl.class);

    private ActionWithLotImpl actionWithLot = new ActionWithLotImpl();


    @Override
    public Lot getLotById(int id) {
        return actionWithLot.getLotById(id);
    }

    @Override
    public void addLot(String lotName, String finishDate, double startPrice, String description, String owner, String state, int ownerId) throws ParseException, SchedulerException {
        actionWithLot.addLot(lotName, finishDate, startPrice, description, owner, state, ownerId);
        setDeadlineLot(new Lot(lotName, finishDate, startPrice, description, owner, state, ownerId));
    }

    @Override
    public void deleteLot(int id) {
        actionWithLot.deleteLot(id);
    }

    @Override
    public List<Lot> getAllLots() {
        return actionWithLot.getAllLots();
    }

    @Override
    public void canceledLot(int id) {
        actionWithLot.canceledLot(id);
    }

    private void setDeadlineLot(Lot lot) throws ParseException, SchedulerException {
        JobDetail job = JobBuilder.newJob(SoldLotJob.class)
                .withIdentity(lot.getLotName(), lot.getDescription()).build();

        Trigger trigger = TriggerBuilder
                .newTrigger()
                .withIdentity(lot.getLotName(), lot.getDescription())
                .startAt(lot.getFinishDate())
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()).build();
        Scheduler scheduler = new StdSchedulerFactory().getScheduler();
        scheduler.getContext().put("lot", lot);
        scheduler.start();
        scheduler.scheduleJob(job, trigger);
    }

}
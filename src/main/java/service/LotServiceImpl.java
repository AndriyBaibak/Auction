package service;

import buisnessLogic.ActionWithLotImpl;
import entity.lot.Lot;
import org.apache.log4j.Logger;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import javax.jws.WebService;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

@WebService(endpointInterface = "service.LotService")
public class LotServiceImpl implements LotService {
    private static Logger log = Logger.getLogger(LotServiceImpl.class);

    private ActionWithLotImpl actionWithLot = new ActionWithLotImpl();

    @Override
    public Lot getLotById(int id) {
        return actionWithLot.getLotById(id);
    }

    @Override
    public void addLot(String lotName, Date finishDate, double startPrice, String description) throws ParseException, SchedulerException {
        try {
            actionWithLot.addLot(lotName, finishDate, startPrice, description);
            setDeadlineLot(new Lot(lotName, finishDate, startPrice, description));
        } catch (Exception ex) {
            log.error("Exception" + ex);
        }
    }

    @Override
    public void deleteLot(int id) {
        try {
            actionWithLot.deleteLot(id);
        } catch (Exception ex) {
            log.error("Exception" + ex);
        }
    }

    @Override
    public List<Lot> getAllLots() {
        return actionWithLot.getAllLots();
    }

    @Override
    public void canceledLot(int id) {
        try {
            actionWithLot.canceledLot(id);
        } catch (Exception ex) {
            log.error("Exception" + ex);
        }
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

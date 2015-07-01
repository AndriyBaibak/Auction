package buisnessLogic;

import buisnessLogic.quartz.SoldLotJob;
import entity.lot.Lot;
import integration.LotIntegration;
import org.apache.log4j.Logger;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.List;

public class ActionWithLotImpl implements ActionWithLot {
    private static Logger log = Logger.getLogger(LotIntegration.class);


    private LotIntegration lotIntegration = new LotIntegration();

    @Override
    public void addLot(Lot lotForSaving) {
            lotIntegration.addLot(lotForSaving);
            setDeadlineLot(lotForSaving);
    }

    @Override
    public Lot getLotById(int id) {
        return lotIntegration.getLotById(id);
    }

    @Override
    public void deleteLot(int id) {
            lotIntegration.deleteLot(id);
    }

    @Override
    public void canceledLot(int id, String owner) {
            lotIntegration.canceledLot(id, owner);
    }

    @Override
    public List<Lot> getAllLots() {
        return lotIntegration.getAllLots();
    }

    private void setDeadlineLot(Lot lot) {
        JobDetail job = JobBuilder.newJob(SoldLotJob.class)
                .withIdentity(lot.getLotName(), lot.getDescription()).build();

        Trigger trigger = TriggerBuilder
                .newTrigger()
                .withIdentity(lot.getLotName(), lot.getDescription())
                .startAt(lot.getFinishDate())
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()).build();
        Scheduler scheduler = null;
        try {
            scheduler = new StdSchedulerFactory().getScheduler();
            scheduler.getContext().put("lot", lot);
            scheduler.start();
            scheduler.scheduleJob(job, trigger);
        }catch (Exception ex){
            log.error("Exception " + ex);
        }
    }
}

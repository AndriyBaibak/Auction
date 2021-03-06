package buisnessLogic.quartz;

import entity.bid.Bid;
import entity.lot.Lot;
import entity.lot.State;
import integration.BidIntegration;
import org.apache.log4j.Logger;
import org.quartz.*;

import java.util.List;

public class SoldLotJob implements Job {

    private BidIntegration bidIntegration = new BidIntegration();

    private static Logger log = Logger.getLogger(SoldLotJob.class);

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        SchedulerContext schedulerContext = null;
        try {
            schedulerContext = context.getScheduler().getContext();
        } catch (SchedulerException e1) {
            log.error(e1);
        }
        Lot soldLot = (Lot) schedulerContext.get("lot");
        List<Bid> list = bidIntegration.getAllBidsOnLotByLotId(soldLot.getCode());
        if (list.isEmpty()) {
            soldLot.setState(State.Not_sold);
        }else {
            soldLot.setState(State.Sold);
        }
    }
}

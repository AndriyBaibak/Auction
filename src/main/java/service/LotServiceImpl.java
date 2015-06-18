package service;

import buisnessLogic.ActionWithLotImpl;
import entity.lot.Lot;
import org.apache.log4j.Logger;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import javax.annotation.Resource;
import javax.annotation.Resources;
import javax.jws.HandlerChain;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

@WebService(serviceName = "LotServiceImpl",
        portName="EntityPort",
        endpointInterface = "service.LotService")
public class LotServiceImpl implements LotService {
    @Resource
    WebServiceContext webServiceContext;

    private static Logger log = Logger.getLogger(LotServiceImpl.class);

    private ActionWithLotImpl actionWithLot = new ActionWithLotImpl();

    @Override
    public Lot getLotById(int id) {
        return actionWithLot.getLotById(id);
    }

    @Override
    public void addLot(Lot lotForSaving) throws ParseException, SchedulerException {
            try {
                actionWithLot.addLot(lotForSaving);

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



}

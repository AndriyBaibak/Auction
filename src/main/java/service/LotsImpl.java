package service;

import buisnessLogic.ActionWithLotImpl;
import entity.lot.Status;
import entity.lot.Lot;

import javax.inject.Inject;
import javax.jws.WebService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Andriy on 20.05.2015.
 */
@WebService(endpointInterface = "service.Lots")
public class LotsImpl implements Lots {
    @Inject
    private ActionWithLotImpl actionWithLot;

    @Override
    public Lot getLotById(int id) {
        return actionWithLot.getLotById(id);
    }

    @Override
    public void addLot(String lotName, Date finishDate, double startPrice, String description, String owner, Status state, int ownerId) {
        actionWithLot.addLot(lotName, finishDate, startPrice, description, owner, state, ownerId);
    }

    @Override
    public void deleteLot(int id) {
        actionWithLot.deleteLot(id);
    }


}
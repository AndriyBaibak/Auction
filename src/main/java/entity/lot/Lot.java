package entity.lot;

import java.util.Date;


/**
 * Created by Andriy on 19.05.2015.
 */
public class Lot {

    private int id;
    private String lotName;
    private Date finishDate;
    private String description;
    private double startPrice;
    private String owner;
    private Status state;
    private int ownerId;

    public Lot(){}

    public Lot(String lotName, Date finishDate, double startPrice, String description){
        this.id = id++;
        this.lotName = lotName;
        this.finishDate = finishDate;
        this.startPrice = startPrice;
        this.description = description;
    }
    public Lot(String lotName, Date finishDate, double startPrice, String description, String owner, Status state, int ownerId){
        this.id = ++id;
        this.lotName = lotName;
        this.finishDate = finishDate;
        this.startPrice = startPrice;
        this.description = description;
        this.owner = owner;
        this.ownerId = ownerId;
        this.state = state;
    }

    public String getLotName() {
        return lotName;
    }

    public void setLotName(String lotName) {
        this.lotName = lotName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(double startPrice) {
        this.startPrice = startPrice;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Status getState() {
        return state;
    }

    public void setState(Status state) {
        this.state = state;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("entity.lot{");
        sb.append("id=").append(id);
        sb.append(", lotName='").append(lotName).append('\'');
        sb.append(", finishDate=").append(finishDate);
        sb.append(", description='").append(description).append('\'');
        sb.append(", startPrice=").append(startPrice);
        sb.append(", owner='").append(owner).append('\'');
        sb.append(", state=").append(state);
        sb.append(", ownerId=").append(ownerId);
        sb.append('}');
        return sb.toString();
    }
}

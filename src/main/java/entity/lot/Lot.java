package entity.lot;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;



/**
 * Created by Andriy on 19.05.2015.
 */
@Entity
@Table
public class Lot {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String lotName;
    @Temporal(TemporalType.TIMESTAMP)
    private Date finishDate;
    private String description;
    private double startPrice;
    private String owner;
    private String state;
    private int ownerId;
    private String remainingTime;
    private transient SimpleDateFormat sp = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

    public Lot(){}

    public Lot(String lotName, Date finishDate, double startPrice, String description){
        this.lotName = lotName;
        this.finishDate = finishDate;
        this.startPrice = startPrice;
        this.description = description;

    }
    public Lot(String lotName, String finishDate, double startPrice, String description, String owner, String state, int ownerId) throws ParseException {

        this.lotName = lotName;
        this.finishDate = sp.parse(finishDate);
        this.startPrice = startPrice;
        this.description = description;
        this.owner = owner;
        this.ownerId = ownerId;
        this.state = state;
        this.remainingTime = Util.defineRemainingTime(new Date(),getFinishDate());

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

    public String getState() {
        return state;
    }

    public void setState(String state) {
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
        final StringBuilder sb = new StringBuilder("Lot{");
        sb.append("id=").append(id);
        sb.append(", lotName='").append(lotName).append('\'');
        sb.append(", finishDate=").append(finishDate);
        sb.append(", description='").append(description).append('\'');
        sb.append(", startPrice=").append(startPrice);
        sb.append(", owner='").append(owner).append('\'');
        sb.append(", state=").append(state);
        sb.append(", ownerId=").append(ownerId);
        sb.append(", remainingTime=").append(remainingTime);
        sb.append('}');
        return sb.toString();
    }
}

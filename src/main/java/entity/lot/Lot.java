package entity.lot;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import javax.persistence.*;
import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;

@Entity
@Table
@XStreamAlias("Lot")
public class Lot implements Serializable, Cloneable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @XStreamAlias("id")
    private int id;
    @XStreamAlias("lotName")
    private String lotName;
    @XStreamAlias("finishDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date finishDate;
    @XStreamAlias("description")
    private String description;
    @XStreamAlias("startPrice")
    private double startPrice;
    @XStreamAlias("owner")
    private String owner;
    @XStreamAlias("state")
    @Enumerated(EnumType.STRING)
    @Column(name = "state")
    private State state;
    @XStreamAlias("remainingTime")
    private String remainingTime;

    public Lot(){}

    public Lot(String lotName, Date finishDate, double startPrice, String description){
            this.lotName = lotName;
            this.finishDate = finishDate;
            this.startPrice = startPrice;
            this.description = description;
            this.owner = "admin";
            this.state = State.active;
            this.remainingTime = Util.defineRemainingTime(new Date(), getFinishDate());
    }
    public Lot(String lotName, Date finishDate, double startPrice, String description, String owner) throws ParseException {
        this.lotName = lotName;
        this.finishDate = finishDate;
        this.startPrice = startPrice;
        this.description = description;
        this.owner = owner;
        this.state = State.active;
        this.remainingTime = Util.defineRemainingTime(new Date(),getFinishDate());

    }
   /* public Lot(int id , String lotName, Date finishDate, double startPrice, String description, String owner, State state, int ownerId, String remainingTime) throws ParseException {
        this.id = id;
        this.lotName = lotName;
        this.finishDate = finishDate;
        this.startPrice = startPrice;
        this.description = description;
        this.owner = owner;
        this.state = state;
        this.remainingTime = Util.defineRemainingTime(new Date(),getFinishDate());
    }*/


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

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void setRemainingTime(String remainingTime) {
        this.remainingTime = remainingTime;
    }

    public String getRemainingTime() {
        return remainingTime;
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
        sb.append(", remainingTime=").append(remainingTime);
        sb.append('}');
        return sb.toString();
    }
}

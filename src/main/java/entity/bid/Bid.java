package entity.bid;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
public class Bid {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @XStreamAlias("id")
    private int id;
    @XStreamAlias("newPrice")
    private double newPrice;
    @XStreamAlias("dateBid")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateBid;
    @XStreamAlias("bidderName")
    private String bidderName;
    @XStreamAlias("lotId")
    private int lotId;

    public Bid(){
    }

    public Bid(double newPrice, String bidderName, int lotId ){
        this.newPrice = newPrice;
        this.lotId = lotId;
        this.bidderName = bidderName;
        this.dateBid = new Date();
    }

   public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

   public int getLotId() {
        return lotId;
    }

    public void setLotId(int lotId) {
        this.lotId = lotId;
    }

    public double getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(double newPrice) {
        this.newPrice = newPrice;
    }

    public Date getDateBid() {
        return dateBid;
    }

    public void setDateBid(Date dateBid) {
        this.dateBid = dateBid;
    }

    public String getBidderName() {
        return bidderName;
    }

    public void setBidderName(String bidderName) {
        this.bidderName = bidderName;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Bid{");
        sb.append("id=").append(id);
        sb.append(", newPrice=").append(newPrice);
        sb.append(", dateBid=").append(dateBid);
        sb.append(", bidderName='").append(bidderName).append('\'');
        sb.append(", lotId=").append(lotId);
        sb.append('}');
        return sb.toString();
    }
}

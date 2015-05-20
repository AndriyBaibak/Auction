package entity.bid;

import java.util.Date;

/**
 * Created by Andriy on 19.05.2015.
 */
public class Bid {

    private int id;
    private double newPrice;
    private Date dateBid;
    private String bidderName;
    private int lotId;

    public Bid(){}

    public Bid(double newPrice, String bidderName, int lotId ){
        this.id = ++id;
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


package DTOs;

import java.util.Date;
import java.util.LinkedList;

public class Delivery{
    private Date date;
    private Address source;
    private LinkedList<Address> destinations;
    private String launchTime;
    private String truckSerialNumber;
    private int driverID;

    public Delivery(Date date, Address source, LinkedList<Address> destinations, String launchTime, String truckSerialNumber, int driverID){
        this.date = date;
        this.source = source;
        this.destinations = destinations;
        this.launchTime = launchTime;
        this.truckSerialNumber = truckSerialNumber;
        this.driverID = driverID;
    }

    public Delivery(){}

    public void setDate(Date date) {
        this.date = date;
    }

    public void setDestinations(LinkedList<Address> destinations) {
        this.destinations = destinations;
    }

    public void setDriverID(int driverID) {
        this.driverID = driverID;
    }

    public void setLaunchTime(String launchTime) {
        this.launchTime = launchTime;
    }

    public void setSource(Address source) {
        this.source = source;
    }

    public void setTruckSerialNumber(String truckSerialNumber) {
        this.truckSerialNumber = truckSerialNumber;
    }

    public Address getSource() {
        return source;
    }

    public Date getDate() {
        return date;
    }

    public int getDriverID() {
        return driverID;
    }

    public String getLaunchTime() {
        return launchTime;
    }

    public String getTruckSerialNumber() {
        return truckSerialNumber;
    }

    public LinkedList<Address> getDestinations() {
        return destinations;
    }

}
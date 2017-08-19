package netview;

import java.util.Date;
import java.util.LinkedList;

/**
 * Created by cellargalaxy on 2017/4/20.
 */
public class Host {
    private String address;
    private String building;
    private String floor;
    private String model;
    private String name;
    private final int times;
    private final LinkedList<PingResult> results;
    private boolean conn;
    private Date date;


    public Host(String address, String building, String floor, String model, String name) {
        this.address = address;
        this.building = building;
        this.floor = floor;
        this.model = model;
        this.name = name;
        this.times = Configuration.getPingTimes();
        results = new LinkedList<PingResult>();
        for (int i = 0; i < times; i++) {
            results.add(new PingResult());
        }
        conn = true;
        date = new Date();
    }

    public Host(int times) {
        this.times = times;
        results = new LinkedList<PingResult>();
        for (int i = 0; i < times; i++) {
            results.add(new PingResult());
        }
        conn = true;
        date = new Date();
    }


    /**
     * 如果返回true，则连通状态有改变，否则没
     *
     * @param result
     * @return
     */
    protected boolean addResult(PingResult result) {
        results.poll();
        results.add(result);
        if (conn) {
            for (PingResult pingResult : results) {
                if (pingResult.getDelay() >= 0) {
                    return false;
                }
            }
            conn = false;
            return true;
        } else {
            if (result.getDelay() >= 0) {
                conn = true;
                return true;
            } else {
                return false;
            }
        }
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTimes() {
        return times;
    }
    
    public LinkedList<PingResult> getResults() {
        return results;
    }
    
    public boolean isConn() {
        return conn;
    }

    public void setConn(boolean conn) {
        this.conn = conn;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Host{" +
                "address='" + address + '\'' +
                ", building='" + building + '\'' +
                ", floor='" + floor + '\'' +
                ", model='" + model + '\'' +
                ", name='" + name + '\'' +
                ", times=" + times +
                ", results=" + results +
                ", conn=" + conn +
                ", date=" + date +
                '}';
    }
}

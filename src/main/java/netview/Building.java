package netview;


import java.util.LinkedList;

/**
 * Created by cellargalaxy on 2017/4/26.
 */
public class Building {

    private String buildingName;
    private LinkedList<Host> hosts;

    public Building(String buildingName) {
        this.buildingName = buildingName;
        hosts = new LinkedList<Host>();
    }


    public void addHost(Host host) {
        hosts.add(host);
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public LinkedList<Host> getHosts() {
        return hosts;
    }

    public void setHosts(LinkedList<Host> hosts) {
        this.hosts = hosts;
    }
}

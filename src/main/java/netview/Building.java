package netview;


import java.util.LinkedList;

/**
 * Created by cellargalaxy on 2017/4/26.
 */
public class Building {
    private final String buildingName;
    private final LinkedList<Host> hosts;

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
    
    public LinkedList<Host> getHosts() {
        return hosts;
    }

}
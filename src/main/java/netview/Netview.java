package netview;


import org.apache.log4j.Logger;

import java.io.File;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by cellargalaxy on 2017/4/22.
 */
public class Netview {
    private static final Logger LOGGER = Logger.getLogger(Netview.class.getName());
    private static final Netview NETVIEW = new Netview();

    private LinkedList<Host> hosts;
    private PingThread pingThread;

    public static void main(String[] args) {
        Netview netview = Netview.getNETVIEW();
        netview.start();
    }

    private Netview() {
        hosts = Sql.findAllHosts(Configuration.getPingTimes());
        pingThread = new PingThread(this);
        LOGGER.info("初始化监控主类");
    }

    public static Netview getNETVIEW() {
        return NETVIEW;
    }

    public void start() {
        new Thread(pingThread, "ping线程").start();
        LOGGER.info("启动ping线程");
    }

    public synchronized void stop() {
        pingThread.stop();
        LOGGER.info("结束ping线程");
    }

    public synchronized boolean addHost(String address, String building, String floor, String model, String name) {
        Host host = new Host(address, building, floor, model, name);
        return addHost(host);
    }

    private synchronized boolean addHost(Host host) {
        if (Sql.addAddress(host)) {
            hosts.add(host);
            LOGGER.info("添加一个主机; address:" + host.getAddress() + " 地址:" + host.getBuilding() + "-" + host.getFloor() + "-" + host.getModel() + "-" + host.getName());
            return true;
        } else {
            return false;
        }
    }

    public synchronized Building addHosts(File file) {
        LinkedList<Host> hosts = ExcelReadIp.readExcelIps(file);
        if (hosts == null) {
            return new Building("文件格式异常，添加失败！");
        } else {
            Building building = new Building("下列Host添加失败");
            for (Host host : hosts) {
                if (!addHost(host)) {
                    building.addHost(host);
                }
            }
            return building;
        }
    }


    public synchronized boolean deleteHost(String address) {
        if (Sql.delleteAddress(address)) {
            Iterator<Host> iterator = hosts.iterator();
            while (iterator.hasNext()) {
                if (iterator.next().getAddress().equals(address)) {
                    iterator.remove();
                    LOGGER.info("删除主机：" + address);
                    return true;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    public synchronized Building createOutTimeBuilding() {
        Building building = new Building("超时");
        for (Host host : hosts) {
            if (!host.isConn()) {
                building.addHost(host);
            }
        }
        return building;
    }

    public synchronized Building createDemandKeyBuilding(String demandKey) {
        Building building = new Building("查询：" + demandKey);
        if (demandKey != null) {
            for (Host host : hosts) {
                if (host.getAddress().contains(demandKey) || host.getBuilding().contains(demandKey) ||
                        host.getFloor().contains(demandKey) || host.getModel().contains(demandKey)) {
                    building.addHost(host);
                } else if (host.getName() != null && host.getName().contains(demandKey)) {
                    building.addHost(host);
                }
            }
        }
        return building;
    }


    public synchronized LinkedList<Building> createAllBuilding() {
        LinkedList<Building> buildings = new LinkedList<Building>();
        main:
        for (Host host : hosts) {
            for (Building building : buildings) {
                if (building.getBuildingName().equals(host.getBuilding())) {
                    building.addHost(host);
                    continue main;
                }
            }
            Building building = new Building(host.getBuilding());
            building.addHost(host);
            buildings.add(building);
        }
        return buildings;
    }

    public synchronized Host[] createAddresses() {
        Host[] hs = new Host[hosts.size()];
        int i = 0;
        for (Host host : hosts) {
            hs[i] = host;
            i++;
        }
        return hs;
    }

    protected void dealChangeStatus(Host host) {
        LOGGER.info("是否连通：" + host.isConn() + " address:" + host.getAddress() + " 地址:" + host.getBuilding() + "-" + host.getFloor() + "-" + host.getModel() + "-" + host.getName());
    }


}

package wechat;

import netview.Host;
import netview.Netview;
import netview.PingResult;
import org.json.JSONArray;
import org.json.JSONObject;
import util.MD5;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

/**
 * Created by 孵化种子 on 2017/8/6.
 */
public class JsonHost {
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT=new SimpleDateFormat("yyyy-MM-dd HH");

    public static void main(String[] args) {
        System.out.println(jsonHostToken());
    }

    public static String jsonHostToken(){
        return MD5.encryption(SIMPLE_DATE_FORMAT.format(new Date()));
    }

    public static JSONArray jsonHosts(){
        JSONArray jsonArray=new JSONArray();
        Host[] hosts=Netview.getNETVIEW().createAddresses();
        for (Host host : hosts) {
            jsonArray.put(jsonHost(host));
        }
        return jsonArray;
    }

    private static JSONObject jsonHost(Host host){
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("address",host.getAddress());
        jsonObject.put("building",host.getBuilding());
        jsonObject.put("floor",host.getFloor());
        jsonObject.put("model",host.getModel());
        jsonObject.put("name",host.getName());
        jsonObject.put("results",jsonPingResults(host.getResults()));
        jsonObject.put("conn",host.isConn());
        jsonObject.put("date",host.getDate());
        return jsonObject;
    }

    private static JSONArray jsonPingResults(LinkedList<PingResult> results){
        JSONArray jsonPingResultArray=new JSONArray();
        for (PingResult result : results) {
            jsonPingResultArray.put(jsonPingResult(result));
        }
        return jsonPingResultArray;
    }

    private static JSONObject jsonPingResult(PingResult pingResult){
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("date",pingResult.getDate());
        jsonObject.put("delay",pingResult.getDelay());
        return jsonObject;
    }
}

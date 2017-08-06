package netview;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by cellargalaxy on 2017/4/20.
 */
public class PingResult {
    private static final Pattern PATTERN = Pattern.compile(Configuration.getPingRegularly());

    private Date date;
    private int delay;

    public PingResult() {
        date = new Date();
        delay = -1;
    }

    public PingResult(String result) {
        delay = regularlyDelay(result);
        date = new Date();
    }

    private int regularlyDelay(String string) {
        try {
            Matcher matcher = PATTERN.matcher(string);
            if (matcher.find()) {
                String delay = matcher.group();
                return new Integer(delay.substring(0, delay.length() - 2));
            } else {
                return -1;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }
}

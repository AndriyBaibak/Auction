package entity.lot;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {
    public static  SimpleDateFormat sp = new SimpleDateFormat("dd.MM.yyyy HH.mm.ss");


    public static String defineRemainingTime(Date start, Date finish) {
        long milliSecond = finish.getTime() - start.getTime();
        long days = milliSecond / (1000 * 60 * 60 * 24);
        long hours = (milliSecond / (1000 * 60 * 60));
        long minutes = (milliSecond / (1000 * 60));
        long seconds = (milliSecond / 1000);
        String str = sp.format(milliSecond);

        StringBuilder sb = new StringBuilder()
                .append(days)
                .append(" days ")
                .append(hours - 24 * days)
                .append(" hours ")
                .append(minutes - 60 * hours)
                .append(" minutes ")
                .append(seconds - 60 * minutes)
                .append(" seconds ");

        return sb.toString();
    }
}

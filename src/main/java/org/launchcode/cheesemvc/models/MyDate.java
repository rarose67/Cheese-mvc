package org.launchcode.cheesemvc.models;

import java.util.Date;

public abstract class MyDate {

    public static String diffDates(Date earlier, Date later)
    {
        long time1 = earlier.getTime();
        long time2 = later.getTime();
        long timediff = time2-time1;
        int days, hours, mins, secs;
        String diff = "";

        secs = Math.round(timediff / 1000);
        mins = secs / 60;
        secs = secs % 60;
        hours = mins / 60;
        mins = mins % 60;
        days = hours / 24;
        hours = hours % 24;

        if (days > 0) {diff += days + " days, "; }
        if (hours > 0) {diff += hours + " hours, "; }
        if (mins > 0) {diff += mins + " minutes, "; }
        if (secs > 0) {diff += secs + " seconds, "; }

        return diff;
    }
}

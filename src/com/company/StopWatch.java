package com.company;

import java.text.DecimalFormat;
import java.util.Timer;
import java.util.TimerTask;

public class StopWatch {
    static int interval;
    static Timer timer;
    public static int hour;
    public static int mins;
    public static int secs;

    public static void main(String[] args) {
        int delay = 1000;
        int period = 1000;
        timer = new Timer();
        interval = 3601;
        System.out.println("00:00:00");
        timer.scheduleAtFixedRate(new TimerTask() {

            public void run() {
                System.out.println(setInterval());

            }
        }, delay, period);
    }

    private static final String setInterval() {
        ++interval;
        int time1 = interval;
        secs = time1%60;
        mins = (int) Math.floor(interval/60)%60;
        hour = (int) Math.floor(interval/3600);

        return new DecimalFormat("00").format(hour)+":"+new DecimalFormat("00").format(mins)+":"+new DecimalFormat("00").format(secs);
    }
}
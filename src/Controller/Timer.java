package Controller;


import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Timer extends JLabel implements Runnable{
    public static int sec = 0;
    public String time;
    @Override
    public synchronized void run() {
        while (true) {
            try {
                Thread.sleep(1000);
                System.out.println(check());
            } catch (InterruptedException ex) {
                Logger.getLogger(Timer.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    public void setZero(){
        sec = -1;
    }

    public String check() {

        sec += 1;
        time = (new DecimalFormat("00").format(Math.floor(sec / 3600) % 24) + ":" + new DecimalFormat("00").format(Math.floor(sec / 60) % 60) + ":" + new DecimalFormat("00").format(Math.floor(sec % 60)));
        return time;


    }

    public String getTime(){
        return String.valueOf(sec);
    }
    public String getLastTime(){
        return time;
    }
}





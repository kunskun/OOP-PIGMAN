package Controller;


import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Timer extends JLabel implements Runnable{
    @Override
    public void run(){
        while(true){

            setText(check(sec));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(RunClock.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
    private int pre = 0, hr = 0, min = 0, sec = 0;
    public String check(int num){

        sec += 1;

        return (new DecimalFormat("00").format(Math.floor(sec/3600)%24)+" : "+new DecimalFormat("00").format(Math.floor(sec/60)%60)+" : "+new DecimalFormat("00").format(Math.floor(sec%60)));


    }





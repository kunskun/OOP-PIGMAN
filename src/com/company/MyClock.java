package com.company;

import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;

public class MyClock extends JLabel implements Runnable{

    @Override
    public void run() {
        while(true){

            Calendar d = Calendar.getInstance();
            int sec = d.get(Calendar.SECOND);
            int min = d.get(Calendar.MINUTE);
            int hour = d.get(Calendar.HOUR_OF_DAY);
            setText(checkNum(hour)+" : "+checkNum(min)+" : "+checkNum(sec));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(MyClock.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public String checkNum(int num){
        String nums = num+"";
        if(nums.length() != 2){
            return ("0"+nums);
        } else {
            return nums;
        }
    }


}

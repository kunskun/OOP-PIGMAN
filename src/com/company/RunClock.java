package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RunClock extends JLabel implements Runnable{
        @Override
        public void run(){
            while(true){
                Calendar d = Calendar.getInstance();
                int sec = d.get(Calendar.SECOND);
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
            if(num != pre){
                sec += 1;
                if (sec < 60) {
                    return (checkNum(hr)+" : "+checkNum(min)+" : "+checkNum(sec-1));
                } else {
                    min += 1; sec %= 60;
                    if(min < 60){
                        return (checkNum(hr)+" : "+checkNum(min)+" : "+checkNum(sec-1));
                    } else {
                        hr += 1;min %= 60; sec %= 60;
                        return (checkNum(hr)+" : "+checkNum(min)+" : "+checkNum(sec-1));
                    }
                }
            }
            pre = num;
            return (checkNum(hr)+" : "+checkNum(min%60)+" : "+checkNum((sec-1)%60));
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



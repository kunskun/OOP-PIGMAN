package com.company;

import javax.swing.*;
import java.awt.*;

public class RunFrame {
        private JFrame f;
        private RunClock r;
        private Thread t;

        public RunFrame(){
            f = new JFrame();
            r = new RunClock();
            t = new Thread((Runnable) r);

            f.setLayout(new BorderLayout());
            f.add(r);
            f.setVisible(true);
            f.setSize(300,300);

            t.start();
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
    }



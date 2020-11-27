/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oop.pigman;

import javax.swing.*;
import javax.swing.*;

/**
 *
 * @author asus
 */
public class test {
    private JFrame f;
    private JTextArea p;
    public test(){
        f = new JFrame();
        p = new JTextArea();
        f.add(p);
        p.setText("TEST");
        f.setVisible(true);
        f.setSize(200,200);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

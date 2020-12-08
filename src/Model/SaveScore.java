package Model;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.*;

public class SaveScore {
    public SaveScore(String time){
        try(FileOutputStream fout = new FileOutputStream("Score.dat");
            OutputStreamWriter oout = new OutputStreamWriter(fout);) {

            PrintWriter p = new PrintWriter(oout);
//            System.out.println(p);
            p.println(time);
//            System.out.println("--->"+time);
        } catch(IOException e){
            System.out.println(e.toString());;
        }
    }
}

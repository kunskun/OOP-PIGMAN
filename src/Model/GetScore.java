package Model;

import java.io.*;

public class GetScore {
    private String time;
    public GetScore(){
        try{
            FileInputStream fin = new FileInputStream("Score.dat");
            InputStreamReader in = new InputStreamReader(fin);
            BufferedReader bin = new BufferedReader(in);

            time = bin.readLine();
//            System.out.println(time);

            bin.close();
            in.close();
            fin.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String getScore(){
        return time;
    }
}

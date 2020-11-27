package oop.pigman.Controller;

import oop.pigman.Model.KeyModel;
import oop.pigman.View.Display;
import oop.pigman.View.Graphic.Assets;

public class GameController implements Runnable{

    //set outside class attribute
    private Display display;
    private KeyModel k;
    //set inside class attribute
    private int width, height;
    private String title;


    public GameController(String title, int width, int height){
        this.title = title;
        this.width = width;
        this.height = height;
        k = new KeyModel();
    }

    public void init(){
        display = new Display(title, width, height);
        display.addKeyListener(k);
        Assets.init();  //load image


    }


    @Override
    public void run() {

    }
}

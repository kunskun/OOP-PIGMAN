package Model;

import Model.TextureModel;

import java.awt.*;
import java.util.Random;

public class TileModel extends Rectangle {
    private static final long serialVersionUID = 1L;

    public TileModel(int x, int y){
        setBounds(x, y, 32, 32);

    }

    public void render(Graphics g){
            g.drawImage(TextureModel.groundGlass2, x, y, 32, 32, null);
    }
}

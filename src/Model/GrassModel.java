package Model;

import Model.TextureModel;

import java.awt.*;

public class GrassModel extends Rectangle {
    public GrassModel(int x, int y) {
        setBounds(x, y, 32, 32);
    }

    public void render(Graphics g){
        g.drawImage(TextureModel.groundGlass1, x, y, 32, 32, null);
    }
}

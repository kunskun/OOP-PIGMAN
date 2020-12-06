package View;

import Model.TextureModel;

import java.awt.*;

public class GlassView extends Rectangle {
    public GlassView(int x, int y) {
        setBounds(x, y, 32, 32);
    }

    public void render(Graphics g){
        g.drawImage(TextureModel.groundGlass1, x, y, 32, 32, null);
    }
}

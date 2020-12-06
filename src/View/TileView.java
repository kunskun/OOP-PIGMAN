package View;

import Model.TextureModel;

import java.awt.*;

public class TileView extends Rectangle {
    private static final long serialVersionUID = 1L;

    public TileView(int x, int y){
        setBounds(x, y, 32, 32);

    }

    public void render(Graphics g){
//        g.setColor(new Color(255,50,252));
//        g.fillRect(x, y, width, height);
        g.drawImage(TextureModel.groundGlass2, x, y, 32, 32, null);
    }
}

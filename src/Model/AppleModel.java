package Model;

import java.awt.*;

public class AppleModel extends Rectangle {
    private static final long serialVersionUID = 1L;

    public AppleModel(int x, int y) {
        setBounds(x+10, y+8, 8, 8);
    }

    public void render(Graphics g){
//        g.setColor(Color.green);
//        g.fillRect(x, y, width, height);
        g.drawImage(TextureModel.apple, x, y, 16, 16, null);
    }
}



import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Pipe {
    private int x, y, width, height;
    private Image pipeHead, pipeLength;
    private boolean isTopPipe;

    public Pipe(int startX, int startY, int width, int height, boolean isTopPipe) {
        x = startX;
        y = startY;
        this.width = width;
        this.height = height;
        this.isTopPipe = isTopPipe;

        try {
            pipeHead = ImageIO.read(new File("src/images/78px-Pipe.png"));
            pipeLength = ImageIO.read(new File("src/images/pipe_part.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void moveLeft() {
        x -= 3;
    }

    public void paint(Graphics g) {
        g.drawImage(pipeHead, x, y, width, GamePanel.PIPE_H, null);
        g.drawImage(pipeLength, x, y + GamePanel.PIPE_H, width, height - GamePanel.PIPE_H, null);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public boolean isTopPipe() {
        return isTopPipe;
    }
}

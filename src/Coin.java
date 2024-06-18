import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Coin {
    public int x, y;
    public static final int SIZE = 20;

    public Coin(int startX, int startY) {
        x = startX;
        y = startY;
    }

    public void moveLeft() {
        x -= 3;
    }

    public void paint(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillOval(x, y, SIZE, SIZE);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 12));
        g.drawString("$", x + 6, y + 14);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, SIZE, SIZE);
    }
}

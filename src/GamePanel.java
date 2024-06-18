import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.ArrayList;

public class GamePanel extends JPanel {
    private Bird bird;
    private ArrayList<Pipe> pipes;
    private ArrayList<Coin> coins;
    private GameEngine gameEngine;
    private Font scoreFont, pauseFont;
    public static final Color bg = new Color(0, 158, 158);
    public static final int PIPE_W = 50, PIPE_H = 30;

    public GamePanel(GameEngine gameEngine, Bird bird, ArrayList<Pipe> pipes, ArrayList<Coin> coins) {
        this.gameEngine = gameEngine;
        this.bird = bird;
        this.pipes = pipes;
        this.coins = coins;
        scoreFont = new Font("Comic Sans MS", Font.BOLD, 18);
        pauseFont = new Font("Arial", Font.BOLD, 48);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(bg);
        g.fillRect(0, 0, GameEngine.WIDTH, GameEngine.HEIGHT);
        bird.update(g);
        for (Pipe pipe : pipes) {
            pipe.paint(g);
        }
        for (Coin coin : coins) {
            coin.paint(g);
        }
        g.setFont(scoreFont);
        g.setColor(Color.BLACK);
        g.drawString("Score: " + gameEngine.getScore(), 10, 20);

        if (gameEngine.isPaused()) {
            g.setFont(pauseFont);
            FontMetrics fm = g.getFontMetrics();
            String pausedText = "PAUSED";
            String startText = "PRESS SPACE TO BEGIN";
            int pausedTextWidth = fm.stringWidth(pausedText);
            int startTextWidth = fm.stringWidth(startText);
            g.setColor(new Color(0, 0, 0, 170));
            g.drawString(pausedText, (GameEngine.WIDTH - pausedTextWidth) / 2, GameEngine.HEIGHT / 2 - 100);
            g.drawString(startText, (GameEngine.WIDTH - startTextWidth) / 2, GameEngine.HEIGHT / 2 + 50);
        }
    }
}

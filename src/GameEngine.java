import javax.swing.JOptionPane;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class GameEngine implements KeyListener, MouseListener, Runnable {
    public static final int FPS = 60, WIDTH = 640, HEIGHT = 480;

    private Bird bird;
    private GamePanel panel;
    private ArrayList<Pipe> pipes;
    private ArrayList<Coin> coins;
    private int time, scroll, coinScore;
    private boolean paused;

    public GameEngine() {
        bird = new Bird();
        pipes = new ArrayList<>();
        coins = new ArrayList<>();
        panel = new GamePanel(this, bird, pipes, coins);
        panel.addMouseListener(this);  // Add mouse listener to the panel
    }

    public void start() {
        paused = true;
        new Thread(this).start();
    }

    @Override
    public void run() {
        while (true) {
            if (!paused) {
                bird.physics();
                if (scroll % 90 == 0) {
                    int pipeHeight = (int) ((Math.random() * HEIGHT) / 5f + (0.2f) * HEIGHT);
                    pipes.add(new Pipe(WIDTH, 0, GamePanel.PIPE_W, pipeHeight, true));
                    pipes.add(new Pipe(WIDTH, HEIGHT - pipeHeight, GamePanel.PIPE_W, pipeHeight, false));
                }
                if (scroll % 150 == 0) {
                    int coinY = (int) (Math.random() * (HEIGHT - Coin.SIZE));
                    coins.add(new Coin(WIDTH, coinY));
                }
                ArrayList<Pipe> pipesToRemove = new ArrayList<>();
                ArrayList<Coin> coinsToRemove = new ArrayList<>();
                boolean game = true;
                for (Pipe pipe : pipes) {
                    pipe.moveLeft();
                    if (pipe.getBounds().x + pipe.getBounds().width <= 0) {
                        pipesToRemove.add(pipe);
                    }
                    if (pipe.getBounds().intersects(bird.x - Bird.RAD, bird.y - Bird.RAD, 2 * Bird.RAD, 2 * Bird.RAD)) {
                        JOptionPane.showMessageDialog(null, "You lose!\n" + "Your score was: " + getScore() + ".");
                        game = false;
                    }
                }
                for (Coin coin : coins) {
                    coin.moveLeft();
                    if (coin.x + Coin.SIZE <= 0) {
                        coinsToRemove.add(coin);
                    }
                    if (coin.getBounds().intersects(bird.x - Bird.RAD, bird.y - Bird.RAD, 2 * Bird.RAD, 2 * Bird.RAD)) {
                        coinsToRemove.add(coin);
                        coinScore += 50;
                    }
                }
                pipes.removeAll(pipesToRemove);
                coins.removeAll(coinsToRemove);
                time++;
                scroll++;

                if (bird.y > HEIGHT || bird.y + Bird.RAD < 0) {
                    game = false;
                }

                if (!game) {
                    pipes.clear();
                    coins.clear();
                    bird.reset();
                    time = 0;
                    scroll = 0;
                    coinScore = 0;
                    paused = true;
                }
            }
            panel.repaint();
            try {
                Thread.sleep(1000 / FPS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            bird.jump();
            paused = false;
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            bird.jump();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void mouseClicked(MouseEvent e) {
        bird.jump();
        paused = false;
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    public int getScore() {
        return time + coinScore;
    }

    public boolean isPaused() {
        return paused;
    }

    public GamePanel getPanel() {
        return panel;
    }
}

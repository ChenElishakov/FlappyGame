

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window {
    private JFrame frame;
    private GameEngine gameEngine;

    public Window() {
        frame = new JFrame("Flappy Bird");
        gameEngine = new GameEngine();
        frame.add(gameEngine.getPanel(), BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.setResizable(false);
        frame.setSize(GameEngine.WIDTH, GameEngine.HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.addKeyListener(gameEngine);
    }

    public void show() {
        gameEngine.start();
    }
}

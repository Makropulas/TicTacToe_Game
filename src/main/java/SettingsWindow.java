import javax.swing.*;
import java.awt.*;

public class SettingsWindow extends JFrame {
    private static final int WINDOW_HEIGHT = 230;
    private static final int WINDOW_WIDTH = 350;

    JButton btnStart = new JButton("Начать новую игру");

    SettingsWindow(GameWindow gameWindow) {
//        setLocationRelativeTo(gameWindow);
        setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - WINDOW_WIDTH) / 2,
                (Toolkit.getDefaultToolkit().getScreenSize().height - WINDOW_HEIGHT) / 2);

        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        btnStart.addActionListener(e -> {
            gameWindow.startNewGame(0, 3, 3, 3);
            setVisible(false);
        });
        add(btnStart);
    }
}

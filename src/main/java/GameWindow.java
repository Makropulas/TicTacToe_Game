import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameWindow extends JFrame {
    private static final Dimension displaySize = Toolkit.getDefaultToolkit().getScreenSize();
    private static final int WINDOW_HEIGHT = 555;
    private static final int WINDOW_WIDTH = 507;
    private static final int WINDOW_POSX = (displaySize.width - WINDOW_WIDTH) / 2;
    private static final int WINDOW_POSY = (displaySize.height - WINDOW_HEIGHT) / 2;

    JButton btnStart = new JButton("Новая игра");
    JButton btnExit = new JButton("Выход");

    Map map;
    SettingsWindow settings;

    GameWindow() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(WINDOW_POSX, WINDOW_POSY);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setTitle("Крестики-Нолики");
        setResizable(false);

        map = new Map();
        settings = new SettingsWindow(this);
        btnExit.addActionListener(new ActionListener() {                      // через анонимный класс
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });
        btnStart.addActionListener(actionEvent -> settings.setVisible(true)); // через лямбду

        JPanel panBottom = new JPanel(new GridLayout(1, 2));
        panBottom.add(btnStart);
        panBottom.add(btnExit);
        add(panBottom, BorderLayout.SOUTH);
        add(map);
        setVisible(true);
    }

    void startNewGame(int mode, int fSzX, int fSzY, int wLen) {
        map.startNewGame(mode, fSzX, fSzY, wLen);
    }
}

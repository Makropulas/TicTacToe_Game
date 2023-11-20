import javax.swing.*;
import java.awt.*;

public class SettingsWindow extends JFrame {
    private static final int WINDOW_HEIGHT = 230;
    private static final int WINDOW_WIDTH = 350;

    GameWindow gameWindow;

    private final int MIN_FIELD_SIZE = 3;
    private final int MAX_FIELD_SIZE = 10;
    private final int MIN_WIN_LENGTH = 3;

    private final String FIELD_SIZE_PREFIX = "Установленный размер поля: ";
    private final String WIN_LENGTH_PREFIX = "Установленная длина: ";

    JButton btnStart = new JButton("Начать новую игру");
    JRadioButton humanVsAI = new JRadioButton("Человек против компьютера");
    JRadioButton humanVsHuman = new JRadioButton("Человек против человека");
    JSlider slideFieldSize;
    JSlider slideWinLength;


    SettingsWindow(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
        slideFieldSize = new JSlider(MIN_FIELD_SIZE, MAX_FIELD_SIZE, MIN_FIELD_SIZE);
        slideWinLength = new JSlider(MIN_WIN_LENGTH, MIN_FIELD_SIZE, MIN_FIELD_SIZE);
//        setLocationRelativeTo(gameWindow);
        setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - WINDOW_WIDTH) / 2,
                (Toolkit.getDefaultToolkit().getScreenSize().height - WINDOW_HEIGHT) / 2);

        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setTitle("Настройки");
        setResizable(false);
        setLayout(new GridLayout(10, 1));
        gameModeRegulation();
        fieldParametersRegulation();
        add(btnStart);

        btnStart.addActionListener(e -> btnStartDelegate());
    }

    private void btnStartDelegate() {
        int gameMode;
        if (humanVsAI.isSelected()) {
            gameMode = Map.MODE_HUMAN_VS_AI;
        } else if (humanVsHuman.isSelected()) {
            gameMode = Map.MODE_HUMAN_VS_HUMAN;
        } else {
            throw new RuntimeException("Неизвестный режим игры");
        }
        int fieldSize = slideFieldSize.getValue();
        int winLength = slideWinLength.getValue();
        gameWindow.startNewGame(gameMode, fieldSize, fieldSize, winLength);
        setVisible(false);
    }

    private void gameModeRegulation() {
        add(new JLabel("Выберите режим игры"));
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(humanVsAI);
        buttonGroup.add(humanVsHuman);
        add(humanVsAI);
        add(humanVsHuman);
        humanVsAI.setSelected(true);
    }

    private void fieldParametersRegulation() {
        JLabel labelFieldSize = new JLabel(FIELD_SIZE_PREFIX + MIN_FIELD_SIZE);
        JLabel labelWinLength = new JLabel(WIN_LENGTH_PREFIX + MIN_FIELD_SIZE);

        slideWinLength.addChangeListener(e -> labelWinLength.setText(WIN_LENGTH_PREFIX + slideWinLength.getValue()));
        slideFieldSize.addChangeListener(e -> {
            int currentValue = slideFieldSize.getValue();
            labelFieldSize.setText(FIELD_SIZE_PREFIX + currentValue);
            slideWinLength.setMaximum(currentValue);
        });

        add(new JLabel("Выберите размеры поля"));
        add(labelFieldSize);
        add(slideFieldSize);
        add(new JLabel("Выберите длину для победы"));
        add(labelWinLength);
        add(slideWinLength);
    }
}
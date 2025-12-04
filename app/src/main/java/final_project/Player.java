package final_project;

import javax.swing.*;
import java.awt.*;

public class Player {
    private String name;
    private int score;

    public Player() {
        setName();
        score = 0;
        System.out.println("Player Initialized");
    }

    private void setName() {
        name = JOptionPane.showInputDialog(
                    null,
                    "Enter your name:",
                    "Set Player Name",
                    JOptionPane.QUESTION_MESSAGE
                );

        //System.out.println("Player Name set to: " + name);
    }

    public String getName() {
        return name;
    }
}

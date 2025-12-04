package final_project;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class Player {
    private String name;
    private int winScore;
    private int lossScore;
    private int drawScore;
    private static final String DATA_FILE = "C:/Users/alexw/Git Repositories/Final_Project/app/src/main/resources/Scores.csv";
    private static ArrayList<String> masterData = new ArrayList<>();

    public Player() {
        setName();
        winScore = 0;
        lossScore = 0;
        drawScore = 0;
        readDataFile();
        System.out.println("Player Initialized");
    }

    public void incrementWinScore() {
        winScore++;
        System.out.println("Win score incremented to: " + winScore);
    }

    public void incrementLossScore() {
        lossScore++;
        System.out.println("Loss score incremented to: " + lossScore);
    }

    public void incrementDrawScore() {
        drawScore++;
        System.out.println("Draw score incremented to: " + drawScore);
    }

    private void setName() {
        name = JOptionPane.showInputDialog(
                    null,
                    "Enter your name:",
                    "Set Player Name",
                    JOptionPane.QUESTION_MESSAGE
                );
    }

    public String getName() {
        return name;
    }

    private void readDataFile(){
        try {
            File file = new File(DATA_FILE);
            Scanner scanner = new Scanner(file);

            // Skip header
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(",");
                if(data[0].equals(name)){
                    winScore = Integer.parseInt(data[1]);
                    lossScore = Integer.parseInt(data[2]);
                    drawScore = Integer.parseInt(data[3]);
                    System.out.println("Player data found: " + name + ", Wins: " + winScore + ", Losses: " + lossScore + ", Draws: " + drawScore);
                    break;
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while reading player data.");
            e.printStackTrace();
        }
    }

    public void writeDataFile(){
        //Read in data and mark current player's data
        int replaceLine = -1;
        try {
            File file = new File(DATA_FILE);
            Scanner scanner = new Scanner(file);

            // Skip header
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                masterData.add(line);
                String[] data = line.split(",");
                if(data[0].equals(name)){
                    replaceLine = masterData.size() - 1;
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while reading player data.");
            e.printStackTrace();
        }
 
        //write data back to file
        try {
            java.io.FileWriter writer = new java.io.FileWriter(DATA_FILE);

            // Write header
            writer.write("Name,Wins,Losses,Draws\n");

            for(int i = 0; i < masterData.size(); i++){
                if(i == replaceLine){
                    writer.write(name + "," + winScore + "," + lossScore + "," + drawScore + "\n");
                } else {
                    writer.write(masterData.get(i) + "\n");
                }
            }

            // If player is new, add their data
            if(replaceLine == -1){
                writer.write(name + "," + winScore + "," + lossScore + "," + drawScore + "\n");
            }

            writer.close();
            System.out.println("Player data written to file.");
        } catch (Exception e) {
            System.out.println("An error occurred while writing player data.");
            e.printStackTrace();
        }
    }
}

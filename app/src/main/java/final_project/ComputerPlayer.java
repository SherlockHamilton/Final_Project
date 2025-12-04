package final_project;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class ComputerPlayer{
    private int[] availableSpots;
    
    public ComputerPlayer() {
        System.out.println("Computer Player Initialized");
    }

    public void makeMove(String[][] board) {
        System.out.println("Computer is making a move");
        
        availableSpots = findAvailableSpot(board);
        Random rand = new Random();
        int choiceIndex = rand.nextInt(availableSpots.length);
        
        while(true){
            try {
                int choice = availableSpots[choiceIndex];
                int row = choice / 3;
                int col = choice % 3;
                
                if (board[row][col] == null) {
                    GameController.markButton(row, col);
                    break;
                } else {
                    choiceIndex = rand.nextInt(availableSpots.length);
                }
            } catch (NullPointerException e) {
                choiceIndex = rand.nextInt(availableSpots.length);
            }
        }
    }

    private int[] findAvailableSpot(String[][] board) {
        int[] spots = new int[9]; //8 possible open spots
        int count = 0;
        
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == null) {
                    spots[count] = i * 3 + j;
                    count++;
                }
            }
        }

        return spots;
    }
    
}

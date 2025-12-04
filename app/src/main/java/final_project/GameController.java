package final_project;

public class GameController {
    private static int status; //-1: not started, 0: in progress, 1: ended
    private static int turn; //0: player1, 1: player2
    private static String winner;
    public static Player currentPlayer;
    private static ComputerPlayer compPlayer;
    private static String[][] board;
    private static GameGraphic graphics;

    GameController() {
        status = -1;
        currentPlayer = new Player();      
        compPlayer = new ComputerPlayer();
        graphics = new GameGraphic();
        //graphics.updateMessageBox(currentPlayer.getName()); get box to display name
        board = new String[3][3];
        //printBoard();
        
        System.out.println("Game Controller Initialized");
        graphics.setUpGame();
        startGame();
    }

    public static void startGame() {
        status = 0;
        turn = 0;
        System.out.println("Game Started");
        graphics.updateMessageBox();
    }

    public static void changeTurn(){
        if(status == 0){ //game still running
            if(turn == 0){ 
                turn = 1; //Computer's turn
                compPlayer.makeMove(board);
            } else { 
                turn = 0; //Player's turn
            }
        }
    }

    public static int getTurn(){
        return turn;
    }
    
    public static int getStatus() {
        return status;
    }

    public static String getCurrentPlayerName() {
        System.out.println("Getting Current Player Name: " + currentPlayer.getName());
        return currentPlayer.getName();
        
        //return currentPlayer.getName();
    }

    public static void printBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == null) {
                    System.out.print("- ");
                } else {
                    System.out.print(board[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    public static void markButton(int row, int col) {
        //System.out.println("Marking position: (" + col + ", " + row + ")");
        if(turn == 0)
            board[row][col] = "X";
        else{
            board[row][col] = "O";
            graphics.clickButton(row, col);
        }
        //printBoard();
        checkWin();
    }

    private static void checkWin() {
        //Check rows, columns, and diagonals for a win
        for(int i = 0; i < 3; i++) {
            //Check for across
            if(board[i][0] != null && board[i][0].equals(board[i][1]) && board[i][1].equals(board[i][2])) {
                status = 1;
                System.out.println("Win detected in row " + i);
                break;
            }
            //Check for down
            if(board[0][i] != null && board[0][i].equals(board[1][i]) && board[1][i].equals(board[2][i])) {
                status = 1;
                System.out.println("Win detected in column " + i);
                break;
            }
            //Check for diagonals
            if(i == 0) {
                //Check for diagonal \
                if(board[0][0] != null && board[0][0].equals(board[1][1]) && board[1][1].equals(board[2][2])) {
                    status = 1;
                    System.out.println("Win detected in diagonal \\");
                    break;
                }
            } else if(i == 2) {
                //Check for diagonal /
                if(board[0][2] != null && board[0][2].equals(board[1][1]) && board[1][1].equals(board[2][0])) {
                    status = 1;
                    System.out.println("Win detected in diagonal /");
                    break;
                }
            }
        }

        if(status == 1) {
            //Notify game graphic of game over
            GameGraphic graphics = new GameGraphic();
            graphics.gameOver(winner);
        }
    }
}

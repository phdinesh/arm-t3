
package application;

import application.minmax.TicTacToeSlow;
import com.googlecode.javacv.cpp.opencv_core.CvRect;


public class TicTacToeBoard {

    private static TicTacToeBoard ticTacToeBoard; // singleton object

    public static final int HUMAN = 0;
    public static final int COMPUTER = 1;
    public static final int EMPTY = 2;

    private CvRect rectangleArray[];

    public TicTacToeSlow machinePlayer;

    public int[][] board = new int[3][3];

    private TicTacToeBoard() {
       // board[0][0] = HUMAN;
        //board[0][1] = COMPUTER;
        machinePlayer = new TicTacToeSlow();
        clearBoard();
        machinePlayer.clearBoard();

    }

    public void clearBoard(){
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                board[i][j] = EMPTY;
            }
        }
        machinePlayer.clearBoard();
        ApplicationContext.applicationWindow.updatePanel();
    }

    public void setRectangleArray(CvRect rectangles[]){
        this.rectangleArray = rectangles;
    }

    public CvRect[] getRectangleArray(){
        return rectangleArray;
    }
    

    public void place(int row, int col, int player){

        board[row][col] = player;
        machinePlayer.playMove(player, row, col);
        ApplicationContext.applicationWindow.updatePanel();
    }

    public int[][] getBoard(){
        return board;
    }


    //singleton accessor
    public static TicTacToeBoard getTicTacToeBoard(){
        if(ticTacToeBoard == null){
            ticTacToeBoard = new TicTacToeBoard();
        }
        return ticTacToeBoard;
    }

    public void printGame(){
        System.out.println("Current Game Board =====================================");

        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                System.out.print(board[i][j]+"\t");
            }
            System.out.println("");
        }

        System.out.println(" =======================================================");

    }

}

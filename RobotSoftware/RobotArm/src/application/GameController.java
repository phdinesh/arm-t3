/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package application;

import application.minmax.Best;
import com.googlecode.javacv.CanvasFrame;
import com.googlecode.javacv.FrameGrabber.Exception;
import com.googlecode.javacv.cpp.opencv_core.IplImage;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import robotarm.VideoController;

import static com.googlecode.javacv.cpp.opencv_core.*;
import static com.googlecode.javacv.cpp.opencv_imgproc.*;

/**
 *
 * @author Dinesh
 */
public class GameController {

    public static final int EASY_GAME = 0;
    public static final int HARD_GAME = 1;
    public static int GAME_MODE = 1;


    public static boolean GAME_RUNS = true;

    //This will update gambeboard rectangles
    public static void calibrateRectangles() throws Exception{


        IplImage image = VideoController.getVideoInput().grab();

        IplImage copy = cvCreateImage(cvGetSize(image), 8, 3);
        cvCopy(image, copy);

        CvRect[] grid =  ImageProcessingUtilities.solveGrid(copy);

        TicTacToeBoard.getTicTacToeBoard().setRectangleArray(grid);

        System.out.println("=========================================================");
        System.out.println("Camera Calibration ok ......| Board Detected ............");
        System.out.println("=========================================================");
    }


    public static void startGame(){

        while(!gameFull()){
            
            boolean ok = false;
            while(!ok){

                System.out.println("Paused...");
                while(!GAME_RUNS){
                    
                }
                System.out.println("Runs.");

                if(detectWining(TicTacToeBoard.COMPUTER)){

                    JOptionPane.showMessageDialog(ApplicationContext.applicationWindow, "Computer Wins");
                    return;
                    //break;
                }

                try {
                    ok = findPlayerPosition();

                    if(!ok){
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }else{
                        if(detectWining(TicTacToeBoard.HUMAN)){
                            JOptionPane.showMessageDialog(ApplicationContext.applicationWindow, "Player wins");
                        return;
                            //break;
                        }
                    }
                } catch (Exception ex) {
                    ApplicationContext.applicationWindow.txtError.append(ex.getMessage()+"\n");
                    Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if(gameFull() == false)
                placeRobotPosition();

        }

        
    }
    //Send robot coordinate, update board data
    public static void placeRobotPosition(){

        int machine = getComputerPoint();
        if(machine != -1){
            TicTacToeBoard.getTicTacToeBoard().place(machine / 3,machine % 3, TicTacToeBoard.COMPUTER);
            try {
               RobotArmController.place(8 - machine);

            } catch (IOException ex) {
                ApplicationContext.applicationWindow.txtError.append("Error in Communication.\n");
                Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        

    }
    //Accss camera, process and find last played position
    public static boolean findPlayerPosition() throws Exception{

        int [][] boardData = TicTacToeBoard.getTicTacToeBoard().getBoard();

        IplImage newState = VideoController.getVideoInput().grab();
         cvSmooth(newState, newState, CV_GAUSSIAN, 3); // smoothing image


        IplImage grayImage_s = cvCreateImage(cvGetSize(newState),8, 1);


        cvCvtColor(newState,grayImage_s, CV_BGR2GRAY);

        cvAdaptiveThreshold(grayImage_s, grayImage_s,255, CV_ADAPTIVE_THRESH_GAUSSIAN_C, CV_THRESH_BINARY, 5,3);

        for(int row = 0; row < 3; row ++){
            for(int col = 0; col < 3; col ++){
                if(boardData[row][col] == TicTacToeBoard.EMPTY){

                    //we should check for new value.
                    cvSetImageROI(grayImage_s, TicTacToeBoard.getTicTacToeBoard().getRectangleArray()[row * 3 + col]);
                    if(ImageProcessingUtilities.hasACircle(grayImage_s)){
                        TicTacToeBoard.getTicTacToeBoard().place(row, col, TicTacToeBoard.HUMAN);
                        System.out.println("====================== "+row+"======="+col);
                        System.out.println("Found.... Success");
                        return true;
                    }

                }
            }
        }
        return false;

    }


    public static int getComputerPoint(){
//    boolean full = gameFull();
//    while(!full){
//            int random = (int) Math.floor(Math.random()*9);
//            System.out.println("----------------------------- Random"+random);
//            if( TicTacToeBoard.getTicTacToeBoard().board[random/3][random % 3] == TicTacToeBoard.EMPTY){
//                return random;
//            }
//
//        }
//    return -1;
       Best best = TicTacToeBoard.getTicTacToeBoard().machinePlayer.chooseMove(TicTacToeBoard.COMPUTER);
       return best.row * 3 + best.column;

    }

    public static boolean gameFull(){
        int data[][] = TicTacToeBoard.getTicTacToeBoard().getBoard();
        for(int i = 0; i < 3; i++){
            for(int j =0; j < 3; j++){
                if(data[i][j] == TicTacToeBoard.EMPTY){
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean detectWining(int player){

        int data[][] = TicTacToeBoard.getTicTacToeBoard().getBoard();
        boolean found = false;
        for(int row = 0; row < 3; row++){
           for(int col = 0; col < 3; col++){
               if(data[row][col] != player){
                   break;
               }
               if(col == 2){
                 found = true;
                return true;
               }
           }
        }

        for(int col = 0; col < 3; col++){
            for(int row = 0; row < 3; row++){
                if(data[row][col] != player){
                    break;
                }
                if(row == 2){
                    found = true;
                    return true;
                }
            }
        }

        if(data[0][0] == player && data[1][1] == player && data[2][2] == player){
            return true;
        }

        if(data[0][2] == player && data[1][1] == player && data[2][0] == player){
            return true;
        }
        return false;
    }
    
    

}

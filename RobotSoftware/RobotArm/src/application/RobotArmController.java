/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package application;

//import communication.SerialCommunicator;
import application.communication.SerialCommunicator;
import application.kinematics.ArmPosition;
import application.kinematics.KinematicsDataLoader;
import application.kinematics.PlacementData;
import dynamixel.CoordinateSender;
import dynamixel.DynamixelConnector;
import dynamixel.DynamixelController;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dinesh
 */
public class RobotArmController {

    public static boolean isArmMoving(){
        DynamixelConnector connector = DynamixelController.getMotorConnector();

        return connector.isMoving(4) || connector.isMoving(2) || connector.isMoving(3) || connector.isMoving(1);
    }

    public static void waitForMovement(){

        while(isArmMoving()){
            delay(1);
        }

    }

    public static void place(int position) throws IOException{

        int de = 0;
        PlacementData[] allPlacementData = KinematicsDataLoader.getDataLoader().getAllPlacementData();

         PlacementData data = allPlacementData[position];

         penUp();
         waitForMovement();
        //place to first.
        ArrayList<ArmPosition> forwardLine = data.getForwardLine();
        CoordinateSender.writeToMotors(forwardLine.get(0));

        while(isArmMoving()){
            delay(1);
        }

        penDown();
        waitForMovement();

        for(ArmPosition pos : forwardLine){
            CoordinateSender.writeToMotors(pos);
            
        }
        penUp();

                while(isArmMoving()){
                delay(1);
            }

        ArrayList<ArmPosition> backwardLine = data.getBackwardLine();
        CoordinateSender.writeToMotors(backwardLine.get(0));

        while(isArmMoving()){
            delay(1);
        }
        
        penDown();
        waitForMovement();
        for(ArmPosition pos : backwardLine){
            CoordinateSender.writeToMotors(pos);
        }
        penUp();

        neutral();
        
    }

    public static void neutral(){
        try {
            //try {
            penUp();
        } catch (IOException ex) {
            Logger.getLogger(RobotArmController.class.getName()).log(Level.SEVERE, null, ex);
        }
            


    }

    public static void penUp() throws IOException{

        DynamixelController.getMotorConnector().setGoal(1, 690 );
        delay(5);
    }

    public static void penDown() throws IOException{
        DynamixelController.getMotorConnector().setGoal(1, 545 );
        delay(5);
    }

    public static void delay(int time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException ex) {
            Logger.getLogger(RobotArmController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    

}

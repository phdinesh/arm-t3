/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dynamixel;

import application.kinematics.ArmPosition;

/**
 *
 * @author Dinesh
 */
public class CoordinateSender {

    public static void writeToMotors(int data[]){

        DynamixelController.getMotorConnector().setGoals(data);
        
        
    }

    public static void writeToMotors(int theta1, int theta2, int theta3){
        DynamixelController.getMotorConnector().setGoal(4, theta1);
        DynamixelController.getMotorConnector().setGoal(3, theta2);
        DynamixelController.getMotorConnector().setGoal(2, theta3);
        
    }
    
    public static void writeToMotors(ArmPosition pos){
        DynamixelController.getMotorConnector().setGoal(4, pos.getTheta1());
        DynamixelController.getMotorConnector().setGoal(3, pos.getTheta2());
        DynamixelController.getMotorConnector().setGoal(2, pos.getTheta3());
    }



    

    public static void writeMotorAngles(String data[]){

        int nos[] = new int[data.length];

        for(int i = 0; i < data.length ; i++){
           nos[i] = Integer.parseInt(data[i]) ;
            System.out.println(data[i]);
        }

        DynamixelController.getMotorConnector().setGoals(nos);
    }


}

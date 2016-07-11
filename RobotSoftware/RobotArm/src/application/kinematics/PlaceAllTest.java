/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package application.kinematics;

import application.RobotArmController;
import dynamixel.DynamixelController;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dinesh
 */
public class PlaceAllTest {

    public static void main(String[] args) {


        DynamixelController.getMotorConnector().initializeSystem();
        RobotArmController.delay(5);
        DynamixelController.getMotorConnector().setSpeeds(new int[]{200,200,200,100});

        for(int i = 0; i < 9; i++){
            try {
                RobotArmController.waitForMovement();
                RobotArmController.place(i);
            } catch (IOException ex) {
                Logger.getLogger(PlaceAllTest.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        
    }

}

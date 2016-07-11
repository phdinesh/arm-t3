
import application.kinematics.ArmPosition;
import application.kinematics.KinematicsTestFrame;
import application.kinematics.PlaceAllTest;
import dynamixel.DynamixelController;
import dynamixel.DynamixelSystemTest;
import dynamixel.DynamixelTestWindow;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Dinesh
 */
public class Test {


    public static void main(String[] args) {

        DynamixelController.initController(new DynamixelConnectorImpl());
        //DynamixelSystemTest.main(args);

        //new DynamixelTestWindow().setVisible(true);

        PlaceAllTest.main(args);
       // new KinematicsTestFrame().setVisible(true);

    }
}

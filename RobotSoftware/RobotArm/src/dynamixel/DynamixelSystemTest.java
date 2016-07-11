/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dynamixel;

/**
 *
 * @author Dinesh
 */
public class DynamixelSystemTest {

    public static void main(String[] args) {

        DynamixelConnector connector = DynamixelController.getMotorConnector();

        connector.initializeSystem();

        connector.setLightStatus(3, true);

        connector.setSpeed(4, 50);
        connector.setGoal(4, 480);

        
    }

}

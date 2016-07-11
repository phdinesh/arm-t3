/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dynamixel;

/**
 *
 * @author Dinesh
 */
public class DynamixelController {

    private static DynamixelController controller;

    private DynamixelConnector connector;

    private DynamixelController(DynamixelConnector con) {
        connector = con;
    }

    public static void initController(DynamixelConnector connector){
        controller = new DynamixelController(connector);
    }

    public static DynamixelConnector getMotorConnector(){
        
        if(controller == null)
            throw new RuntimeException("Controller must be initialized, before use");

        return controller.connector;
    }


    

}

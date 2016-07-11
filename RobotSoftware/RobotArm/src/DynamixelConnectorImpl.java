
import dynamixel.DynamixelConnector;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Dinesh
 */
public class DynamixelConnectorImpl implements DynamixelConnector {

    public DynamixelConnectorImpl() {
    }

    @Override
    public void setLightStatus(int servoId, boolean on) {
        dynamixel_jni.dxl_write_byte(servoId, 25, 1);

        
    }

    @Override
    public boolean initializeSystem() {

        int res = dynamixel_jni.dxl_initialize();
        dynamixel_jni.dxl_set_baud(1);
        System.out.println("Result "+res);
        return res == 1;

    }

    @Override
    public void setGoal(int servoId, int goalPos) {
        dynamixel_jni.dxl_write_word(servoId, 30, goalPos);
    }

    @Override
    public void setSpeed(int servoId, int speed) {
            dynamixel_jni.dxl_write_word(servoId, 32, speed);

    }

    @Override
    public void setSpeeds(int speeds[]){
        
        for(int i = 0; i < speeds.length; i++){
            
            dynamixel_jni.dxl_write_word(4-i, 32, speeds[i]);

        }

    }

    @Override
    public void setGoals(int[] goals) {
        for(int i = 0; i < goals.length; i++){
            System.out.println("Id : "+ (4-i)+ " value "+goals[i]);
            dynamixel_jni.dxl_write_word(4-i, 30, goals[i]);

        }
    }

    @Override
    public boolean isMoving(int servoId){
        int dxl_read_byte = dynamixel_jni.dxl_read_byte(servoId, 46);
        if(dxl_read_byte == 0){
            return false;
        }
        return true;
    }


    

    


}

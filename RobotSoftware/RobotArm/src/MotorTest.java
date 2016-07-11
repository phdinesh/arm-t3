/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Dinesh
 */
public class MotorTest {

    public static void main(String[] args) {


        int res = dynamixel_jni.dxl_initialize();
        dynamixel_jni.dxl_set_baud(1);//1 means 1 Mbaud; I think this uses the same scheme as the AX-12 does.
		if (res == 1){
			System.out.println("Successfully opened USB2Dynamixel");
		}else{
			System.out.println("Failed to open USB2Dynamixel");
		}
		//flash LED briefly to demonstrate communication
		dynamixel_jni.dxl_write_byte(2, 25, 1);//on servo 1, turn on LED.

                while(true){
                    
                }
        
    }

}

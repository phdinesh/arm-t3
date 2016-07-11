/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package application.kinematics;


import static java.lang.Math.*;
/**
 *
 * @author Dinesh
 */
public class KinematicsConstant {
    /*
     * Length of first robot arm
     */
    public static final int LENGTH1 = 57;

    public static final int LENGTH2 = 80;

    public static final int LENGTH3 = 104;



    public static double getXCoord(int theta1, int theta2, int theta3){
        double ang1 = toRadians(theta1);
        double ang2 = toRadians(180 - theta2); // Servo 2 is reverse directed.
        double ang3 = toRadians(theta3);

        return LENGTH1 * cos(ang1) + LENGTH2 * cos(ang1 + ang2) + LENGTH3 * cos(ang1 + ang2 + ang3);

    }

    public static double getYCoord(int theta1, int theta2, int theta3){
        double ang1 = toRadians(theta1);
        double ang2 = toRadians(135 - theta2); // Servo 2 is reverse directed.
        double ang3 = toRadians(theta3);

        return LENGTH1 * cos(ang1) + LENGTH2 * cos(ang1 + ang2) + LENGTH3 * cos(ang1 + ang2 + ang3);

    }


}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package application.kinematics;


import java.io.Serializable;

/**
 * This class holds 3 angles of 3 servos.
 * @author Dinesh
 */
public class ArmPosition implements Serializable , Comparable<ArmPosition>{

    private static final long serialVersionUID = 1L;


    

    private int theta1;
    private int theta2;
    private int theta3;

    private double x;


    public ArmPosition() {
    }

    public ArmPosition(int theta1, int theta2, int theta3) {
        this.theta1 = theta1;
        this.theta2 = theta2;
        this.theta3 = theta3;
        x = KinematicsConstant.getXCoord(this.theta1,this.theta2, this.theta3);
    }

    public int getTheta1() {
        return theta1;

    }

    public void setTheta1(int theta1) {
        this.theta1 = theta1;
        x = KinematicsConstant.getXCoord(this.theta1, theta2, theta3);
    }

    public int getTheta2() {
        return theta2;
    }

    public void setTheta2(int theta2) {
        this.theta2 = theta2;
        x = KinematicsConstant.getXCoord(theta1, this.theta2, theta3);
    }

    public int getTheta3() {
        return theta3;
    }

    public void setTheta3(int theta3) {
        this.theta3 = theta3;
        x = KinematicsConstant.getXCoord(theta1, theta2, theta3);
    }

    @Override
    public String toString() {
       return theta1+","+theta2+","+theta3;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public int compareTo(ArmPosition o) {
        if(x < o.getX()){
            return -1;
        }else if(x > o.getX()){
            return 1;
        }else{
            return 0;
        }
    }
    


    

}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dynamixel;

/**
 * This interface will handle any kind of implementations needed.
 * @author Dinesh
 */
public interface DynamixelConnector {


    public boolean initializeSystem();

    public void setLightStatus(int servoId, boolean on);

    public void setGoal(int servoId, int goalPos);

    public void setSpeed(int servoId, int speed);

    public void setSpeeds(int speeds[]);

    public void setGoals(int goals[]);

    public boolean isMoving(int servoId);

   // public void setGoals(String line);

    
}

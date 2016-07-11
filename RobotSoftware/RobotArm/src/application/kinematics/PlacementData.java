/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package application.kinematics;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * This class holds data of a given placement data.
 * All the coordinates for a given place.
 * @author Dinesh
 */
public class PlacementData implements Serializable {
private static final long serialVersionUID = 2L;

    private int squareNumber = 0;

    private ArrayList<ArmPosition> forwardLine ; // Data for / Line

    private ArrayList<ArmPosition> backwardLine; // Data for \ Line

    public PlacementData() {
    }

    public PlacementData(int number){
        this.squareNumber = number;
    }

    public PlacementData(ArrayList<ArmPosition> forwardLine, ArrayList<ArmPosition> backwardLine) {
        this.forwardLine = forwardLine;
        this.backwardLine = backwardLine;
    }

    public ArrayList<ArmPosition> getBackwardLine() {
        return backwardLine;
    }

    public void setBackwardLine(ArrayList<ArmPosition> backwardLine) {
        this.backwardLine = backwardLine;
    }

    public ArrayList<ArmPosition> getForwardLine() {
        return forwardLine;
    }

    public void setForwardLine(ArrayList<ArmPosition> forwardLine) {
        this.forwardLine = forwardLine;
    }

    public int getSquareNumber() {
        return squareNumber;
    }

    public void setSquareNumber(int squareNumber) {
        this.squareNumber = squareNumber;
    }

    
    
}

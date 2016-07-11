/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package application;

/**
 *
 * @author Dinesh
 */
public class TTTLine {

    private int minPixel;
    private int maxPixel;

    public TTTLine() {
    }

    public TTTLine(int minPixel, int maxPixel) {
        this.minPixel = minPixel;
        this.maxPixel = maxPixel;
    }

    public int getMaxPixel() {
        return maxPixel;
    }

    public void setMaxPixel(int maxPixel) {
        this.maxPixel = maxPixel;
    }

    public int getMinPixel() {
        return minPixel;
    }

    public void setMinPixel(int minPixel) {
        this.minPixel = minPixel;
    }

    

}

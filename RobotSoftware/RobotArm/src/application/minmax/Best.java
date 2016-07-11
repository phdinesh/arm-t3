/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package application.minmax;

/**
 *
 * @author Toshiba
 */
public final class Best {

    public int row;
    public int column;
    public int val;

    public Best(int v){
        this(v,0,0);
    }
    public Best(int v, int r, int c){
        val = v;
        row = r;
        column = c;
    }

}

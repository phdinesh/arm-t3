/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package application.gui;

import application.TicTacToeBoard;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/**
 *
 * @author Dinesh
 */
public class TicTacToePanel extends JPanel {

    public TicTacToePanel() {


    }

    public TicTacToePanel(int width, int height){
        setSize(width, height);
        setPreferredSize(getSize());
        setBackground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int data[][] = TicTacToeBoard.getTicTacToeBoard().getBoard();
        int cell_width = getWidth() / 3;
        int cell_height = getHeight()/3;

        g.setColor(Color.red);

        Graphics2D graphic = (Graphics2D) g;

        graphic.setStroke(new BasicStroke(5));
        for(int i = 0; i <= 4; i++){
            //System.out.println("Painint...");
            graphic.drawLine(cell_width * i, 0, cell_width * i, getHeight());
        
            graphic.drawLine(getWidth(), cell_height * i, 0, cell_height * i);
        }

        graphic.setStroke(new BasicStroke(3));
        for(int row = 0; row < 3; row++){
            for(int col = 0; col < 3; col++){

                if(data[row][col] == TicTacToeBoard.HUMAN){
                    graphic.drawOval(col * cell_width , row * cell_height , cell_width , cell_height);
                    //g.fillOval(col * cell_width + cell_width/5, row * cell_height + cell_height/5, cell_width / 2, cell_height/2);
                
                }else if(data[row][col] == TicTacToeBoard.COMPUTER){
                    graphic.drawLine(col * cell_width,row * cell_height , (col+1)*cell_width,  (row+1)*cell_height);
                    graphic.drawLine((col+1) * cell_width,row * cell_height , (col)*cell_width,  (row+1)*cell_height);
                }
            }
        }


        
        
    }

    
    
}

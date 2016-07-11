/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * KinematicsEditor.java
 *
 * Created on Aug 10, 2013, 6:30:15 PM
 */
package application.kinematics;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Dinesh
 */
public class KinematicsEditor extends javax.swing.JFrame {

    /** Creates new form KinematicsEditor */
    public KinematicsEditor() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
            initComponents();

            setLocationRelativeTo(null);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(KinematicsEditor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(KinematicsEditor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(KinematicsEditor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(KinematicsEditor.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void writeCoordinates() {

        ObjectOutputStream oos = null;
        try {
            PlacementData data = new PlacementData(Integer.parseInt(cmbBoxNo.getSelectedItem() + ""));
            String[] lines = backwardData.getText().split("\n");
            //StringTokenizer tokenizer = new StringTokenizer(backwardData.getText(), null)
            ArrayList<ArmPosition> backwardList = new ArrayList<ArmPosition>();
            for (String aLine : lines) {
                aLine = aLine.trim();
                aLine = aLine.replaceAll("\\s+", " ");
                String[] valueString = aLine.split(",");

                try{
                    ArmPosition position = new ArmPosition(Integer.parseInt(valueString[0]), Integer.parseInt(valueString[1]), Integer.parseInt(valueString[2]));
                    backwardList.add(position);
                }catch(Exception e){

                }
                //System.out.println(position.getX());
            }

          //  Collections.sort(backwardList);

//            System.out.println("sorted....");
            for(ArmPosition p : backwardList){
                System.out.println(p.getTheta1()+" "+p.getTheta2()+" "+p.getTheta3());
            }


            System.out.println("---------");
            ArrayList<ArmPosition> forwardList = new ArrayList<ArmPosition>();
            lines = forwardData.getText().split("\n");
            for (String aLine : lines) {
                aLine = aLine.trim();
                aLine = aLine.replaceAll("\\s+", " ");
                String[] valueString = aLine.split(",");
                 try{
                    ArmPosition position = new ArmPosition(Integer.parseInt(valueString[0]), Integer.parseInt(valueString[1]), Integer.parseInt(valueString[2]));
                    forwardList.add(position);
                }catch(Exception e){

                }
               // System.out.println(position);
            }

           // Collections.sort(forwardList);
            data.setForwardLine(forwardList);
            data.setBackwardLine(backwardList);
            try {
                oos = new ObjectOutputStream(new FileOutputStream("data" + data.getSquareNumber() + ".obj"));
                oos.writeObject(data);
            } catch (IOException ex) {
                Logger.getLogger(KinematicsEditor.class.getName()).log(Level.SEVERE, null, ex);
            }
           } finally {
            try {
                if(oos != null)
                    oos.close();
            } catch (IOException ex) {
                Logger.getLogger(KinematicsEditor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cmbBoxNo = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        backwardData = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        forwardData = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Kinematics Editor");

        cmbBoxNo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8" }));

        backwardData.setColumns(20);
        backwardData.setFont(new java.awt.Font("Lucida Bright", 1, 14));
        backwardData.setRows(5);
        jScrollPane1.setViewportView(backwardData);

        forwardData.setColumns(20);
        forwardData.setFont(new java.awt.Font("Lucida Bright", 1, 14));
        forwardData.setRows(5);
        jScrollPane2.setViewportView(forwardData);

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12));
        jButton1.setText("Save Coordinates");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel1.setText("Backward Line ( \\ )");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel2.setText("Forward Line ( / )");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel3.setText("Square Number : ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel1)
                                .addGap(119, 119, 119)))
                        .addComponent(jLabel2)
                        .addGap(4, 4, 4))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(41, 41, 41)
                        .addComponent(cmbBoxNo, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(274, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbBoxNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(11, 11, 11)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 337, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGap(14, 14, 14)
                .addComponent(jButton1))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        writeCoordinates();
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new KinematicsEditor().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea backwardData;
    private javax.swing.JComboBox cmbBoxNo;
    private javax.swing.JTextArea forwardData;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}

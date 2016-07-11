/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package application.kinematics;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dinesh
 */
public class KinematicsDataLoader {

    private static KinematicsDataLoader dataLoader;

    private PlacementData[] allPlacementData;

    private KinematicsDataLoader() throws IOException {
        allPlacementData = new PlacementData[9];
        loadObjects();
    }

    public void loadObjects() throws IOException {


        for(int i = 0; i< 9; i++){
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data"+i+".obj"));
            try {
                PlacementData data = (PlacementData) ois.readObject();
                allPlacementData[i] = data;
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(KinematicsDataLoader.class.getName()).log(Level.SEVERE, null, ex);
                throw new IOException("Class Not Found Exception");
            }
        }


    }

    public PlacementData[] getAllPlacementData() {
        return allPlacementData;
    }

    public void setAllPlacementData(PlacementData[] allPlacementData) {
        this.allPlacementData = allPlacementData;
    }

     public static KinematicsDataLoader getDataLoader() throws IOException{
         if(dataLoader == null){
             dataLoader = new KinematicsDataLoader();
         }
         return dataLoader;
     }

     public static void main(String[] args) throws IOException {

         KinematicsDataLoader loader = getDataLoader();

         PlacementData data = loader.getAllPlacementData()[0];

         System.out.println(data.getBackwardLine().get(0).getX());


    }

}

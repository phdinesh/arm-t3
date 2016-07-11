/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import com.googlecode.javacpp.BytePointer;
import java.util.ArrayList;
import com.googlecode.javacv.CanvasFrame;

import static com.googlecode.javacv.cpp.opencv_core.*;
import static com.googlecode.javacv.cpp.opencv_imgproc.*;

/**
 *
 * @author Dinesh
 */
public class ImageProcessingUtilities {


    //Goal --> Find 9 rectangles
    public static CvRect[] solveGrid(IplImage image){

        cvSmooth(image, image, CV_GAUSSIAN, 3); // smoothing image

        IplImage gray_image = cvCreateImage(cvGetSize(image),8, 1); // creates an gray image
        cvCvtColor(image, gray_image, CV_BGR2GRAY);

        //Processing for gray Image
        cvAdaptiveThreshold(gray_image, gray_image,255, CV_ADAPTIVE_THRESH_GAUSSIAN_C, CV_THRESH_BINARY, 5,3);
        CanvasFrame canvas = new CanvasFrame("Test");
        canvas.showImage(gray_image);
        //access pixel data
        BytePointer imageData = gray_image.imageData();
        int width = gray_image.width(); int height = gray_image.height();


        //column indexes
        System.out.print("Row :  ");
        for(int i = 0; i < width; i++){
            System.out.printf("%3s",(i+" "));
        }
        System.out.println("");

        //let's calculate sum along the each row
        int sum_along_rows[] = new int[height];
        int total = 0;
        for(int row = 0; row < height; row++){
            total = 0;
            System.out.print("Row : "+row+" - ");
            for(int col = 0; col < width; col++){
                total += imageData.get(row * width + col) == 0 ? 1 : 0;

                System.out.printf("%3s",(imageData.get(row * width + col)+" "));

            }
            sum_along_rows[row] = total;
            System.out.println("");
        }
        //print data
        System.out.println("---------------------------------------------");
        for(int i = 0 ; i < sum_along_rows.length; i++){
            System.out.println("Row = "+i+"--"+sum_along_rows[i]);
        }

        nAverageFilter(sum_along_rows, 5);

        int scaler = 1;
        // Let's apply the row thresholding(row threshold value may change according to camera position and image)
        final int ROW_THRESHOLD = 100 / scaler;
        final int ROW_MIN = 50 / scaler;

        for(int i = 0; i < sum_along_rows.length; i++){
            sum_along_rows[i] = sum_along_rows[i] >= ROW_THRESHOLD ? sum_along_rows[i] : 0;
        }


        /*
         * So we have to identify 4 horizontal lines.
         * Each of line is speread between upper row and a lower row,
         * Let's find upper row and lower row for each of line
         */

        ArrayList<TTTLine> horizontalLineList = new ArrayList<TTTLine>(4);
        int last_met = -100;
        System.out.println("----------------------------- Finding line segments ------------------------------");
        for(int i = 0; i < sum_along_rows.length; i++){

            if(sum_along_rows[i] != 0 &&(i - last_met) > ROW_MIN){
                System.out.println("Line opening found --- @ "+i);
                cvDrawLine(image, cvPoint(0, i), cvPoint(width, i),CV_RGB(255, 0, 0), 1, CV_AA, 0);
                TTTLine aLine = new TTTLine();
                aLine.setMinPixel(i);
                while(sum_along_rows[i] != 0){
                    i++;
                }
                aLine.setMaxPixel(i-1);

                horizontalLineList.add(aLine);
                last_met = i;
                cvDrawLine(image, cvPoint(0, i-1), cvPoint(width, i-1),CV_RGB(255, 0, 0), 1, CV_AA, 0);
                System.out.println("Line closing found --- @ "+(i-1));
            }
        }


        /*
         * Similar to sum along each rows, let's calculate some along each column, in order to
         * find verticle lines.
         */

        int sum_along_columns[] = new int[width];
        for(int col = 0; col < width; col++){
            total = 0;
            for(int row = 0; row < height; row++){
                total += imageData.get(row * width + col)== 0 ? 1 : 0;
            }
            sum_along_columns[col] = total;
        }

        nAverageFilter(sum_along_columns, 5);

        // Thresholding for columns, never related whether thresholding may work
        final int COL_THRESHOLD = 50/scaler;

        for(int i = 0; i < sum_along_columns.length; i++){
            if(sum_along_columns[i] < COL_THRESHOLD){
                sum_along_columns[i] = 0;
            }
        }

        System.out.println("---------------------------- Thresholding ----------------------");
        for(int i = 0; i < sum_along_columns.length; i++){
            System.out.println("Sum @ Column : "+i +" = "+sum_along_columns[i]);
        }

        /* After thresholding let's find vertical lines
         *
         */
        last_met = -100;
        final int COL_MIN = 50/scaler;
        ArrayList<TTTLine> verticalLineList = new ArrayList<TTTLine>(4);

        for(int i = 0; i < sum_along_columns.length; i++){

            if(sum_along_columns[i] != 0 && (i - last_met) > COL_MIN){ // start line
                cvDrawLine(image, cvPoint(i, 0), cvPoint(i, height),CV_RGB(255, 0, 0), 1, CV_AA, 0);
                TTTLine aLine = new TTTLine();
                aLine.setMinPixel(i);
                while(sum_along_columns[i] != 0){
                    i++;
                }
                aLine.setMaxPixel(i-1);
                verticalLineList.add(aLine);

                last_met = i;
                cvDrawLine(image, cvPoint(i-1, 0), cvPoint(i-1, height),CV_RGB(255, 0, 0), 1, CV_AA, 0);

            }

        }
        //We have find all the lines in image, next is create Rectangles

        CvRect rectangles[] = new CvRect[9];

        for(int i = 0; i < 9; i++){
            int row = i / 3; int col = i % 3;
            TTTLine h1,h2, v1, v2;
            v1 = verticalLineList.get(col); v2 = verticalLineList.get(col + 1);
            h1 = horizontalLineList.get(row); h2 = horizontalLineList.get(row + 1);
            rectangles[i] = cvRect(v1.getMinPixel(),h1.getMinPixel(), v2.getMaxPixel() - v1.getMinPixel(), h2.getMaxPixel() - h1.getMinPixel());
           // new CanvasFrame("Subimage "+i).showImage(subImage);
        }


        new CanvasFrame("Lines").showImage(image);
        return rectangles;

        
        
    }

    //This will search for a circle in an image, image is assumed to be grayscale
    //
    public static  boolean hasACircle(IplImage gray){
        cvSmooth(gray, gray, CV_GAUSSIAN, 5);

        //
        CvMemStorage memory = cvCreateMemStorage(0);
        CvSeq circles = new CvSeq();

        circles = cvHoughCircles(gray, memory, CV_HOUGH_GRADIENT, 1, 100, 60, 50, 5, 500); // tune parameters.

        if(circles.total() > 0){
            return true;
        }
        return false;
    }

    
    private static void nAverageFilter(int data[], int n) {

        for (int i = 0; i < data.length - n; i++) {
            int total = 0;
            for (int j = i; j < i + n; j++) {
                total += data[j];
            }
            data[i] = total / n;

        }

    }
}

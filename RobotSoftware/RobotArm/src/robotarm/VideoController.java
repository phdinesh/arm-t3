/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package robotarm;

import com.googlecode.javacv.FrameGrabber.Exception;
import com.googlecode.javacv.OpenCVFrameGrabber;

/**
 *
 * @author Dinesh
 */
public class VideoController {

    private static OpenCVFrameGrabber videoInput;

    public static OpenCVFrameGrabber getVideoInput() throws Exception{
        if(videoInput == null){
            videoInput = new OpenCVFrameGrabber(0);
            videoInput.setImageHeight(240 * 2);
            videoInput.setImageWidth(320 * 2);
            videoInput.start();
        }
        return videoInput;
    }

    
    

}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package application.communication;

import application.RobotArmController;
import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.UnsupportedCommOperationException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dinesh
 */
public class SerialCommunicator {

    private static SerialCommunicator communicator;

    private SerialPort port;
    private InputStream inputStream;
    private OutputStream outputStream;

    public SerialCommunicator() {
    }

    public void connect(String pordID) throws IOException{
        try {
            if (port != null) {
                port.close();
            }
            String portName = "COM49"; //txtPort.getText();
            CommPortIdentifier portId = null;
            Enumeration portIdentifiers = CommPortIdentifier.getPortIdentifiers();
            while (portIdentifiers.hasMoreElements()) {
                CommPortIdentifier nextElement = (CommPortIdentifier) portIdentifiers.nextElement();
                if (nextElement.getPortType() == CommPortIdentifier.PORT_SERIAL && nextElement.getName().equals(portName)) {
                    portId = nextElement;
                    break;
                }
                //System.out.println(nextElement );
            }
            port = (SerialPort) portId.open("CNC", 100);
            port.setSerialPortParams(115200, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
            inputStream = port.getInputStream();
            outputStream = port.getOutputStream();

            RobotArmController.neutral();

        } catch (PortInUseException ex) {
            Logger.getLogger(SerialCommunicator.class.getName()).log(Level.SEVERE, null, ex);
            throw new IOException(ex.getMessage());
        } catch (UnsupportedCommOperationException ex) {
            Logger.getLogger(SerialCommunicator.class.getName()).log(Level.SEVERE, null, ex);
            throw new IOException(ex.getMessage());
        }
        
    }

    int count = 0;
    public void write(int data) throws IOException{
        
        outputStream.write(data);
       // System.out.println("serial "+data);
        outputStream.flush();
        
      }

    public int read() throws IOException{

        return inputStream.read();
    }

    public static SerialCommunicator getCommunicator(){
        if(communicator == null){
            communicator = new SerialCommunicator();
        }
        return communicator;
    }

}

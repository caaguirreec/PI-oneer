/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pioneer;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Cesar
 */
public class Pioneer {
 static String  wsStartEngine="";
 static final GpioController gpio = GpioFactory.getInstance();
 static final GpioPinDigitalOutput pin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "Engine1", PinState.LOW); 
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
        startservice();
        
        
        
        
        
    }
 
    public static void startservice(){
   
        
   
    Thread ConnectionCheckbarcode = new Thread(){
        public  void run(){
            try {
                while(true){
                    //wsStartEngine = RestClientService.engineStatus();
                    wsStartEngine= "ON";
                    if(null!=wsStartEngine)
                    switch (wsStartEngine) {
                        case "ON":
                            startEngine1();
                             System.out.println("Engine started");
                            break;
                        case "OFF":
                            stopEngine1();
                            System.out.println("Engine stopped");
                            break;
                        default:
                            System.out.println("No order from the server obtained");
                            break;
                    }
                     this.sleep(3000);
                }
                
               
            } catch (InterruptedException ex) {
                Logger.getLogger(Pioneer.class.getName()).log(Level.SEVERE, null, ex);
            }
    
      }};
    ConnectionCheckbarcode.start();
    }
     
    public static void startEngine1(){
      
     
      
      pin.high();
        
     }
     
     public static void stopEngine1(){
     
      
      
      pin.low();
        
     }
          
    
}

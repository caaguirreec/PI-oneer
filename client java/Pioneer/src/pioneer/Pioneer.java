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
 * @author César Aguirre and Santiago Molina
 */
public class Pioneer {
 static String  wsStartEngine="";
 static final GpioController gpio = GpioFactory.getInstance();
 static final GpioPinDigitalOutput pin1 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "Engine1", PinState.LOW);
 static final GpioPinDigitalOutput pin2 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_02, "Engine1", PinState.LOW); 
 static final GpioPinDigitalOutput pin3 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_03, "Engine2", PinState.LOW); 
 static final GpioPinDigitalOutput pin4 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_04, "Engine2", PinState.LOW); /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       System.out.println("Pi-oneer operational and waiting for action");
       (new Thread(new LedOperationAdvisory())).start(); 
        startservice();      
        
    }
    /** 
    * This method allows to start the listining service to the server and action the engines. It employs a separated thread in order to avoid the main application to stop.
    */
    public static void startservice(){
   
    
   
    Thread ConnectionCheckbarcode = new Thread(){
        public  void run(){
          
            while(true){
                 try {
                System.out.println("Waiting for server");
                wsStartEngine = RestClientService.engineStatus();
               
                if(null!=wsStartEngine)
                    switch (wsStartEngine) {
                        case "FORWARD":
                            forward();
                            System.out.println("Pi-oneer forward");
                            
                            break;
                        case "BACKWARD":
                            backward();
                            System.out.println("Pi-oneer backward");
                            break;
                        case "LEFT":
                            left();
                            System.out.println("Pi-oneer left");
                            break;
                        case "RIGHT":
                            right();
                            System.out.println("Pi-oneer right");
                            break;
                        case "STOP":
                            stopPioneer();
                            System.out.println(wsStartEngine);
                            break;
                    }
               
                    this.sleep(50);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Pioneer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
    
      }};
    ConnectionCheckbarcode.start();
    }
    /** 
    * This method allows the Pioneer engines to move in the same sense to forward
    */
    public static void forward(){
      pin1.low();
      pin2.high();
      pin3.low();
      pin4.high();
     
     }
    /** 
    * This method allows the Pioneer engines to move in the same sense to backward
    */
     public static void backward(){
      pin1.high();
      pin2.low();
      pin3.high();
      pin4.low();
     }
    /** 
    * This method allows the Pioneer engines to move in diferent senses to left
    */
     public static void left(){
      
      pin1.high();
      pin2.low();
      pin3.low();
      pin4.high();
        
     }   
         /** 
    * This method allows the Pioneer engines to move in diferent senses to right
    */
     public static void right(){
      
      pin1.low();
      pin2.high();
      pin3.high();
      pin4.low();
        
     } 
         /** 
    * This method allows the Pioneer engines to stop moving. It is implemented but not used in the first development stage. 
    */
     public static void stopPioneer(){
      
      pin1.low();
      pin2.low();
      pin3.low();
      pin4.low();
        
     }  
}

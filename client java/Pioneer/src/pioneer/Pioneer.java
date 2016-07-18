package pioneer;

import com.pi4j.wiringpi.Gpio;
import com.pi4j.wiringpi.SoftPwm;
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
 
    static final GpioController gpio = GpioFactory.getInstance(); 
    static final GpioPinDigitalOutput pin1 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "Engine1", PinState.LOW);
    static final GpioPinDigitalOutput pin2 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_02, "Engine1", PinState.LOW); 
    static final GpioPinDigitalOutput pin3 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_03, "Engine2", PinState.LOW); 
    static final GpioPinDigitalOutput pin4 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_04, "Engine2", PinState.LOW);

    static int pwmServo1 = 6;
    static int pwmServo2 = 5;
    
    static int pwmRange = 200;
    static int pwmStep = 1;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       System.out.println("Pi-oneer operational and waiting for action");
       (new Thread(new LedOperationAdvisory())).start(); 
       
       //Set PWM
        Gpio.wiringPiSetup();
        SoftPwm.softPwmCreate(pwmServo1,0,pwmRange);   
        SoftPwm.softPwmCreate(pwmServo2,0,pwmRange); 
        
        //Start REST service for navigation
        startservice();       
    }
    /** 
    * This method allows to start the listining service to the server and action the engines. It employs a separated thread in order to avoid the main application to stop.
    */
    public static void startservice()
    {  
        Thread ConnectionCheckbarcode = new Thread()
        {
            public  void run()
            {
                //Create REST service object
                RestClientService RESTService = new RestClientService();
                String  serviceAction = "";
                int serviceParam = -1;
                
                //Align camera
                int pwmIniVal = Math.round((float)0.08 * pwmRange);
                SoftPwm.softPwmWrite(pwmServo1, pwmIniVal);
                SoftPwm.softPwmWrite(pwmServo2, pwmIniVal);
                
                int servo1PWMval = pwmIniVal;
                int servo2PWMval = pwmIniVal;
                int servoPWMLimit = pwmIniVal*2;

                while(true)
                {
                    try 
                    {
                    System.out.println("Waiting for server");

                    //Check service status
                    RESTService.checkServiceStatus();

                    //Get action and parameters
                    serviceAction = RESTService.serviceAction;
                    serviceParam = RESTService.serviceParam1;

                    if(null!=serviceAction)
                        switch (serviceAction) 
                        {
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
                                System.out.println("Pi-oneer stopped");
                                break;
                            case "SERVO1":
                                servo1PWMval = serviceParam;
                                System.out.println("Servo1 move: " + serviceParam);
                                SoftPwm.softPwmWrite(pwmServo1, serviceParam);
                                break;
                            case "SERVO2":
                                servo2PWMval = serviceParam;
                                System.out.println("Servo2 move: " + serviceParam);
                                SoftPwm.softPwmWrite(pwmServo2, serviceParam);
                                break;
                            case "SERVO1UP":
                                System.out.println("Servo1 Up");
                                
                                //Check servo limits
                                if(servo1PWMval>=servoPWMLimit)
                                {
                                    servo1PWMval = servoPWMLimit;
                                }
                                else
                                {
                                    servo1PWMval = servo1PWMval + pwmStep;
                                }
                                SoftPwm.softPwmWrite(pwmServo1, servo1PWMval);
                                break;
                            case "SERVO1DOWN":
                                System.out.println("Servo1 Down");
                                
                                //Check servo limits
                                if(servo1PWMval<=0)
                                {
                                    servo1PWMval = 0;
                                }
                                else
                                {
                                    servo1PWMval = servo1PWMval - pwmStep;
                                }
                                SoftPwm.softPwmWrite(pwmServo1, servo1PWMval);
                                break;
                            case "SERVO1STOP":
                                System.out.println("Servo1 Stop");
                                SoftPwm.softPwmWrite(pwmServo1, servo1PWMval);
                                break;
                            case "SERVO2RIGHT":
                                System.out.println("Servo2 Right");
                                
                                //Check servo limits
                                if(servo2PWMval<=0)
                                {
                                    servo2PWMval = 0;
                                }
                                else
                                {
                                    servo2PWMval = servo2PWMval - pwmStep;
                                }
                                SoftPwm.softPwmWrite(pwmServo2, servo2PWMval);
                                break;
                            case "SERVO2LEFT":
                                System.out.println("Servo2 Left");
                                
                                //Check servo limits
                                if(servo2PWMval>=servoPWMLimit)
                                {
                                    servo2PWMval = servoPWMLimit;
                                }
                                else
                                {
                                    servo2PWMval = servo2PWMval + pwmStep;
                                }
                                SoftPwm.softPwmWrite(pwmServo2, servo2PWMval);
                                break;
                            case "SERVO2STOP":
                                System.out.println("Servo2 Stop");
                                SoftPwm.softPwmWrite(pwmServo2, servo2PWMval);
                                break;
                        }
                        this.sleep(20);
                    } catch (InterruptedException ex) 
                    {
                        Logger.getLogger(Pioneer.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        };
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

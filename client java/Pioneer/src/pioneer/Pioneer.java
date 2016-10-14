package pioneer;


import com.pi4j.component.servo.ServoDriver;
import com.pi4j.component.servo.ServoProvider;
import com.pi4j.component.servo.impl.RPIServoBlasterProvider;
import com.pi4j.wiringpi.Gpio;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author César Aguirre and Santiago Molina
 */
public class Pioneer
{
    
    static final GpioController gpio = GpioFactory.getInstance();
    static final GpioPinDigitalOutput pin1 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "Engine1", PinState.LOW);
    static final GpioPinDigitalOutput pin2 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_02, "Engine1", PinState.LOW);
    static final GpioPinDigitalOutput pin3 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_03, "Engine2", PinState.LOW);
    static final GpioPinDigitalOutput pin4 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_04, "Engine2", PinState.LOW);   

    static int pwmStep = 5;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, InterruptedException
    {
                
        System.out.println("Pi-oneer operational and waiting for action");



    (new Thread(new LedOperationAdvisory())).start();
    (new Thread(new UltrasonicDistanceFrontSensor(RaspiPin.GPIO_12,RaspiPin.GPIO_08))).start();

    //Set PWM
    Gpio.wiringPiSetup();

    //Start REST service for navigation
    startservice();
    }
 
    /**
     * This method allows to start the listening service to the server and action the engines. It employs a separated thread in order to avoid the main application to stop.
     */
    public static void startservice()
    {
        Thread ConnectionCheckbarcode = new Thread()
        {
            public  void run()
            {
                
                ServoProvider servoProvider;
                try
                {
                    servoProvider = new RPIServoBlasterProvider();
                    
                    ServoDriver servo1 = servoProvider.getServoDriver(servoProvider.getDefinedServoPins().get(0)); //GPIO 7 according github diagram
                    ServoDriver servo2 = servoProvider.getServoDriver(servoProvider.getDefinedServoPins().get(1)); //GPIO 0 according github diagram
                    
                    //Create REST service object
                    RestClientService RESTService = new RestClientService();
                    String  serviceAction;
                    int serviceParam;
                    
                    //Align camera
                    int pwmIniVal1 = 105;
                    int pwmIniVal2 = 160;
                    servo1.setServoPulseWidth(pwmIniVal1);
                    servo2.setServoPulseWidth(pwmIniVal2);
                    
                    int servo1PWMval = pwmIniVal1;
                    int servo2PWMval = pwmIniVal2;
                    int servo1PWMLimitLow = 80;
                    int servo1PWMLimitHigh = 185;
                    int servo2PWMLimitLow = 120;
                    int servo2PWMLimitHigh = 230;
                    
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
                                        servo1.setServoPulseWidth(serviceParam);
                                        break;
                                    case "SERVO2":
                                        servo2PWMval = serviceParam;
                                        System.out.println("Servo2 move: " + serviceParam);
                                        servo2.setServoPulseWidth(serviceParam);
                                        break;
                                    case "SERVO1UP":
                                        System.out.println("Servo1 Up");
                                        
                                        servo1PWMval = servo1PWMval + pwmStep;
                                        
                                        //Check limits
                                        if(servo1PWMval>=servo1PWMLimitHigh)
                                        {
                                            servo1PWMval = servo1PWMLimitHigh;
                                        }
                                         servo1.setServoPulseWidth(servo1PWMval);
                                        break;
                                    case "SERVO1DOWN":
                                        System.out.println("Servo1 Down");
                                        
                                        servo1PWMval = servo1PWMval - pwmStep;
                                            
                                        //Check limits
                                        if(servo1PWMval<=servo1PWMLimitLow)
                                        {
                                            servo1PWMval = servo1PWMLimitLow;
                                        }
                                        servo1.setServoPulseWidth(servo1PWMval);
                                        break;
                                    case "SERVO1STOP":
                                        System.out.println("Servo1 Stop");
                                        servo1.setServoPulseWidth(servo1PWMval);
                                        break;
                                    case "SERVO2RIGHT":
                                        System.out.println("Servo2 Right");   
                                        
                                        servo2PWMval = servo2PWMval - pwmStep;
                                        
                                        //Check limits
                                        if(servo2PWMval<=servo2PWMLimitLow)
                                        {
                                            servo2PWMval = servo2PWMLimitLow;
                                        }
                                        servo2.setServoPulseWidth(servo2PWMval);
                                        break;
                                    case "SERVO2LEFT":
                                        System.out.println("Servo2 Left");
                                        
                                        servo2PWMval = servo2PWMval + pwmStep;
                                        
                                        //Check limits
                                        if(servo2PWMval>=servo2PWMLimitHigh)
                                        {
                                            servo2PWMval = servo2PWMLimitHigh;
                                        }

                                        servo2.setServoPulseWidth(servo2PWMval);
                                        break;
                                    case "SERVO2STOP":
                                        System.out.println("Servo2 Stop");
                                        servo2.setServoPulseWidth(servo2PWMval);
                                        break;
                                }
                            System.out.println("Servo 1 Value: " + servo1PWMval);
                            System.out.println("Servo 2 Value: " + servo2PWMval);
                            this.sleep(20);
                        }
                        catch (InterruptedException ex)
                        {
                            Logger.getLogger(Pioneer.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                    }
                } catch (IOException ex)
                {
                    Logger.getLogger(Pioneer.class.getName()).log(Level.SEVERE, null, ex);
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

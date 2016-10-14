/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package pioneer;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;
import java.util.concurrent.TimeoutException;

/**
 *
 * @author Cesar
 */
public class UltrasonicDistanceFrontSensor implements Runnable
{
    private final static int TRIG_DURATION_IN_MICROS = 10; // trigger duration of 10 micro s
    private final static int WAIT_DURATION_IN_MILLIS = 1000; // wait 60 milli s
    private final static int TIMEOUT = 2100;
    private final static GpioController gpio = GpioFactory.getInstance();
    private final GpioPinDigitalInput echoPin;
    private final GpioPinDigitalOutput trigPin;
    
    
    @Override
    public void run()
    {
        //Pin echoPin = RaspiPin.GPIO_12; // PI4J custom numbering (pin 11)
        //Pin trigPin = RaspiPin.GPIO_08; // PI4J custom numbering (pin 7)
        //UltrasonicDistanceFrontSensor monitor = new UltrasonicDistanceFrontSensor( echoPin, trigPin );
        
        
        while( true )
        {
            try
            {
                String newLine = System.getProperty("line.separator");
                System.out.printf(  this.measureDistance()+"cm"+newLine );
                RestClientService.sendDistanceUltrasoundSensor(this.measureDistance());
                try
                {
                    Thread.sleep( WAIT_DURATION_IN_MILLIS );
                } catch (InterruptedException ex) {
                    System.err.println( "Interrupt during trigger" );
                }
                
            } catch (TimeoutException ex) {
                System.err.println( "Interrupt timeout" );
            }
            
        }
    }
    
    
    
    UltrasonicDistanceFrontSensor( Pin echoPin, Pin trigPin )
    {
        this.echoPin = gpio.provisionDigitalInputPin( echoPin );
        this.trigPin = gpio.provisionDigitalOutputPin( trigPin );
        this.trigPin.low();
    }
    
    /*
    * This method returns the distance measured by the sensor in cm
    *
    * @throws TimeoutException if a timeout occurs
    */
    public float measureDistance() throws TimeoutException
    {
        this.triggerSensor();
        long duration = this.measureSignal();
        return (float) ((duration/100) * 1.75);
    }
    
    /**
     * Put a high on the trig pin for TRIG_DURATION_IN_MICROS
     */
    private void triggerSensor()
    {
        try
        {
            this.trigPin.high();
            Thread.sleep(  TRIG_DURATION_IN_MICROS);
            this.trigPin.low();
        }
        catch (InterruptedException ex)
        {
            System.err.println( "Interrupt during trigger"+ex );
        }
    }
    
    
    /**
     * @return the duration of the signal in micro seconds
     * @throws DistanceMonitor.TimeoutException if no low appears in time
     */
    private long measureSignal() throws TimeoutException
    {
        int countdown = TIMEOUT;
        long start = System.nanoTime();
        while( this.echoPin.isHigh() && countdown > 0 )
        {
            countdown--;
        }
        long end = System.nanoTime();
        
        if( countdown <= 0 )
        {
            return 0;
        }
        return (long)( ( end - start ) / 1000 );  // Return micro seconds
    }
    
}


package pioneer;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

/**
 *
 * @author César Aguirre and Santiago Molina
 */
public class LedOperationAdvisory implements Runnable {
    static final GpioController gpio = GpioFactory.getInstance();
    static final GpioPinDigitalOutput AdvisorLed = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_29, "AdvisoreLed1", PinState.LOW);

/**
 *
 * This method allows to notify through a toggling led that the Java Client is operational once the raspberry is started
 */
    @Override
    public void run() {
        
          while(true){
          try {
              AdvisorLed.toggle();
              Thread.sleep(500);
        } catch (InterruptedException ex) {
           
        }}
            }
}

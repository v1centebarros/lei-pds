package lab12.ex02.thermoMVC.view;

import lab12.ex02.thermoMVC.model.Thermometer;
import lab12.ex02.thermoMVC.model.ThermometerListener;

import javax.swing.*;

public class consoleDisplay extends JPanel implements ThermometerListener {

    // The Unicode symbol for degrees
    private static final char DEGREE_SYMBOL = '\u00B0';

    /** The thermometer whose temperature is being displayed */
    protected Thermometer thermometer;

    /**
     * Creates a console-displayed thermometer
     * @param t the thermometer whose temperature is displayed
     */
    public consoleDisplay(Thermometer t) {
        thermometer = t;
    }

    /**
     * Create the string to display in the thermometer
     * @return the string to display in the thermometer
     */
    private String getDisplayString() {
        double celcius =  ( thermometer.getTemperature() - 32 ) * 1.8 ;
        double kelvin =  celcius + 273.15;
        return "" + thermometer.getTemperature() + DEGREE_SYMBOL + " F" +" / " + celcius + DEGREE_SYMBOL + " C" + " / " + kelvin + " K"  ;
    }

    /**
     * Change the temperature displayed
     */
    @Override
    public void temperatureChanged() {
        System.out.println(getDisplayString());
    }
}

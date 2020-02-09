package Visualization;

import java.awt.GridLayout;
import java.util.Hashtable;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
/*
 * A simple swing slider example with different constructors
 */

public class Slider {
    public static final int SMALLEST_VISUALIZATION_SIZE = 0;
    public static final int BIGGEST_VISUALIZATION_SIZE = 100;
    public static final int NUMBER_OF_TICKS = 5;


    private int currentSimulationSpeed;
    private int speedBeforePause;

    public Slider() {
        currentSimulationSpeed = 50;
        speedBeforePause = 50;
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("Simulation Rate");
        frame.setSize(200, 200);
        JPanel panel1 = new JPanel();

        JSlider slider = new JSlider();
        slider.setMajorTickSpacing((BIGGEST_VISUALIZATION_SIZE-SMALLEST_VISUALIZATION_SIZE)/(NUMBER_OF_TICKS-1));
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);

        Hashtable<Integer, JLabel> ticks = makeTicks();

        slider.setLabelTable(ticks);
        slider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                currentSimulationSpeed = ((JSlider)e.getSource()).getValue();
            }
        });
        panel1.add(slider);
        frame.add(panel1);
        frame.pack();
        frame.setVisible(true);
    }

    private  Hashtable<Integer, JLabel> makeTicks(){
        Hashtable<Integer, JLabel> ticks = new Hashtable<Integer, JLabel>();
        for (int i=0; i<NUMBER_OF_TICKS; i++){
            int currentValue = (BIGGEST_VISUALIZATION_SIZE/(NUMBER_OF_TICKS-1))*i;
            ticks.put(currentValue, new JLabel(String.valueOf(currentValue)));
        }
        return ticks;
    }

    public void setSimulationSpeed(boolean setLastSpeed){
        if (setLastSpeed){
            currentSimulationSpeed = speedBeforePause;
        }
        else {
            speedBeforePause = currentSimulationSpeed;
            currentSimulationSpeed = 0;
        }
    }

    public int getCurrentSimulationSpeed() {
        return currentSimulationSpeed;
    }
}

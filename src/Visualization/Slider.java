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

    public Slider() {
        currentSimulationSpeed = (SMALLEST_VISUALIZATION_SIZE+BIGGEST_VISUALIZATION_SIZE)/2;
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("Simulation Rate");
        frame.setSize(600, 600);
        frame.setLayout(new GridLayout(3, 1));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set the panel to add buttons
        JPanel panel1 = new JPanel();

        JLabel status = new JLabel("Simulation Rate: "+ String.valueOf(currentSimulationSpeed), JLabel.CENTER);

        JSlider slider = new JSlider();
        slider.setMajorTickSpacing((BIGGEST_VISUALIZATION_SIZE-SMALLEST_VISUALIZATION_SIZE)/(NUMBER_OF_TICKS-1));
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);

        Hashtable<Integer, JLabel> ticks = makeTicks();

        slider.setLabelTable(ticks);
        slider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                currentSimulationSpeed = ((JSlider)e.getSource()).getValue();
                status.setText("Simulation Rate: " + currentSimulationSpeed);
            }
        });
        panel1.add(slider);
        frame.add(panel1);
        frame.add(status);
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

    public int getCurrentSimulationSpeed() {
        return currentSimulationSpeed;
    }
}
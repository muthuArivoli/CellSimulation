package cellsociety;

import javax.swing.DefaultBoundedRangeModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

import java.util.Hashtable;

/*
 * A simple swing slider example with different constructors
 */

public class Slider {
    public Slider() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("Simualtion Rate");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        JSlider slider = new JSlider();
        slider.setMajorTickSpacing(25);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        Hashtable ticks = new Hashtable();
        ticks.put(0, new JLabel("0"));
        ticks.put(25, new JLabel("25"));
        ticks.put(50, new JLabel("50"));
        ticks.put(75, new JLabel("75"));
        ticks.put(100, new JLabel("100"));

        // Set the label to be drawn
        slider.setLabelTable(ticks);

        // Add the slider to the panel
        panel.add(slider);

        // Set the window to be visible as the default to be false
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);

    }

}
package Visualization;

import java.util.ResourceBundle;

public class ButtonNames {
    private static final String DEFAULT_RESOURCES_PACKAGE = "Visualization.ButtonNames";

    String[] buttonNames;
    private ResourceBundle myResources;

    public ButtonNames(){
        buttonNames = new String[6];
        myResources = ResourceBundle.getBundle(DEFAULT_RESOURCES_PACKAGE);
        buttonNames[0] = (myResources.getString("DisplayGraph"));
        buttonNames[1] = (myResources.getString("HideGraph"));
        buttonNames[2] = (myResources.getString("Pause"));
        buttonNames[3] = (myResources.getString("Resume"));
        buttonNames[4] = (myResources.getString("Step"));
        buttonNames[5] = (myResources.getString("ChangeSimulation"));
    }

    public String[] getButtonNames(){
        return buttonNames;
    }
}

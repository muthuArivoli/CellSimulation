package configuration;

import java.util.ResourceBundle;

public class ConfigurationProperties {
    private static final String DEFAULT_RESOURCES_PACKAGE = "configuration.ConfigurationProperties";

    String[] properties;
    private ResourceBundle myResources;

    public ConfigurationProperties(){
        properties = new String[40];
        myResources = ResourceBundle.getBundle(DEFAULT_RESOURCES_PACKAGE);
        properties[0] = (myResources.getString("LETSSIMULATE"));
        properties[1] = (myResources.getString("Chooseafiletoupload"));
        properties[2] = (myResources.getString("PERCOLATION"));
        properties[3] = (myResources.getString("WATOR"));
        properties[4] = (myResources.getString("GAMEOFLIFE"));
    }

    public String[] getProperties(){
        return properties;
    }
}
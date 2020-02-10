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
        properties[5] = (myResources.getString("SEGREGATION"));
        properties[6] = (myResources.getString("Color1"));
        properties[7] = (myResources.getString("Color2"));
        properties[8] = (myResources.getString("Color3"));
        properties[9] = (myResources.getString("Color4"));
        properties[10] = (myResources.getString("Color5"));
        properties[11] = (myResources.getString("FireSimulation"));
        properties[12] = (myResources.getString("GameofLifeSimulation"));
        properties[13] = (myResources.getString("PercolationSimulation"));
        properties[14] = (myResources.getString("SegregationSimulation"));
        properties[15] = (myResources.getString("WaTorSimulation"));
        properties[16] = (myResources.getString("Color6"));
        properties[17] = (myResources.getString("Font"));
    }

    public String[] getProperties(){
        return properties;
    }
}
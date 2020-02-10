package Visualization;

import java.util.ResourceBundle;

public class Shapes {
    private static final String DEFAULT_RESOURCES_PACKAGE = "Visualization.Shapes";

    String[] myShapes;
    private ResourceBundle myResources;

    public Shapes(){
        myShapes = new String[6];
        myResources = ResourceBundle.getBundle(DEFAULT_RESOURCES_PACKAGE);
        myShapes[0] = (myResources.getString("Hexagon"));
        myShapes[1] = (myResources.getString("TRIANGLE"));
        myShapes[2] = (myResources.getString("Square"));
        System.out.println(myShapes[0]);
        System.out.println(myShapes[1]);
    }

    public String[] getMyShapes(){
        return myShapes;
    }
}
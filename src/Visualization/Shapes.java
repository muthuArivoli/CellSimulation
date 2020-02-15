package Visualization;

import java.util.ResourceBundle;

/**
 * Shapes holds the information for the text of the shapes
 */
public class Shapes {
    private static final String DEFAULT_RESOURCES_PACKAGE = "Visualization.Shapes";

    String[] myShapes;
    private ResourceBundle myResources;

    /**
     * creates a new list with the names of the shapes
     */
    public Shapes(){
        myShapes = new String[6];
        myResources = ResourceBundle.getBundle(DEFAULT_RESOURCES_PACKAGE);
        myShapes[0] = (myResources.getString("Hexagon"));
        myShapes[1] = (myResources.getString("TRIANGLE"));
        myShapes[2] = (myResources.getString("Square"));
        System.out.println(myShapes[0]);
        System.out.println(myShapes[1]);
    }

    /**
     * @return The list of shapes
     */
    public String[] getMyShapes(){
        return myShapes;
    }
}
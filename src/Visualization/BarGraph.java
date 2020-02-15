package Visualization;

import cellsociety.cellstate.CellState;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Bar Graph is a graph representing the instances of different cell states
 */
public class BarGraph {
    private static final String DEFAULT_RESOURCES_PACKAGE = "Visualization.GraphInformation";

    String[] myInformation;
    BarChart<String,Number> bc;
    private ResourceBundle myResources;

    /**
     * This is the constructor for the BarGraph
     * @param numStates A map of of each possible state and how many cells are currently that state
     */
    public BarGraph(Map<CellState, Double> numStates) {
        getInformationFromFile();
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        bc = new BarChart<String, Number>(xAxis, yAxis);
        updateChart(numStates);
        bc.setTitle(myInformation[1]);
        xAxis.setLabel(myInformation[2]);
        yAxis.setLabel(myInformation[3]);

    }

    /**
     * @return bc returns the Bar Graph
     */
    public BarChart getBarGraph(){
        return bc;
    }

    /**
     * @param state The state that is having its number changed
     * @param amount How much the cell is changing
     */
    public void addSeries(CellState state, double amount){
        XYChart.Series series = new XYChart.Series();
        series.setName(state.toString());
        XYChart.Data data = new XYChart.Data(state.toString(), amount);
        series.getData().add(data);
        bc.getData().add(series);
//        series.getNode().setStyle(state.getColor().toString());
    }

    /**
     * @param numStates The new condition of the cells
     * changes the chart to represent the new amount of each cell
     */
    public void updateChart(Map<CellState, Double> numStates) {
        for(CellState s  : numStates.keySet()){
            addSeries(s, numStates.get(s));
        }
    }

    /**
     * changes the chart to represent the new amount of each cell
     */
    private void getInformationFromFile(){
        myResources = ResourceBundle.getBundle(DEFAULT_RESOURCES_PACKAGE);
        myInformation = new String[10];
        myInformation[0] = (myResources.getString("DisplayGraph"));
        myInformation[1] = (myResources.getString("StateSummary"));
        myInformation[2] = (myResources.getString("State"));
        myInformation[3] = (myResources.getString("NumberonScreen"));
        myInformation[4] = (myResources.getString("SimulationSpeedText"));
        myInformation[5] = (myResources.getString("Filenotfound"));
    }

    /**
     * @return the text representing the simulation speed
     */
    public String getSimulationSpeedText() {
        return myInformation[4];
    }

    /**
     * @return the text representing the file not found
     */
    public String getFileNotFound() {
        return myInformation[5];
    }
}
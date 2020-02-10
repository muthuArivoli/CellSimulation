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

public class BarGraph {
    private static final String DEFAULT_RESOURCES_PACKAGE = "Visualization.GraphInformation";

    String[] myInformation;
    BarChart<String,Number> bc;
    private ResourceBundle myResources;

    public BarGraph(Map<CellState, Double> numStates) {
        getInformationFromFile();
        printForDebugging();
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        bc = new BarChart<String, Number>(xAxis, yAxis);
        updateChart(numStates);
        bc.setTitle(myInformation[1]);
        xAxis.setLabel(myInformation[2]);
        yAxis.setLabel(myInformation[3]);

    }

    public BarChart getBarGraph(){
        return bc;
    }

    public void addSeries(CellState state, double amount){
        XYChart.Series series = new XYChart.Series();
        series.setName(state.toString());
        XYChart.Data data = new XYChart.Data(state.toString(), amount);
        //data.getNode().setStyle(state.getColor().toString());
        series.getData().add(data);
        bc.getData().addAll(series);
    }

    public void updateChart(Map<CellState, Double> numStates) {
        for(CellState s  : numStates.keySet()){
            addSeries(s, numStates.get(s));
        }
    }

    public void getInformationFromFile(){
        myResources = ResourceBundle.getBundle(DEFAULT_RESOURCES_PACKAGE);
        myInformation = new String[10];
        myInformation[0] = (myResources.getString("DisplayGraph"));
        myInformation[1] = (myResources.getString("StateSummary"));
        myInformation[2] = (myResources.getString("State"));
        myInformation[3] = (myResources.getString("NumberonScreen"));
        myInformation[4] = (myResources.getString("SimulationSpeedText"));
    }

    private void printForDebugging(){
        for (int i=0; i<myInformation.length; i++){
            System.out.println(myInformation[i]);
        }
    }

    public String getSimulationSpeedText() {
        return myInformation[4];
    }
}
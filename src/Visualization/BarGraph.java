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

public class BarGraph {
    BarChart<String,Number> bc;

    public BarGraph(Map<CellState, Double> numStates) {
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        bc = new BarChart<String, Number>(xAxis, yAxis);
        updateChart(numStates);
        bc.setTitle("State Summary");
        xAxis.setLabel("State");
        yAxis.setLabel("Number on Screen");
    }

    public BarChart getBarGraph(){
        return bc;
    }

    public void addSeries(CellState state, double amount){
        XYChart.Series series = new XYChart.Series();
        series.setName(state.toString());
        XYChart.Data data = new XYChart.Data(state.toString(), amount);
        data.getNode().setStyle(state.getColor().toString());
        series.getData().add(data);
        bc.getData().addAll(series);
    }

    public void updateChart(Map<CellState, Double> numStates) {
        for(CellState s  : numStates.keySet()){
            addSeries(s, numStates.get(s));
        }
    }
}
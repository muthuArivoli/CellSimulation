package Visualization;

import cellsociety.cellstate.CellState;
import javafx.application.Application;
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

    public void addSeries(String name, double amount){
        XYChart.Series series = new XYChart.Series();
        series.setName(name);
        series.getData().add(new XYChart.Data(name, amount));
        bc.getData().addAll(series);
    }

    public void updateChart(Map<CellState, Double> numStates) {
        for(CellState s  : numStates.keySet()){
            addSeries(s.toString(), numStates.get(s));
        }
    }
}
package configuration;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import cellsociety.Cell;
import configuration.parameters.*;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

public class GridBuilder {

    private static final double ORIGINAL_DIST = .5;

    public GridBuilder(){}

    public ArrayList<ArrayList<Cell>> makeGrid(Parameter param) {
        ArrayList<ArrayList<Cell>> grid = new ArrayList<ArrayList<Cell>>();
        for(int i = 0; i < param.getGridWidth(); i++){
            ArrayList<Cell> row = new ArrayList<Cell>();
            for(int j = 0; j < param.getGridLength(); j++){
                if(Math.random() > param.getPercentage()){
                    row.add(new Cell(param.getPossibleStates().get(0)));
                }
                else{
                    row.add(new Cell(param.getPossibleStates().get(1)));
                }
            }
            grid.add(row);
        }
        return grid;
    }

    public Parameter makeParameter(File file){
        Parameter param;
        String title = "";
        int width = 0;
        int length = 0;
        double percentage = 0;

        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();

            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

            NodeList nList = doc.getElementsByTagName("Configuration");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                System.out.println("\nCurrent Element :" + nNode.getNodeName());
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    System.out.println("Title : " + eElement.getElementsByTagName("title").item(0).getTextContent());
                    title =  eElement.getElementsByTagName("title").item(0).getTextContent();
                    System.out.println("gridWidth : " + eElement.getElementsByTagName("gridWidth").item(0).getTextContent());
                    width = Integer.parseInt(eElement.getElementsByTagName("gridWidth").item(0).getTextContent());
                    System.out.println("gridLength : " + eElement.getElementsByTagName("gridLength").item(0).getTextContent());
                    length =  Integer.parseInt(eElement.getElementsByTagName("gridLength").item(0).getTextContent());
                    System.out.println("percentage : " + eElement.getElementsByTagName("percentage").item(0).getTextContent());
                    percentage =  Integer.parseInt(eElement.getElementsByTagName("percentage").item(0).getTextContent());
                    percentage = percentage/10.0;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        title.replaceAll("\\P{Print}","");

        if(title.equals("Fire")){
            double prob = ORIGINAL_DIST;
            return new FireParameter(title, length, width, prob, percentage);
        }
        if(title.equals("Game of Life")){
            return new GameOfLifeParameter(title, length, width, percentage);
        }
        if(title.equals("Percolation")){
            return new PercolationParameter(title, length, width, percentage);
        }
        if(title.equals("Segregation")){
            double prob = ORIGINAL_DIST;
            return new SegregationParameter(title, length, width, prob, percentage);
        }
        if(title.equals("WaTor")){
            double prob = ORIGINAL_DIST;
            return new WaTorParameter(title, length, width, prob, percentage);
        }
        else{
            return new FireParameter();
        }
    }

    public ArrayList<ArrayList<Cell>> reconstructGrid(Collection grid) {
        ArrayList<ArrayList<Cell>> madeGrid = new ArrayList<ArrayList<Cell>>(grid);
        return madeGrid;
    }
}


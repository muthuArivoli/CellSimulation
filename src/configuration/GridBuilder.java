package configuration;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import cellsociety.Cell;
import configuration.configurationerror.NullParameterException;
import configuration.parameters.*;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GridBuilder {

    private static final double ORIGINAL_DIST = .15;

    private Parameter param;
    private String title;
    private int width;
    private int length;
    private double percentage;

    public GridBuilder(){
        Parameter param;
        String title = "";
        int width = 0;
        int length = 0;
        double percentage = 0;
    }
    public ArrayList<ArrayList<Cell>> makeGrid(Parameter param) throws NullParameterException {
        ArrayList<ArrayList<Cell>> grid = new ArrayList<ArrayList<Cell>>();
        try {
            for (int i = 0; i < param.getGridWidth(); i++) {
                ArrayList<Cell> row = new ArrayList<Cell>();
                for (int j = 0; j < param.getGridLength(); j++) {
                    if (Math.random() > param.getPercentage()) {
                        System.out.println(Math.random());
                        row.add(new Cell(param.getPossibleStates().get(0)));
                    } else {
                        row.add(new Cell(param.getPossibleStates().get(1)));
                    }
                }
                grid.add(row);
            }
        }
        catch{
            throw NullParameterException();
            try{
                for (int i = 0; i < 100; i++) {
                    ArrayList<Cell> row = new ArrayList<Cell>();
                    for (int j = 0; j < 100; j++) {
                        if (Math.random() > .8) {
                            System.out.println(Math.random());
                            row.add(new Cell(param.getPossibleStates().get(0)));
                        } else {
                            row.add(new Cell(param.getPossibleStates().get(1)));
                        }
                    }
                }
            }
        }
        return grid;
    }

    public Parameter makeParameter(File file){
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();
            readFile(doc);
            assignParameter();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return param;
    }

    public List<List<Cell>> reconstructGrid(Collection grid) {
        List<List<Cell>> madeGrid = new ArrayList<List<Cell>>(grid);
        return madeGrid;
    }

    private void readFile(Document doc){
        NodeList nList = doc.getElementsByTagName("Configuration");
        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            System.out.println("\nCurrent Element :" + nNode.getNodeName());
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                title =  eElement.getElementsByTagName("title").item(0).getTextContent();
                width = Integer.parseInt(eElement.getElementsByTagName("gridWidth").item(0).getTextContent());
                length =  Integer.parseInt(eElement.getElementsByTagName("gridLength").item(0).getTextContent());
                percentage =  Integer.parseInt(eElement.getElementsByTagName("percentage").item(0).getTextContent())/10.0;
            }
        }
    }

    private void assignParameter(){
        System.out.println(title);
        System.out.println(percentage);
        if(title.equals("Fire")){
            double prob = ORIGINAL_DIST;
            param = new FireParameter(title, length, width, prob, percentage);
        }
        if(title.equals("Game of Life")){
            param = new GameOfLifeParameter(title, length, width, percentage);
        }
        if(title.equals("Percolation")){
            param = new PercolationParameter(title, length, width, percentage);
        }
        if(title.equals("Segregation")){
            double prob = ORIGINAL_DIST;
            param = new SegregationParameter(title, length, width, prob, percentage);
        }
        if(title.equals("WaTor")){
            double prob = ORIGINAL_DIST;
            param = new WaTorParameter(title, length, width, prob, percentage);
        }
        else{
            param = new FireParameter();
        }

    }
}


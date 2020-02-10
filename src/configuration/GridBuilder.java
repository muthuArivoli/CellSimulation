package configuration;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import cellsociety.Cell;
import configuration.configurationerror.IncorrectFileTypeError;
import configuration.configurationerror.MalformedConfigurationException;
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
    private static final double ORIGINAL_DIST = .3;
    private static final File BLANK_FILE = new File("./resources/DefaultFire.xml");

    private Parameter param;
    private String title;
    private int width;
    private int length;
    private double percentage;
    private String type;
    private Document doc;

    public GridBuilder(){
        Parameter param;
        String title = "";
        String type = "";
        int width = 0;
        int length = 0;
        double percentage = 0;
    }

    public Parameter makeParameter(File file){
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();
        } catch (Exception e) {
            throw new IncorrectFileTypeError("File could not be made to conform to XML format");
        }
        readFile(doc);
        return param;
    }

    public ArrayList<ArrayList<Cell>> makeGrid(Parameter param) throws NullParameterException {
        ArrayList<ArrayList<Cell>> grid = new ArrayList<ArrayList<Cell>>();
        try {
            for (int i = 0; i < param.getGridWidth(); i++) {
                ArrayList<Cell> row = new ArrayList<Cell>();
                for (int j = 0; j < param.getGridLength(); j++) {
                    row.add(param.makeCell(param.getPercentage()));
                }
                grid.add(row);
            }
        }
        catch(Error e){
            try{
                for(int i = 0; i < 100; i++) {
                    ArrayList<Cell> row = new ArrayList<Cell>();
                    for (int j = 0; j < 100; j++) {
                        row.add(param.makeCell(.8));
                    }
                }
                throw new NullParameterException("No parameter found");
            } catch (Exception ex) {
                throw new MalformedConfigurationException("This should not be reached");
            }
        }
        return grid;
    }

    public List<List<Cell>> reconstructGrid(Collection grid) {
        List<List<Cell>> madeGrid = new ArrayList<List<Cell>>(grid);
        return madeGrid;
    }

    private void readFile(Document doc) throws MalformedConfigurationException {
        try {
            NodeList nList = doc.getElementsByTagName("Configuration");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    title = eElement.getElementsByTagName("title").item(0).getTextContent();
                    type = eElement.getElementsByTagName("type").item(0).getTextContent();
                    width = Integer.parseInt(eElement.getElementsByTagName("gridWidth").item(0).getTextContent());
                    length = Integer.parseInt(eElement.getElementsByTagName("gridLength").item(0).getTextContent());
                    percentage = Integer.parseInt(eElement.getElementsByTagName("percentage").item(0).getTextContent()) / 10.0;
                }
            }
        }
        catch(Error e){
            throw new MalformedConfigurationException("Configuration File is incorrect");
        }
        assignParameter();
    }

    private void assignParameter(){
        if(title.equals("Fire")){
            readFireParam();
        }
        else if(title.equals("Game of Life")){
            readGameOfLifeParam();
        }
        else if(title.equals("Percolation")){
            readPercolationParam();
        }
        else if(title.equals("Segregation")){
            readSegregationParam();
        }
        else if(title.equals("WaTor")){
            readWatorParam();
        }
    }

    private void readFireParam() throws MalformedConfigurationException{
        try{
            NodeList nList = doc.getElementsByTagName(title);
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    percentage = Integer.parseInt(eElement.getElementsByTagName("percentage").item(0).getTextContent()) / 10.0;
                }
            }
        }
        catch(Exception e){
            throw new MalformedConfigurationException("Could not find all related fields for parameter. " +
                    "Missing values set to default.");
        }
        double prob = .8;
        param = new FireParameter(type, length, width, prob, percentage);
    }

    private void readGameOfLifeParam() throws MalformedConfigurationException{
        try{
            NodeList nList = doc.getElementsByTagName(title);
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    percentage = Integer.parseInt(eElement.getElementsByTagName("percentage").item(0).getTextContent()) / 10.0;
                }
            }
        }
        catch(Exception e){
            throw new MalformedConfigurationException("Could not find all related fields for parameter. " +
                    "Missing values set to default.");
        }
        param = new GameOfLifeParameter(type, length, width, percentage);
    }
    private void readPercolationParam() throws MalformedConfigurationException{
        try{
            NodeList nList = doc.getElementsByTagName(title);
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    percentage = Integer.parseInt(eElement.getElementsByTagName("percentage").item(0).getTextContent()) / 10.0;
                }
            }
        }
        catch(Exception e){
            throw new MalformedConfigurationException("Could not find all related fields for parameter. " +
                    "Missing values set to default.");
        }
        param = new PercolationParameter(type, length, width, percentage);
    }
    private void readSegregationParam() throws MalformedConfigurationException{
        try{
            NodeList nList = doc.getElementsByTagName(title);
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    percentage = Integer.parseInt(eElement.getElementsByTagName("percentage").item(0).getTextContent()) / 10.0;
                }
            }
        }
        catch(Exception e){
            throw new MalformedConfigurationException("Could not find all related fields for parameter. " +
                    "Missing values set to default.");
        }
        param = new SegregationParameter(type, length, width, prob, percentage);
    }
    private void readWatorParam()throws MalformedConfigurationException{
        try{
            NodeList nList = doc.getElementsByTagName(title);
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    percentage = Integer.parseInt(eElement.getElementsByTagName("percentage").item(0).getTextContent()) / 10.0;
                }
            }
        }
        catch(Exception e){
            throw new MalformedConfigurationException("Could not find all related fields for parameter. " +
                    "Missing values set to default.");
        }
        param = new WatorParameter(type, length, width, prob, percentage);
    }
}


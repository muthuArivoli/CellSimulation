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
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class GridBuilder {
    private static final double ORIGINAL_DIST = .3;
    private static final File BLANK_FILE = new File("./resources/DefaultFire.xml");
    private static final List<String> possibleTypes = new ArrayList<String>(Arrays.asList("Square", "Hexagon", "Triangle"));

    private Parameter param;
    private String title;
    private int width;
    private int length;
    private double percentage;
    private String type;
    private Document doc;

    public GridBuilder(){}

    public Parameter makeParameter(File file) throws IncorrectFileTypeError{
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();
        } catch (Exception e) {
            throw new IncorrectFileTypeError("File could not be made to conform to XML format. A default simulation has been run.");
        }
        readFile(doc);
        return param;
    }

    public List<List<Cell>> makeGrid(Parameter param) throws NullParameterException {
        List<List<Cell>> grid = new ArrayList<List<Cell>>();
        try {
            for (int i = 0; i < param.getGridWidth(); i++) {
                ArrayList<Cell> row = new ArrayList<Cell>();
                for (int j = 0; j < param.getGridLength(); j++) {
                    row.add(param.makeCell());
                }
                grid.add(row);
            }
        }
        catch(Error e){
            try{
                for(int i = 0; i < 100; i++) {
                    ArrayList<Cell> row = new ArrayList<Cell>();
                    for (int j = 0; j < 100; j++) {
                        row.add(param.makeCell());
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
                    checkType();
                    width = Integer.parseInt(eElement.getElementsByTagName("gridWidth").item(0).getTextContent());
                    length = Integer.parseInt(eElement.getElementsByTagName("gridLength").item(0).getTextContent());
                }
            }
        }
        catch(Error e){
            throw new MalformedConfigurationException("Configuration File is incorrect");
        }
        assignParameter();
    }

    private void checkType() {
        if(!possibleTypes.contains(type)){
            type = "Square";
        }
    }

    private void assignParameter(){
        if(title.equals("Fire")){
            try{
                readFireParam();
            }
            catch(MalformedConfigurationException e){
                param = new FireParameter();
            }
        }
        else if(title.equals("Game of Life")){
            try{
                readGameOfLifeParam();
            }
            catch(MalformedConfigurationException e){
                param = new GameOfLifeParameter();
            }
        }
        else if(title.equals("Percolation")){
            try{
                readPercolationParam();
            }
            catch(MalformedConfigurationException e){
                param = new PercolationParameter();
            }
        }
        else if(title.equals("Segregation")){
            try{
                readSegregationParam();
            }
            catch(MalformedConfigurationException e){
                param = new SegregationParameter();
            }
        }
        else if(title.equals("WaTor")){
            try{
                readWatorParam();
            }
            catch(MalformedConfigurationException e){
                param = new WatorParameter();
            }
        }
    }

    private void readFireParam() throws MalformedConfigurationException{
        try{
            NodeList nList = doc.getElementsByTagName(title);
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    double percentAlive = Integer.parseInt(eElement.getElementsByTagName("percentAlive").item(0).getTextContent()) / 100.0;
                    double percentBurning = Integer.parseInt(eElement.getElementsByTagName("percentBurning").item(0).getTextContent()) / 100.0;
                    double percentCatch = Integer.parseInt(eElement.getElementsByTagName("percentCatch").item(0).getTextContent()) / 100.0;
                    param = new FireParameter(type, length, width, percentCatch, percentAlive, percentBurning);
                }
            }
        }
        catch(Exception e){
            throw new MalformedConfigurationException("Could not find all related fields for parameter. " +
                    "Missing values set to default.");
        }
    }

    private void readGameOfLifeParam() throws MalformedConfigurationException{
        try{
            NodeList nList = doc.getElementsByTagName(title);
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    double percentAlive = Integer.parseInt(eElement.getElementsByTagName("percentAlive").item(0).getTextContent()) / 100.0;
                    param = new GameOfLifeParameter(type, length, width, percentAlive);
                }
            }
        }
        catch(Exception e){
            throw new MalformedConfigurationException("Could not find all related fields for parameter. " +
                    "Missing values set to default.");
        }
    }
    private void readPercolationParam() throws MalformedConfigurationException{
        try{
            NodeList nList = doc.getElementsByTagName(title);
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    double probOpen = Integer.parseInt(eElement.getElementsByTagName("probOpen").item(0).getTextContent()) / 100.0;
                    param = new PercolationParameter(type, length, width, probOpen);
                }
            }
        }
        catch(Exception e){
            throw new MalformedConfigurationException("Could not find all related fields for parameter. " +
                    "Missing values set to default.");
        }
    }
    private void readSegregationParam() throws MalformedConfigurationException{
        try{
            NodeList nList = doc.getElementsByTagName(title);
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    double percentMinority = Integer.parseInt(eElement.getElementsByTagName("percentMinority").item(0).getTextContent()) / 100.0;
                    double percentTolerance = Integer.parseInt(eElement.getElementsByTagName("percentTolerance").item(0).getTextContent()) / 100.0;
                    double percentOpen = Integer.parseInt(eElement.getElementsByTagName("percentOpen").item(0).getTextContent()) / 100.0;
                    param = new SegregationParameter(type, length, width, percentTolerance, percentMinority, percentOpen);
                }
            }
        }
        catch(Exception e){
            throw new MalformedConfigurationException("Could not find all related fields for parameter. " +
                    "Missing values set to default.");
        }
    }
    private void readWatorParam()throws MalformedConfigurationException{
        try{
            NodeList nList = doc.getElementsByTagName(title);
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    double percentPrey = Integer.parseInt(eElement.getElementsByTagName("percentPrey").item(0).getTextContent()) / 100.0;
                    double percentPredator = Integer.parseInt(eElement.getElementsByTagName("percentPredator").item(0).getTextContent()) / 100.0;
                    int birthRate = Integer.parseInt(eElement.getElementsByTagName("birthRate").item(0).getTextContent());
                    int energy = Integer.parseInt(eElement.getElementsByTagName("energy").item(0).getTextContent());
                    param = new WatorParameter(type, length, width, percentPrey, percentPredator, energy, birthRate);
                }
            }
        }
        catch(Exception e){
            throw new MalformedConfigurationException("Could not find all related fields for parameter. " +
                    "Missing values set to default.");
        }
    }
}


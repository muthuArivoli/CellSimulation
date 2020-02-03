package configuration;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import cellsociety.Cell;
import configuration.parameters.FireParameter;
import configuration.parameters.Parameter;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

public class GridBuilder {

    public GridBuilder(){}

    public ArrayList<ArrayList<Cell>> makeGrid(File file, Parameter param) {
        ArrayList<ArrayList<Cell>> grid = new ArrayList<ArrayList<Cell>>();
//        try {
//            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
//            DocumentBuilder db = dbf.newDocumentBuilder();
//            Document doc = db.parse(file);
//            doc.getDocumentElement().normalize();
//            System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
//            NodeList nodeList = doc.getElementsByTagName("student");
//            for (int itr = 0; itr < nodeList.getLength(); itr++) {
//                Node node = nodeList.item(itr);
//                if (node.getNodeType() == Node.ELEMENT_NODE) {
//                    Element eElement = (Element) node;
//                    System.out.println("Marks: " + eElement.getElementsByTagName("marks").item(0).getTextContent());
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        return grid;
    }

    public Parameter makeParameter(File file){
        Parameter param = new FireParameter();
//        try {
//            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
//            DocumentBuilder db = dbf.newDocumentBuilder();
//            Document doc = db.parse(file);
//            doc.getDocumentElement().normalize();
//            System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
//            NodeList nodeList = doc.getElementsByTagName("student");
//            for (int itr = 0; itr < nodeList.getLength(); itr++) {
//                Node node = nodeList.item(itr);
//                if (node.getNodeType() == Node.ELEMENT_NODE) {
//                    Element eElement = (Element) node;
//                    System.out.println("Marks: " + eElement.getElementsByTagName("marks").item(0).getTextContent());
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        return param;
    }

    public ArrayList<ArrayList<Cell>> reconstructGrid(Collection grid) {
        ArrayList<ArrayList<Cell>> madeGrid = new ArrayList<ArrayList<Cell>>(grid);
        return madeGrid;
    }
}


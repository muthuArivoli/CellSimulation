package Visualization;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SimulationFile {
    private String myPath;
    private String myFileName;
    private String mySimulationName;
    private ArrayList<String> arrayOfRules;
    private Map<String, String> rulesRelatingConditionOfCellToColor;
    private String cellStatus;

    public SimulationFile(String path){
        myPath = path;
        arrayOfRules = new ArrayList<String>();
        rulesRelatingConditionOfCellToColor = new HashMap<String, String>();
        findFileName();
        readFileAndCreateVariables();
    }

    private void readFileAndCreateVariables() {
        try {
            File newFile = new File(myPath);
            Scanner myReader = new Scanner(newFile);
            if (myReader.hasNextLine()) {
                mySimulationName = myReader.nextLine();
            }
            while (myReader.hasNextLine()) {
                String nextLine = myReader.nextLine();
                if (nextLine.equals("***")){
                    break;
                }
                arrayOfRules.add(nextLine);
            }
            while (myReader.hasNextLine()) {
                String nextLine = myReader.nextLine();
                if (nextLine.equals("***")){
                    break;
                }
                rulesRelatingConditionOfCellToColor.put(nextLine.substring(0, nextLine.indexOf(" ")), nextLine.substring(nextLine.indexOf(" ")+1));
            }
            if (myReader.hasNextLine()) {
                cellStatus = myReader.nextLine();
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }
    }

    private void findFileName() {
        String tempPath = myPath;
        while(tempPath.indexOf("/")!=-1){
            tempPath = tempPath.substring(tempPath.indexOf("/")+1);
        }
        myFileName = tempPath;
    }

    public String getFileName() {
        return myFileName;
    }

    public String getSimulationName() {
        return mySimulationName;
    }

    public ArrayList<String> getArrayOfRules(){
        return arrayOfRules;
    }

    public Map<String, String> getRulesRelatingConditionOfCellToColor() {
        return rulesRelatingConditionOfCellToColor;
    }

    public String getCellStatus() {
        return cellStatus;
    }
}

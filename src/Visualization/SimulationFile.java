package Visualization;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class SimulationFile {
    private String myPath;
    private String myFileName;
    private String mySimulationName;
    private ArrayList<String> arrayOfRules;
    private List<Text> myText;

    public SimulationFile(String path){
        myPath = path;
        arrayOfRules = new ArrayList<String>();
        findFileName();
        readFileAndCreateVariables();
        createArrayOfTextFromSimulationFile();
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
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }
    }

    private void createArrayOfTextFromSimulationFile() {
        myText = new ArrayList<>();
        createTextBoxWithTheFollowingInformation(getFileName());
        createTextBoxWithTheFollowingInformation(getSimulationName());
        for (int i=0; i<arrayOfRules.size(); i++) {
            createTextBoxWithTheFollowingInformation(arrayOfRules.get(i));
        }
    }

    private void createTextBoxWithTheFollowingInformation(String text){
        Text currentText = new Text(25, 25, text);
        currentText.setFill(Color.BLACK);
        currentText.setFont(Font.font(java.awt.Font.SERIF, 15));
        myText.add(currentText);
    }

    private void findFileName() {
        String tempPath = myPath;
        while(tempPath.indexOf("/")!=-1){
            tempPath = tempPath.substring(tempPath.indexOf("/")+1);
        }
        myFileName = tempPath;
    }

    public List<Text> getArrayOfText(){
        return myText;
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

    public void addArrayOfTextToScreen(Group root) {
        for (int i=0; i<myText.size(); i++) {
            myText.get(i).setY(50+i*25);
            root.getChildren().add(myText.get(i));
        }
    }
}

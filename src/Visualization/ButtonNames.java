package Visualization;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ButtonNames {

    String[] buttonNames;

    public ButtonNames(String path){
        buttonNames = new String[4];
        try {
            File newFile = new File(path);
            Scanner myReader = new Scanner(newFile);
            int i=0;
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                buttonNames[i] = data;
                i++;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    public String[] getButtonNames(){
        return buttonNames;
    }
}

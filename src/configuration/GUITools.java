package configuration;

import cellsociety.CellStates;
import configuration.parameters.Parameter;
import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Objects;

public class GUITools {

    public GUITools(){}

    public ImageView getImageView(String filename){
        Image img =  new Image(Objects.requireNonNull(this.getClass().getClassLoader().getResourceAsStream(filename)));
        ImageView img_v = new ImageView(img);
        return img_v;
    }

    public Text makeText(String txt, String font, int size, Paint textColor, double x, double y) {
        Text text = new Text(txt);
        text.setFont(Font.font (font, size));
        text.setFill(textColor);
        text.setX(x);
        text.setY(y);
        return text;
    }

    public Button makeButtons(double x, double y, String txt, double width, String color){
        Button button = new Button(txt);
        button.setStyle(color);
        button.setMinWidth(width);
        button.setEffect(new DropShadow());
        button.setLayoutX(x);
        button.setLayoutY(y);
        return button;
    }

    public ComboBox makeDropDown(String txt, double x, double y, ArrayList<Parameter> possible){
        ComboBox combo_box = new ComboBox(FXCollections.observableArrayList(possible));
        combo_box.setEffect(new DropShadow());
        combo_box.setPromptText(txt);
        combo_box.setLayoutY(y);
        combo_box.setLayoutX(x);
        return combo_box;
    }
}

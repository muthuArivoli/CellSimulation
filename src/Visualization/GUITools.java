package Visualization;

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
import java.util.List;
import java.util.Objects;

/**
 * GUITools holds all the information about creating new GUI elements
 */
public class GUITools {

    /**
     * @param txt The text to go in the Text Box
     * @param font The font to go in the Text Box
     * @param size The size of the Text Box
     * @param textColor The color of the Text Box
     * @param x The x position of the Text Box
     * @param y The y position of the Text Box
     * @return text The created text box
     */
    public Text makeText(String txt, String font, int size, Paint textColor, double x, double y) {
        Text text = new Text(txt);
        text.setFont(Font.font (font, size));
        text.setFill(textColor);
        text.setX(x);
        text.setY(y);
        return text;
    }

    /**
     * @param x The x position of the button
     * @param y The y position of the button
     * @param txt The text to go on the button
     * @param width The width of the button
     * @param color The color of the Text Box
     * @return text The created button
     */
    public Button makeButtons(double x, double y, String txt, double width, String color){
        Button button = new Button(txt);
        button.setStyle(color);
        button.setMinWidth(width);
        button.setEffect(new DropShadow());
        button.setLayoutX(x);
        button.setLayoutY(y);
        return button;
    }

    /**
     * @param x The x position of the combo box
     * @param y The y position of the combo box
     * @param txt The text to go in the combo box
     * @param possible The possible options in the combo box
     * @return text The created combo box
     */
    public ComboBox makeDropDown(String txt, double x, double y, List<Parameter> possible){
        ComboBox combo_box = new ComboBox(FXCollections.observableArrayList(possible));
        combo_box.setEffect(new DropShadow());
        combo_box.setPromptText(txt);
        combo_box.setLayoutY(y);
        combo_box.setLayoutX(x);
        return combo_box;
    }

}

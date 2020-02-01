package configuration;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import java.util.Objects;

public class GUITools {
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

    public Button makeButtons(double x, double y, String txt){
        Button button = new Button(txt);
        button.setLayoutX(x);
        button.setLayoutY(y);
        return button;
    }
}

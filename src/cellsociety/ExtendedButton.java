package cellsociety;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class ExtendedButton extends Button {
    private ImageView myView;

    public ExtendedButton (String name, int x, int y){
        super(name);
        myView.setX(x);
        myView.setY(y);
    }
    public Node getView () {
        return myView;
    }
}

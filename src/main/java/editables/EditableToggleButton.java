package editables;

import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import org.beru.ide.beruide.application.Views;
import org.beru.ide.beruide.styles.Icons;

import java.io.File;

public class EditableToggleButton extends ToggleButton {
    TextField tf = new TextField();

    public EditableToggleButton(String text, File file){
        setText(text);
        tf.setText(getText());
        setText("");
        setGraphic(tf);
        tf.setOnAction(ae -> {
            setText(tf.getText());
            String archive = file.getAbsolutePath() + "/" + tf.getText();
            File n = new File(archive);
            if(n.mkdirs()) {
                Views.INSTANCE.getFileExplorer().load(file);
            }
        });
    }
}

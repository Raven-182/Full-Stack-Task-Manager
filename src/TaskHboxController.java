import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class TaskHboxController extends HBox {

    public TaskHboxController(String task) {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("TaskHbox.fxml"));

        fxmlLoader.setController(this);
        try {

            fxmlLoader.load();
            setLabelText(task);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private HBox hBoxRoot;

    @FXML
    private Button markCompleteButton;

    @FXML
    private Button removeButton;

    @FXML
    private Label tasklabel;

    HBox getHBoxRoot() {
        return hBoxRoot;
    }

    public Button getMarkCompleteButton() {
        return markCompleteButton;
    }

    public void setMarkCompleteButton(Button markCompleteButton) {
        this.markCompleteButton = markCompleteButton;
    }

    public Button getRemoveButton() {
        return removeButton;
    }

    public void setRemoveButton(Button removeButton) {
        this.removeButton = removeButton;
    }

    public Label getTasklabel() {
        return tasklabel;
    }

    public void setLabelText(String text) {
        this.tasklabel.setText(text);
    }

    public void setTasklabel(Label tasklabel) {
        this.tasklabel = tasklabel;
    }

    @FXML
    void markCompleteButtonClicked(ActionEvent event) {

    }

    @FXML
    void removeButtonClicked(ActionEvent event) {

    }
}

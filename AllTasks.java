
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class AllTasks {
    Scene homeScene;

    public void setHome(Scene home) {
        this.homeScene = home;
    }

    public void initData(String task) {
        list.getItems().add(task);

    }

    @FXML
    private Button backButton;

    @FXML
    private ListView<String> list;

    @FXML
    private Label todolabel;

    @FXML
    void backButtonClicked(ActionEvent event) throws IOException {

        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.setScene(homeScene);
        stage.setTitle("Home");
        stage.show();

    }

}

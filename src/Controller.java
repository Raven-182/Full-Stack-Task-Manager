
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class Controller {

    public void initialize() {
        datePicker.setValue(LocalDate.now());
    }

    final File FILE = new File("src/Tasks.txt");

    @FXML
    private Button addButton;

    @FXML
    private TextField addItemField;

    @FXML
    private DatePicker datePicker;

    // list of tasks with buttons
    @FXML
    private ListView<String> list;

    @FXML
    private Label todolabel;

    // this class creates custom listview cells
    static class Cell extends ListCell<String> {

        public Cell() {
            super();

        }

        @Override
        public void updateItem(String task, boolean empty) {
            super.updateItem(task, empty);
            setGraphic(null);
            setText(null);
            if (task != null && !empty) {
                TaskHboxController addedCell = new TaskHboxController(task);
                setGraphic(addedCell.getHBoxRoot());
                addedCell.getRemoveButton().setOnAction(e -> getListView().getItems().remove(getItem()));
                addedCell.getMarkCompleteButton().setOnAction(e -> {

                    addedCell.setLabelText("Completed: " + task);
                    Alert alert = new Alert(AlertType.INFORMATION,
                            "To view Completed tasks, go to Menu -> View Completed Tasks");
                    alert.show();
                    getListView().getItems().remove(getItem());
                    // adds completed task to file
                    FileManager completed = new FileManager();

                    try {
                        completed.writeFile("Completed:  " + task);
                    } catch (IOException e1) {

                        e1.printStackTrace();
                    }
                });

            }

        }

    }

    @FXML
    void addButtonClicked(ActionEvent event) throws IOException {
        Task toDo = new Task(addItemField.getText(), datePicker.getValue());
        FileManager fileManager = new FileManager();
        fileManager.writeFile(toDo.toString());
        // show task
        list.getItems().add(toDo.toString());
        list.setCellFactory(param -> new Cell());

    }

    FileManager fManager = new FileManager();

    @FXML
    void goToCompletedScene(ActionEvent event) throws IOException {
        Stage stage = (Stage) addButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Completed.fxml"));
        Parent completeSceneParent = loader.load();
        Scene completeScene = new Scene(completeSceneParent);
        CompletedTasks controller = loader.getController();
        controller.setHome(stage.getScene());
        // reads completed tasks and displays them
        try (Scanner scan = new Scanner(FILE)) {
            do {
                if (scan.nextLine().startsWith("Completed: "))
                    controller.initData(scan.nextLine());
            } while (scan.hasNextLine());
        } catch (Exception e) {
            System.out.println("File not found");
        }
        stage.setScene(completeScene);
        stage.setTitle("Completed task");
        stage.show();
    }

    @FXML
    void allTasksButtonClicked() throws IOException {
        Stage stage = (Stage) addButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("alltasks.fxml"));
        Parent allTasks = loader.load();
        Scene allTasksScene = new Scene(allTasks);
        AllTasks controller = loader.getController();
        controller.setHome(stage.getScene());
        // writes tasks to list view using initData
        try (Scanner scan = new Scanner(FILE)) {
            while (scan.hasNextLine()) {

                controller.initData(scan.nextLine());
            }
        } catch (Exception e) {
            System.out.println("File not found");
        }
        stage.setScene(allTasksScene);
        stage.setTitle("Completed task");
        stage.show();

    }

}

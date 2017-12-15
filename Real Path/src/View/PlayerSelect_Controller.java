package View;

import Controller.GameManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class PlayerSelect_Controller {

    @FXML
    private void goBack(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("main_menu.fxml"));
        stage.setScene(new Scene(root, 1280, 720));
        stage.show();
    }
    @FXML
    private void start(ActionEvent event) throws IOException {
        GameManager manager = new GameManager();
        Stage stage = new Stage();
        manager.newGame();
        Scene scene =  (new GameScene()).getScene(manager);
        stage.setTitle("Head Ball");

        stage.setFullScreen(false);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

}

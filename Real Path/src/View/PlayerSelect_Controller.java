package View;

import Controller.GameManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.io.IOException;

public class PlayerSelect_Controller {
    @FXML
    ToggleGroup playerOne;
    @FXML
    ToggleGroup playerTwo;
    @FXML
    ToggleGroup gameMode;



    @FXML
    private void goBack(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("main_menu.fxml"));
        stage.setScene(new Scene(root, 1280, 720));
        stage.show();
    }

    @FXML
    private void Start(ActionEvent event) throws IOException {
        RadioButton playerOneToggle = (RadioButton)playerOne.getSelectedToggle();
        RadioButton playerTwoToggle = (RadioButton)playerTwo.getSelectedToggle();
        RadioButton gameModeToggle = (RadioButton)gameMode.getSelectedToggle();

        System.out.println("Player One: " + playerOneToggle.getEllipsisString());
        System.out.println("Player Two: " + playerTwoToggle.getEllipsisString());
        System.out.println("Game Mode: " + gameModeToggle.getText());


        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        GameManager manager = new GameManager();
        manager.newGame();
        Scene scene = (new GameScene()).getScene(manager);
        stage.setTitle("Head Ball");

        stage.setFullScreen(false);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

        /*
        GameScene gameScene = new GameScene();
        Scene scene = gameScene.getScene();
        stage.setScene(scene);
        stage.show();
        */
    }
/*
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
*/
}

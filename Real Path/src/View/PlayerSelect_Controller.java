package View;

import Controller.GameManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class PlayerSelect_Controller {
    @FXML
    ToggleGroup playerOne, playerTwo, gameMode, ballSelection, mapSelection;
    @FXML
    TextField scoreLimit, timeLimit;
    @FXML
    RadioButton radioBallOne, radioBallTwo, radioMapOne, radioMapTwo,radioMapThree;

    private static final int maxLimit = 9; // max limit for time and score

    @FXML
    private void goBack(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("main_menu.fxml"));
        stage.setScene(new Scene(root, 1280, 720));
        stage.show();
    }

    @FXML
    private void Start(ActionEvent event) throws IOException {
        RadioButton playerOneToggle = (RadioButton) playerOne.getSelectedToggle();
        RadioButton playerTwoToggle = (RadioButton) playerTwo.getSelectedToggle();
        RadioButton gameModeToggle = (RadioButton) gameMode.getSelectedToggle();
        RadioButton ballToggle = (RadioButton) ballSelection.getSelectedToggle();
        RadioButton mapToggle = (RadioButton) mapSelection.getSelectedToggle();


        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        GameManager manager = new GameManager();
        manager.newGame();
        Scene scene = (new GameScene()).getScene(manager);
        stage.setTitle("Head Ball");

        // passing user's selections to game manager
        manager.setGameMode(Integer.parseInt(gameModeToggle.getEllipsisString()));
        manager.setSelectedBall(Integer.parseInt(ballToggle.getEllipsisString()));
        manager.setSelectedChar1(Integer.parseInt(playerOneToggle.getEllipsisString()));
        manager.setSelectedChar2(Integer.parseInt(playerTwoToggle.getEllipsisString()));
        manager.setSelectedBackground(Integer.parseInt(mapToggle.getEllipsisString()));

//        manager.setSelectedTimeLimit(Integer.parseInt(timeLimit.getText()));
//        manager.setSelectedScoreLimit(Integer.parseInt(scoreLimit.getText()));

        stage.setFullScreen(false);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    // disables map and ball select if random mode selected
    // or enable if normal mode selecsted
    @FXML
    private void changeDisabled(ActionEvent event) throws IOException {
        radioBallOne.setDisable(!radioBallOne.isDisabled());
        radioBallTwo.setDisable(!radioBallTwo.isDisabled());
        radioMapOne.setDisable(!radioMapOne.isDisabled());
        radioMapTwo.setDisable(!radioMapTwo.isDisabled());
        radioMapThree.setDisable(!radioMapThree.isDisabled());
    }

    // if user input exceeds the max limit
    // then replace input with the max limit
    @FXML
    private void increaseTimeLimit(ActionEvent event) throws IOException {
        if (!(Integer.parseInt(timeLimit.getText()) >= maxLimit))
            timeLimit.setText(Integer.toString((Integer.parseInt(timeLimit.getText())) + 1));
    }

    @FXML
    private void decreaseTimeLimit(ActionEvent event) throws IOException {
        if (!(Integer.parseInt(timeLimit.getText()) <= 1))
            timeLimit.setText(Integer.toString((Integer.parseInt(timeLimit.getText())) - 1));
    }

    @FXML
    private void increaseScoreLimit(ActionEvent event) throws IOException {
        if (!(Integer.parseInt(scoreLimit.getText()) >= maxLimit))
            scoreLimit.setText(Integer.toString((Integer.parseInt(scoreLimit.getText())) + 1));
    }

    @FXML
    private void decreaseScoreLimit(ActionEvent event) throws IOException {
        if (!(Integer.parseInt(scoreLimit.getText()) <= 1))
            scoreLimit.setText(Integer.toString((Integer.parseInt(scoreLimit.getText())) - 1));


    }
}


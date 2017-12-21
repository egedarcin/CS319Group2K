package View;

import Controller.GameManager;

import Controller.InputManager;
import Controller.SoundManager;
import Modal.Ball;
import Modal.GameData;
import Modal.Headballer;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;

import java.io.IOException;
import java.util.ArrayList;

public class GameScene {

    private long StartTime;
    private long StartTime2;
    private boolean goal = false;
    private boolean isFinished = false;
    private boolean win = false;
    private long powerUpStart;


    public GameScene() {


    }


    public Scene getScene(GameManager manager) {
        SoundManager soundManager = new SoundManager();


        Group root = new Group(); //Create a group for holding all objects on the screen
        Scene scene = new Scene(root, GameManager.WIDTH, GameManager.HEIGHT);

        VBox pauseMenu = new VBox();
        final Button continueButton = new Button("Continue");
        continueButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                manager.setPause(false);
                pauseMenu.setOpacity(0);
                pauseMenu.setDisable(true);
            }
        });

        // pause menu
        final Button exitButton = new Button("Exit");
        exitButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                try {
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    Parent root = FXMLLoader.load(getClass().getResource("main_menu.fxml"));
                    stage.setScene(new Scene(root, 1280, 720));
                    stage.show();
                } catch (IOException e) {

                }
            }
        });

        final Button pauseButton = new Button("Pause");
        pauseButton.setLayoutX(15);
        pauseButton.setLayoutY(15);
        pauseButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                manager.setPause(true);
                pauseMenu.setOpacity(100);
                pauseMenu.setDisable(false);
            }
        });

        final Button powerupButton = new Button("SpeedPowerUp");
        powerupButton.setLayoutX((GameManager.WIDTH / 2) - 200);
        powerupButton.setLayoutY((GameManager.HEIGHT - 30));
        powerupButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                powerupButton.setDisable(true);
                manager.setPowerups(true);
                powerUpStart = System.currentTimeMillis();
            }
        });

        pauseMenu.getChildren().addAll(continueButton, exitButton);
        pauseMenu.setLayoutX(640);
        pauseMenu.setLayoutY(360);
        pauseMenu.setOpacity(0);
        pauseMenu.setDisable(true);


        Label timeLabel = new Label();
        Label label = new Label();
        label.setText(manager.getData().getScore1() + " - " + manager.getData().getScore2());
        label.setLayoutX((GameManager.WIDTH / 2) - 50);
        label.setLayoutY(0);
        label.setFont(Font.font("Cambria", 54));
        label.setPrefSize(160, 40);

        Label goalLabel = new Label();
        Image image = new Image(getClass().getResourceAsStream("/View/goalLabel.png"));
        goalLabel.setGraphic(new ImageView(image));
        goalLabel.setPrefSize(250, 100);
        goalLabel.setLayoutX(400);
        goalLabel.setLayoutY(150);
        goalLabel.setVisible(false);

        Label winLabel = new Label();
        Image image2 = new Image(getClass().getResourceAsStream("/View/winner.png"));
        //winLabel.setGraphic(new ImageView(image2));
        winLabel.setPrefSize(200, 100);
        winLabel.setLayoutX(470);
        winLabel.setLayoutY(300);
        winLabel.setVisible(false);

        Label winner = new Label();

        Image first = new Image(getClass().getResourceAsStream("/View/1.png"));
        Image two = new Image(getClass().getResourceAsStream("/View/2.png"));
        Image draw = new Image(getClass().getResourceAsStream("/View/draw.jpg"));

        winner.setVisible(false);


        ArrayList<String> inputs = new ArrayList<String>();

        scene.setOnKeyPressed(e -> {
            String code = e.getCode().toString();

            if (!inputs.contains(code))

            {

                inputs.add(code);


            }


        });

        scene.setOnKeyReleased(e -> {
            String code = e.getCode().toString();
            inputs.remove(code);


        });


        Canvas canvas = new Canvas(GameManager.WIDTH, GameManager.HEIGHT);

        root.getChildren().add(canvas);

        GraphicsContext context = canvas.getGraphicsContext2D();

        Image stadium = new Image(manager.getBackUrl());
        final Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);

        Duration duration = Duration.seconds(1.0 / 180.0f); // Set duration for frame.


        //Create an ActionEvent, on trigger it executes a world time step and moves the balls to new position

        EventHandler<ActionEvent> ae = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                if (!manager.isPause()) {
                    //Create time step. Set Iteration count 8 for velocity and 3 for positions
                    manager.getWorld().step(1.0f / 180.0f, 8, 3);

                    //Move balls to the new position computed by JBox2D
                    Body body = (Body) manager.getBall().getUserData();
                    float xpos = GameManager.toPixelPosX(body.getPosition().x);
                    float ypos = GameManager.toPixelPosY(body.getPosition().y);
                    manager.getBall().setLayoutX(xpos - manager.getBALL_RADIUS());
                    manager.getBall().setLayoutY(ypos - manager.getBALL_RADIUS());

                    Body body2 = (Body) manager.getHeadballer1().getUserData();
                    float xpos2 = GameManager.toPixelPosX(body2.getPosition().x);
                    float ypos2 = GameManager.toPixelPosY(body2.getPosition().y);
                    manager.getHeadballer1().setLayoutX(xpos2 - (manager.HEADSIZE));
                    manager.getHeadballer1().setLayoutY(ypos2 - (manager.HEADSIZE));

                    Body body3 = (Body) manager.getHeadballer2().getUserData();
                    float xpos3 = GameManager.toPixelPosX(body3.getPosition().x);
                    float ypos3 = GameManager.toPixelPosY(body3.getPosition().y);
                    manager.getHeadballer2().setLayoutX(xpos3 - (manager.HEADSIZE));
                    manager.getHeadballer2().setLayoutY(ypos3 - (manager.HEADSIZE));


                    if (manager.getSelectedBackground() != 2) {
                        Vec2 vel1 = new Vec2(0.0f, body2.getLinearVelocity().y);
                        body2.setLinearVelocity(vel1);

                        Vec2 vel2 = new Vec2(0.0f, body3.getLinearVelocity().y);
                        body3.setLinearVelocity(vel2);
                    } else if (manager.getSelectedBackground() == 2) {
                        if (ypos2 < 670) {
                            Vec2 vel1 = new Vec2(0.0f, body2.getLinearVelocity().y);
                            body2.setLinearVelocity(vel1);
                        }
                        if (ypos3 < 670) {
                            Vec2 vel2 = new Vec2(0.0f, body3.getLinearVelocity().y);
                            body3.setLinearVelocity(vel2);
                        }


                    }

                    if (manager.isPower()) {

                        body.setLinearVelocity(body.getLinearVelocity().add(body.getLinearVelocity()));
                    }

                    manager.getInManager().movementController(inputs, manager);


                    label.setText(manager.getData().getScore1() + " - " + manager.getData().getScore2());

                    if (manager.goalScored()) {
                        StartTime = System.currentTimeMillis();
                        goal = true;
                        goalLabel.setVisible(true);
                        soundManager.playCheering();


                    }

                    long tEnd = System.currentTimeMillis();
                    long tDelta = tEnd - StartTime;
                    double elapsedSeconds = tDelta / 1000.0;

                    if (elapsedSeconds > 1.5 && goal) {
                        manager.resetScene();
                        powerupButton.setDisable(false);
                        manager.setPowerups(false);
                        Image stadium = new Image(manager.getBackUrl());
                        context.drawImage(stadium, 0, 0, stadium.getWidth(), stadium.getHeight(), 0, 0, 1280, 720);
                        goal = false;
                        goalLabel.setVisible(false);
                    }


                    int finish = manager.isFinished();

                    if (finish > 0) {
                        StartTime2 = System.currentTimeMillis();
                        isFinished = true;


                        if (finish == 1) {
                            winner.setGraphic(new ImageView(first));
                            win = true;
                        } else if (finish == 2) {

                            winner.setGraphic(new ImageView(two));
                            win = true;
                        } else if (finish == 3) {
                            win = false;
                        }
                    }

                    long tEnd2 = System.currentTimeMillis();
                    long tDelta2 = tEnd2 - StartTime2;
                    double elapsedSeconds2 = tDelta2 / 1000.0;

                    if ((isFinished) && (elapsedSeconds2 > 1.5)) {

                        manager.endGame();

                        if (win) {

                            winner.setPrefSize(75, 75);
                            winner.setLayoutX(593);
                            winner.setLayoutY(190);
                            winLabel.setGraphic(new ImageView(image2));
                            winner.setVisible(true);
                            winLabel.setVisible(true);
                            goalLabel.setVisible(false);
                            isFinished = true;
                        } else if (!win) {
                            winLabel.setGraphic(new ImageView(draw));
                            winLabel.setVisible(true);
                            goalLabel.setVisible(false);
                            isFinished = true;
                        }
                        pauseMenu.setOpacity(100);
                        pauseMenu.setDisable(false);
                        continueButton.setOpacity(0);
                        continueButton.setDisable(true);

                    }
                    long tEnd3 = System.currentTimeMillis();
                    long tDelta3 = tEnd3 - manager.getGameStartTime();
                    double elapsedSeconds3 = tDelta3 / 1000.0;
                    timeLabel.setText("" + (int) elapsedSeconds3);

                    timeLabel.setLayoutX((GameManager.WIDTH / 2) + 95);
                    timeLabel.setLayoutY(0);
                    timeLabel.setFont(Font.font("Cambria", 54));
                    timeLabel.setPrefSize(160, 40);

                    tEnd = System.currentTimeMillis();
                    tDelta = tEnd - powerUpStart;
                    elapsedSeconds = tDelta / 1000.0;

                    if (elapsedSeconds >= 10) {
                        manager.setPowerups(false);

                    }
                }
            }

        };


        context.drawImage(stadium, 0, 0, stadium.getWidth(), stadium.getHeight(), 0, 0, 1280, 720);


        /**
         * Set ActionEvent and duration to the KeyFrame.
         * The ActionEvent is trigged when KeyFrame execution is over.
         */
        KeyFrame frame = new KeyFrame(duration, ae, null, null);

        timeline.getKeyFrames().add(frame);


        final Button btn = new Button();
        btn.setLayoutX((GameManager.WIDTH / 2) - 15);
        btn.setLayoutY((GameManager.HEIGHT - 30));
        btn.setText("Start");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                timeline.playFromStart();
                btn.setVisible(false);
            }
        });


        final Button btn2 = new Button();
        btn2.setLayoutX((GameManager.WIDTH / 2) + 30);
        btn2.setLayoutY((GameManager.HEIGHT - 30));
        btn2.setText("Hit Ball");
        btn2.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                Vec2 vec = new Vec2(0, 5000000.0f);
                Vec2 point = ((Body) manager.getBall().getUserData()).getPosition();
                ((Body) manager.getBall().getUserData()).applyLinearImpulse(vec, point);


            }
        });
        root.getChildren().add(timeLabel);
        root.getChildren().add(winLabel);
        root.getChildren().add(winner);
        root.getChildren().add(goalLabel);
        root.getChildren().add(manager.getBall());
        root.getChildren().add(manager.getHeadballer1());
        root.getChildren().add(manager.getHeadballer2());
        root.getChildren().add(label);
        root.getChildren().add(btn);
        root.getChildren().add(btn2);
        root.getChildren().add(powerupButton);
        root.getChildren().add(pauseButton);
        root.getChildren().add(pauseMenu);
        return scene;
    }
}

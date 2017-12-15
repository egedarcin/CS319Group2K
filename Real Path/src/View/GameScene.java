package View;

import Controller.GameManager;

import Controller.InputManager;
import Modal.Ball;
import Modal.GameData;
import Modal.Headballer;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;

import java.util.ArrayList;

public class GameScene {


    public GameScene() {


    }


    public Scene getScene(GameManager manager) {


        Group root = new Group(); //Create a group for holding all objects on the screen
        Scene scene = new Scene(root, GameManager.WIDTH, GameManager.HEIGHT);


        ArrayList<String> inputs = new ArrayList<String>();

        scene.setOnKeyPressed(e -> {
            String code = e.getCode().toString();

            // only add once... prevent duplicates

            if (!inputs.contains(code))
                inputs.add(code);

            //if(code.equals(String "UP")){boolean}

        });

        scene.setOnKeyReleased(e -> {
            String code = e.getCode().toString();
            inputs.remove(code);

        });


        final Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);

        Duration duration = Duration.seconds(1.0 / 180.0); // Set duration for frame.


        //Create an ActionEvent, on trigger it executes a world time step and moves the balls to new position
        EventHandler<ActionEvent> ae = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {

                //Create time step. Set Iteration count 8 for velocity and 3 for positions
                manager.getWorld().step(1.0f / 180.f, 8, 3);

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


                Vec2 vel1 = new Vec2(0.0f, body2.getLinearVelocity().y);
                body2.setLinearVelocity(vel1);

                Vec2 vel2 = new Vec2(0.0f, body3.getLinearVelocity().y);
                body3.setLinearVelocity(vel2);

                manager.getInManager().movementController(inputs, manager);

                //manager.goalScored();


            }
        };


        Canvas canvas = new Canvas(GameManager.WIDTH, GameManager.HEIGHT);

        root.getChildren().add(canvas);

        GraphicsContext context = canvas.getGraphicsContext2D();

        Image stadium = new Image("/View/stadium2.jpg" /*manager.getBackUrl()*/);
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

        Label label = new Label();
        label.setText(manager.getData().getScore1() + " - " + manager.getData().getScore2());
        label.setLayoutX((GameManager.WIDTH / 2) - 30);
        label.setLayoutY(0);
        label.setFont(Font.font("Cambria", 32));
        label.setPrefSize(100, 40);


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


        root.getChildren().add(manager.getBall());
        root.getChildren().add(manager.getHeadballer1());
        root.getChildren().add(manager.getHeadballer2());
        root.getChildren().add(label);
        root.getChildren().add(btn);
        root.getChildren().add(btn2);


        return scene;
    }
}

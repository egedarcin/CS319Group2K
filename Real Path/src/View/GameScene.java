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
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;

import java.util.ArrayList;

public class GameScene  {

private long StartTime;
private long StartTime2;
private boolean goal = false;
private boolean isFinished = false;
private boolean win = false;
 public GameScene(){


}


public Scene getScene(GameManager manager) {



    Group root = new Group(); //Create a group for holding all objects on the screen
    Scene scene = new Scene(root, GameManager.WIDTH, GameManager.HEIGHT);



    Label label = new Label();
    label.setText(manager.getData().getScore1()+" - "+ manager.getData().getScore2());
    label.setLayoutX((GameManager.WIDTH/2)-50);
    label.setLayoutY(0);
    label.setFont(Font.font("Cambria", 54));
    label.setPrefSize(160,40);

    Label goalLabel = new Label();
    Image image = new Image(getClass().getResourceAsStream("/View/goalLabel.png"));
    goalLabel.setGraphic(new ImageView(image));
    goalLabel.setPrefSize(250,100);
    goalLabel.setLayoutX(400);
    goalLabel.setLayoutY(150);
    goalLabel.setVisible(false);

    Label winLabel = new Label();
    Image image2 = new Image(getClass().getResourceAsStream("/View/winner.png"));
    //winLabel.setGraphic(new ImageView(image2));
    winLabel.setPrefSize(200,100);
    winLabel.setLayoutX(470);
    winLabel.setLayoutY(300);
    winLabel.setVisible(false);

    Label winner = new Label();

    Image first = new Image(getClass().getResourceAsStream("/View/1.png"));
    Image  two = new Image(getClass().getResourceAsStream("/View/2.png"));
    Image  draw = new Image(getClass().getResourceAsStream("/View/draw.jpg"));

    winner.setVisible(false);


        ArrayList<String> inputs = new ArrayList<String>();
        ArrayList<String> inputs2 = new ArrayList<String>();
        scene.setOnKeyPressed(e-> {
            String code = e.getCode().toString();

            // only add once... prevent duplicates
            if(code.equals("A")||code.equals("W")||code.equals("S")||code.equals("D")) {
                if (!inputs.contains(code))
                    inputs.add(code);
            }
            if(code.equals("LEFT")||code.equals("RIGHT")||code.equals("UP")||code.equals("DOWN"))
            {
                if (!inputs2.contains(code))
                     inputs2.add(code);
            }


        });

        scene.setOnKeyReleased(e->{
            String code = e.getCode().toString();
            inputs.remove( code );
            inputs2.remove(code);

        });



        final Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);

        Duration duration = Duration.seconds(1.0/180.0); // Set duration for frame.



        //Create an ActionEvent, on trigger it executes a world time step and moves the balls to new position

        EventHandler<ActionEvent> ae = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                if(!manager.isPause()){
                //Create time step. Set Iteration count 8 for velocity and 3 for positions
                manager.getWorld().step(1.0f/180.f,8, 3);

                //Move balls to the new position computed by JBox2D
                Body body = (Body)manager.getBall().getUserData();
                float xpos = GameManager.toPixelPosX(body.getPosition().x);
                float ypos = GameManager.toPixelPosY(body.getPosition().y);
                manager.getBall().setLayoutX(xpos-manager.getBALL_RADIUS());
                manager.getBall().setLayoutY(ypos-manager.getBALL_RADIUS());

                Body body2 = (Body)manager.getHeadballer1().getUserData();
                float xpos2 = GameManager.toPixelPosX(body2.getPosition().x);
                float ypos2 = GameManager.toPixelPosY(body2.getPosition().y);
                manager.getHeadballer1().setLayoutX(xpos2-(manager.HEADSIZE));
                manager.getHeadballer1().setLayoutY(ypos2-(manager.HEADSIZE));

                Body body3 = (Body)manager.getHeadballer2().getUserData();
                float xpos3 = GameManager.toPixelPosX(body3.getPosition().x);
                float ypos3 = GameManager.toPixelPosY(body3.getPosition().y);
                manager.getHeadballer2().setLayoutX(xpos3-(manager.HEADSIZE));
                manager.getHeadballer2().setLayoutY(ypos3-(manager.HEADSIZE));



                Vec2 vel1 = new Vec2(0.0f,body2.getLinearVelocity().y);
                body2.setLinearVelocity(vel1);

                Vec2 vel2 = new Vec2(0.0f,body3.getLinearVelocity().y);
                body3.setLinearVelocity(vel2);

                    manager.getInManager().movementController2(inputs2,manager);
                    manager.getInManager().movementController1(inputs,manager);


                label.setText(manager.getData().getScore1()+" - "+ manager.getData().getScore2());




                if(manager.goalScored()){
                    StartTime = System.currentTimeMillis();
                    goal = true;
                    goalLabel.setVisible(true);
                }

                long tEnd = System.currentTimeMillis();
                long tDelta = tEnd - StartTime;
                double elapsedSeconds = tDelta / 1000.0;
                if(elapsedSeconds>1.5&&goal)
                {
                 manager.resetScene();
                 goal = false;
                 goalLabel.setVisible(false);
                }


                 int finish =   manager.isFinished();

                 if(finish>0)
                {
                    StartTime2 = System.currentTimeMillis();
                    isFinished = true;


                    if(finish==1)
                    {
                        winner.setGraphic(new ImageView(first));
                        win = true;
                    }

                    else if(finish==2)
                    {

                        winner.setGraphic(new ImageView(two));
                        win = true ;
                    }

                    else if(finish==3)
                    {
                        win = false;
                    }


                }

                    long tEnd2 = System.currentTimeMillis();
                    long tDelta2 = tEnd2 - StartTime2;
                    double elapsedSeconds2 = tDelta2 / 1000.0;

                    if((isFinished) && (elapsedSeconds2 > 1.5))
                    {

                      manager.endGame();

                      if(win)
                      {

                        winner.setPrefSize(75,75);
                        winner.setLayoutX(593);
                        winner.setLayoutY(190);
                        winLabel.setGraphic(new ImageView(image2));
                        winner.setVisible(true);
                        winLabel.setVisible(true);
                        goalLabel.setVisible(false);
                        isFinished = true;
                      }

                      else if(!win)
                      {
                        winLabel.setGraphic(new ImageView(draw));
                        winLabel.setVisible(true);
                        goalLabel.setVisible(false);
                        isFinished = true;
                      }

                    }


            }


            }
        };


        Canvas canvas = new  Canvas(GameManager.WIDTH,GameManager.HEIGHT);

        root.getChildren().add(canvas);

        GraphicsContext context = canvas.getGraphicsContext2D();

        Image stadium = new Image (  manager.getBackUrl());
        context.drawImage(stadium,  0,0,stadium.getWidth(),stadium.getHeight(),0,0,1280,720);



        /**
         * Set ActionEvent and duration to the KeyFrame.
         * The ActionEvent is trigged when KeyFrame execution is over.
         */
        KeyFrame frame = new KeyFrame(duration, ae, null,null);

        timeline.getKeyFrames().add(frame);


        final Button btn = new Button();
        btn.setLayoutX((GameManager.WIDTH/2) -15);
        btn.setLayoutY((GameManager.HEIGHT-30));
        btn.setText("Start");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                timeline.playFromStart();
                btn.setVisible(false);
            }
        });




    final Button btn2 = new Button();
    btn2.setLayoutX((GameManager.WIDTH/2) +30);
    btn2.setLayoutY((GameManager.HEIGHT-30));
    btn2.setText("Hit Ball");
    btn2.setOnAction(new EventHandler<ActionEvent>() {
        public void handle(ActionEvent event) {
            Vec2 vec = new Vec2(0,5000000.0f);
            Vec2 point = ((Body)manager.getBall().getUserData()).getPosition();
            ((Body)manager.getBall().getUserData()).applyLinearImpulse(vec,point);


        }
    });
    root.getChildren().add(winLabel);
    root.getChildren().add(winner);
    root.getChildren().add(goalLabel);
    root.getChildren().add(manager.getBall());
    root.getChildren().add(manager.getHeadballer1());
    root.getChildren().add(manager.getHeadballer2());
    root.getChildren().add(label);
    root.getChildren().add(btn);
    root.getChildren().add(btn2);


   return scene;
}
}

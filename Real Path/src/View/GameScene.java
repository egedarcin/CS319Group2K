package View;

import Controller.GameManager;

import Modal.Ball;
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
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;

import java.util.ArrayList;

public class GameScene  {


public GameScene(){


}


public Scene getScene() {



        Group root = new Group(); //Create a group for holding all objects on the screen
        Scene scene = new Scene(root, GameManager.WIDTH, GameManager.HEIGHT);

        //create ball
        final Ball ball = new Ball(680f, 580f, GameManager.BALL_RADIUS/2,"ball2.png");
        final Headballer headballer2 = new Headballer(220f, 72f, GameManager.BALL_RADIUS/2,"1.png");
        final Headballer headballer1 = new Headballer(980f, 72f, GameManager.BALL_RADIUS/2,"2.png");


        //Add ground to the application, this is where balls will land
        GameManager.addGround(GameManager.WIDTH, 50);

        //Add left and right walls so balls will not move outside the viewing area.
        GameManager.addWall(-40,GameManager.HEIGHT,1f,GameManager.HEIGHT); //Left wall
        GameManager.addWall(GameManager.WIDTH-40,GameManager.HEIGHT,1f,GameManager.HEIGHT); //Right wall
        GameManager.addWall(0,GameManager.HEIGHT, GameManager.WIDTH,1f); // Top wall








        ArrayList<String> inputs = new ArrayList<String>();

        scene.setOnKeyPressed(e-> {
            String code = e.getCode().toString();

            // only add once... prevent duplicates

            if ( !inputs.contains(code) )
                inputs.add( code );

            //if(code.equals(String "UP")){boolean}

        });

        scene.setOnKeyReleased(e->{
            String code = e.getCode().toString();
            inputs.remove( code );

        });












        final Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);

        Duration duration = Duration.seconds(1.0/180.0); // Set duration for frame.



        //Create an ActionEvent, on trigger it executes a world time step and moves the balls to new position
        EventHandler<ActionEvent> ae = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                //Create time step. Set Iteration count 8 for velocity and 3 for positions
                GameManager.world.step(1.0f/180.f, 8, 3);

                //Move balls to the new position computed by JBox2D
                Body body = (Body)ball.getUserData();
                float xpos = GameManager.toPixelPosX(body.getPosition().x);
                float ypos = GameManager.toPixelPosY(body.getPosition().y);
                ball.setLayoutX(xpos);
                ball.setLayoutY(ypos);

                Body body2 = (Body)headballer1.getUserData();
                float xpos2 = GameManager.toPixelPosX(body2.getPosition().x);
                float ypos2 = GameManager.toPixelPosY(body2.getPosition().y);
                headballer1.setLayoutX(xpos2);
                headballer1.setLayoutY(ypos2);

                Body body3 = (Body)headballer2.getUserData();
                float xpos3 = GameManager.toPixelPosX(body3.getPosition().x);
                float ypos3 = GameManager.toPixelPosY(body3.getPosition().y);
                headballer2.setLayoutX(xpos3);
                headballer2.setLayoutY(ypos3);

                //  if(GameManager.world.getContactManager().m_contactList)

                Vec2 vel1 = new Vec2(0.0f,body2.getLinearVelocity().y);
                body2.setLinearVelocity(vel1);

                Vec2 vel2 = new Vec2(0.0f,body3.getLinearVelocity().y);
                body3.setLinearVelocity(vel2);

                if(inputs.contains("LEFT")){
                    Vec2 vecc2 = new Vec2(-250.0f,body2.getLinearVelocity().y);
                    body2.setLinearVelocity(vecc2);

                /*Vec2 newPosition = new Vec2(body2.getPosition().x-(0.6f),body2.getPosition().y);
                body2.setTransform(newPosition,0);*/
                }
                if(inputs.contains("RIGHT")){
                    Vec2 vecc2 = new Vec2(250.0f,body2.getLinearVelocity().y);
                    body2.setLinearVelocity(vecc2);
                }
                if(inputs.contains("DOWN")){
                    Vec2 vecc2 = new Vec2(0.0f,body2.getLinearVelocity().y-5f);
                    body2.setLinearVelocity(vecc2);
                }
                if(inputs.contains("UP")){
                    if(body2.getLinearVelocity().y >=-0.3f &&body2.getLinearVelocity().y <=0.3f && body2.getPosition().y<=200){Vec2 vel  = new Vec2(0.0f, 500.0f);
                        body2.setLinearVelocity(vel);}
                }
                if(inputs.contains("A")){
                    Vec2 vecc2 = new Vec2(-250.0f,body3.getLinearVelocity().y);
                    body3.setLinearVelocity(vecc2);
                }
                if(inputs.contains("D")){
                    Vec2 vecc2 = new Vec2(250.0f,body3.getLinearVelocity().y);
                    body3.setLinearVelocity(vecc2);
                }
                if(inputs.contains("W")){
                    if(body3.getLinearVelocity().y >=-0.3f &&body3.getLinearVelocity().y <=0.3f &&  body3.getPosition().y<=200){Vec2 vel  = new Vec2(0.0f, 500.0f);
                        body3.setLinearVelocity(vel);}
                }
                if(inputs.contains("S")){
                    Vec2 vecc2 = new Vec2(0.0f,body3.getLinearVelocity().y - 5f);
                    body3.setLinearVelocity(vecc2);
                }



            }
        };


        Canvas canvas = new  Canvas(GameManager.WIDTH,GameManager.HEIGHT);

        root.getChildren().add(canvas);

        GraphicsContext context = canvas.getGraphicsContext2D();

        Image stadium = new Image ("/View/stadium2.jpg");
        context.drawImage(stadium,  0,0,stadium.getWidth(),stadium.getHeight(),0,0,canvas.getWidth(),canvas.getHeight());



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



        root.getChildren().add(ball);
        root.getChildren().add(headballer2);
        root.getChildren().add(headballer1);
        final Button btn2 = new Button();
        btn2.setLayoutX((GameManager.WIDTH/2) +30);
        btn2.setLayoutY((GameManager.HEIGHT-30));
        btn2.setText("Bounce Ball");
        btn2.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                Vec2 vec = new Vec2(0,500000.0f);
                Vec2 point = ((Body)ball.getUserData()).getPosition();
                ((Body)ball.getUserData()).applyLinearImpulse(vec,point);


            }
        });
        root.getChildren().add(btn);
        root.getChildren().add(btn2);

   return scene;
}
}

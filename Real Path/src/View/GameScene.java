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
import javafx.util.Duration;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;

import java.util.ArrayList;

public class GameScene  {
public Scene scene;

public GameScene(){

    scene = getScene();
}


public Scene getScene() {

    Group root = new Group(); //Create a group for holding all objects on the screen
    Scene scene = new Scene(root, GameManager.WIDTH, GameManager.HEIGHT);


    Ball ball = new Ball(45, 90, GameManager.BALL_RADIUS-20);

    Headballer headballer1 = new Headballer(20,5,GameManager.BALL_RADIUS);
    Headballer headballer2 = new Headballer(80,5,GameManager.BALL_RADIUS);
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


    //Add ground to the application, this is where balls will land
    GameManager.addGround(100, 20
    );

    //Add left and right walls so balls will not move outside the viewing area.
    GameManager.addWall(0,100,1,100); //Left wall
    GameManager.addWall(99,100,1,100); //Right wall
    GameManager.addWall(0,100,100,1);

    Body body = (Body)ball.node.getUserData();
    Body body2 = (Body)headballer1.node.getUserData();
    Body body3 = (Body)headballer2.node.getUserData();
    body2.setLinearDamping(1.0f);
    body2.setGravityScale(10.f);
    body3.setLinearDamping(1.0f);
    body3.setGravityScale(10.f);
    body.setLinearDamping(0.5f);


    final Timeline timeline = new Timeline();
    timeline.setCycleCount(Timeline.INDEFINITE);

    Duration duration = Duration.seconds(1.0/180.0); // Set duration for frame.

    //Create an ActionEvent, on trigger it executes a world time step and moves the balls to new position
    EventHandler<ActionEvent> ae = new EventHandler<ActionEvent>() {
        public void handle(ActionEvent t) {


            //Create time step. Set Iteration count 8 for velocity and 3 for positions
            GameManager.world.step(1.0f/180.f, 8, 3);
            Vec2 vel1 = new Vec2(0.0f,body2.getLinearVelocity().y);
            body2.setLinearVelocity(vel1);

            Vec2 vel2 = new Vec2(0.0f,body3.getLinearVelocity().y);
            body3.setLinearVelocity(vel2);

            if(inputs.contains("LEFT")){

                Vec2 newPosition = new Vec2(body2.getPosition().x-(0.1f),body2.getPosition().y);
                body2.setTransform(newPosition,0);
            }
            if(inputs.contains("RIGHT")){
                Vec2 newPosition = new Vec2(body2.getPosition().x+(0.1f),body2.getPosition().y);
                body2.setTransform(newPosition,0);
            }
            if(inputs.contains("UP")){
                if(body2.getLinearVelocity().y >=-0.1f &&body2.getLinearVelocity().y <=0.1f && body2.getLinearVelocity().y>=0.0f){Vec2 vel  = new Vec2(0.0f, 150.0f);
                    body2.setLinearVelocity(vel);}
            }
            if(inputs.contains("A")){
                Vec2 newPosition = new Vec2(body3.getPosition().x-(0.1f),body3.getPosition().y);
                body3.setTransform(newPosition,0);
            }
            if(inputs.contains("D")){
                Vec2 newPosition = new Vec2(body3.getPosition().x+(0.1f),body3.getPosition().y);
                body3.setTransform(newPosition,0);
            }
            if(inputs.contains("W")){
                if(body3.getLinearVelocity().y >=-0.1f &&body3.getLinearVelocity().y <=0.1f && body3.getLinearVelocity().y>=0.0f){Vec2 vel  = new Vec2(0.0f, 150.0f);
                    body3.setLinearVelocity(vel);}
            }



            //Move balls to the new position computed by JBox2D



        }
    };


    /**
     * Set ActionEvent and duration to the KeyFrame.
     * The ActionEvent is trigged when KeyFrame execution is over.
     */
    KeyFrame frame = new KeyFrame(duration, ae, null,null);

    timeline.getKeyFrames().add(frame);

    Canvas canvas = new  Canvas(1366,768);

    root.getChildren().add(canvas);

    GraphicsContext context = canvas.getGraphicsContext2D();

    Image stadium = new Image ("/sample/stadium2.jpg");
    Image balll = new Image("/sample/ball.png");
    Image headballerr = new Image ("/sample/1.png");
    Image headballerr2 = new Image ("/sample/10.png");


    context.drawImage(stadium,  0,0,stadium.getWidth(),stadium.getHeight(),0,0,canvas.getWidth(),canvas.getHeight());

    new AnimationTimer()
    {
        @Override
        public void handle(long currentNanoTime )
        {
            context.clearRect(0, 0, 512,512);
            float xpos = GameManager.toPixelPosX(body.getPosition().x);
            float ypos = GameManager.toPixelPosY(body.getPosition().y);
            float xpos2 = GameManager.toPixelPosX(body2.getPosition().x);
            float ypos2 = GameManager.toPixelPosY(body2.getPosition().y);
            float xpos3 = GameManager.toPixelPosX(body3.getPosition().x);
            float ypos3 = GameManager.toPixelPosY(body3.getPosition().y);
            context.drawImage(stadium,  0,0,stadium.getWidth(),stadium.getHeight(),0,0,canvas.getWidth(),canvas.getHeight());
            context.drawImage(balll,xpos,ypos,136.6, 76.8);
            context.drawImage(headballerr,xpos2,ypos2,191.24, 107.50);
            context.drawImage(headballerr2,xpos3,ypos3,191.24, 107.50 );
        }}.start();



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
    btn2.setText("Bounce Ball");
    btn2.setOnAction(new EventHandler<ActionEvent>() {
        public void handle(ActionEvent event) {
            Vec2 vec = new Vec2(0,5000.0f);
            Vec2 point = body.getPosition();
            body.applyLinearImpulse(vec,point);


        }
    });
    root.getChildren().add(btn);
    root.getChildren().add(btn2);
   return scene;
}
}

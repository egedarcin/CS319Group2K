package sample;

        import javafx.animation.AnimationTimer;
        import javafx.application.Application;
        import javafx.fxml.FXMLLoader;
        import javafx.scene.Group;
        import javafx.scene.Scene;
        import javafx.scene.canvas.Canvas;
        import javafx.scene.canvas.GraphicsContext;
        import javafx.scene.image.Image;

        import javafx.stage.Stage;

        import java.util.ArrayList;

public class GameScene extends Application{


    @Override
    public void start(Stage primaryStage) throws Exception{
        Stage GameStage = primaryStage;
        Group root = new Group();
        GameStage.setTitle("HeadBall");
        Scene scene = new Scene(root,GameStage.getWidth(),GameStage.getHeight());

        GameStage.setScene(scene);


        Canvas canvas = new  Canvas(500,500);
        root.getChildren().add(canvas);

        GraphicsContext context = canvas.getGraphicsContext2D();

        double plainLevel = canvas.getHeight()*4/5;

        HandBaller handballer1 = new HandBaller(canvas.getWidth()*4/5,canvas.getHeight()*4/5,0,0,"/sample/1.png");
        context.drawImage(handballer1.getVision(),handballer1.getPositionX(),handballer1.getPositionY());


        //Ball ball = new Ball("/sample/ball.png")




        Image stadium = new Image ("/sample/stadium2.png");
        context.drawImage(stadium,  0,0,stadium.getWidth(),stadium.getHeight(),0,0,canvas.getWidth(),canvas.getHeight());




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

        handballer1.setSpeedX(0);
        handballer1.setSpeedY(0);

        final long startTime = System.nanoTime();

        new AnimationTimer()
        {
            @Override
            public void handle(long currentNanoTime)
            {
                context.clearRect(0, 0, 512,512);
                context.drawImage(stadium,  0,0,stadium.getWidth(),stadium.getHeight(),0,0,canvas.getWidth(),canvas.getHeight());
                context.drawImage(handballer1.getVision(),handballer1.getPositionX(),handballer1.getPositionY(),100,100);

                handballer1.setSpeedX(0);
                if(handballer1.getPositionY()<canvas.getHeight()*4/5 ){handballer1.setSpeedY(handballer1.getSpeedY()+1);}
                if(handballer1.getPositionY()>=canvas.getHeight()*4/5){handballer1.setSpeedY(0);}
                if(inputs.contains("LEFT")){
                    handballer1.setSpeedX(-5);

                }
                if(inputs.contains("RIGHT")){
                    handballer1.setSpeedX(+5);
                }
                if(inputs.contains("UP") && handballer1.getPositionY()>=canvas.getHeight()*4/5){handballer1.setSpeedY(-20);}

                handballer1.setPositionX(handballer1.getPositionX()+handballer1.getSpeedX());
                handballer1.setPositionY(handballer1.getPositionY()+handballer1.getSpeedY());
              //  System.out.println(handballer1.getPositionY()+" "+handballer1.getSpeedY());
                context.drawImage(handballer1.getVision(),handballer1.getPositionX(),handballer1.getPositionY(),100,100);
            }
        }.start();



        GameStage.widthProperty().addListener(
                observable ->{canvas.setWidth(GameStage.getWidth());redraw(context,stadium,canvas);});
        GameStage.heightProperty().addListener(
                observable -> {canvas.setHeight(GameStage.getHeight());redraw(context,stadium,canvas);});
        GameStage.show();

    }
    public void redraw(GraphicsContext context, Image stadium, Canvas canvas) {

        context.drawImage(stadium,0,0,stadium.getWidth(),stadium.getHeight(),0,0,canvas.getWidth(),canvas.getHeight());}



    public static void main(String[] args) {
        launch(args);
    }
}

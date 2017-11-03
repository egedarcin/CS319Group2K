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

    private double X;
    private double Y;
    private double getX(){return X;}
    private double getY(){return Y;}
    private void setX(double x){this.X = x;}
    private void setY(double y){this.Y = y;}
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

        Image player1 = new Image("/sample/1P2.png");
        // Image player2 = new Image("/sample/1P1.png");
        Image stadium = new Image ("/sample/stadium2.png");
        // Image ball = new Image("/sample/ball.png");

        setX(canvas.getWidth()*4/5);
        setY( canvas.getHeight()*4/5);
        //context.drawImage(stadium,0,0,stadium.getWidth(),stadium.getHeight(),0,0,canvas.getWidth(),canvas.getHeight());
        context.drawImage(player1,canvas.getWidth()*4/5,canvas.getHeight()*4/5);
        //context.drawImage(player2,canvas.getWidth()/5,canvas.getHeight()*4/5);
        //context.drawImage(ball,player2.,player2.getHeight()-30);




       /* GameStage.widthProperty().addListener(
                observable ->{canvas.setWidth(GameStage.getWidth());redraw(context,stadium,canvas);});
        GameStage.heightProperty().addListener(
                observable -> {canvas.setHeight(GameStage.getHeight());redraw(context,stadium,canvas);});*/

        ArrayList<String> inputs = new ArrayList<String>();

        scene.setOnKeyPressed(e-> {
            String code = e.getCode().toString();

            // only add once... prevent duplicates
            if ( !inputs.contains(code) )
                inputs.add( code );

        });

        scene.setOnKeyReleased(e->{
            String code = e.getCode().toString();
            inputs.remove( code );

        });


        final long startTime = System.nanoTime();

        new AnimationTimer()
        {
            @Override
            public void handle(long currentNanoTime)
            {
                context.clearRect(0, 0, 512,512);
                context.drawImage(stadium,0,0,stadium.getWidth(),stadium.getHeight(),0,0,canvas.getWidth(),canvas.getHeight());
                context.drawImage(player1,getX(),getY());
                if(inputs.contains("LEFT")){
                    setX(getX()-5);
                    context.drawImage(player1,getX(),getY());
                }
                if(inputs.contains("RIGHT")){
                    setX(getX()+5);
                    context.drawImage(player1,getX(),getY());
                }
            }
        }.start();
        GameStage.show();

    }
   /* public void redraw(GraphicsContext context, Image stadium, Canvas canvas) {

        context.drawImage(stadium,0,0,stadium.getWidth(),stadium.getHeight(),0,0,canvas.getWidth(),canvas.getHeight());
        /*context.drawImage(player1,canvas.getWidth()*4/5,canvas.getHeight()*4/5);
        context.drawImage(player2,canvas.getWidth()/5,canvas.getHeight()*4/5);
        context.drawImage(ball,player2.getWidth()+30,player2.getHeight()-30);*/


    public static void main(String[] args) {
        launch(args);
    }
}

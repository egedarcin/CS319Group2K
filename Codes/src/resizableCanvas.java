package sample;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class resizableCanvas extends Canvas {
    public resizableCanvas()
    {
        widthProperty().addListener(evt -> draw());
        heightProperty().addListener(evt -> draw());
    }
    private void draw() {
        double width = getWidth();
        double height = getHeight();

        GraphicsContext gc = getGraphicsContext2D();
        gc.clearRect(0, 0, width, height);

        gc.setStroke(Color.RED);
        gc.strokeLine(0, 0, width, height);
        gc.strokeLine(0, height, width, 0);
        Image player1 = new Image("/sample/1P2.png");
        Image player2 = new Image("/sample/1P1.png");
        Image stadium = new Image ("/sample/stadium2.png");
        Image ball = new Image("/sample/ball.png");
         gc.drawImage(stadium,0,0);
       /* gc.drawImage(player1,400,400);
        gc.drawImage(player2,25,400);
        gc.drawImage(ball,50,350);
    */ }

    @Override
    public boolean isResizable() {
        return true;
    }

    @Override
    public double prefWidth(double height) {
        return getWidth();
    }

    @Override
    public double prefHeight(double width) {
        return getHeight();
    }

}



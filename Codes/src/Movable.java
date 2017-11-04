package sample;

public class Movable extends GameObject{
    private final int gravity = 10;

    public Movable(double positionX, double positionY,double speedX, double speedY,String url){
        super(positionX,positionY,url);
        this.speedX = speedX;
        this.speedY = speedY;

    }

    private double speedX;


    public double getSpeedX() {
        return speedX;
    }

    public void setSpeedX(double speedX) {
        this.speedX = speedX;
    }

    public  double getSpeedY() {
        return speedY;
    }

    public void setSpeedY(double speedY) {
        this.speedY = speedY;
    }

    private double speedY;
}

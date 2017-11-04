package sample;

import javafx.scene.image.Image;

public abstract class GameObject {

    public Image getVision() {
        vision = new Image(getImageUrl());
        return vision;
    }

    private Image vision;

    public GameObject(double positionX,double positionY,String imageUrl){
        this.positionX = positionX;
        this.positionY = positionY;
        this.ImageUrl = imageUrl;
        vision = new Image(imageUrl);

    }

    public double getPositionX() {
        return positionX;
    }

    public void setPositionX(double positionX) {
        this.positionX = positionX;
    }

    private double positionX;

    public double getPositionY() {
        return positionY;
    }

    public void setPositionY(double positionY) {
        this.positionY = positionY;
    }

    private double positionY;

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    private String ImageUrl;

}

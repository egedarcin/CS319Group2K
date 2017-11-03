package sample;

public abstract class GameObject {

    private double getPositionX() {
        return positionX;
    }

    private void setPositionX(double positionX) {
        this.positionX = positionX;
    }

    private double positionX;

    private double getPositionY() {
        return positionY;
    }

    private void setPositionY(double positionY) {
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

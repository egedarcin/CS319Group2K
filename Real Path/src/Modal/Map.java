package Modal;

import Controller.GameManager;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class Map extends Parent {

    private float posX;

    public float getPosY() {
        return posY;
    }

    public void setPosY(float posY) {
        this.posY = posY;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public float getPosX() {

        return posX;
    }

    public void setPosX(float posX) {
        this.posX = posX;
    }

    private float posY;
    private String ImageUrl;

    GameManager manager;

    public Map(float posX, float posY, String ImageUrl, GameManager manager) {
        this.posX = posX;
        this.posY = posY;
        this.ImageUrl = ImageUrl;
        this.manager = manager;
        setImage(ImageUrl);
    }

    abstract void create();

    public void setImage(String url)
    {
        ImageView iv = new ImageView();

        Image image = new Image(Ball.class.getResourceAsStream(url));
        iv.setImage(image);

        getChildren().add(iv);
    }
}

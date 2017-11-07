package Modal;

import Controller.GameManager;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;

public class Movable {
    public Node node;

    //X and Y position of the ball in JBox2D world
    private float posX;
    private float posY;
    private Color color ;
    //Ball radius in pixels
    private int radius;

    public Movable(float posX, float posY, int radius)
    {
        this.posX = posX;
        this.posY = posY;
        this.radius = radius;

        node = create();
    }


    private Node create() {

        Circle object = new Circle();
        object.setRadius(radius);
        object.setFill(color); //set look and feel

        /**
         * Set ball position on JavaFX scene. We need to convert JBox2D coordinates
         * to JavaFX coordinates which are in pixels.*/

        object.setLayoutX(GameManager.toPixelPosX(posX));
        object.setLayoutY(GameManager.toPixelPosY(posY));

        BodyDef bd = new BodyDef();
        bd.type = BodyType.DYNAMIC;
        bd.position.set(posX, posY);

        CircleShape cs = new CircleShape();
        cs.m_radius = radius * 0.1f;

        FixtureDef fd = new FixtureDef();
        fd.shape = cs;
        fd.density = 0.6f;
        fd.friction = 0.3f;
        fd.restitution = 0.8f;
        Body body = GameManager.world.createBody(bd);
        body.createFixture(fd);
        object.setUserData(body);

        return object;

    }
}

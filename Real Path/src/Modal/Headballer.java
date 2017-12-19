package Modal;

import Controller.GameManager;
import javafx.scene.Parent;
import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;

public class Headballer extends Map implements Movable {

    private float radius;

    public Headballer(float posX, float posY, float radius, String url,GameManager manager)
    {
        super(posX,posY,url,manager);
        this.radius = radius;

        create();
    }


    @Override
    public void create()
    {
        this.setLayoutX(GameManager.toPixelPosX(getPosX())-manager.HEADSIZE);
        this.setLayoutY(GameManager.toPixelPosY(getPosY())-manager.HEADSIZE);

        //Create an JBox2D body defination for ball.
        BodyDef bd = new BodyDef();
        bd.type = BodyType.DYNAMIC;
        bd.position.set(getPosX(),getPosY());

        CircleShape cs = new CircleShape();
        cs.m_radius = radius  ;  //We need to convert radius to JBox2D equivalent

        // Create a fixture for ball
        FixtureDef fd = new FixtureDef();
        fd.shape = cs;
        fd.density = 15.0f;
        fd.friction = 1f;
        fd.restitution = 0.0f;

        /**
         * Virtual invisible JBox2D body of ball. Bodies have velocity and position.
         * Forces, torques, and impulses can be applied to these bodies.
         */
        Body body = manager.getWorld().createBody(bd);


        body.createFixture(fd);
        this.setUserData(body);

    }
}

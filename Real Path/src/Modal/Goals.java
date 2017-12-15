package Modal;

import Controller.GameManager;
import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;

public class Goals extends Map {
    private int width;
    private int height;

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }


    public Goals(float posX, float posY, String ImageUrl,int width, int height, GameManager manager) {
        super(posX, posY, ImageUrl, manager);
        this.width = width;
        this.height = height;
        create();
    }

    @Override
    void create() {
        PolygonShape ps = new PolygonShape();
        ps.setAsBox(width,height);


        FixtureDef fd = new FixtureDef();
        fd.shape = ps;

        fd.friction = 0.9f;

        BodyDef bd = new BodyDef();
        bd.position.set(super.getPosX(),super.getPosX() );
        manager.getWorld().createBody(bd).createFixture(fd);
        this.setUserData(bd);
    }
}

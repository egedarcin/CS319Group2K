package Controller;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;
public class GameManager {

    public static final World world = new World(new Vec2(0.0f, -250.0f));

    //Screen width and height
    public static final int WIDTH = 1366;
    public static final int HEIGHT = 768 ;

    //Ball radius in pixel
    public static final int BALL_RADIUS =64;

    public static void addGround(float width, float height){
        PolygonShape ps = new PolygonShape();
        ps.setAsBox(width,height);

        FixtureDef fd = new FixtureDef();
        fd.shape = ps;
        fd.friction = 0.9f;
        BodyDef bd = new BodyDef();
        bd.position= new Vec2(0.0f,-10f);

        world.createBody(bd).createFixture(fd);

    }
    public static void addWall(float posX, float posY, float width, float height){
        PolygonShape ps = new PolygonShape();
        ps.setAsBox(width,height);


        FixtureDef fd = new FixtureDef();
        fd.shape = ps;

        fd.friction = 0.9f;

        BodyDef bd = new BodyDef();
        bd.position.set(posX, posY);
        world.createBody(bd).createFixture(fd);

    }

    public static float toPixelPosX(float posX) {
        float x = WIDTH*posX / 1366f;
        return x;
    }

    //Convert a JavaFX pixel x coordinate to a JBox2D x coordinate
    public static float toPosX(float posX) {
        float x =   (posX*100.0f*1.0f)/WIDTH;
        return x;
    }

    //Convert a JBox2D y coordinate to a JavaFX pixel y coordinate
    public static float toPixelPosY(float posY) {
        float y = HEIGHT - (1.0f*HEIGHT) * posY / 768f;
        return y;
    }

    //Convert a JavaFX pixel y coordinate to a JBox2D y coordinate
    public static float toPosY(float posY) {
        float y = 100.0f - ((posY * 100*1.0f) /HEIGHT) ;
        return y;
    }

    //Convert a JBox2D width to pixel width
    public static float toPixelWidth(float width) {
        return WIDTH*width / 100.0f;
    }

    //Convert a JBox2D height to pixel height
    public static float toPixelHeight(float height) {
        return HEIGHT*height/100.0f;
    }
}

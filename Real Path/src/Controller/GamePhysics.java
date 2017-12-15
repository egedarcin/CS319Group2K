package Controller;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;

public class GamePhysics {


    static boolean isThereKick;



    public static void setThereKick(boolean thereKick) {
        isThereKick = thereKick;
    }

    public void addWall(float posX, float posY, float width, float height, World world){
        PolygonShape ps = new PolygonShape();
        ps.setAsBox(width,height);


        FixtureDef fd = new FixtureDef();
        fd.shape = ps;

        fd.friction = 0.9f;

        BodyDef bd = new BodyDef();
        bd.position.set(posX, posY);
        world.createBody(bd).createFixture(fd);
}

    public  void addGround(float width, float height, World world){
        PolygonShape ps = new PolygonShape();
        ps.setAsBox(width,height);

        FixtureDef fd = new FixtureDef();
        fd.shape = ps;
        fd.friction = 0.4f;
        BodyDef bd = new BodyDef();
        bd.position= new Vec2(0.0f,0f);

        world.createBody(bd).createFixture(fd);

    }

    public  void addGoals( World world, float goalsHeights ,float
                           goal1Width , float goal2Width ,float goalsY , float goal1X  , float goal2X){
        PolygonShape ps = new PolygonShape();
        ps.setAsBox(goal2Width,goalsHeights);

        FixtureDef fd = new FixtureDef();
        fd.shape = ps;
        fd.friction = 0.9f;
        BodyDef bd = new BodyDef();
        bd.position= new Vec2(goal2X,goalsY);

        world.createBody(bd).createFixture(fd);


        PolygonShape ps2 = new PolygonShape();
        ps2.setAsBox(goal1Width,goalsHeights);

        FixtureDef fd2 = new FixtureDef();
        fd2.shape = ps2;
        fd2.friction = 0.9f;
        BodyDef bd2 = new BodyDef();
        bd2.position= new Vec2(goal1X,goalsY);

        world.createBody(bd2).createFixture(fd2);

    }

    public static boolean checkKick(){

        return isThereKick;

    }


}

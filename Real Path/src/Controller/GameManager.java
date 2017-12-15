package Controller;
import Modal.Ball;
import Modal.GameData;
import Modal.Goals;
import Modal.Headballer;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;
public class GameManager {

    private GameData data = new GameData();

    public GameData getData() {
        return data;
    }

    public GamePhysics getPhysics() {
        return physics;
    }

    public InputManager getInManager() {
        return inManager;
    }

    private GamePhysics physics = new GamePhysics();
    private InputManager inManager = new InputManager();
    public static final Vec2 initialHeadballer1Pos   = new Vec2(100,49);
    public static final Vec2 initialHeadballer2Pos   = new Vec2(1000,49);
    public static final Vec2 initialBallPos   = new Vec2(600,600);


    //Screen width and height
    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720 ;

    public static final int HEADSIZE = 48;


    //Ball radius in pixel
    private int BALL_RADIUS = 32 ;







    private   World world ;
    private  Headballer headballer1;
    private  Headballer headballer2;
    private Ball ball;

    public final float goalsHeights = 5;
    private final float goal1Width = 130;
    private final float goal2Width = 70;
    private final float goalsY = 300;
    private final float goal1X = 0;
    private final float goal2X = 1210;

    private int gameMode;
    private  float gravity = -300.0f;
    private float groundFriction;
    private int selectedChar1;
    private int selectedChar2;
    private  int selectedBall;
    private int selectedBackground;
    private String hb1Url;
    private String hb2Url;
    private String ballUrl;
    private String backUrl;








    public boolean goalScored()
    {
       Body ball =  (Body)(this.getBall().getUserData());


       if((ball.getPosition().x< goal1Width )&&
               (ball.getPosition().y< goalsY ))
       {
            data.updateScore(data.getScore1(),data.getScore2()+1);
            resetScene();
            return true;
       }

       else if((ball.getPosition().x > goal2X )&&
               (ball.getPosition().y < goalsY ))
       {
           data.updateScore(data.getScore1()+1,data.getScore2());

           resetScene();
           return true;
       }
       return false;
    }


    public void resetScene()
    {
        Body hb1 = (Body)this.getHeadballer1().getUserData();
        Body hb2 = (Body)this.getHeadballer2().getUserData();
        Body ball = (Body)this.getBall().getUserData();



        ball.synchronizeTransform();
        hb1.synchronizeTransform();
        hb2.synchronizeTransform();

        hb2.getPosition().set(initialHeadballer2Pos);
        ball.getPosition().set(initialBallPos);



        hb1.setType(BodyType.DYNAMIC);
        hb2.setType(BodyType.DYNAMIC);
        ball.setType(BodyType.DYNAMIC);

    }

    public void newGame()
    {
        this.setWorld( new World(new Vec2(0.0f, this.getGravity())));
        CollisionListener cl = new CollisionListener();
        this.world.setContactListener(cl);
        headballer1 = new Headballer(initialHeadballer1Pos.x,initialHeadballer1Pos.y, HEADSIZE,"1.png"/*getHb1Url()*/,this);
        headballer2 = new Headballer(initialHeadballer2Pos.x,initialHeadballer2Pos.y,HEADSIZE,"11.png"/*getHb2Url()*/,this);
        ball = new Ball(initialBallPos.x,initialBallPos.y,getBALL_RADIUS(),"ball.png"/*getBallUrl()*/,this);

        //Add ground to the application, this is where balls will land
        physics.addGround(GameManager.WIDTH, 1.0f, this.world);

        //Add left and right walls so balls will not move outside the viewing area.
        physics.addWall(0,GameManager.HEIGHT,1f,GameManager.HEIGHT,this.world); //Left wall
        physics.addWall(GameManager.WIDTH,GameManager.HEIGHT,1f,GameManager.HEIGHT,this.world); //Right wall
        physics.addWall(0,GameManager.HEIGHT, GameManager.WIDTH,1f,this.world); // Top wall
        physics.addGoals(this.world,goalsHeights ,
         goal1Width ,goal2Width , goalsY , goal1X  ,goal2X
    );
    }

   public boolean isFinished()
   {
        if(data.getScore1()==data.getScoreLimit())
        {
            //player1 won
            endGame();
            return true;
        }

        else if (data.getScore2()==data.getScoreLimit())
        {
            //player2 won
            endGame();
            return true;
        }

        else if(data.getCurrentTime()>=data.getTimeLimit())
        {
            if(data.getScore1()>data.getScore2()){/*player1 won*/ }
            if(data.getScore1()<data.getScore2()){/*player2 won*/ }
            if(data.getScore1()==data.getScore2()){/*noone won*/ }
            endGame();
            return true;
        }
        return false;
   }

   public void endGame()
   {}

   public void checkPowerups(){}























    public int getGameMode() {
        return gameMode;
    }

    public void setGameMode(int gameMode) {
        this.gameMode = gameMode;
    }

    public float getGravity() {
        return gravity;
    }

    public void setGravity(float gravity) {
        this.gravity = gravity;
    }

    public float getGroundFriction() {
        return groundFriction;
    }

    public void setGroundFriction(float groundFriction) {
        this.groundFriction = groundFriction;
    }

    public int getSelectedChar1() {
        return selectedChar1;
    }

    public void setSelectedChar1(int selectedChar1) {
        this.selectedChar1 = selectedChar1;
    }

    public int getSelectedChar2() {
        return selectedChar2;
    }

    public void setSelectedChar2(int selectedChar2) {
        this.selectedChar2 = selectedChar2;
    }

    public int getSelectedBall() {
        return selectedBall;
    }

    public void setSelectedBall(int selectedBall) {
        this.selectedBall = selectedBall;
    }

    public int getSelectedBackground() {
        return selectedBackground;
    }

    public void setSelectedBackground(int selectedBackground) {
        this.selectedBackground = selectedBackground;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public Headballer getHeadballer1() {
        return headballer1;
    }

    public void setHeadballer1(Headballer headballer1) {
        this.headballer1 = headballer1;
    }

    public Headballer getHeadballer2() {
        return headballer2;
    }

    public void setHeadballer2(Headballer headballer2) {
        this.headballer2 = headballer2;
    }

    public Ball getBall() {
        return ball;
    }

    public void setBall(Ball ball) {
        this.ball = ball;
    }



    public static float toPixelPosX(float posX) {
        float x = WIDTH*posX / 1280f;
        return x;
    }

    //Convert a JavaFX pixel x coordinate to a JBox2D x coordinate
    public static float toPosX(float posX) {
        float x =   (posX*100.0f*1.0f)/WIDTH;
        return x;
    }

    //Convert a JBox2D y coordinate to a JavaFX pixel y coordinate
    public static float toPixelPosY(float posY) {
        float y = HEIGHT - (1.0f*HEIGHT) * posY / 720f;
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
    public String getHb1Url() {
        return hb1Url;
    }

    public void setHb1Url(String hb1Url) {
        this.hb1Url = hb1Url;
    }

    public String getHb2Url() {
        return hb2Url;
    }

    public void setHb2Url(String hb2Url) {
        this.hb2Url = hb2Url;
    }

    public String getBallUrl() {
        return ballUrl;
    }

    public void setBallUrl(String ballUrl) {
        this.ballUrl = ballUrl;
    }

    public String getBackUrl() {
        return backUrl;
    }

    public void setBackUrl(String backUrl) {
        this.backUrl = backUrl;
    }
    public int getBALL_RADIUS() {
        return BALL_RADIUS;
    }

    public void setBALL_RADIUS(int BALL_RADIUS) {
        this.BALL_RADIUS = BALL_RADIUS;
    }

}

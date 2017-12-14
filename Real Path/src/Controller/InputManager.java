package Controller;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;

import java.util.ArrayList;

public class InputManager {

    public void movementController(ArrayList<String> inputs, GameManager manager)
    {
        Body body = (Body)manager.getBall().getUserData();

        Body body2 = (Body)manager.getHeadballer1().getUserData();


        Body body3 = (Body)manager.getHeadballer2().getUserData();


        if(inputs.contains("LEFT")){
            Vec2 vecc2 = new Vec2(-250.0f,body2.getLinearVelocity().y);
            body2.setLinearVelocity(vecc2);

                /*Vec2 newPosition = new Vec2(body2.getPosition().x-(0.6f),body2.getPosition().y);
                body2.setTransform(newPosition,0);*/
        }
        if(inputs.contains("RIGHT")){
            Vec2 vecc2 = new Vec2(250.0f,body2.getLinearVelocity().y);
            body2.setLinearVelocity(vecc2);
        }
        if(inputs.contains("DOWN")){
            Vec2 vecc2 = new Vec2(0.0f,body2.getLinearVelocity().y-5f);
            body2.setLinearVelocity(vecc2);
        }
        if(inputs.contains("UP")){
            if(body2.getLinearVelocity().y >=-0.3f &&body2.getLinearVelocity().y <=0.3f && body2.getPosition().y<=200){Vec2 vel  = new Vec2(0.0f, 500.0f);
                body2.setLinearVelocity(vel);}
        }
        if(inputs.contains("A")){
            Vec2 vecc2 = new Vec2(-250.0f,body3.getLinearVelocity().y);
            body3.setLinearVelocity(vecc2);
        }
        if(inputs.contains("D")){
            Vec2 vecc2 = new Vec2(250.0f,body3.getLinearVelocity().y);
            body3.setLinearVelocity(vecc2);
        }
        if(inputs.contains("W")){
            if(body3.getLinearVelocity().y >=-0.3f &&body3.getLinearVelocity().y <=0.3f &&  body3.getPosition().y<=200){Vec2 vel  = new Vec2(0.0f, 500.0f);
                body3.setLinearVelocity(vel);}
        }
        if(inputs.contains("S")){
            Vec2 vecc2 = new Vec2(0.0f,body3.getLinearVelocity().y - 5f);
            body3.setLinearVelocity(vecc2);
        }

    }
}

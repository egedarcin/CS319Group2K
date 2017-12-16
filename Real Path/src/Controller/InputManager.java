package Controller;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyType;

import java.util.ArrayList;

public class InputManager {

    public void movementController1(ArrayList<String> inputs, GameManager manager)
    {


        Body body2 = (Body)manager.getHeadballer1().getUserData();




        if(inputs.contains("A")){
            Vec2 vecc2 = new Vec2(-250.0f,body2.getLinearVelocity().y);
            body2.setLinearVelocity(vecc2);


        }
        if(inputs.contains("D")){
            Vec2 vecc2 = new Vec2(250.0f,body2.getLinearVelocity().y);
            body2.setLinearVelocity(vecc2);
        }
        if(inputs.contains("S")){
            Vec2 vecc2 = new Vec2(0.0f,body2.getLinearVelocity().y-5f);
            body2.setLinearVelocity(vecc2);
        }
        if(inputs.contains("W")){
            if(body2.getPosition().y>body2.getContactList().contact.m_fixtureA.m_body.getPosition().y)
            {Vec2 vel  = new Vec2(0, 700.0f);
                body2.setLinearVelocity(vel);}
              }

    }
    public void movementController2(ArrayList<String> inputs2, GameManager manager)
    {


        Body body3 = (Body)manager.getHeadballer2().getUserData();

        if(inputs2.contains("LEFT")){
            Vec2 vecc2 = new Vec2(-250.0f,body3.getLinearVelocity().y);
            body3.setLinearVelocity(vecc2);
        }
        if(inputs2.contains("RIGHT")){
            Vec2 vecc2 = new Vec2(250.0f,body3.getLinearVelocity().y);
            body3.setLinearVelocity(vecc2);
        }
        if(inputs2.contains("UP")){
            if((body3.getPosition().y>body3.getContactList().contact.m_fixtureA.m_body.getPosition().y))
            {Vec2 vel  = new Vec2(0, 700.0f);
            body3.setLinearVelocity(vel);}
        }
        if(inputs2.contains("DOWN")){
            Vec2 vecc2 = new Vec2(0.0f,body3.getLinearVelocity().y - 5f);
            body3.setLinearVelocity(vecc2);
        }}
}

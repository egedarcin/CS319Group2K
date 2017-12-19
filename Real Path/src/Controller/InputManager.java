package Controller;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyType;

import java.util.ArrayList;

public class InputManager {

    public void movementController(ArrayList<String> inputs, GameManager manager)
    {
        Body body = (Body)manager.getBall().getUserData();

        Body body2 = (Body)manager.getHeadballer1().getUserData();


        Body body3 = (Body)manager.getHeadballer2().getUserData();


        if(inputs.contains("A")){
            Vec2 vecc2 = new Vec2(-250.0f,body2.getLinearVelocity().y);
            body2.setLinearVelocity(vecc2);

                /*Vec2 newPosition = new Vec2(body2.getPosition().x-(0.6f),body2.getPosition().y);
                body2.setTransform(newPosition,0);*/
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

            if(((body2.getContactList().contact.m_fixtureA.m_body.m_type== BodyType.STATIC)
                    &&(body2.getPosition().y>body2.getContactList().contact.m_fixtureA.m_body.getPosition().y))||
                    ((body2.getContactList().contact.m_fixtureB.m_body.m_type== BodyType.STATIC)
                            &&(body2.getPosition().y>body2.getContactList().contact.m_fixtureB.m_body.getPosition().y)) ||
                     ((body2.getContactList().contact.m_fixtureB.m_body.m_type== BodyType.DYNAMIC)
                    &&(body2.getPosition().y>body2.getContactList().contact.m_fixtureB.m_body.getPosition().y))||
                    ((body2.getContactList().contact.m_fixtureA.m_body.m_type== BodyType.DYNAMIC)
                            &&(body2.getPosition().y>body2.getContactList().contact.m_fixtureA.m_body.getPosition().y)))
            {Vec2 vel  = new Vec2(body2.getLinearVelocity().x, 700.0f);
                body2.setLinearVelocity(vel);}
        }
        if(inputs.contains("LEFT")){
            Vec2 vecc2 = new Vec2(-250.0f,body3.getLinearVelocity().y);
            body3.setLinearVelocity(vecc2);
        }
        if(inputs.contains("RIGHT")){
            Vec2 vecc2 = new Vec2(250.0f,body3.getLinearVelocity().y);
            body3.setLinearVelocity(vecc2);
        }
        if(inputs.contains("UP")){
            if(((body3.getContactList().contact.m_fixtureA.m_body.m_type== BodyType.STATIC)
                    &&(body3.getPosition().y>body3.getContactList().contact.m_fixtureA.m_body.getPosition().y))||
                    ((body3.getContactList().contact.m_fixtureB.m_body.m_type== BodyType.STATIC)
                    &&(body3.getPosition().y>body3.getContactList().contact.m_fixtureB.m_body.getPosition().y)) ||
                    ((body3.getContactList().contact.m_fixtureA.m_body.m_type== BodyType.DYNAMIC)
                    &&(body3.getPosition().y>body3.getContactList().contact.m_fixtureA.m_body.getPosition().y))||
                    ((body3.getContactList().contact.m_fixtureB.m_body.m_type== BodyType.DYNAMIC)
                    &&(body3.getPosition().y>body3.getContactList().contact.m_fixtureB.m_body.getPosition().y)))
            {Vec2 vel  = new Vec2(body3.getLinearVelocity().x, 700.0f);
                body3.setLinearVelocity(vel);}
        }
        if(inputs.contains("DOWN")){
            Vec2 vecc2 = new Vec2(0.0f,body3.getLinearVelocity().y - 5f);
            body3.setLinearVelocity(vecc2);
        }

    }
}

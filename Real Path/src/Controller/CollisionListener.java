    package Controller;

    import org.jbox2d.callbacks.ContactImpulse;
    import org.jbox2d.callbacks.ContactListener;
    import org.jbox2d.collision.Manifold;
    import org.jbox2d.dynamics.Body;
    import org.jbox2d.dynamics.BodyType;
    import org.jbox2d.dynamics.contacts.Contact;

    public class CollisionListener implements ContactListener {



        @Override
        public void endContact(Contact contact) {
            if(GamePhysics.checkKick()){GamePhysics.setThereKick(false);}
        }

        @Override
        public void preSolve(Contact contact, Manifold manifold) {

        }

        @Override
        public void postSolve(Contact contact, ContactImpulse contactImpulse) {

        }


        @Override
        public void beginContact(Contact contact)

        {

            if(contact.m_fixtureA.m_body.m_type.equals(BodyType.DYNAMIC)&&contact.m_fixtureB.m_body.m_type.equals(BodyType.DYNAMIC))
             {
                if(contact.m_fixtureA.m_density!=contact.m_fixtureB.m_density)

                {GamePhysics.setThereKick(true);}


             }
        }
    }

package com.mygdx.game;

import com.badlogic.gdx.physics.box2d.*;
import missiles.MissileA;


public class GameContactListener implements ContactListener {


    @Override
    public void beginContact(Contact contact) {
            //System.out.println("Contact");
            Fixture fa = contact.getFixtureA();
            Fixture fb = contact.getFixtureB();

       // System.out.println(bullets.size());
            if(fb.getBody().getUserData()=="MissileA"){

                System.out.println(fa.getBody().getType()+" has hit "+ fb.getBody().getType());
            }
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold manifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse contactImpulse) {

    }
}

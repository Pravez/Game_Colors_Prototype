package com.color.game.actors;


import com.badlogic.gdx.physics.box2d.Body;
import com.color.game.box2d.UserData;

public class Missile extends GameActor{


    public Missile(Body body) {
        super(body);
    }

    @Override
    public UserData getUserData() {
        return null;
    }
}

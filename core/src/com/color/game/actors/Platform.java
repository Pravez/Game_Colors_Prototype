package com.color.game.actors;


import com.badlogic.gdx.physics.box2d.Body;
import com.color.game.box2d.PlatformUserData;

public class Platform extends GameActor{

    public Platform(Body body) {
        super(body);
    }

    @Override
    public PlatformUserData getUserData() {
        return (PlatformUserData) userData;
    }
}

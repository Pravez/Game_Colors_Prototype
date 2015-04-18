package com.color.game.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.Body;
import com.color.game.box2d.PikeUserData;
import com.color.game.box2d.UserData;

public class Pike extends GameActor{

    private Texture texture;


    public Pike(Body body) {
        super(body);
    }

    @Override
    public UserData getUserData() {
        return (PikeUserData) userData;
    }
}

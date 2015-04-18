package com.color.game.actors;


import com.badlogic.gdx.physics.box2d.Body;
import com.color.game.box2d.UserData;

public class Missile extends GameActor{


    public Missile(Body body) {
        super(body);
        this.body.setLinearVelocity(50, 0);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    private void move() {
    }

    @Override
    public UserData getUserData() {
        return null;
    }
}

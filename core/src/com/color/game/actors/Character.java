package com.color.game.actors;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.color.game.box2d.CharacterUserData;
import com.color.game.utils.Constants;

public class Character extends GameActor{

    private boolean jumping;
    private boolean left, right;
    private boolean onWall;

    public Character(Body body) {
        super(body);
        addListener(new CharacterListener());
        body.setLinearDamping(2.0f);
        jumping = false;
        left = false;
        right = false;
        onWall = false;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        move();
    }

    @Override
    public CharacterUserData getUserData() {
        return (CharacterUserData) userData;
    }

    public void jump(){
        if(!jumping){
            body.applyLinearImpulse(getUserData().getJumpingLinearImpulse(), body.getWorldCenter(), true);
            jumping = true;
        }
    }

    public void move(){

        Vector2 linearImpulse = new Vector2(0,0);

        if(right){
            if(body.getLinearVelocity().x < Constants.CHARACTER_MAX_VELOCITY.x){
                linearImpulse.x = getUserData().getMovingLinearImpulse().x;
            }else{
                body.setLinearVelocity(Constants.CHARACTER_MAX_VELOCITY.x, body.getLinearVelocity().y);
            }
        }

        if(left){
            if(body.getLinearVelocity().x > -Constants.CHARACTER_MAX_VELOCITY.x){
                linearImpulse.x = -getUserData().getMovingLinearImpulse().x;
            }else{
                body.setLinearVelocity(-Constants.CHARACTER_MAX_VELOCITY.x, body.getLinearVelocity().y);
            }
        }

        body.applyLinearImpulse(linearImpulse, body.getWorldCenter(), true);

    }

    public void landed(){
        jumping = false;
    }

    class CharacterListener extends InputListener{
        @Override
        public boolean keyDown(InputEvent event, int keycode) {
            if(keycode == Input.Keys.LEFT){
                left = true;
            }
            if(keycode == Input.Keys.RIGHT){
                right = true;
            }
            if(keycode == Input.Keys.SPACE){
                jump();
            }
            if(keycode == Input.Keys.SHIFT_LEFT || keycode == Input.Keys.SHIFT_RIGHT){
                getUserData().increaseMovement();
            }
            return true;
        }

        @Override
        public boolean keyUp(InputEvent event, int keycode) {
            if(keycode == Input.Keys.LEFT){
                left = false;
            }
            if(keycode == Input.Keys.RIGHT){
                right = false;
            }
            if(keycode == Input.Keys.SHIFT_LEFT || keycode == Input.Keys.SHIFT_RIGHT){
                getUserData().decreaseMovement();
            }
            return true;
        }
    }

    public boolean isOnWall() {
        return onWall;
    }

    public void OnWall(boolean onWall) {
        this.onWall = onWall;
    }
}

package com.color.game.actors;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.color.game.box2d.CharacterUserData;
import com.color.game.enums.CharacterState;
import com.color.game.utils.Constants;

public class Character extends GameActor{

    private boolean jumping;
    private boolean left, right;
    private boolean onWall;
    private CharacterState state;

    public Character(Body body) {

        super(body);
        addListener(new CharacterListener());
        body.setLinearDamping(2.0f);

        state = CharacterState.IDLE;

        jumping = false;
        left = false;
        right = false;
        onWall = false;

    }

    @Override
    public void act(float delta) {
        super.act(delta);
        move();
        if(body.getPosition().y<=0){
            setState(CharacterState.DEAD);
            right=false;
            left=false;
        }
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
        if(right || left)
            setState(CharacterState.MOVING);
        else
            setState(CharacterState.IDLE);
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
                state = CharacterState.JUMPING;
            }
            if(keycode == Input.Keys.SHIFT_LEFT || keycode == Input.Keys.SHIFT_RIGHT){
                getUserData().increaseMovement();
            }
            if((left || right) && !(state == CharacterState.JUMPING)){
                setState(CharacterState.MOVING);
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
            if(!left && !right){
                setState(CharacterState.IDLE);
            }
            return true;
        }
    }

    public Vector2 getPosition() {
        return this.body.getPosition();
    }

   /* public boolean isOnWall() {
        return onWall;
    }

    public void onWall(boolean onWall) {
        this.onWall = onWall;
        if(onWall){

        }
    }*/

    public CharacterState getState() {
        return state;
    }

    public void setState(CharacterState state) {
        this.state = state;
    }

    public boolean isDead(){
        return state == CharacterState.DEAD;
    }
}

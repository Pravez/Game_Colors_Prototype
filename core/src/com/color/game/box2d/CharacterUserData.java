package com.color.game.box2d;


import com.badlogic.gdx.math.Vector2;
import com.color.game.enums.UserDataType;
import com.color.game.utils.Constants;

public class CharacterUserData extends UserData {


    public Vector2 jumpingLinearImpulse;
    public Vector2 movingLinearImpulse;
    public float gravityFactor;

    public CharacterUserData(){
        super();
        userDataType = UserDataType.CHARACTER;
        jumpingLinearImpulse = Constants.CHARACTER_JUMPING_LINEAR_IMPULSE;
        movingLinearImpulse = Constants.CHARACTER_MOVING_LINEAR_IMPULSE;
    }

    public Vector2 getJumpingLinearImpulse() {
        return jumpingLinearImpulse;
    }

    public Vector2 getMovingLinearImpulse() {
        return movingLinearImpulse;
    }

    public void increaseMovement(){
        jumpingLinearImpulse = Constants.CHARACTER_JUMPING_LINEAR_IMPULSE_BOOSTED;
        movingLinearImpulse = Constants.CHARACTER_MOVING_LINEAR_IMPULSE_BOOSTED;
        Constants.CHARACTER_MAX_VELOCITY = Constants.CHARACTER_MAX_VELOCITY_BOOSTED;
    }

    public void decreaseMovement(){
        jumpingLinearImpulse = Constants.CHARACTER_JUMPING_LINEAR_IMPULSE;
        movingLinearImpulse = Constants.CHARACTER_MOVING_LINEAR_IMPULSE;
        Constants.CHARACTER_MAX_VELOCITY = Constants.CHARACTER_MAX_VELOCITY_NORMAL;
    }

    public void setGravityOnWall(){

    }

    public void setGravityNormal(){

    }

    public void setJumpingLinearImpulse(Vector2 jumpingLinearImpulse) {
        this.jumpingLinearImpulse = jumpingLinearImpulse;
    }
}

package com.color.game.box2d;


import com.badlogic.gdx.math.Vector2;
import com.color.game.enums.UserDataType;
import com.color.game.utils.Constants;

public class CharacterUserData extends UserData {


    public Vector2 jumpingLinearImpulse;
    public Vector2 movingRightLinearImpulse;
    private Vector2 movingLeftLinearImpulse;

    public CharacterUserData(){
        super();
        userDataType = UserDataType.CHARACTER;
        jumpingLinearImpulse = Constants.CHARACTER_JUMPING_LINEAR_IMPULSE;
        movingRightLinearImpulse = Constants.CHARACTER_MOVING_RIGHT_LINEAR_IMPULSE;
        movingLeftLinearImpulse = Constants.CHARACTER_MOVING_LEFT_LINEAR_IMPULSE;
    }

    public Vector2 getJumpingLinearImpulse() {
        return jumpingLinearImpulse;
    }

    public Vector2 getMovingRightLinearImpulse() {
        return movingRightLinearImpulse;
    }

    public void setJumpingLinearImpulse(Vector2 jumpingLinearImpulse) {
        this.jumpingLinearImpulse = jumpingLinearImpulse;
    }

    public Vector2 getMovingLeftLinearImpulse() {
        return movingLeftLinearImpulse;
    }
}

package com.color.game.box2d;


import com.color.game.enums.UserDataType;
import com.color.game.utils.Constants;

public class MissileUserData extends UserData{

    public MissileUserData(){
        super(Constants.MISSILE_WIDTH, Constants.MISSILE_HEIGHT);
        userDataType = UserDataType.MISSILE;
    }
}

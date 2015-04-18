package com.color.game.box2d;


import com.color.game.enums.UserDataType;

public class MissileUserData extends  UserData{

    public MissileUserData(){
        super(0,0);
        userDataType = UserDataType.MISSILE;
    }

    public MissileUserData(float width, float height){
        super(width, height);
        userDataType = UserDataType.MISSILE;
    }
}

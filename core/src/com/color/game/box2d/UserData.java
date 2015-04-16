package com.color.game.box2d;


import com.color.game.enums.UserDataType;

public abstract class UserData {

    protected UserDataType userDataType;

    public UserData(){

    }

    public UserDataType getUserDataType() {
        return userDataType;
    }
}

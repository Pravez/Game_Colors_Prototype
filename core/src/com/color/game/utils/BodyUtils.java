package com.color.game.utils;


import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.color.game.box2d.UserData;
import com.color.game.enums.UserDataType;

public class BodyUtils {

    public static boolean bodyIsCharacter(Body body) {
        UserData userData = (UserData) body.getUserData();

        return userData != null && userData.getUserDataType() == UserDataType.CHARACTER;
    }

    public static boolean bodyIsPlatform(Body body) {
        UserData userData = (UserData) body.getUserData();

        return userData != null && userData.getUserDataType() == UserDataType.PLATFORM;
    }

    public static boolean bodyIsDoor(Body body) {
        UserData userData = (UserData) body.getUserData();

        return userData != null && userData.getUserDataType() == UserDataType.DOOR;
    }

    public static boolean bodyIsMissile(Body body){
        UserData userData = (UserData) body.getUserData();

        return userData != null && userData.getUserDataType() == UserDataType.MISSILE;
    }

    public static boolean bodyIsStatic(Body body){
        UserData userData = (UserData) body.getUserData();

        return userData != null && body.getType() == BodyDef.BodyType.StaticBody;
    }
}

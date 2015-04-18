package com.color.game.box2d;


import com.color.game.enums.UserDataType;

public abstract class UserData {

    protected UserDataType userDataType;
    protected float width;
    protected float height;
    protected boolean flaggedForDelete;

    public UserData(float width, float height){
        this.width = width;
        this.height = height;
        flaggedForDelete = false;
    }

    public UserDataType getUserDataType() {
        return userDataType;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public boolean isFlaggedForDelete() {
        return flaggedForDelete;
    }

    public void setFlaggedForDelete(boolean flaggedForDelete) {
        this.flaggedForDelete = flaggedForDelete;
    }
}

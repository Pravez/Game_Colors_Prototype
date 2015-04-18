package com.color.game.stages;

import com.badlogic.gdx.math.Vector2;

public class GameStage extends BaseStage {

    public GameStage() {
        super();
    }

    @Override
    public boolean keyUp(int keyCode) {
        character.keyUp(keyCode);
        return super.keyUp(keyCode);
    }

    @Override
    public boolean keyDown(int keyCode) {
        character.keyDown(keyCode);
        return super.keyDown(keyCode);
    }

    @Override
    protected void drawStage() {

    }

    @Override
    protected void actStage() {

    }

    @Override
    public void init() {
        initializeScene(new Vector2(3, 2));
    }

    @Override
    public void end() {

    }
}

package com.color.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.color.game.stages.GameStage;

public class GameScreen implements Screen {

    public GameStage stage;

    public GameScreen(){
        stage = new GameStage();
    }

    @Override
    public void show() {
        this.stage.respawn();
        Gdx.input.setInputProcessor(this.stage);
    }

    @Override
    public void render(float v) {

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(v);
        stage.draw();
    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() { }

    @Override
    public void dispose() {

    }
}

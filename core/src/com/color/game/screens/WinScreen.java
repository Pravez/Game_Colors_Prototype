package com.color.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Timer;
import com.color.game.ColorGame;
import com.color.game.levels.LevelManager;

public class WinScreen implements Screen {

    private BitmapFont font;
    private Stage stage;
    private Timer timer;

    public WinScreen() {
        timer = new Timer();

        this.stage = new Stage();
        Table table = new Table();

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("28 Days Later.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 42;
        font = generator.generateFont(parameter);
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        Label title = new Label("You Won", new Label.LabelStyle(font, Color.RED));

        table.add(title).padBottom(30).row();
        Image image = new Image();
        image.setDrawable(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("dragons.png")), 0, 0, 0.25f, 0.25f)));
        table.add(image);

        table.setFillParent(true);
        stage.addActor(table);

        generator.dispose();
    }

    @Override
    public void show() {
        timer.clear();
        timer.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                ColorGame colorGame = ((ColorGame) Gdx.app.getApplicationListener());
                if (LevelManager.isFinished()) {
                    colorGame.setScreen(colorGame.getMenuScreen());
                } else {
                    colorGame.setGameScreen();
                }
            }
        }, 2.0f);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) { }

    @Override
    public void pause() { }

    @Override
    public void resume() { }

    @Override
    public void hide() { }

    @Override
    public void dispose() {
        stage.dispose();
        font.dispose();
    }
}

package com.color.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Timer;
import com.color.game.ColorGame;

public class EndScreen implements Screen {

    private BitmapFont font;
    private Stage stage;
    private Timer timer;

    public EndScreen() {
        timer = new Timer();

        this.stage = new Stage();
        Table table = new Table();

        Sprite sprite = new Sprite(new Texture(Gdx.files.internal("dialog.png")));
        table.setBackground(new SpriteDrawable(sprite));

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Future-Earth.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 28;
        font = generator.generateFont(parameter);
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        table.add(new Label("You finished the game,", new Label.LabelStyle(font, new Color(142f/255, 188f/255, 224f/255, 1)))).row();
        table.add(new Label("Congratulations !", new Label.LabelStyle(font, new Color(142f/255, 188f/255, 224f/255, 1)))).padBottom(30).row();

        Image image = new Image();
        image.setDrawable(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("character-idle.png")), 0, 0, 1f/6, 1f/8)));
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
                ((ColorGame) Gdx.app.getApplicationListener()).setMenuScreen();
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

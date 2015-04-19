package com.color.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.color.game.ColorGame;

public class OptionScreen implements Screen {

    private Stage stage;
    private BitmapFont font;
    private Skin skin;

    private Label soundValue;
    private Label musicValue;

    public OptionScreen() {
        this.stage = new Stage();
        Table table = new Table();
        skin = new Skin();

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("28 Days Later.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 42;
        font = generator.generateFont(parameter);
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        Label title = new Label("Options", new Label.LabelStyle(font, Color.RED));

        parameter.size = 30;
        skin.add("28days", generator.generateFont(parameter));
        skin.addRegions(new TextureAtlas(Gdx.files.internal("uiskin.atlas")));
        skin.load(Gdx.files.internal("uiskin.json"));
        TextButton buttonMenu = new TextButton("Back to menu", skin);

        Label music = new Label("Music Volume :", skin);
        Slider sliderMusic = new Slider(0.0f, 1.0f, 0.1f, false, skin);
        musicValue = new Label("" + (ColorGame.musicVolume * 10), skin);

        Label sound = new Label("Sound Volume :", skin);
        Slider sliderSound = new Slider(0.0f, 1.0f, 0.1f, false, skin);
        soundValue = new Label("" + (ColorGame.soundVolume * 10), skin);

        table.add(title).padLeft(1.8f * music.getPrefWidth()).padBottom(40).row();

        table.add(music).padLeft(-music.getPrefWidth());
        table.add(sliderMusic, musicValue);
        table.row();

        table.add(sound).padLeft(-music.getPrefWidth());
        table.add(sliderSound, soundValue);
        table.row();

        table.add(buttonMenu).size(250,60).padTop(80).padLeft(1.8f * music.getPrefWidth()).row();

        table.setFillParent(true);
        stage.addActor(table);

        sliderMusic.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                ColorGame.setMusicVolume(((Slider) actor).getValue());
                musicValue.setText("" + (ColorGame.musicVolume * 10));
            }
        });

        sliderSound.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                ColorGame.soundVolume = ((Slider) actor).getValue();
                soundValue.setText("" + (ColorGame.soundVolume * 10));
            }
        });

        buttonMenu.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((ColorGame) Gdx.app.getApplicationListener()).setMenuScreen();
            }
        });

        generator.dispose();
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        font.dispose();
        skin.dispose();
    }
}

package com.color.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Timer;
import com.color.game.ColorGame;

public class DeathScreen implements Screen {

    private Stage stage;
    private Table table;
    private SpriteBatch batch;
    private Texture texture;
    private Label title;

    private Timer timer;

    public DeathScreen() {
        texture = new Texture(Gdx.files.internal("skull.png"));
        batch = new SpriteBatch();

        this.stage = new Stage();
        this.table = new Table();

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("28 Days Later.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 62;
        BitmapFont font = generator.generateFont(parameter);
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        generator.dispose();

        this.title = new Label("You died", new Label.LabelStyle(font, Color.RED));

        table.add(title).padTop(Gdx.graphics.getHeight()/2).row();
        table.setFillParent(true);
        stage.addActor(table);

        timer = new Timer();
        timer.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                ((ColorGame)Gdx.app.getApplicationListener()).setScreen(((ColorGame)Gdx.app.getApplicationListener()).getGameScreen());
            }
        }, 1.0f);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        int width = Gdx.graphics.getWidth();
        int height = Gdx.graphics.getHeight();
        int textureHeight = (texture.getHeight() * width/2) / texture.getWidth();
        batch.draw(texture, width/4, height/2 - textureHeight/2, width/2, textureHeight);
        batch.end();

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

    }
}

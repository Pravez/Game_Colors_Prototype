package com.color.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Timer;
import com.color.game.ColorGame;

public class SplashScreen implements Screen {

    private Sprite background;
    private Stage stage;
    private SpriteBatch batch;
    private BitmapFont font;

    private float fadeTimeAlpha = 0f;
    private boolean fadeOut = false;
    private boolean fadeIn = true;

    public SplashScreen() {
        background = new Sprite(new Texture(Gdx.files.internal("background.png")));

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("28 Days Later.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 62;
        font = generator.generateFont(parameter);
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        this.stage = new Stage();
        Table table = new Table();
        table.add(new Label("Game Color Prototype", new Label.LabelStyle(font, Color.WHITE)));
        table.setFillParent(true);
        this.stage.addActor(table);

        batch = new SpriteBatch();

        generator.dispose();
    }

    public void fadeOut() {
        this.fadeOut = true;
    }

    private void runFading() {
        if (fadeOut) {
            fadeTimeAlpha -= 0.05f;
            if (fadeTimeAlpha <= -1.0f) {
                ((ColorGame) Gdx.app.getApplicationListener()).endSplashScreen();
            }
        }
        if (fadeIn) {
            fadeTimeAlpha += 0.1f;
            if (fadeTimeAlpha >= 1.1f) {
                fadeTimeAlpha = 1.0f;
                fadeIn = false;
                ((ColorGame) Gdx.app.getApplicationListener()).initGame();
            }
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        Color color = batch.getColor();
        batch.setColor(color.r, color.g, color.b, fadeTimeAlpha < 0 ? 0 : fadeTimeAlpha);
        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();

        stage.act(delta);
        stage.draw();

        runFading();
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
        this.stage.dispose();
        this.font.dispose();
        this.batch.dispose();
    }
}

package com.color.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.color.game.elements.Block;
import com.color.game.elements.Wall;
import com.color.game.level.Map;


public class GameScreen implements Screen {

    Map map;
    ShapeRenderer shapeRenderer;
    SpriteBatch batch;
    Texture img;
    int unity = 20; // l'unité de dimension, une unité vaut 20 pixels


    public GameScreen(){
        batch = new SpriteBatch();
        img = new Texture("badlogic.jpg");
        map = new Map();
        shapeRenderer = new ShapeRenderer();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float v) {

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        /*batch.begin();
        batch.draw(img, 0, 0);
        batch.end();*/

        /** Affichage de la Map **/
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(0.8f, 0, 0, 0.5f);
        shapeRenderer.rect(0, 0, unity * map.size.x, unity * map.size.y);
        // Affichage des murs
        shapeRenderer.setColor(1, 1, 1, 1);
        for (Wall w : this.map.walls) {
            shapeRenderer.rect(w.position.x * unity, w.position.y * unity, unity, unity);
        }
        // Affichage des Blocs de couleur
        for (Block b : this.map.blocks) {
            switch (b.color) {
                case RED:
                    shapeRenderer.setColor(1, 0, 0, 1);
                    break;
                case GREEN:
                    shapeRenderer.setColor(0, 1, 0, 1);
                    break;
                case BLUE:
                    shapeRenderer.setColor(0, 0, 1, 1);
                    break;
            }
            shapeRenderer.rect(b.bounds.x * unity, b.bounds.y * unity, b.bounds.width * unity, b.bounds.height * unity);
        }
        shapeRenderer.end();

        // Update des données
        map.update();
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
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}

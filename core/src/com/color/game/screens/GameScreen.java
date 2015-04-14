package com.color.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.color.game.elements.Block;
import com.color.game.elements.protoEnums.ProtoColor;
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
        /** Affichage de la Map **/
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(0, 0, 0, 1);
        shapeRenderer.rect(0, 0, unity * map.size.x, unity * map.size.y);

        // Affichage des Blocs
        for (int i = 0 ; i < this.map.size.x ; i++) {
            for (int j = 0 ; j < this.map.size.y ; j++) {
                Block b = this.map.blocks.get(i).get(j);
                if (b.color != ProtoColor.EMPTY) {
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
                        case NEUTRAL:
                            shapeRenderer.setColor(1, 1, 1, 1);
                            break;
                        default:
                            break;
                    }
                    shapeRenderer.rect(i * unity, j * unity, unity, unity);
                }
            }
        }

        // Affichage du personnage
        shapeRenderer.setColor(1, 1, 0, 1);
        int radius = unity/2;
        int posX = (int) (this.map.character.position.x * unity + radius);
        int posY = (int) (this.map.character.position.y * unity + radius);
        shapeRenderer.circle(posX, posY, radius);

        shapeRenderer.end();

        // Update des données
        map.update(v);
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

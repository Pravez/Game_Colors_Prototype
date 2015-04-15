package com.color.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Timer;
import com.color.game.elements.Block;
import com.color.game.elements.protoEnums.ProtoColor;
import com.color.game.level.Map;

public class GameScreen implements Screen {

    Map map;
    ShapeRenderer shapeRenderer;
    SpriteBatch batch;
    Texture img;
    Timer.Task changingColorTask;
    public static final int unity = 20; // l'unité de dimension, une unité vaut 20 pixels

    public GameScreen(){
        batch = new SpriteBatch();
        img = new Texture("badlogic.jpg");
        map = new Map();
        shapeRenderer = new ShapeRenderer();
        changingColorTask = new Timer.Task() {
            @Override
            public void run() {
                changeCharacterColor();
            }
        };
        new Timer().scheduleTask(changingColorTask, 5.0f, 5.0f);
    }

    private void changeCharacterColor() {
        this.map.character.color = this.map.character.color.next();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float deltaTime) {
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
                    setShapeRendererColor(b.color);
                    shapeRenderer.rect(i * unity, j * unity, unity, unity);
                }
            }
        }

        // Affichage du personnage
        setShapeRendererColor(this.map.character.color);
        int radius = unity/2;
        int posX = (int) (this.map.character.bounds.x + radius);
        int posY = (int) (this.map.character.bounds.y + radius);
        shapeRenderer.circle(posX, posY, radius);
        shapeRenderer.rect((this.map.size.x - 1) * unity, (this.map.size.y - 1) * unity, unity, unity);
        shapeRenderer.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(1, 1, 1, 1);
        shapeRenderer.circle(posX, posY, radius);
        shapeRenderer.end();

        // Update des données

        map.update(deltaTime);
    }

    public void setShapeRendererColor(ProtoColor color) {
        switch (color) {
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

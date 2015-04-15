package com.color.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Timer;
import com.color.game.elements.*;
import com.color.game.elements.protoEnums.ProtoColor;
import com.color.game.level.Map;

public class GameScreen implements Screen {

    boolean lerpChangingColor; // boolean --> do we have to start the linear interpolation of the character color ?
    float lerpChangingTime; // between 0 and 1 --> where are we in the linear interpolation state ?
    static float lerpChangingDelay = 0.75f; // the delay of the linear interpolation
    static float changingColorDelay = 5.0f; // the delay between each color change
    float changingColorTime; // time since the last color change

    Map map; // the Map of the Game
    ShapeRenderer shapeRenderer; // the shapeRenderer to render shapes
    Timer timer; // the timer to call timed methods

    Timer.Task lerpColorTask; // the task to launch the linear interpolation
    Timer.Task changingColorTask; // the task to change the character color

    public static final int unity = 20; // the dimension unity in the map, one unit equals 20 pixels

    /**
     * Constructor of the GameScreen
     */
    public GameScreen(Game game){
        map = new Map(this); // initialising the Map
        shapeRenderer = new ShapeRenderer(); // initialising the ShapeRenderer

        /** Tasks **/
        // Task to change the Character color
        changingColorTask = new Timer.Task() {
            @Override
            public void run() {
                changeCharacterColor();
            }
        };
        // Task to launch the linear interpolation for the character color
        lerpColorTask = new Timer.Task() {
            @Override
            public void run() {
                lerpChangingColor = true;
                lerpChangingTime = 0f;
            }
        };
        timer = new Timer();
        init(); // init the Game
    }

    /**
     * Init method called to boot or reboot the game
     */
    public void init() {
        timer.clear(); // remove the tasks from the timer
        timer.scheduleTask(lerpColorTask, changingColorDelay - lerpChangingDelay, changingColorDelay); // every 5s, before changing the color, we begin the linear interpolation
        timer.scheduleTask(changingColorTask, changingColorDelay, changingColorDelay); // every 5s, we change the character color
        lerpChangingColor = false; // we are not changing the color of the character
        changingColorTime = 0f;
    }

    /**
     * Method to change the character color
     */
    private void changeCharacterColor() {
        this.map.character.color = this.map.character.color.next();
        lerpChangingColor = false; // we have finished changing its color
        changingColorTime = 0f;
    }

    @Override
    public void show() { }

    /**
     * Method to render the GameScreen
     * @param deltaTime the time since the last render
     */
    @Override
    public void render(float deltaTime) {
        // Clear the background of the screen with white
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        /** Rendering the Map **/
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(0, 0, 0, 1);
        shapeRenderer.rect(0, 0, unity * map.size.x, unity * map.size.y); // painting the map in black

        // Rendering blocks
        for (int i = 0 ; i < this.map.size.x ; i++) {
            for (int j = 0 ; j < this.map.size.y ; j++) {
                Block b = this.map.blocks.get(i).get(j);
                if (b.color != ProtoColor.EMPTY) {
                    shapeRenderer.setColor(getColor(b.color));
                    shapeRenderer.rect(i * unity, j * unity, unity, unity);
                }
            }
        }

        // Rendering the doors
        shapeRenderer.setColor(Color.MAROON);
        for (Door d : this.map.doors) {
            shapeRenderer.rect(d.bounds.x * unity, d.bounds.y * unity, d.bounds.width * unity, d.bounds.height * unity);
        }

        // Rendering the character
        int radius = unity/2;

        shapeRenderer.setColor(getColor(map.character.color.next()));
        shapeRenderer.circle(0 + radius, (this.map.size.y - 1) * unity + radius, radius);

        if (lerpChangingColor) { // linear interpolation of the color
            shapeRenderer.setColor(getColor(this.map.character.color).lerp(getColor(map.character.color.next()), lerpChangingTime));
            lerpChangingTime += deltaTime * lerpChangingDelay;
        } else {
            shapeRenderer.setColor(getColor(this.map.character.color));
        }

        int posX = (int) (this.map.character.bounds.x + radius);
        int posY = (int) (this.map.character.bounds.y + radius);
        shapeRenderer.circle(posX, posY, radius);

        // Render the color block on the upper side
        float gap = (changingColorTime * 360) / changingColorDelay;
        shapeRenderer.arc(0 + radius, (this.map.size.y - 1) * unity + radius, radius, 90, 360 - gap);
        changingColorTime += deltaTime;

        shapeRenderer.rect((this.map.size.x - 1) * unity, (this.map.size.y - 1) * unity, unity, unity); // the color block on the upper side
        shapeRenderer.end();

        // Rendering the outline of the character in white
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(1, 1, 1, 1);
        shapeRenderer.circle(posX, posY, radius);
        shapeRenderer.end();

        // Updating the datas of the map
        map.update(deltaTime);
    }

    /**
     * Method to get the Color from the ProtoColor enum
     * @param color the ProtoColor
     * @return the Color corresponding
     */
    public Color getColor(ProtoColor color) {
        switch (color) {
            case RED:
                return new Color(1, 0, 0, 1);
            case GREEN:
                return new Color(0, 1, 0, 1);
            case BLUE:
                return new Color(0, 0, 1, 1);
            case NEUTRAL:
                return new Color(1, 1, 1, 1);
        }
        return new Color(0, 0, 0, 0);
    }

    @Override
    public void resize(int i, int i1) { }

    @Override
    public void pause() { }

    @Override
    public void resume() { }

    @Override
    public void hide() { }

    @Override
    public void dispose() { }
}

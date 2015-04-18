package com.color.game.actors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.color.game.enums.PlatformColor;
import com.color.game.utils.Constants;

import java.util.ArrayList;

public class GaugeColor extends Actor {

    private Rectangle bounds;
    private ShapeRenderer shapeRenderer;
    private Color current;
    private Color next;
    private float timePassed;

    private boolean red;
    private boolean yellow;
    private boolean blue;
    private boolean refreshRed;
    private boolean refreshYellow;
    private boolean refreshBlue;

    private Rectangle redBounds;
    private Rectangle yellowBounds;
    private Rectangle blueBounds;

    private float timeRed;
    private float timeYellow;
    private float timeBlue;

    public GaugeColor(Rectangle bounds) {

        this.bounds = bounds;
        setWidth(bounds.width);
        setHeight(bounds.height);

        float width = (bounds.width - 6)/3;

        redBounds = new Rectangle(bounds.x, bounds.y, width,bounds.height);
        yellowBounds = new Rectangle(bounds.x + width + 2, bounds.y, width, bounds.height);
        blueBounds = new Rectangle(bounds.x +width*2 + 2*2, bounds.y, width, bounds.height);

        red=false;
        blue=false;
        yellow = false;

        refreshBlue=false;
        refreshRed=false;
        refreshYellow=false;

        timeBlue=0f;
        timeYellow=0f;
        timeRed=0f;


        this.shapeRenderer = new ShapeRenderer();
    }

    public void initColors(Color current, Color next) {
        this.current = current;
        this.next = next;
        this.timePassed = 0f;
    }

    public void useRed(){
        if(!refreshRed) {
            refreshRed = true;
            red = true;
        }
    }

    public void useBlue(){
        if(!refreshBlue) {
            refreshBlue = true;
            blue = true;
        }
    }

    public void useYellow(){
        if(!refreshYellow) {
            refreshYellow = true;
            yellow = true;
        }
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        if(refreshRed)
            timeRed += delta;
        if(refreshBlue)
            timeBlue += delta;
        if(refreshYellow)
            timeYellow += delta;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        if(!refreshRed){
            shapeRenderer.setColor(Color.RED);
            shapeRenderer.rect(redBounds.x, redBounds.y, redBounds.width, redBounds.height);
        }else{
            float height = this.redBounds.height * timeRed / Constants.CHARACTER_CHANGING_COLOR_DELAY;
            shapeRenderer.setColor(Color.RED);
            shapeRenderer.rect(redBounds.x, redBounds.y, redBounds.width, height);
            if(height >= (redBounds.height/5)*4) {
                red = false;
            }
            if(height >= redBounds.height){
                refreshRed = false;
                timeRed = 0f;
            }
        }
        if(!refreshBlue){
            shapeRenderer.setColor(Color.BLUE);
            shapeRenderer.rect(blueBounds.x, blueBounds.y, blueBounds.width, blueBounds.height);
        }else{
            float height = this.blueBounds.height * timeBlue / Constants.CHARACTER_CHANGING_COLOR_DELAY;
            shapeRenderer.setColor(Color.BLUE);
            shapeRenderer.rect(blueBounds.x, blueBounds.y, blueBounds.width, height);
            if(height >= (blueBounds.height/5)*4) {
                blue = false;
            }
            if(height >= blueBounds.height){
                refreshBlue = false;
                timeBlue = 0f;
            }
        }
        if(!refreshYellow){
            shapeRenderer.setColor(Color.YELLOW);
            shapeRenderer.rect(yellowBounds.x, yellowBounds.y, yellowBounds.width, yellowBounds.height);
        }else{
            float height = this.yellowBounds.height * timeYellow / Constants.CHARACTER_CHANGING_COLOR_DELAY;
            shapeRenderer.setColor(Color.YELLOW);
            shapeRenderer.rect(yellowBounds.x, yellowBounds.y, yellowBounds.width, height);
            if(height >= (yellowBounds.height/5)*4) {
                yellow = false;
            }
            if(height >= yellowBounds.height){
                refreshYellow = false;
                timeYellow = 0f;
            }
        }

        shapeRenderer.end();

        // Drawing outline
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.DARK_GRAY);
        shapeRenderer.rect(redBounds.x, redBounds.y, redBounds.width, redBounds.height);
        shapeRenderer.rect(blueBounds.x, blueBounds.y, blueBounds.width, blueBounds.height);
        shapeRenderer.rect(yellowBounds.x, yellowBounds.y, yellowBounds.width, yellowBounds.height);
        shapeRenderer.line(redBounds.x, redBounds.y + (redBounds.height / 5) * 4, redBounds.x + redBounds.width, redBounds.y + (redBounds.height / 5) * 4);
        shapeRenderer.line(blueBounds.x, blueBounds.y + (blueBounds.height / 5) * 4, blueBounds.x + blueBounds.width, blueBounds.y + (blueBounds.height / 5) * 4);
        shapeRenderer.line(yellowBounds.x, yellowBounds.y+(yellowBounds.height/5)*4,yellowBounds.x+yellowBounds.width, yellowBounds.y+(yellowBounds.height/5)*4);
        shapeRenderer.end();
        batch.begin();
    }

    public ArrayList<PlatformColor> getActivatedColors(){
        ArrayList<PlatformColor> activated = new ArrayList<PlatformColor>();
        if (red) activated.add(PlatformColor.RED);
        if(yellow) activated.add(PlatformColor.YELLOW);
        if(blue) activated.add(PlatformColor.BLUE);

        return activated;
    }
}

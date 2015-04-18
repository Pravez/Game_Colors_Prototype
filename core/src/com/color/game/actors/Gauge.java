package com.color.game.actors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.color.game.utils.Constants;

public class Gauge extends Actor {

    private boolean activated;
    private boolean refresh;

    private Rectangle bounds;
    private Color color;

    private float time;

    private ShapeRenderer shapeRenderer;

    public Gauge(Rectangle bounds, Color color) {
        this.bounds = bounds;
        this.color = color;

        this.shapeRenderer = new ShapeRenderer();

        restart();
    }

    public boolean isActivated() {
        return this.activated;
    }

    public void use() {
        if (!this.refresh) {
            this.refresh = true;
            this.activated = true;
        }
    }

    public void restart() {
        this.time = 0f;
        this.refresh = false;
        this.activated = false;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        // Drawing inside
        this.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        this.shapeRenderer.setColor(this.color);
        float height = this.bounds.height;

        if (this.refresh) {
            height = this.bounds.height * time / Constants.CHARACTER_CHANGING_COLOR_DELAY;

            if (height >= (bounds.height / 5) * 4) {
                activated = false;
            }
            if (height >= bounds.height){
                refresh = false;
                time = 0f;
            }
        }
        this.shapeRenderer.rect(bounds.x, bounds.y, bounds.width, height);
        this.shapeRenderer.end();

        // Drawing outline
        this.shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        this.shapeRenderer.setColor(Color.DARK_GRAY);
        this.shapeRenderer.rect(bounds.x, bounds.y, bounds.width, bounds.height);
        this.shapeRenderer.line(bounds.x, bounds.y + (bounds.height / 5) * 4, bounds.x + bounds.width, bounds.y + (bounds.height / 5) * 4);
        this.shapeRenderer.end();
    }

    @Override
    public void act(float delta) {
        if (refresh) {
            this.time += delta;
        }
    }
}

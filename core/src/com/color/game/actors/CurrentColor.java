package com.color.game.actors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.color.game.utils.Constants;

public class CurrentColor extends Actor {

    private Rectangle bounds;
    private ShapeRenderer shapeRenderer;
    private Color current;
    private Color next;
    private float timePassed;

    public CurrentColor(Rectangle bounds) {
        this.bounds = bounds;
        setWidth(bounds.width);
        setHeight(bounds.height);
        this.shapeRenderer = new ShapeRenderer();
    }

    public void initColors(Color current, Color next) {
        this.current = current;
        this.next = next;
        this.timePassed = 0f;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        this.timePassed += delta;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.end();
        // Drawing colours content
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(this.current);
        float width = this.bounds.width * timePassed / Constants.CHARACTER_CHANGING_COLOR_DELAY;
        shapeRenderer.rect(this.bounds.x, this.bounds.y, this.bounds.width - width, this.bounds.height);
        shapeRenderer.setColor(this.next);
        shapeRenderer.rect(this.bounds.x + this.bounds.width - width, this.bounds.y, width, this.bounds.height);
        shapeRenderer.end();
        // Drawing outline
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.DARK_GRAY);
        shapeRenderer.rect(this.bounds.x, this.bounds.y, this.bounds.width, this.bounds.height);
        shapeRenderer.end();
        batch.begin();
    }
}

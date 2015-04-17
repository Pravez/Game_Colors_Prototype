package com.color.game.actors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class CurrentColor extends Actor {

    private Character character;
    private Rectangle bounds;
    private ShapeRenderer shapeRenderer;
    private Color current;
    private Color next;
    private float timePassed = 0f;

    public CurrentColor(Character character, Rectangle bounds) {
        this.character = character;
        this.bounds = bounds;
        setWidth(bounds.width);
        setHeight(bounds.height);
        this.current = character.getPlatformColor().getColor();
        this.next = character.getPlatformColor().next().getColor();
        this.shapeRenderer = new ShapeRenderer();
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        // Here, changing the color
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.end();
        // Drawing colours content
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(this.current);
        shapeRenderer.rect(this.bounds.x, this.bounds.y, this.bounds.width, this.bounds.height);
        shapeRenderer.end();
        // Drawing outline
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.DARK_GRAY);
        shapeRenderer.rect(this.bounds.x, this.bounds.y, this.bounds.width, this.bounds.height);
        shapeRenderer.end();
        batch.begin();
    }
}

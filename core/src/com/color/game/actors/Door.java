package com.color.game.actors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.color.game.box2d.DoorUserData;
import com.color.game.box2d.UserData;
import com.color.game.stages.GameStage;

public class Door extends GameActor {

    private Rectangle bounds;
    private ShapeRenderer shapeRenderer;
    private Color color;

    public Door(Body body, Rectangle bounds) {
        super(body);
        this.bounds = bounds;
        setWidth(bounds.width);
        setHeight(bounds.height);
        this.shapeRenderer = new ShapeRenderer();
        this.color = Color.MAROON;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.end();
        // Drawing colours content
        shapeRenderer.setProjectionMatrix(GameStage.camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(this.color);
        shapeRenderer.rect(this.bounds.x, this.bounds.y, this.bounds.width, this.bounds.height);
        shapeRenderer.end();
        // Drawing outline
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.DARK_GRAY);
        shapeRenderer.rect(this.bounds.x, this.bounds.y, this.bounds.width, this.bounds.height);
        shapeRenderer.end();
        batch.begin();
    }

    @Override
    public UserData getUserData() {
        System.out.println("GetDoorUserData");
        return (DoorUserData)userData;
    }
}

package com.color.game.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.physics.box2d.Body;
import com.color.game.box2d.PlatformUserData;
import com.color.game.enums.PlatformColor;
import com.color.game.stages.GameStage;
import com.color.game.utils.Constants;

public class ColorPlatform extends GameActor {

    private Texture texture;
    private ShapeRenderer shapeRenderer;
    private PlatformColor color;

    public ColorPlatform(Body body, PlatformColor color) {
        super(body);
        //this.texture = new Texture(Gdx.files.internal("block.png"));
        this.shapeRenderer = new ShapeRenderer();
        this.color = color;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.end();
        shapeRenderer.setProjectionMatrix(GameStage.camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(this.color.getColor());
        shapeRenderer.rect(super.screenRectangle.x, super.screenRectangle.y, super.screenRectangle.width, super.screenRectangle.height);
        shapeRenderer.end();
        batch.begin();
    }

    @Override
    public PlatformUserData getUserData() {
        return (PlatformUserData) userData;
    }
}

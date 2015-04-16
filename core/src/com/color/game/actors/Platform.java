package com.color.game.actors;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.physics.box2d.Body;
import com.color.game.box2d.PlatformUserData;
import com.color.game.stages.GameStage;
import com.color.game.utils.Constants;

public class Platform extends GameActor{

    private Texture texture;

    public Platform(Body body) {
        super(body);
        texture = new Texture(Gdx.files.internal("block.png"));
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.setProjectionMatrix(GameStage.camera.combined);
        for (int i = 0 ; i < userData.getWidth() ; i ++) {
            for (int j = 0 ; j < userData.getHeight() ; j++) {
                batch.draw(texture, super.screenRectangle.x + i * Constants.WORLD_TO_SCREEN, super.screenRectangle.y + j * Constants.WORLD_TO_SCREEN, Constants.WORLD_TO_SCREEN, Constants.WORLD_TO_SCREEN);
            }
        }
        //batch.draw(texture, super.screenRectangle.x, super.screenRectangle.y, super.screenRectangle.width, super.screenRectangle.height);
    }

    @Override
    public PlatformUserData getUserData() {
        return (PlatformUserData) userData;
    }
}

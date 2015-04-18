package com.color.game.stages;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.color.game.actors.*;
import com.color.game.enums.PlatformColor;
import com.color.game.utils.WorldUtils;

public class GameStage extends BaseStage {

    public GameStage() {
        super();
    }

    @Override
    public boolean keyUp(int keyCode) {
        character.keyUp(keyCode);
        return super.keyUp(keyCode);
    }

    @Override
    public boolean keyDown(int keyCode) {
        character.keyDown(keyCode);
        return super.keyDown(keyCode);
    }

    @Override
    protected void drawStage() {

    }

    @Override
    protected void actStage() {

    }

    @Override
    public void init() {
        initializeScene(new Vector2(3, 2));
    }

    @Override
    public void end() {

    }

    @Override
    public void createDoors() {
        this.doors.add(new Door(WorldUtils.createDoor(map, 74, 42, 2, 4), new Rectangle(74, 42, 2, 4)));

        for (Door d : this.doors) {
            this.addActor(d);
        }
    }

    @Override
    public void createColoredPlatforms() {
        colorPlatforms.add(new ColorPlatform(WorldUtils.createPlatform(map, 30 + 5, 8, 10, 2), PlatformColor.RED));
        colorPlatforms.add(new ColorPlatform(WorldUtils.createPlatform(map, 55, 14, 10, 2), PlatformColor.RED));
        colorPlatforms.add(new ColorPlatform(WorldUtils.createPlatform(map, 35, 22, 10, 2), PlatformColor.RED));
        colorPlatforms.add(new ColorPlatform(WorldUtils.createPlatform(map, 15, 25, 10, 2), PlatformColor.RED));

        colorPlatforms.add(new ColorPlatform(WorldUtils.createPlatform(map, 15, 15, 10, 2), PlatformColor.BLUE));
        colorPlatforms.add(new ColorPlatform(WorldUtils.createPlatform(map, 35, 18, 10, 2), PlatformColor.BLUE));
        colorPlatforms.add(new ColorPlatform(WorldUtils.createPlatform(map, 60, 23, 10, 2), PlatformColor.BLUE));

        colorPlatforms.add(new ColorPlatform(WorldUtils.createPlatform(map, 82, 22, 10, 2), PlatformColor.YELLOW));
        colorPlatforms.add(new ColorPlatform(WorldUtils.createPlatform(map, 95, 30, 10, 2), PlatformColor.YELLOW));

        for (ColorPlatform c : colorPlatforms) {
            this.addActor(c);
        }
    }

    @Override
    public void createPlatforms(){
        //Ground
        platforms.add(new Platform(WorldUtils.createPlatform(map, 0, 0, 30, 2)));
        platforms.add(new Platform(WorldUtils.createPlatform(map, 50, 0, 30, 2)));
        platforms.add(new Platform(WorldUtils.createPlatform(map, 100, 0, 32, 2)));

        //Walls
        platforms.add(new Platform(WorldUtils.createPlatform(map, 0, 0, 1, 50)));
        platforms.add(new Platform(WorldUtils.createPlatform(map, 130, 6, 1, 50)));

        //Platforms
        platforms.add(new Platform(WorldUtils.createPlatform(map, 60, 40, 30, 2)));

        for(Platform p : platforms) {
            this.addActor(p);
        }
    }

    @Override
    public void createPikes(){
        pikes.add(new Pike(WorldUtils.createPike(map, 120,2,1,1)));

        for(Pike p : pikes){
            this.addActor(p);
        }
    }
}

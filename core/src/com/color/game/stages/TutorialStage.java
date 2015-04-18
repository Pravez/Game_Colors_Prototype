package com.color.game.stages;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.color.game.actors.*;
import com.color.game.enums.PlatformColor;
import com.color.game.screens.GameScreen;
import com.color.game.utils.WorldUtils;

public class TutorialStage extends IStage {

    private boolean isFinished = false;

    public TutorialStage(){
        super();
    }

    @Override
    public void init() {
        initializeScene(new Vector2(3, 2));
    }

    @Override
    public void end() {
        this.isFinished = true;
        super.delete();
        GameScreen.initGameStage();
    }

    public boolean isFinished() { return this.isFinished; }

    @Override
    public void createDoors() {
        this.doors.add(new Door(WorldUtils.createDoor(map, 182, 2, 2, 6), new Rectangle(180, 2, 2, 6)));

        for (Door d : this.doors) {
            this.addActor(d);
        }
    }

    @Override
    public void createColoredPlatforms() {
        colorPlatforms.add(new ColorPlatform(WorldUtils.createPlatform(map, 35, 8, 10, 2), PlatformColor.RED));
        colorPlatforms.add(new ColorPlatform(WorldUtils.createPlatform(map, 85, 8, 10, 2), PlatformColor.BLUE));
        colorPlatforms.add(new ColorPlatform(WorldUtils.createPlatform(map, 135, 8, 10, 2), PlatformColor.YELLOW));

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
        platforms.add(new Platform(WorldUtils.createPlatform(map, 150, 0, 32, 2)));

        //Walls
        platforms.add(new Platform(WorldUtils.createPlatform(map, 0, 0, 1, 50)));
        platforms.add(new Platform(WorldUtils.createPlatform(map, 180, 8, 1, 50)));

        for(Platform p : platforms) {
            this.addActor(p);
        }
    }
}

package com.color.game.utils;


import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.color.game.box2d.CharacterUserData;
import com.color.game.box2d.PlatformUserData;

public class WorldUtils {


    public static World createWorld(){
        return new World(Constants.WORLD_GRAVITY, true);
    }

    public static Body createStaticElement(World world, float x, float y, float width, float height){
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(new Vector2(x, y));
        Body body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width, height);
        body.createFixture(shape, Constants.STATIC_ELEMENTS_DENSITY);
        body.setUserData(new PlatformUserData());
        shape.dispose();

        return body;
    }

    public static Body createDynamicElement(World world, float x, float y, float width, float height){
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;

        //To keep from rotations
        bodyDef.fixedRotation = true;

        bodyDef.position.set(new Vector2(x, y));
        Body body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width, height);
        body.setGravityScale(Constants.CHARACTER_GRAVITY_SCALE);
        body.createFixture(shape, Constants.DYNAMIC_ELEMENTS_DENSITY);
        body.resetMassData();
        body.setUserData(new CharacterUserData());
        shape.dispose();

        return body;
    }

    public static Body createCharacter(World world, float x, float y){
        return createDynamicElement(world, x, y, Constants.CHARACTER_WIDTH, Constants.CHARACTER_HEIGHT);
    }
}

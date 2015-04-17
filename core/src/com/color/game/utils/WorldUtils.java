package com.color.game.utils;


import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.color.game.box2d.CharacterUserData;
import com.color.game.box2d.DoorUserData;
import com.color.game.box2d.PlatformUserData;

/**
 * Utility class, useful to create automatically bodies and worlds for platforms and characters.
 */
public class WorldUtils {


    /**
     * Method to create a new {@link com.badlogic.gdx.physics.box2d.World}
     * @return The new world created
     */
    public static World createWorld(){
        return new World(Constants.WORLD_GRAVITY, true);
    }

    /**
     * Method to create the body of a static element
     * @param world The world in which the element is
     * @param x his position in x
     * @param y his position in y
     * @param width his width
     * @param height his height
     * @return the body created
     */
    public static Body createPlatform(World world, float x, float y, float width, float height){

        float data[] = convertDatas(x, y, width, height);

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(new Vector2(data[0], data[1]));
        Body body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(data[2], data[3]);
        body.createFixture(shape, Constants.STATIC_ELEMENTS_DENSITY);
        body.setUserData(new PlatformUserData(data[2], data[3]));
        shape.dispose();

        return body;
    }

    /**
     * Method to create a dynamic element
     * @param world The world in which the element will be
     * @param x his starting x position
     * @param y his starting y position
     * @param width his width
     * @param height his height
     * @return the body created
     */
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
        body.setUserData(new CharacterUserData(width, height));
        shape.dispose();

        return body;
    }

    public static Body createDoor(World world, float x, float y, float width, float height){

        float data[] = convertDatas(x, y, width, height);

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(new Vector2(data[0], data[1]));
        Body body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(data[2], data[3]);
        body.createFixture(shape, Constants.STATIC_ELEMENTS_DENSITY);
        body.setUserData(new DoorUserData(data[2], data[3]));
        shape.dispose();

        return body;
    }

    /**
     * Method to create a character, a dynamic body
     * @param world His world
     * @param x His starting x position
     * @param y His starting y position
     * @return the body created
     */
    public static Body createCharacter(World world, float x, float y){
        return createDynamicElement(world, x, y, Constants.CHARACTER_WIDTH, Constants.CHARACTER_HEIGHT);
    }

    public static float[] convertDatas(float x, float y, float width, float height){
        float[] datas = new float[4];

        if(width<=1) width=2;
        if(height <= 1) height=2;

        datas[0] = x + width/2;
        datas[1] = y + height/2;
        datas[2] = width/2;
        datas[3] = height/2;

        return datas;
    }
}

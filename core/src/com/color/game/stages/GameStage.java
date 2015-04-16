package com.color.game.stages;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.color.game.actors.Character;
import com.color.game.actors.Platform;
import com.color.game.utils.BodyUtils;
import com.color.game.utils.Constants;
import com.color.game.utils.WorldUtils;

import java.util.ArrayList;

public class GameStage extends Stage implements ContactListener{

    public World world;
    public ArrayList<Platform> platforms;
    public com.color.game.actors.Character character;

    private final float TIME_STEP = 1/300f;
    private float accumulator = 0f;

    private OrthographicCamera camera;
    private Box2DDebugRenderer renderer;

    public GameStage(){
        initializeScene();

        Gdx.input.setInputProcessor(this);
    }

    private void initializeScene(){

        createWorld();
        setupCamera();
        setKeyboardFocus(character);
        renderer = new Box2DDebugRenderer();

    }

    private void createWorld(){
        world = WorldUtils.createWorld();
        world.setContactListener(this);
        createPlatforms();
        character = new Character(WorldUtils.createCharacter(world, 2f, 2f));
        this.addActor(character);
    }

    private void createPlatforms(){
        platforms = new ArrayList<Platform>();
        platforms.add(new Platform(WorldUtils.createStaticElement(world, 25, 0, 50, 2)));
        platforms.add(new Platform(WorldUtils.createStaticElement(world, 25, 5, 2, 5)));
        platforms.add(new Platform(WorldUtils.createStaticElement(world, 35,15,2,15)));
        for(Platform p : platforms) {
            this.addActor(p);
        }
    }

    private void setupCamera(){
        camera = new OrthographicCamera(Constants.VIEWPORT_WIDTH, Constants.VIEWPORT_HEIGHT);
        camera.position.set(0, 0, 0f);
        camera.update();
    }

    @Override
    public void act(float delta){
        super.act(delta);

        accumulator+=delta;

        while(accumulator >= delta){
            world.step(TIME_STEP, 6, 2);
            accumulator -= TIME_STEP;
        }

    }

    @Override
    public void draw(){
        super.draw();
        renderer.render(world, camera.combined);
    }

    @Override
    public void beginContact(Contact contact) {

        Body a = contact.getFixtureA().getBody();
        Body b = contact.getFixtureB().getBody();

        System.out.println("Friction : " + contact.getFriction());
        System.out.println("TangentSPeed : " + contact.getTangentSpeed());

        if ((BodyUtils.bodyIsCharacter(a) && BodyUtils.bodyIsPlatform(b)) ||
                (BodyUtils.bodyIsPlatform(a) && BodyUtils.bodyIsCharacter(b))) {

            character.landed();
            System.out.println("landed");
        }

    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold manifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse contactImpulse) {

    }
}

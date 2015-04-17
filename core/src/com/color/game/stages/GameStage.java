package com.color.game.stages;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.color.game.actors.Character;
import com.color.game.actors.ColorPlatform;
import com.color.game.actors.Platform;
import com.color.game.enums.PlatformColor;
import com.color.game.screens.DeathScreen;
import com.color.game.utils.BodyUtils;
import com.color.game.utils.WorldUtils;

import java.util.ArrayList;

public class GameStage extends Stage implements ContactListener{

    public World world;
    public ArrayList<Platform> platforms;
    public ArrayList<ColorPlatform> colorPlatforms;
    public Character character;

    private final float TIME_STEP = 1/300f;
    private float accumulator = 0f;

    public static OrthographicCamera camera;
    public Box2DDebugRenderer renderer;

    private Texture background;
    private SpriteBatch batch;

    public GameStage(){
        initializeScene();

        background = new Texture(Gdx.files.internal("background.png"));
        batch = new SpriteBatch();

        renderer = new Box2DDebugRenderer();

        Gdx.input.setInputProcessor(this);
    }

    private void initializeScene(){
        createWorld();
        setupCamera();
    }

    private void createWorld(){
        world = WorldUtils.createWorld();
        world.setContactListener(this);
        createPlatforms();
        createColoredPlatforms();
        createCharacter();
    }

    private void createCharacter(){
        character = new Character(WorldUtils.createCharacter(world, 3, 2));
        setKeyboardFocus(character);
        this.addActor(character);
    }

    private void createColoredPlatforms() {
        colorPlatforms = new ArrayList<ColorPlatform>();

        colorPlatforms.add(new ColorPlatform(WorldUtils.createStaticElement(world, 10, 5, 12, 2), PlatformColor.BLUE));
        colorPlatforms.add(new ColorPlatform(WorldUtils.createStaticElement(world, 60, 5, 14, 2), PlatformColor.YELLOW));
        colorPlatforms.add(new ColorPlatform(WorldUtils.createStaticElement(world, 110, 5, 10, 2), PlatformColor.RED));

        for (ColorPlatform c : colorPlatforms) {
            this.addActor(c);
        }
    }

    private void createPlatforms(){
        platforms = new ArrayList<Platform>();
        //Ground
        platforms.add(new Platform(WorldUtils.createStaticElement(world, 0, 0, 30, 2)));
        platforms.add(new Platform(WorldUtils.createStaticElement(world, 100,0,30,2)));
        platforms.add(new Platform(WorldUtils.createStaticElement(world, 50,0,30,2)));

        //Walls
        platforms.add(new Platform(WorldUtils.createStaticElement(world, 0, 0, 1, 50)));
        platforms.add(new Platform(WorldUtils.createStaticElement(world, 130,0,1,50)));

        //Platforms
        platforms.add(new Platform(WorldUtils.createStaticElement(world, 30+5, 8, 10, 1)));
        platforms.add(new Platform(WorldUtils.createStaticElement(world, 80+5, 8, 10, 1)));

        for(Platform p : platforms) {
            this.addActor(p);
        }
    }

    private void setupCamera(){
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        camera = new OrthographicCamera(60, 60 * (h / w));
        camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
        camera.update();
    }

    @Override
    public void act(float delta){
        super.act(delta);
        batch.begin();
        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();

        accumulator+=delta;

        while(accumulator >= delta){
            world.step(TIME_STEP, 6, 2);
            accumulator -= TIME_STEP;
            if(character.isDead()){
                //world.destroyBody(character.getBody());
                ((Game) Gdx.app.getApplicationListener()).setScreen(new DeathScreen());
            }
        }

    }

    @Override
    public void draw(){
        super.draw();

        this.camera.position.x = this.character.getPosition().x;
        this.camera.update();
        renderer.render(world, camera.combined);
    }


    @Override
    public void beginContact(Contact contact) {

        Body a = contact.getFixtureA().getBody();
        Body b = contact.getFixtureB().getBody();


        /*Body temp = null;
        boolean jumpedOnWall = false;*/

        if ((BodyUtils.bodyIsCharacter(a) && BodyUtils.bodyIsPlatform(b)) ||
                (BodyUtils.bodyIsPlatform(a) && BodyUtils.bodyIsCharacter(b))) {

            /*if(BodyUtils.bodyIsPlatform(a))
                temp = a;
            else if(BodyUtils.bodyIsPlatform(b))
                temp = b;*/


            //if(temp.getPosition().x+temp.get)


            /*if(temp != null) {
                for (Platform p : walls) {
                    if(temp.equals(p.getBody())){
                        if((p.getBody().getPosition().y)<=character.getY()){
                            character.landed();
                        }else {
                            jumpedOnWall = true;
                        }
                    }
                }
            }*/

            /*if(jumpedOnWall){
                character.onWall(true);
            }else{*/
                character.landed();
            //}
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

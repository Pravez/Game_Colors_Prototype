package com.color.game.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.color.game.ColorGame;
import com.color.game.Map;
import com.color.game.actors.*;
import com.color.game.actors.Character;
import com.color.game.utils.BodyUtils;
import com.color.game.utils.WorldUtils;

import java.util.ArrayList;

public abstract class IStage extends Stage implements ContactListener {

    public Map map;
    public ArrayList<Platform> platforms;
    public ArrayList<ColorPlatform> colorPlatforms;
    public ArrayList<Door> doors;
    public static com.color.game.actors.Character character;

    public static GaugeColor gaugeColor;

    private final float TIME_STEP = 1/300f;
    private float accumulator = 0f;

    public static OrthographicCamera camera;
    public Box2DDebugRenderer renderer;

    private Texture background;
    private SpriteBatch batch;

    private static Sound jumpSound;
    private static Sound landSound;

    protected Vector2 characterPos;

    public IStage() {
        background = new Texture(Gdx.files.internal("background.png"));
        batch = new SpriteBatch();

        renderer = new Box2DDebugRenderer();
        // Les sons ne resteront pas là indéfiniment et seront dans une classe spécifique plus tard
        jumpSound = Gdx.audio.newSound(Gdx.files.internal("jump.mp3"));
        landSound = Gdx.audio.newSound(Gdx.files.internal("landing.wav"));

        this.platforms = new ArrayList<Platform>();
        this.colorPlatforms = new ArrayList<ColorPlatform>();
        this.doors = new ArrayList<Door>();
    }

    public abstract void init();

    public abstract void end();

    public static void playJumpSound() {
        jumpSound.play(0.1f);
    }

    public static void playLandSound() {
        landSound.play(0.1f);
    }

    public void delete() {
        this.getActors().removeValue(character, true);
    }

    public void respawn() {
        map.world.destroyBody(character.getBody());
        this.getActors().removeValue(character, true);
        createCharacter();
        this.addActor(IStage.character);
    }

    protected void initializeScene(Vector2 characterPos){
        this.characterPos = characterPos;
        setupColorInfo();
        createWorld();
        setupCamera();
    }

    private void createWorld(){
        map = WorldUtils.createMap();
        map.world.setContactListener(this);
        createCharacter();
        createPlatforms();
        createColoredPlatforms();
        createDoors();
        this.addActor(GameStage.character);
        this.addActor(gaugeColor);
    }

    private void createCharacter() {
        character = new Character(WorldUtils.createCharacter(map, this.characterPos.x, this.characterPos.y));
        setKeyboardFocus(character);
    }

    protected abstract void createDoors();/* {
        this.doors = new ArrayList<Door>();

        this.doors.add(new Door(WorldUtils.createDoor(map, 74, 42, 2, 4), new Rectangle(74, 42, 2, 4)));

        for (Door d : this.doors) {
            this.addActor(d);
        }
    }*/

    protected abstract void createColoredPlatforms();/* {
        colorPlatforms = new ArrayList<ColorPlatform>();

        colorPlatforms.add(new ColorPlatform(WorldUtils.createPlatform(map, 35, 8, 10, 2), PlatformColor.RED));
        colorPlatforms.add(new ColorPlatform(WorldUtils.createPlatform(map, 55, 8, 10, 2), PlatformColor.BLUE));
        colorPlatforms.add(new ColorPlatform(WorldUtils.createPlatform(map, 105, 8, 10, 2), PlatformColor.YELLOW));

        for (ColorPlatform c : colorPlatforms) {
            this.addActor(c);
        }
    }*/

    protected abstract void createPlatforms();/*{
        platforms = new ArrayList<Platform>();
        //Ground
        platforms.add(new Platform(WorldUtils.createPlatform(map, 0, 0, 30, 2)));
        platforms.add(new Platform(WorldUtils.createPlatform(map, 50, 0, 30, 2)));
        platforms.add(new Platform(WorldUtils.createPlatform(map, 100, 0, 32, 2)));
        platforms.add(new Platform(WorldUtils.createPlatform(map, 150, 0, 32, 2)));

        //Walls
        platforms.add(new Platform(WorldUtils.createPlatform(map, 0, 0, 1, 50)));
        platforms.add(new Platform(WorldUtils.createPlatform(map, 130, 6, 1, 50)));

        for(Platform p : platforms) {
            this.addActor(p);
        }
    }*/

    private void setupCamera(){
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        camera = new OrthographicCamera(100, 100 * (h / w));
        camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
        camera.update();
    }

    private void setupColorInfo() {
        gaugeColor = new GaugeColor(new Rectangle(20, Gdx.graphics.getHeight() - 65, 75, 50));
    }

    @Override
    public void act(float delta){
        super.act(delta);
        batch.begin();
        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();

        accumulator+=delta;

        while(accumulator >= delta){
            map.world.step(TIME_STEP, 6, 2);
            accumulator -= TIME_STEP;
            if (character.isDead()) {
                ((ColorGame) Gdx.app.getApplicationListener()).setScreen(((ColorGame) Gdx.app.getApplicationListener()).getDeathScreen());
            }
        }
    }

    @Override
    public void draw(){
        super.draw();

        camera.position.x = character.getPosition().x;
        camera.position.y = character.getPosition().y + camera.viewportHeight/4;
        if (camera.position.x < camera.viewportWidth / 2f) {
            camera.position.x = camera.viewportWidth / 2f;
        }
        if (camera.position.y < camera.viewportHeight / 2f) {
            camera.position.y = camera.viewportHeight / 2f;
        }
        if (camera.position.x > map.getWidth() - camera.viewportWidth / 2f) {
            camera.position.x = map.getWidth() - camera.viewportWidth / 2f;
        }
        if (camera.position.y > map.getHeight() - camera.viewportHeight / 2f) {
            camera.position.y = map.getHeight() - camera.viewportHeight / 2f;
        }
        camera.update();
        renderer.render(map.world, camera.combined);
    }

    @Override
    public void beginContact(Contact contact) {
        Body a = contact.getFixtureA().getBody();
        Body b = contact.getFixtureB().getBody();

        if ((BodyUtils.bodyIsCharacter(a) && BodyUtils.bodyIsDoor(b)) || (BodyUtils.bodyIsDoor(a) && BodyUtils.bodyIsCharacter(b))) {
            this.end();
            ((ColorGame) Gdx.app.getApplicationListener()).setScreen(((ColorGame) Gdx.app.getApplicationListener()).getWinScreen());
        }

        if ((BodyUtils.bodyIsCharacter(a) && BodyUtils.bodyIsPlatform(b)) || (BodyUtils.bodyIsPlatform(a) && BodyUtils.bodyIsCharacter(b))) {
            playLandSound();
            character.landed();
        }
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}

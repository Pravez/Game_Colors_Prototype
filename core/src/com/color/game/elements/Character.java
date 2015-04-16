package com.color.game.elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.color.game.elements.protoEnums.ProtoColor;
import com.color.game.level.Map;
import com.color.game.screens.GameScreen;

import java.util.ArrayList;


public class Character {


    static final float characHeight = 20.f;
    static final float characWidth = 20.f;
    static final float BASE_ACCELERATION = 5000f;
    static final float BASE_JUMP_VELOCITY = 400f;
    static final float JUMP_VELOCITY_ON_WALL = 5000f;
    static final float GRAVITY = 3000f;
    static final float GRAVITY_ON_WALL = 500.0f;
    static final float BASE_MAX_VEL = 200f;
    static final float ENHANCED_MAX_VEL = 350f;
    static final float DAMP = 0.83f;
    static final int RIGHT = 1;
    static final int LEFT = -1;


    public enum ProtoState {
        IDLE,
        JUMPING,
        MOVING,
        DEAD,
    }

    public Map map;

    public Vector2 position;
    public Vector2 acceleration;
    public Vector2 velocity;
    public ProtoState state;
    public int direction;
    public Rectangle bounds;
    public ProtoColor color;
    public float statetime;
    public boolean grounded;
    public float current_jump_velocity;
    public boolean onWall;
    public float currentMaxVelocity;

    public boolean jumpPressed;

    public Rectangle[] nearbyRects;

    public Character(Map map, float x, float y){

        this.map = map;

        this.position = new Vector2(x, y);
        this.state = ProtoState.IDLE;
        this.bounds = new Rectangle();
        this.bounds.height = characHeight;
        this.bounds.width = characWidth;
        this.bounds.x = position.x*GameScreen.unity + 0.5f;
        this.bounds.y = position.y*GameScreen.unity+10f;
        this.direction = RIGHT;
        this.color = ProtoColor.RED;
        this.grounded = true;
        this.onWall = false;
        this.current_jump_velocity = 0;
        currentMaxVelocity = 0;

        this.acceleration = new Vector2(0,0);
        this.velocity = new Vector2(0,0);
        statetime = 0;

        this.nearbyRects = new Rectangle[]{new Rectangle(), new Rectangle(), new Rectangle(), new Rectangle()};


        //Tests part
        jumpPressed = false;

    }

    public void update(float deltatime){

        performKeys();

        //Then calculate the velocity depending on the acceleration
        if(onWall)
            acceleration.y = -GRAVITY_ON_WALL;
        else
            acceleration.y = -GRAVITY;

        acceleration.scl(deltatime);

        velocity.add(acceleration.x, acceleration.y);
        if(velocity.x > currentMaxVelocity) velocity.x = currentMaxVelocity;
        if(velocity.x < -currentMaxVelocity) velocity.x = -currentMaxVelocity;

        //Used by the "character on wheels" effect
        if(acceleration.x == 0) velocity.x *= DAMP;

        //Here is the pure movement
        velocity.scl(deltatime);

        //Catch the exception of being out of the matrix
        try {
            move();
        }catch(IndexOutOfBoundsException ioobe){
            ioobe.printStackTrace();
            state = ProtoState.DEAD;
        }

        //Here is the "character on wheels" effect
        velocity.scl(1.0f / deltatime);


        statetime += deltatime;

    }

    public void performKeys(){

        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            direction = LEFT;
            if(state != ProtoState.JUMPING)
                state = ProtoState.MOVING;
            acceleration.x = BASE_ACCELERATION *direction;

        }else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            direction = RIGHT;
            if(state != ProtoState.JUMPING)
                state = ProtoState.MOVING;
            acceleration.x = BASE_ACCELERATION *direction;

        }else{
            if(state != ProtoState.JUMPING)
                state = ProtoState.IDLE;
            acceleration.x = 0;

        }

        //To increase speed
        if(Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) || Gdx.input.isKeyPressed(Input.Keys.SHIFT_RIGHT)){
            currentMaxVelocity = ENHANCED_MAX_VEL;
        }else{
            currentMaxVelocity = BASE_MAX_VEL;
        }


        //Region for the jump
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && state != ProtoState.JUMPING && !jumpPressed){
            if(grounded) {
                state = ProtoState.JUMPING;
                grounded = false;
                current_jump_velocity = BASE_JUMP_VELOCITY;
            }

            if(onWall){
                state = ProtoState.JUMPING;
                current_jump_velocity = BASE_JUMP_VELOCITY;
                velocity.x += JUMP_VELOCITY_ON_WALL;
            }
            jumpPressed = true;
        }

        if(Gdx.input.isKeyPressed(Input.Keys.SPACE) && state == ProtoState.JUMPING && jumpPressed){


            current_jump_velocity *= 0.96f;

            //Value where the character won't jump anymore and just fall
            if(velocity.y <= 150.0f && velocity.y >= 1.0f){
                state = ProtoState.IDLE;
            }

            //We change y velocity depending on the jumping velocity
            velocity.y = current_jump_velocity;

        }else{
            state = ProtoState.IDLE;
        }

        jumpPressed = Gdx.input.isKeyPressed(Input.Keys.SPACE);
        //end region

    }

    public void move() throws IndexOutOfBoundsException {

        boolean leftWall = true;

        //We first move in X coordinates
        this.bounds.x += velocity.x;
        updateNearbyRectangles();
        for (Rectangle r : nearbyRects) {
            //If it hits in X a block
            if (bounds.overlaps(r)) {
                if (velocity.x < 0) {
                    bounds.x = r.x + r.width + 0.01f;
                    leftWall = false;
                }
                if (velocity.x > 0) {
                    bounds.x = r.x - bounds.width - 0.01f;
                    leftWall = false;
                }
                velocity.x = 0;
            }
        }

        onWall = !grounded && !leftWall;

        //Then in Y coordinates
        this.bounds.y += velocity.y;
        updateNearbyRectangles();
        for (Rectangle r : nearbyRects) {
            //If it hits in Y a block
            if (bounds.overlaps(r)) {
                if (velocity.y < 0) {
                    bounds.y = r.y + r.height + 0.01f;
                    grounded = true;
                    if (state != ProtoState.DEAD) state = ProtoState.IDLE;
                }
                if (velocity.y > 0) {
                    grounded = false;
                    bounds.y = r.y - bounds.height - 0.01f;
                }
                velocity.y = 0;
            }
        }

        position.x = (int) ((bounds.x + bounds.width / 2) / GameScreen.unity);
        position.y = (int) ((bounds.y + bounds.height / 2) / GameScreen.unity);

        if (position.y < 0) {
            state = ProtoState.DEAD;
        }

        if (isGameEnded()) {
            //map.game.game.setScreen(new WinScreen(map.game.game));
        }
    }

    private void updateNearbyRectangles() throws IndexOutOfBoundsException{

        Vector2 medium = new Vector2(bounds.x + GameScreen.unity/2, bounds.y+GameScreen.unity/2);

        int r1x = Math.max(0, (int) medium.x);
        int r1y = Math.max(0, (int) Math.floor(medium.y-GameScreen.unity/2));
        int r2x = Math.max(0, (int) Math.min(59*GameScreen.unity, (medium.x + GameScreen.unity / 2)));
        int r2y = Math.max(0, (int) Math.floor(medium.y));
        int r3x = Math.max(0, (int)(medium.x));
        int r3y = Math.max(0, (int) Math.floor(medium.y + GameScreen.unity/2));
        int r4x = Math.max(0, (int)(medium.x-GameScreen.unity/2));
        int r4y = Math.max(0, (int) Math.floor(medium.y));

        ArrayList<ArrayList<Block>> blocks = map.blocks;

        Block b1 = blocks.get(r1x/GameScreen.unity).get(r1y/GameScreen.unity);
        Block b2 = blocks.get(r2x/GameScreen.unity).get(r2y/GameScreen.unity);
        Block b3 = blocks.get(r3x/GameScreen.unity).get(r3y/GameScreen.unity);
        Block b4 = blocks.get(r4x/GameScreen.unity).get(r4y/GameScreen.unity);

        if(b1.isDeadly(this.color) || b2.isDeadly(this.color) || b3.isDeadly(this.color) || b4.isDeadly(this.color)){
            state = ProtoState.DEAD;
        }

        if(b1.color != ProtoColor.EMPTY){
            nearbyRects[0].set(b1.position.x*GameScreen.unity, b1.position.y*GameScreen.unity, GameScreen.unity, GameScreen.unity);
        }else{
            nearbyRects[0].set(-1,-1,0,0);
        }

        if(b2.color != ProtoColor.EMPTY){
            nearbyRects[1].set(b2.position.x*GameScreen.unity, b2.position.y*GameScreen.unity , GameScreen.unity, GameScreen.unity);
        }else{
            nearbyRects[1].set(-1, -1, 0, 0);
        }

        if(b3.color != ProtoColor.EMPTY){
            nearbyRects[2].set(b3.position.x*GameScreen.unity, b3.position.y*GameScreen.unity, GameScreen.unity, GameScreen.unity);
        }else{
            nearbyRects[2].set(-1, -1, 0, 0);
        }

        if(b4.color != ProtoColor.EMPTY){
            nearbyRects[3].set(b4.position.x*GameScreen.unity, b4.position.y*GameScreen.unity , GameScreen.unity, GameScreen.unity);
        }else{
            nearbyRects[3].set(-1, -1, 0, 0);
        }
    }

    public boolean isGameEnded(){

        boolean end = false;
        for(Door d : map.doors){
            Vector2 doorPos = new Vector2(d.bounds.x, d.bounds.y);
            if(this.position.equals(doorPos)){
                end = true;
            }
        }

        return end;
    }
}

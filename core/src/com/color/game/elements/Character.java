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
    static final float ACCELERATION = 5000f;
    static final float BASE_JUMP_VELOCITY = 600f; // how high the character will jump
    static final float GRAVITY = 2000f;
    static final float MAX_VEL = 200f;
    static final float DAMP = 0.80f;
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
    public float statetime;
    public boolean grounded;
    public float current_jump_velocity;
    public boolean onWall;

    public Rectangle[] nearbyRects;

    public Character(Map map, float x, float y){

        this.map = map;

        this.position = new Vector2(x, y);
        System.out.println(position.x + " , " + position.y);
        this.state = ProtoState.IDLE;
        this.bounds = new Rectangle();
        this.bounds.height = characHeight;
        this.bounds.width = characWidth;
        this.bounds.x = position.x*GameScreen.unity + 0.5f;
        this.bounds.y = position.y*GameScreen.unity+10f;
        this.direction = RIGHT;
        this.grounded = true;
        this.onWall = false;
        this.current_jump_velocity = 0;

        this.acceleration = new Vector2(0,0);
        this.velocity = new Vector2(0,0);
        statetime = 0;

        this.nearbyRects = new Rectangle[]{new Rectangle(), new Rectangle(), new Rectangle(), new Rectangle()};

    }

    public void update(float deltatime){

        performKeys();

        //Then calculate the velocity depending on the acceleration
        acceleration.y = -GRAVITY;
        acceleration.scl(deltatime);

        velocity.add(acceleration.x, acceleration.y);
        if(velocity.x > MAX_VEL) velocity.x = MAX_VEL;
        if(velocity.x < -MAX_VEL) velocity.x = -MAX_VEL;

        //Used by the "character on wheels" effect
        if(acceleration.x == 0) velocity.x *= DAMP;



        //Here is the pure movement
        velocity.scl(deltatime);
        move();

        //Here is the "character on wheels" effect
        velocity.scl(1.0f / deltatime);


        statetime += deltatime;

    }

    public void performKeys(){

        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            direction = LEFT;
            if(state != ProtoState.JUMPING)
                state = ProtoState.MOVING;
            acceleration.x = ACCELERATION*direction;

        }else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            direction = RIGHT;
            if(state != ProtoState.JUMPING)
                state = ProtoState.MOVING;
            acceleration.x = ACCELERATION*direction;
            System.out.println(bounds.x);

        }else{
            if(state != ProtoState.JUMPING)
                state = ProtoState.IDLE;
            acceleration.x = 0;

        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) { // Pressed one time : we want to jump
            if(grounded && state != ProtoState.JUMPING) {
                state = ProtoState.JUMPING;
                grounded = false;
                current_jump_velocity = BASE_JUMP_VELOCITY;
            }

            if(onWall && state != ProtoState.JUMPING){
                onWall = false;
                state = ProtoState.JUMPING;
                current_jump_velocity = BASE_JUMP_VELOCITY;
            }

            velocity.y = current_jump_velocity;
        }

        if(Gdx.input.isKeyPressed(Input.Keys.SPACE)){ // Still pressing : still jumping
            //TO-DO
            //Jump to the maximum
            //Modify the jump velocity to fall well
            if(velocity.y >= 1) {
                current_jump_velocity *= 0.96f;
            }
        }

    }

    public void move(){

        //We first move in X coordinates
        this.bounds.x += velocity.x;
        updateNearbyRectangles();
        for(Rectangle r : nearbyRects){
            //If it hits in X a block
            if(bounds.overlaps(r)){
                if(velocity.x < 0){
                    bounds.x = r.x + r.width + 0.01f;
                    onWall = true;
                }
                if(velocity.x > 0){
                    onWall = true;
                    bounds.x = r.x - r.width - 0.01f;
                }
                velocity.x = 0;
            }
        }

        //Then in Y coordinates
        this.bounds.y += velocity.y;
        updateNearbyRectangles();
        for(Rectangle r : nearbyRects){
            //If it hits in Y a block
            if(bounds.overlaps(r)){
                if(velocity.y < 0){
                    bounds.y = r.y + r.height + 0.01f;
                    grounded = true;
                    state = ProtoState.IDLE;
                }
                if (velocity.y > 0) {
                    bounds.y = r.y - r.height - 0.01f;
                }
                velocity.y = 0;
            }
        }

        position.x = bounds.x / GameScreen.unity;
        position.y = bounds.y / GameScreen.unity;

        if(position.y <= 0){
            state = ProtoState.DEAD;
        }
    }

    private void updateNearbyRectangles() {

        int r1x = Math.max(0, (int) bounds.x);
        int r1y = Math.max(0,(int)Math.floor(bounds.y-GameScreen.unity));
        int r2x = Math.max(0,(int)(bounds.x + GameScreen.unity));
        int r2y = Math.max(0,(int)Math.floor(bounds.y));
        int r3x = Math.max(0,(int)(bounds.x));
        int r3y = Math.max(0,(int)Math.floor(bounds.y + GameScreen.unity));
        int r4x = Math.max(0,(int)bounds.x-GameScreen.unity);
        int r4y = Math.max(0,(int)Math.floor(bounds.y));

        ArrayList<ArrayList<Block>> blocks = map.blocks;

        Block b1 = blocks.get(r1x/GameScreen.unity).get(r1y/GameScreen.unity);
        Block b2 = blocks.get(r2x/GameScreen.unity).get(r2y/GameScreen.unity);
        Block b3 = blocks.get(r3x/GameScreen.unity).get(r3y/GameScreen.unity);
        Block b4 = blocks.get(r4x/GameScreen.unity).get(r4y/GameScreen.unity);

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
}

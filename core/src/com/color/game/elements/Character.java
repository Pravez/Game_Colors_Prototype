package com.color.game.elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.color.game.level.Map;


public class Character {


    static final float characHeight = 1.0f;
    static final float characWidth = 0.5f;
    static final float ACCELERATION = 20f;
    static final float JUMP_VELOCITY = 10;
    static final float GRAVITY = 20.0f;
    static final float MAX_VEL = 6f;
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

    public Rectangle[] nearbyRects;

    public Character(Map map, float x, float y){

        this.map = map;

        this.position = new Vector2(x, y);
        this.state = ProtoState.IDLE;
        this.bounds.height = characHeight;
        this.bounds.width = characWidth;
        this.bounds.x = position.x + 0.1f;
        this.bounds.y = position.y;
        this.direction = RIGHT;

        this.acceleration = new Vector2(0,0);
        this.velocity = new Vector2(0,0);

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

        velocity.scl(deltatime);

        move();

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

        }else{
            if(state != ProtoState.JUMPING)
                state = ProtoState.IDLE;
            acceleration.x = 0;

        }

        if(Gdx.input.isKeyPressed(Input.Keys.SPACE)){
            state = ProtoState.JUMPING;

            //We change y velocity depending on the jumping velocity
            velocity.y = JUMP_VELOCITY;
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
                    bounds.x = r.x + r.width + bounds.width/2;
                }
                if(velocity.x > 0){
                    bounds.x = r.x - r.width - bounds.width/2;
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
                    bounds.y = r.y + r.height + bounds.height/2;
                }
                if (velocity.y > 0) {
                    bounds.y = r.y - r.height - bounds.height/2;
                }
                velocity.y = 0;
            }
        }
    }

    private void updateNearbyRectangles() {

    }
}

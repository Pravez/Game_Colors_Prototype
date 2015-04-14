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

    }

    public void update(float deltatime){

    }


    public void performKeys(){

        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){

            if(state != ProtoState.JUMPING)
                state = ProtoState.MOVING;

            direction = LEFT;

        }else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){

            if(state != ProtoState.JUMPING)
                state = ProtoState.MOVING;
            direction = RIGHT;
        }

        if(Gdx.input.isKeyPressed(Input.Keys.SPACE)){
            state = ProtoState.JUMPING;

            //We change y velocity depending on the jumping velocity
            velocity.y = JUMP_VELOCITY;
        }

    }
}

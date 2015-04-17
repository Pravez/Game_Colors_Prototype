package com.color.game.utils;


import com.badlogic.gdx.math.Vector2;

/**
 * Class of constants used by the whole program
 */
public class Constants {


    public static final int APP_WIDTH = 840;
    public static final int APP_HEIGHT = 600;

    public static final int GLOBAL_STARTING_SPRITES_WIDTH = APP_WIDTH/2;
    public static final int GLOBAL_STARTING_SPRITES_HEIGHT = APP_HEIGHT/2;

    public static final float WORLD_TO_SCREEN = 2;

    public static final int VIEWPORT_WIDTH = 100;
    public static final int VIEWPORT_HEIGHT = 100;

    public static final float CHARACTER_WIDTH = 1f;
    public static final float CHARACTER_HEIGHT = 1f;

    public static final Vector2 WORLD_GRAVITY = new Vector2(0, -75);

    public static final float STATIC_ELEMENTS_DENSITY = 0f;
    public static float DYNAMIC_ELEMENTS_DENSITY = 0.4f;

    /**
     * Character constants
     */
    public static final float CHARACTER_GRAVITY_SCALE = 3f;
    public static final Vector2 CHARACTER_JUMPING_LINEAR_IMPULSE = new Vector2(0, 125);
    public static final Vector2 CHARACTER_MOVING_LINEAR_IMPULSE = new Vector2(7,0);
    public static final Vector2 CHARACTER_MAX_VELOCITY_NORMAL = new Vector2(25f, 0);

    public static final float CHARACTER_GRAVITY_SCALE_ON_WALL = 1.5f;
    public static final Vector2 CHARACTER_JUMPING_LINEAR_IMPULSE_BOOSTED = new Vector2(0, 160);
    public static final Vector2 CHARACTER_MOVING_LINEAR_IMPULSE_BOOSTED = new Vector2(10,0);
    public static final Vector2 CHARACTER_MAX_VELOCITY_BOOSTED = new Vector2(35f, 0);

    public static Vector2 CHARACTER_MAX_VELOCITY = new Vector2(25f, 0);

    public static final float CHARACTER_CHANGING_COLOR_DELAY = 5.0f;


}

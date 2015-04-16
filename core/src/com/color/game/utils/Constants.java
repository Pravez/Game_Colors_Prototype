package com.color.game.utils;


import com.badlogic.gdx.math.Vector2;

public class Constants {


    public static final int APP_WIDTH = 840;
    public static final int APP_HEIGHT = 600;

    public static final int VIEWPORT_WIDTH = 50;
    public static final int VIEWPORT_HEIGHT = 50;

    public static final float CHARACTER_WIDTH = 1f;
    public static final float CHARACTER_HEIGHT = 2f;

    public static final Vector2 WORLD_GRAVITY = new Vector2(0, -10);

    public static final float STATIC_ELEMENTS_DENSITY = 0f;
    public static float DYNAMIC_ELEMENTS_DENSITY = 0.5f;

    public static final float CHARACTER_GRAVITY_SCALE = 3f;
    public static final Vector2 CHARACTER_JUMPING_LINEAR_IMPULSE = new Vector2(0, 100f);
    public static final float CHARACTER_SPEED = 1f;
    public static final Vector2 CHARACTER_MOVING_RIGHT_LINEAR_IMPULSE = new Vector2(5f,0);
    public static final Vector2 CHARACTER_MOVING_LEFT_LINEAR_IMPULSE = new Vector2(-5f, 0);
    public static final Vector2 CHARACTER_MAX_VELOCITY_X = new Vector2(20f, 0);




}

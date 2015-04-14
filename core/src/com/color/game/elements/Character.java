package com.color.game.elements;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.color.game.elements.protoEnums.ProtoState;
import com.color.game.level.Map;


public class Character {

    public Map map;

    public Vector2 position;
    public Vector2 acceleration;
    public Vector2 velocity;
    public ProtoState state;
    public Rectangle bounds;
}

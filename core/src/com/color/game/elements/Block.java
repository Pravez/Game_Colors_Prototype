package com.color.game.elements;


import com.badlogic.gdx.math.Rectangle;
import com.color.game.elements.protoEnums.ProtoColor;

public class Block {

    public Rectangle bounds;
    public ProtoColor color;

    public Block(Rectangle bounds, ProtoColor color) {
        this.bounds = bounds;
        this.color = color;
    }
}

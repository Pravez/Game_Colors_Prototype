package com.color.game.elements;

import com.badlogic.gdx.math.Vector2;
import com.color.game.elements.protoEnums.ProtoColor;

public class Block {

    public ProtoColor color;
    public Vector2 position;

    public Block(ProtoColor color, int x, int y) {
        this.color = color;
        this.position = new Vector2(x, y);
    }

    public boolean isDeadly(ProtoColor color){
        if(this.color != ProtoColor.EMPTY && this.color != ProtoColor.NEUTRAL) {
            return this.color != color;
        }else{
            return false;
        }
    }
}

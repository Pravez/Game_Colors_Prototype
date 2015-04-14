package com.color.game.level;

import com.badlogic.gdx.math.Vector2;
import com.color.game.elements.*;
import com.color.game.elements.Character;
import com.color.game.elements.protoEnums.ProtoColor;

import java.util.ArrayList;

public class Map {

    public Character character;

    public Vector2 size;
    public ArrayList<ArrayList<Block>> blocks = new ArrayList<ArrayList<Block>>();

    public Map() {
        character = new Character(this, 2, 1);
        this.size = new Vector2(30, 15);
        for (int i = 0 ; i < size.x ; i++) {
            blocks.add(new ArrayList<Block>());
        }
        for (int i = 0 ; i < size.x ; i++) {
            for (int j = 0 ; j < size.y ; j++) {
                blocks.get(i).add(new Block(ProtoColor.EMPTY));
            }
        }
        // Creation of the five blocks on the floor on the left
        for (int i = 0 ; i < 5 ; i++) {
            this.blocks.get(i).set(0, new Block(ProtoColor.NEUTRAL));
        }

        // Creation of the five blocks on the floor on the right
        for (int i = 25 ; i < 30 ; i++) {
            this.blocks.get(i).set(0, new Block(ProtoColor.NEUTRAL));
        }

        // Creation of the ceiling
        for (int i = 0 ; i < size.x ; i++) {
            this.blocks.get(i).set((int) (size.y - 1), new Block(ProtoColor.NEUTRAL));
        }

        // Creation of the walls
        for (int i = 0; i < size.y ; i++) {
            this.blocks.get(0).set(i, new Block(ProtoColor.NEUTRAL));
            this.blocks.get((int) (size.x - 1)).set(i, new Block(ProtoColor.NEUTRAL));
        }

        // Creation of the Colored Blocks
        int width = 4;
        for (int i = 7 ; i < 7 + width ; i++) {
            this.blocks.get(i).set(1, new Block(ProtoColor.RED));
        }
        for (int i = 13 ; i < 13 + width ; i++) {
            this.blocks.get(i).set(1, new Block(ProtoColor.GREEN));
        }
        for (int i = 19 ; i < 19 + width ; i++) {
            this.blocks.get(i).set(1, new Block(ProtoColor.BLUE));
        }
    }

    public void update(float deltaTime) {
        this.character.update(deltaTime);
        if (this.character.state == Character.ProtoState.DEAD) {
            this.character = new Character(this, 2, 1);
        }
    }
}

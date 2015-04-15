package com.color.game.level;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.color.game.elements.*;
import com.color.game.elements.Character;
import com.color.game.elements.protoEnums.ProtoColor;
import com.color.game.screens.GameScreen;

import java.util.ArrayList;

public class Map {

    public GameScreen game;

    public Character character;
    public Door door;

    public Vector2 size;
    public ArrayList<ArrayList<Block>> blocks = new ArrayList<ArrayList<Block>>();

    public Map(GameScreen game) {
        this.game = game;
        this.character = new Character(this, 2, 1);
        this.size = new Vector2(60, 30);
        this.door = new Door(new Rectangle(this.size.x - 1, 1, 1, 2));

        for (int i = 0 ; i < size.x ; i++) {
            blocks.add(new ArrayList<Block>());
        }
        for (int i = 0 ; i < size.x ; i++) {
            for (int j = 0 ; j < size.y ; j++) {
                blocks.get(i).add(new Block(ProtoColor.EMPTY, i, j));
            }
        }
        // Creation of the five blocks on the floor on the left
        for (int i = 0 ; i < 20 ; i++) {
            this.blocks.get(i).set(0, new Block(ProtoColor.NEUTRAL,i,0));
        }

        // Creation of the five blocks on the floor on the right
        for (int i = 40 ; i < 60 ; i++) {
            this.blocks.get(i).set(0, new Block(ProtoColor.NEUTRAL,i,0));
        }

        // Creation of the ceiling
        for (int i = 0 ; i < size.x ; i++) {
            this.blocks.get(i).set((int) (size.y - 1), new Block(ProtoColor.NEUTRAL,i,(int)size.y-1));
        }

        // Creation of the walls
        for (int i = 0; i < size.y ; i++) {
            this.blocks.get(0).set(i, new Block(ProtoColor.NEUTRAL,0,i));
            if (size.x - 1 != door.bounds.x || i != door.bounds.y ) { // if this is not the door
                this.blocks.get((int) (size.x - 1)).set(i, new Block(ProtoColor.NEUTRAL, (int) (size.x - 1),i));
            }
        }

        // Creation of the Colored Blocks
        int width = 4;
        for (int i = 22 ; i < 22 + width ; i++) {
            this.blocks.get(i).set(1, new Block(ProtoColor.RED,i,1));
        }
        for (int i = 28 ; i < 28 + width ; i++) {
            this.blocks.get(i).set(1, new Block(ProtoColor.GREEN,i,1));
        }
        for (int i = 34 ; i < 34 + width ; i++) {
            this.blocks.get(i).set(1, new Block(ProtoColor.BLUE,i,1));
        }
    }

    public void update(float deltaTime) {
        this.character.update(deltaTime);
        if (this.character.state == Character.ProtoState.DEAD) {
            this.game.init();
            this.character = null;
            this.character = new Character(this, 2, 1);
        }
    }
}

package com.color.game.level;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.color.game.elements.*;
import com.color.game.elements.Character;
import com.color.game.elements.protoEnums.ProtoColor;
import com.color.game.screens.GameScreen;

import java.util.ArrayList;

public class Map {

    public GameScreen game; // the Screen containing the game

    public Character character; // the main character of the game
    public ArrayList<Door> doors = new ArrayList<Door>(); // the list of the doors

    public Vector2 size; // the size of the map
    public ArrayList<ArrayList<Block>> blocks = new ArrayList<ArrayList<Block>>(); // the grid of the blocks

    /**
     * Map Constructor
     * @param game the Screen containing the main Game
     */
    public Map(GameScreen game) {
        this.game = game;
        this.character = new Character(this, 2, 1); // initialising the character
        this.size = new Vector2(60, 30); // the size of the map
        // Adding two doors, one on the left, one on the right
        this.doors.add(new Door(new Rectangle(this.size.x - 1, 1, 1, 2)));

        // Initialising the ArrayLists
        for (int i = 0 ; i < size.x ; i++) {
            blocks.add(new ArrayList<Block>());
        }
        for (int i = 0 ; i < size.x ; i++) {
            for (int j = 0 ; j < size.y ; j++) {
                blocks.get(i).add(new Block(ProtoColor.EMPTY, i, j)); // fill the grid with Empty Blocks
            }
        }
        // Creation of the five blocks on the floor on the left
        for (int i = 0 ; i < 20 ; i++) {
            this.blocks.get(i).set(0, new Block(ProtoColor.NEUTRAL,i,0));
        }

        // Creation of the five blocks on the floor on the right
        for (int i = 40 ; i < 43 ; i++) {
            this.blocks.get(i).set(0, new Block(ProtoColor.NEUTRAL,i,0));
        }

        for(int i=55;i<60;i++){
            this.blocks.get(i).set(0, new Block(ProtoColor.NEUTRAL,i,0));
        }

        for(int i=1;i<10;i++){
            this.blocks.get(42).set(i, new Block(ProtoColor.NEUTRAL, 42, i));
            this.blocks.get(55).set(i, new Block(ProtoColor.NEUTRAL, 55, i));
        }

        this.blocks.get(43).get(9).setColor(ProtoColor.NEUTRAL);
        this.blocks.get(44).get(9).setColor(ProtoColor.NEUTRAL);
        this.blocks.get(53).get(9).setColor(ProtoColor.NEUTRAL);
        this.blocks.get(54).get(9).setColor(ProtoColor.NEUTRAL);
        this.blocks.get(55).get(9).setColor(ProtoColor.RED);


        // Creation of the ceiling
        for (int i = 0 ; i < size.x ; i++) {
            this.blocks.get(i).set((int) (size.y - 1), new Block(ProtoColor.NEUTRAL,i,(int)size.y-1));
        }

        // Creation of the walls
        for (int i = 3; i < size.y - 1 ; i++) {
            this.blocks.get(0).set(i, new Block(ProtoColor.NEUTRAL,0,i)); // left wall
            this.blocks.get((int) (size.x - 1)).set(i, new Block(ProtoColor.NEUTRAL, (int) (size.x - 1),i)); // right wall
        }

        // Creation of the Colored Blocks
        int width = 4;
        for (int i = 22 ; i < 22 + width ; i++) {
            this.blocks.get(i).set(3, new Block(ProtoColor.NEUTRAL,i,3));
        }
        for (int i = 28 ; i < 28 + width ; i++) {
            this.blocks.get(i).set(5, new Block(ProtoColor.YELLOW,i,5));
        }
        for (int i = 34 ; i < 34 + width ; i++) {
            this.blocks.get(i).set(7, new Block(ProtoColor.BLUE,i,7));
        }
        for(int i=22;i<38;i++){
            this.blocks.get(i).set(0, new Block(ProtoColor.NEUTRAL, i, 0));
        }

    }

    /**
     * Update method
     * @param deltaTime the deltaTime since the last update
     */
    public void update(float deltaTime) {
        this.character.update(deltaTime); // update the character
        if (this.character.state == Character.ProtoState.DEAD) { // if the character is dead
            this.game.init(); // we reboot the game
            this.character = null;
            this.character = new Character(this, 2, 1); // we put the character at his initial position
        }
    }
}

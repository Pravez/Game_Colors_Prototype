package com.color.game.level;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.color.game.elements.Block;
import com.color.game.elements.Wall;
import com.color.game.elements.protoEnums.ProtoColor;

import java.util.ArrayList;

public class Map {

    public Vector2 size;
    public ArrayList<Block> blocks = new ArrayList<Block>();
    public ArrayList<Wall> walls = new ArrayList<Wall>();

    public Map() {
        this.size = new Vector2(30, 15);
        // Création des cinq blocs au sol à gauche
        for (int i = 0 ; i < 5 ; i++) {
            this.walls.add(new Wall(new Vector2(i, 0)));
        }

        // Création des cinq blocs au sol à droite
        for (int i = 25 ; i < 30 ; i++) {
            this.walls.add(new Wall(new Vector2(i, 0)));
        }

        // Création des blocs de couleur
        this.blocks.add(new Block(new Rectangle(7, 1, 4, 1), ProtoColor.RED));
        this.blocks.add(new Block(new Rectangle(13, 1, 4, 1), ProtoColor.GREEN));
        this.blocks.add(new Block(new Rectangle(19, 1, 4, 1), ProtoColor.BLUE));
    }

    public void update() {

    }
}

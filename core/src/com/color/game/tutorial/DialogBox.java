package com.color.game.tutorial;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class DialogBox extends Stage {

    public Table table;

    public DialogBox() {
        this.table = new Table();


        super.addActor(this.table);
    }

    public void show() {
        super.act();
        super.draw();
    }
}

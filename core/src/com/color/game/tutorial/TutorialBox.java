package com.color.game.tutorial;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.*;
import com.color.game.utils.Constants;
import com.color.game.utils.DialogUtils;

import java.util.ArrayList;

public class TutorialBox extends Stage {

    private DialogBox dialogBox;
    private ArrayList<ArrayList<Actor>> tutorialActors;
    private int currentTable = 0;
    private boolean isFinished = false;

    public TutorialBox() {
        this.tutorialActors = new ArrayList<ArrayList<Actor>>();
        this.dialogBox = new DialogBox();

        init();
    }

    public boolean isFinished() {
        return this.isFinished;
    }

    @Override
    public void draw() {
        super.draw();
        this.dialogBox.show();
    }

    public void init() {
        addFirstActors();
        addSecondActors();
        addThirdActors();
        addForthActors();
        addFifthActors();

        dialogBox.setTableActors(tutorialActors.get(currentTable));
    }

    public void addFirstActors() {
        ArrayList<Actor> actors = new ArrayList<Actor>();
        actors.add(DialogUtils.createTitleLabel("Hello, welcome", Color.BLACK));
        actors.add(DialogUtils.createTitleLabel("to our world !", Color.BLACK));
        actors.add(DialogUtils.createTextLabel("   ", Color.BLACK));
        actors.add(DialogUtils.createTextLabel("I am H2G2, your guide.", Color.BLACK));
        actors.add(DialogUtils.createTextLabel("I will help you find the", Color.BLACK));
        actors.add(DialogUtils.createTextLabel("exit of this strange world.", Color.BLACK));
        actors.add(DialogUtils.createTextLabel("   ", Color.BLACK));
        actors.add(DialogUtils.createTextLabel("Press SPACE to continue", Color.BLACK));
        this.tutorialActors.add(actors);
    }

    public void addSecondActors() {
        ArrayList<Actor> actors = new ArrayList<Actor>();
        actors.add(DialogUtils.createTextLabel("Do you see those color blocks ?", Color.BLACK));
        actors.add(DialogUtils.createTextLabel("They are inactive for", Color.BLACK));
        actors.add(DialogUtils.createTextLabel("the moment, but you have", Color.BLACK));
        actors.add(DialogUtils.createTextLabel("the capability to change it...", Color.BLACK));
        actors.add(DialogUtils.createTextLabel("   ", Color.BLACK));
        actors.add(DialogUtils.createTextLabel("Press SPACE to continue", Color.BLACK));
        this.tutorialActors.add(actors);
    }

    public void addThirdActors() {
        ArrayList<Actor> actors = new ArrayList<Actor>();
        actors.add(DialogUtils.createTextLabel("Your 'armor' can activate", Color.BLACK));
        actors.add(DialogUtils.createTextLabel("the blocks according to", Color.BLACK));
        actors.add(DialogUtils.createTextLabel("their color. All blocks", Color.BLACK));
        actors.add(DialogUtils.createTextLabel("of the corresponding color", Color.BLACK));
        actors.add(DialogUtils.createTextLabel("will be active but only for ", Color.BLACK));
        actors.add(DialogUtils.createTextLabel(Constants.CHARACTER_CHANGING_COLOR_DELAY + " seconds", Color.BLACK));
        actors.add(DialogUtils.createTextLabel("   ", Color.BLACK));
        actors.add(DialogUtils.createTextLabel("Press SPACE to continue", Color.BLACK));
        this.tutorialActors.add(actors);
    }

    public void addForthActors() {
        ArrayList<Actor> actors = new ArrayList<Actor>();
        actors.add(DialogUtils.createTextLabel("Here are the key controls :", Color.BLACK));
        actors.add(DialogUtils.createTextLabel("A for the Red Blocks", Color.BLACK));
        actors.add(DialogUtils.createTextLabel("Z for the Blue Blocks", Color.BLACK));
        actors.add(DialogUtils.createTextLabel("E for the Yellow Blocks", Color.BLACK));
        actors.add(DialogUtils.createTextLabel("Here you are !", Color.BLACK));
        actors.add(DialogUtils.createTextLabel("   ", Color.BLACK));
        actors.add(DialogUtils.createTextLabel("Press SPACE to continue", Color.BLACK));
        this.tutorialActors.add(actors);
    }

    public void addFifthActors() {
        ArrayList<Actor> actors = new ArrayList<Actor>();
        actors.add(DialogUtils.createTextLabel("Oh ! I nearly forgot...", Color.BLACK));
        actors.add(DialogUtils.createTextLabel("Use the Arrow keys to move", Color.BLACK));
        actors.add(DialogUtils.createTextLabel("and use the Spacebar to", Color.BLACK));
        actors.add(DialogUtils.createTextLabel("jump.", Color.BLACK));
        actors.add(DialogUtils.createTextLabel("Good luck !", Color.BLACK));
        actors.add(DialogUtils.createTextLabel("   ", Color.BLACK));
        actors.add(DialogUtils.createTextLabel("Press SPACE to continue", Color.BLACK));
        this.tutorialActors.add(actors);
    }

    @Override
    public boolean keyDown(int keycode) {
        if(keycode == Input.Keys.ENTER){
            currentTable++;
            if (currentTable >= tutorialActors.size()) {
                isFinished = true;
            } else {
                dialogBox.setTableActors(tutorialActors.get(currentTable));
            }
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        if(keycode == Input.Keys.SPACE){
            currentTable++;
            if (currentTable >= tutorialActors.size()) {
                isFinished = true;
            } else {
                dialogBox.setTableActors(tutorialActors.get(currentTable));
            }
        }
        return true;
    }
}

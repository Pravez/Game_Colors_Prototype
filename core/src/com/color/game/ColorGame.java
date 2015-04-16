package com.color.game;

import com.badlogic.gdx.Game;
import com.color.game.screens.GameScreen;

public class ColorGame extends Game {

    @Override
	public void create () {
        this.setScreen(new GameScreen());
	}
}

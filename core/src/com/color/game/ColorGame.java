package com.color.game;

import com.badlogic.gdx.Game;
import com.color.game.screens.DeathScreen;
import com.color.game.screens.GameScreen;
import com.color.game.screens.MenuScreen;
import com.color.game.screens.WinScreen;

public class ColorGame extends Game {

    private DeathScreen deathScreen;
    private GameScreen gameScreen;
    private MenuScreen menuScreen;
    private WinScreen winScreen;

    @Override
	public void create () {
        this.setScreen(getMenuScreen());
	}

    public DeathScreen getDeathScreen() {
        if (deathScreen == null)
            deathScreen = new DeathScreen();
        return deathScreen;
    }

    public GameScreen getGameScreen() {
        if (gameScreen == null)
            gameScreen = new GameScreen();
        return gameScreen;
    }

    public MenuScreen getMenuScreen() {
        if (menuScreen == null)
            menuScreen = new MenuScreen();
        return menuScreen;
    }

    public WinScreen getWinScreen() {
        if (winScreen == null)
            winScreen = new WinScreen();
        return winScreen;
    }
}

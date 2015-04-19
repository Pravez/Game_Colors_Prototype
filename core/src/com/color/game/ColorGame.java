package com.color.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.color.game.levels.LevelManager;
import com.color.game.screens.*;
import com.color.game.stages.BaseStage;

public class ColorGame extends Game {

    private DeathScreen deathScreen;
    private GameScreen gameScreen;
    private MenuScreen menuScreen;
    private WinScreen winScreen;
    private OptionScreen optionScreen;

    private static Music music;

    public static float soundVolume = 0f;
    public static float musicVolume = 0f;

    @Override
	public void create () {
        music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
        music.setLooping(true);
        music.setVolume(musicVolume);//0.1f);
        music.play();
        LevelManager.init();
        this.setScreen(getMenuScreen());
	}

    @Override
    public void dispose() {
        super.dispose();
        music.dispose();
        if (gameScreen != null) {
            BaseStage.disposeStage();
        }
        LevelManager.disposeLevels();
    }

    public static void setMusicVolume(float musicVolume) {
        ColorGame.musicVolume = musicVolume;
        music.setVolume(musicVolume);
    }

    public void setGameScreen() {
        this.gameScreen.initGameStage();
        super.setScreen(this.gameScreen);
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

    public OptionScreen getOptionScreen() {
        if (optionScreen == null)
            optionScreen = new OptionScreen();
        return optionScreen;
    }
}

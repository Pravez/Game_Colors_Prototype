package com.color.game.tutorial;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class DialogBox extends Stage {

    public Table table;

    public DialogBox() {
        this.table = new Table();

        // Font
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("28 Days Later.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 42;
        BitmapFont font = generator.generateFont(parameter);
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        // Text
        Label title = new Label("You Won", new Label.LabelStyle(font, Color.RED));

        table.add(title).padBottom(30).row();
        Image image = new Image();
        image.setDrawable(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("dragons.png")), 0, 0, 0.25f, 0.25f)));
        table.add(image);

        table.setFillParent(true);

        generator.dispose();
        super.addActor(this.table);
    }

    public void show() {
        super.act();
        super.draw();
    }

    public void addToTable() {

    }
}

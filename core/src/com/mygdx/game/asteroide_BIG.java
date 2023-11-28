package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class asteroide_BIG extends Asteroides{
    public asteroide_BIG() {
		super(new Sprite(new Texture(Gdx.files.internal("aGreyLarge.png"))), 1, 1);
	}
}

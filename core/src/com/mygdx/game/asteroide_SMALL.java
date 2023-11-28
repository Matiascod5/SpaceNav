package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class asteroide_SMALL extends Asteroides{
	
	public asteroide_SMALL() {
		super(new Sprite(new Texture(Gdx.files.internal("aGreyMedium4.png"))));
	}
}

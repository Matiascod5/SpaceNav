package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class AsteroideCongelado_BIG extends Asteroides{
    public AsteroideCongelado_BIG() {
		super(new Sprite(new Texture(Gdx.files.internal("AsteroideDeHielo_BIG2.png"))), 4, 4);
	}
}

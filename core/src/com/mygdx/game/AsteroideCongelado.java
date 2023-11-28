package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class AsteroideCongelado extends Asteroides{

    public AsteroideCongelado() {
		super(new Sprite(new Texture(Gdx.files.internal("AsteroideDeHieloF.png"))));
	}
    
}

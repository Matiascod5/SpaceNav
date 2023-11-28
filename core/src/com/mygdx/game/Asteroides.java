package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;

public abstract class Asteroides extends Enemigo{
	int x = getX();
	int y = getY();
	//private Sprite sprite;
	
	public Asteroides(Sprite sprite) {
		super(sprite, 1, 1);
	}

	//movimiento

	public void movimiento() {
        x += getxVel();
        y += getyVel();

        if (x+getxVel() < 0 || x+getxVel()+getSprite().getWidth() > Gdx.graphics.getWidth())
        	setxVel(getxVel() * -1);
        if (y+getyVel() < 0 || y+getyVel()+getSprite().getHeight() > Gdx.graphics.getHeight())
        	setyVel(getyVel() * -1);
        getSprite().setPosition(x, y);
    }
}

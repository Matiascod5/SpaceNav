package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;

public abstract class Asteroides extends Enemigo{
	int x = getX();
	int y = getY();
	
	public Asteroides(Sprite sprite, int xVel, int yVel) {
		super(sprite, xVel, yVel);
	}

	public void movimiento() {
       
        if (x+getxVel() < 0 || x+getxVel()+getSprite().getWidth() > Gdx.graphics.getWidth())
        	setxVel(getxVel() * -1);
        if (y+getyVel() < 0 || y+getyVel()+getSprite().getHeight() > Gdx.graphics.getHeight())
        	setyVel(getyVel() * -1);
		x += getxVel();
        y += getyVel();
        getSprite().setPosition(x, y);
    }
}

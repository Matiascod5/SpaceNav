package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class disparo_default extends Disparo{
	//Sprite sprite = getSprite();
	//float x = sprite.getX() + sprite.getWidth()/2-5;
	//float y = getY();
	
	
	
	public disparo_default(float x, float y) {
		super(x,y,0,3,new Texture(Gdx.files.internal("Rocket2.png")), Gdx.audio.newSound(Gdx.files.internal("pop-sound.mp3")), new Texture(Gdx.files.internal("Rocket2.png")));
		/*setxVel(0);
		setyVel(0);
		setSprite(new Texture(Gdx.files.internal("Rocket2.png")));
		getSprite().setPosition(0, 0);
		setSonidoDisparo(Gdx.audio.newSound(Gdx.files.internal("pop-sound.mp3")));
		setTexturaDisparo(new Texture(Gdx.files.internal("Rocket2.png")));*/
	}

	public void efectoEspecial(){

	}

	public void movimiento(){	
		getSprite().setPosition(getSprite().getX()+getxVel(), getSprite().getY()+getyVel());
		if (getSprite().getX() < 0 || getSprite().getX()+getSprite().getWidth() > Gdx.graphics.getWidth()) {
	    	setDestroyed(true);
		}
		if (getSprite().getY() < 0 || getSprite().getY()+getSprite().getHeight() > Gdx.graphics.getHeight()) {
			setDestroyed(true);
		}     
	}
}

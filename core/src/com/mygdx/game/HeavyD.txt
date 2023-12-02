package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class HeavyD extends Disparo {

    
	public HeavyD(float x, float y) {
		super(x,y,0,3,new Sprite(new Texture(Gdx.files.internal("BalazoH.png"))), Gdx.audio.newSound(Gdx.files.internal("DisparoStarWars.mp3")));
		/*setxVel(0);
		setyVel(0);
		setSprite(new Texture(Gdx.files.internal("Rocket2.png")));
		getSprite().setPosition(0, 0);
		setSonidoDisparo(Gdx.audio.newSound(Gdx.files.internal("pop-sound.mp3")));
		setTexturaDisparo(new Texture(Gdx.files.internal("Rocket2.png")));*/
	}

    @Override
    public void efectoEspecial() {
        this.setxVel(50);
        this.setyVel(50);
    }

    @Override
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

package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class DisparoStarWars extends Disparo {

    
	public DisparoStarWars(float x, float y) {
		super(x,y,0,3,new Sprite(new Texture(Gdx.files.internal("x-wing-bolt-red2.png"))), Gdx.audio.newSound(Gdx.files.internal("DisparoStarWars.mp3")));
		/*setxVel(0);
		setyVel(0);
		setSprite(new Texture(Gdx.files.internal("Rocket2.png")));
		getSprite().setPosition(0, 0);
		setSonidoDisparo(Gdx.audio.newSound(Gdx.files.internal("pop-sound.mp3")));
		setTexturaDisparo(new Texture(Gdx.files.internal("Rocket2.png")));*/
	}

    @Override
    public void efectoEspecial() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'efectoEspecial'");
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

	public Disparo crearDisparo(float x, float y){
		Disparo disparo = new DisparoStarWars(x,y);
		return disparo;
	}
    
}

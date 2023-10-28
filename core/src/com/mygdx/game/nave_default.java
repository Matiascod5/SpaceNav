package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;


public class nave_default extends Nave{
	
	public nave_default() {
		setVidas(3);
		setxVel(0);
		setyVel(0);
		setSpriteTexture(new Texture(Gdx.files.internal("MainShip3.png")));
		setSpritePosition(Gdx.graphics.getWidth()/2-50,30);
		setSpriteBounds(Gdx.graphics.getWidth()/2-50,30,45,45);
		setSonidoHerido(Gdx.audio.newSound(Gdx.files.internal("hurt.ogg")));
		setSonidoDisparo(Gdx.audio.newSound(Gdx.files.internal("pop-sound.mp3")));
		setTexturaDisparo(new Texture(Gdx.files.internal("Rocket2.png")));
		setHerido(false);
		setTiempoHeridoMax(50);
	}
	
	public void disparar() {
		if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {         
			Bullet  bala = new Bullet(getX()+ getWidth()/2-5,getY()+ getHeight()-5,0,3,getTexturaDisparo());
		    agregarBala(bala);
		    getSonidoDisparo().play();
	    }
	}
	
	public void efectoEspecial() {
		
	}
}

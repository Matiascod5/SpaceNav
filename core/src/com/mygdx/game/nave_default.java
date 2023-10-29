package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;


public class nave_default extends Nave{
	
	public nave_default() {
		super(3,0,0,new Texture(Gdx.files.internal("MainShip3.png")), Gdx.audio.newSound(Gdx.files.internal("hurt.ogg")),50);
		/*
		setVidas(3);
		setxVel(0);
		setyVel(0);
		setSpriteTexture(new Texture(Gdx.files.internal("MainShip3.png")));
		setSonidoHerido(Gdx.audio.newSound(Gdx.files.internal("hurt.ogg")));
		setHerido(false);
		setTiempoHeridoMax(50);*/
	}

	public void movimiento() {
	        getSprite().setPosition(getSprite().getX()+getxVel(), getSprite().getY()+getyVel());
	        if (getSprite().getX() < 0 || getSprite().getX()+getSprite().getWidth() > Gdx.graphics.getWidth()) {
	            setDestruido(true);
	        }
	        if (getSprite().getY() < 0 || getSprite().getY()+getSprite().getHeight() > Gdx.graphics.getHeight()) {
	        	setDestruido(true);
	        }
			
	        
	    }
	
	
	public void disparar() {
		if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {         
			Bullet  bala = new Bullet(ggetYetX()+ getWidth()/2-5,()+ getHeight()-5,0,3,getTexturaDisparo());
		    agregarBala(bala);
		    getSonidoDisparo().play();
	    }
	}
	
	public void efectoEspecial() {
		
	}
}

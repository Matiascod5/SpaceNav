package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class nave_default extends Nave{
	
	public nave_default() {
		super(5,0,0,new Texture(Gdx.files.internal("MainShip3.png")), Gdx.audio.newSound(Gdx.files.internal("hurt.ogg")),50, new disparo_default(0,0));
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

	public void disparar(SpriteBatch batch, Colisiones Colisiones) {
		//x = sprite.getX() + sprite.getWidth()/2-5;
		//y = sprite.getY()+ sprite.getHeight()-5;
        Disparo bala = new disparo_default(getSprite().getX() + getSprite().getWidth()/2-5, getSprite().getY()+ getSprite().getHeight()-5);
	    Colisiones.agregarBala(bala);
		bala.mostrar(batch);
	    bala.getSonidoDisparo().play();
	   
    }
	
	
	/*public void disparar() {
		if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {         
			Bullet  bala = new Bullet(ggetYetX()+ getWidth()/2-5,()+ getHeight()-5,0,3,getTexturaDisparo());
		    agregarBala(bala);
		    getSonidoDisparo().play();
	    }
	}*/
	
	public void efectoEspecial() {
		
	}

}

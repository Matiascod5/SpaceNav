package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Nave_StarWars extends Nave {

    public Nave_StarWars(){
        super(15, 0, 0, new Texture(Gdx.files.internal("StarWarsDerecha4.png") ),Gdx.audio.newSound(Gdx.files.internal("hurt.ogg")),50  );
    }

    @Override
    public void efectoEspecial() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'efectoEspecial'");
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

    public void disparar(SpriteBatch batch, Colisiones Colisiones, Sprite spr) {
		//x = sprite.getX() + sprite.getWidth()/2-5;
		//y = sprite.getY()+ sprite.getHeight()-5;
        Disparo bala = new DisparoStarWars(spr.getX() + spr.getWidth()/2-5, spr.getY()+ spr.getHeight()-5);
	    Colisiones.agregarBala(bala);
		bala.mostrar(batch);
	    bala.getSonidoDisparo().play();
	   
    }
    
}

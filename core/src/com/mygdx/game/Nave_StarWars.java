package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Nave_StarWars extends Nave {

    public Nave_StarWars(){
        super(15, 3, 3, new Texture(Gdx.files.internal("StarWarsDerecha.png") ),Gdx.audio.newSound(Gdx.files.internal("hurt.ogg")),50  );
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
    
}

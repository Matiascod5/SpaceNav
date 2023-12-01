package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class DirectorNave {
    
    public void construirNaveDefault(Builder builder){
        builder.setTipoNave(TipoNave.NaveDefault);
        builder.setVidas(5);
        builder.setVelocidades(0, 0);
        builder.setSprite(new Texture(Gdx.files.internal("MainShip3.png")));
        builder.setSonidoHerido(Gdx.audio.newSound(Gdx.files.internal("hurt.ogg")));
        builder.setTiempoHeridoMax(50);
        builder.setDisparo(new disparo_default(0,0));
    }

    public void construirNaveStarWars(Builder builder){
        builder.setTipoNave(TipoNave.NaveStarWars);
        builder.setVidas(5);
        builder.setVelocidades(0, 0);
        builder.setSprite(new Texture(Gdx.files.internal("StarWarsDerecha4.png")));
        builder.setSonidoHerido(Gdx.audio.newSound(Gdx.files.internal("r2d2_scream_converted.mp3")));
        builder.setTiempoHeridoMax(50);
        builder.setDisparo(new DisparoStarWars(0,0));
    }
}

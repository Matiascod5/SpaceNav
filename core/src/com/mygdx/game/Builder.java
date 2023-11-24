package com.mygdx.game;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public interface Builder {

    void setTipoNave(TipoNave tipo);
    void setVidas(int vidas);
    void setVelocidades(float xVel, float yVel);
    void setSprite(Texture tx);
    void setSonidoHerido(Sound sonidoHerido);
    void setTiempoHeridoMax(int tiempoHeridoMax);
    void setDisparo(Disparo disparo);
    //Nave getNave();
}

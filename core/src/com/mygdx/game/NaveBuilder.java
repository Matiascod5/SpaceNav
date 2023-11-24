package com.mygdx.game;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public interface NaveBuilder {
    void construirVidas(int vidas);
    void construirVelocidades(float xVel, float yVel);
    void construirSprite(Texture tx);
    void construirSonidoHerido(Sound sonidoHerido);
    void construirTiempoHeridoMax(int tiempoHeridoMax);
    void construirDisparo(Disparo disparo);
    Nave obtenerNave();
}

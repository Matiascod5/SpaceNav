package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface NivelesStrategy{
    public void crearEnemigos(Colisiones colisiones);

    public void Fondo(SpriteBatch batch);

    public void Musica(boolean musicaActivada);

    public void apagarMusica();

    public void apagarExplosion();

    public void Explosion();

    public void mostrarFondo(SpriteBatch batch);
}

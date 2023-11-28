package com.mygdx.game;

public interface NivelesStrategy{
    public void crearEnemigos(Colisiones colisiones);

    public void Fondo();

    public void Musica(boolean musicaActivada);

    public void apagarMusica();

    public void apagarExplosion();

    public void Explosion();
}

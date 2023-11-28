package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Niveles {
    private NivelesStrategy strategy;

    public void crearNivel(Colisiones colisiones, boolean musicaActivada, SpriteBatch batch){
        strategy.crearEnemigos(colisiones);
        strategy.Fondo(batch);
        strategy.Musica(musicaActivada);
    }

    public void setStrategy(NivelesStrategy strategy){
        this.strategy = strategy;
    }

    public NivelesStrategy getStrategy(){
        return this.strategy;
    }
}

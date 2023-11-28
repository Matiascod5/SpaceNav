package com.mygdx.game;

public class Niveles {
    private NivelesStrategy strategy;

    public void crearNivel(Colisiones colisiones, boolean musicaActivada){
        strategy.crearEnemigos(colisiones);
        strategy.Fondo();
        strategy.Musica(musicaActivada);
    }

    public void setStrategy(NivelesStrategy strategy){
        this.strategy = strategy;
    }

    public NivelesStrategy getStrategy(){
        return this.strategy;
    }
}

package com.mygdx.game;

public class ConstruirNave {
    private NaveBuilder naveBuilder;

    public ConstruirNave(NaveBuilder naveBuilder) {
        this.naveBuilder = naveBuilder;
    }

    public void construirNaveEspecifica() {
        
        naveBuilder.construirVidas(3);
        naveBuilder.construirVelocidades(2.0f, 2.0f);
        naveBuilder.construirSprite(new Texture("ruta/a/tu/imagen.png"));
        naveBuilder.construirSonidoHerido(new Sound("ruta/a/tu/sonido.wav"));
        naveBuilder.construirTiempoHeridoMax(100);
        naveBuilder.construirDisparo(new Disparo());
    }

    public Nave obtenerNave() {
        return naveBuilder.obtenerNave();
    }
}

package com.mygdx.game;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class NaveBuilder implements Builder{
    private TipoNave tipo;
    private int vidas;
	private float xVel;
	private float yVel;
	//private boolean herido;
	private int tiempoHeridoMax;
	private Sprite sprite;
	private Disparo disparo;
	private Sound sonidoHerido;
	//private int tiempoHerido;
	//private boolean destruido;

    @Override
    public void setTipoNave(TipoNave tipo){
        this.tipo = tipo;
    }

    @Override
    public void setVidas(int vidas){
        this.vidas = vidas;
    }

    @Override
    public void setVelocidades(float xVel, float yVel){
        this.xVel = xVel;
        this.yVel = yVel;
    }

    @Override
    public void setSprite(Texture tx){
        this.sprite = new Sprite(tx);
    }

    @Override
    public void setSonidoHerido(Sound sonidoHerido){
        this.sonidoHerido = sonidoHerido;
    }

    @Override
    public void setTiempoHeridoMax(int tiempoHeridoMax){
        this.tiempoHeridoMax = tiempoHeridoMax;
    }

    @Override
    public void setDisparo(Disparo disparo){
        this.disparo = disparo;
    }

    public Nave getNave(){
        return new Nave(vidas, xVel, yVel, sprite, sonidoHerido, tiempoHeridoMax, disparo, tipo);
    }

}
package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;

public abstract class disparo_default implements Disparo{
	private int xSpeed;
	private int ySpeed;
	private boolean destroyed = false;
	private Sprite spr;
	
	public disparo_default() {
		this.xSpeed = 0;
		this.ySpeed = 0;
		spr = new Sprite() ;
		spr.setPosition(0, 0);
	}
	
	public void mostrar() {
		spr.draw(batch);
	}
	public void movimiento() {
		
	}
	public void actualizarMov() {
		spr.setPosition(spr.getX()+xSpeed, spr.getY()+ySpeed);
        if (spr.getX() < 0 || spr.getX()+spr.getWidth() > Gdx.graphics.getWidth()) {
            destroyed = true;
        }
        if (spr.getY() < 0 || spr.getY()+spr.getHeight() > Gdx.graphics.getHeight()) {
        	destroyed = true;
        }
		
	}
	public boolean verificarColision() {
		if(spr.getBoundingRectangle().overlaps(b2.getArea())){
        	// Se destruyen ambos
            this.destroyed = true;
            return true;

        }
        return false;
		
	}
	public boolean verificarDestruccion() {
		return false;
		
	}
	public abstract void efectoEspecial();
}

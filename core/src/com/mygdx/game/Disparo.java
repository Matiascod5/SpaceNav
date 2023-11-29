package com.mygdx.game;

//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.audio.Sound;
//import java.util.ArrayList;

public abstract class Disparo {
	private float x;
	private float y;
	private int xVel;
	private int yVel;
	private boolean destroyed;
	private Sprite sprite;
	private Sound sonidoDisparo;
	    
	public Disparo(float x, float y, int xSpeed, int ySpeed, Sprite tx, Sound sonidoDisparo) {
	    this.sprite = tx;
	    sprite.setPosition(x, y);
		//this.x = x;
		//this.y = y;
	    this.xVel = xSpeed;
	    this.yVel = ySpeed;
		this.sonidoDisparo = sonidoDisparo;
		this.destroyed = false;
	}

	public Disparo(float x, float y ){

	}



	public Disparo formatearDisparo(float x, float y){
		this.x = x;
		this.y = y;
		this.destroyed = false;
		this.sprite.setPosition(x,y);
		return this;
	}
	        
	public void mostrar(SpriteBatch batch) {
	    sprite.draw(batch);
	}

		
/*
	public void verificarColisionEnemigo(Disparo b, ArrayList<Enemigo> balls1, ArrayList<Enemigo> balls2, int score, Sound explosionSound, SpriteBatch batch){
		    //b.movimiento();
		for (int j = 0; j < balls1.size(); j++) {    
		    if (b.verificarColision(balls1.get(j))) {          
		        explosionSound.play();
		        balls1.remove(j);
		        balls2.remove(j);
		        j--;
		        score +=10;
		    }   	  
		}
		b.mostrar(batch);
	}
	    
	public boolean verificarColision(Enemigo b2) {
	    if(sprite.getBoundingRectangle().overlaps(b2.getArea())){
	     	// Se destruyen ambos
	        this.destroyed = true;
	        return true;
	    }
	    return false;
    }
	    */
	public boolean verificarDestruccion() {
		return destroyed;
	}
	
	public void setxVel(int xVel){
		this.xVel = xVel;
	}

	public void setyVel(int yVel){
		this.yVel = yVel;
	}

	public void setDestroyed(boolean destroyed){
		this.destroyed = destroyed;
	}

	public void setSprite(Sprite sprite){
		this.sprite = sprite;
	}
	
	public void setSonidoDisparo(Sound sonidoDisparo){
		this.sonidoDisparo = sonidoDisparo;
	}

	public void setX(float x){
		this.x = x;
	}

	public void setY(float y){
		this.y = y;
	}

	public int getxVel(){
		return this.xVel;
	}
	
	public int getyVel(){
		return this.yVel;
	}
	
	public boolean getDestroyed(){
		return this.destroyed;
	}
	
	public Sprite getSprite(){
		return this.sprite;
	}

	public Sound getSonidoDisparo(){
		return this.sonidoDisparo;
	}

	public float getX(){
		return this.x;
	}

	public float getY(){
		return this.y;
	}

	public abstract void movimiento();
	
	public abstract void efectoEspecial();
	
}
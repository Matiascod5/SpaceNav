package com.mygdx.game;

import java.util.Random;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;


public abstract class Enemigo {
	private int x;
	private int y;
	private int xVel;
	private int yVel;
	private Sprite sprite;
	
	public Enemigo(Sprite sprite, int xVel, int yVel) {
		setSpawn();
		//Random r = new Random();
		this.sprite = sprite;
		//this.x = r.nextInt((int)Gdx.graphics.getWidth());
		//this.y = 50+r.nextInt((int)Gdx.graphics.getHeight()-50);
		this.xVel = xVel;
		this.yVel = yVel;
		/* 
		int size = 20+r.nextInt(10);
		
		if (x-size < 0) this.x = x+size;
    	if (x+size > Gdx.graphics.getWidth())this.x = x-size;
         
        //validar que borde de esfera no quede fuera
    	if (y-size < 0) this.y = y+size;
    	if (y+size > Gdx.graphics.getHeight())this.y = y-size;
    	*/
        sprite.setPosition(x, y);
        this.setxVel(xVel);
        this.setyVel(yVel);
	}

	public void setSpawn(){
		Random r = new Random();
		this.x = 50+r.nextInt((int)Gdx.graphics.getWidth()-50);
		this.y = 50+r.nextInt((int)Gdx.graphics.getHeight()-50);

		/*
		int size = 20+r.nextInt(10);
		
		if (x-size < 0) this.x = x+size;
    	if (x+size > Gdx.graphics.getWidth())this.x = x-size;
         
        //validar que borde de esfera no quede fuera
    	if (y-size < 0) this.y = y+size;
    	if (y+size > Gdx.graphics.getHeight())this.y = y-size;
		*/
	}
	
	public abstract void movimiento();

	public Rectangle getArea() {
    	return sprite.getBoundingRectangle();
    }
	public void setPosition() {
		this.sprite.setPosition(x, y);
	}
    
    public void setX(int x) {
    	this.x = x;
    }
    
    public void setY(int y) {
    	this.y = y;
    }
    
    public void setxVel(int xVel) {
		this.xVel = xVel;
	}
    
    public void setyVel(int yVel) {
		this.yVel = yVel;
	}
    
    public void setSprite(Sprite sprite) {
    	this.sprite = sprite;
    }
    
    public int getX() {
    	return this.x;
    }
    
    public int getY() {
    	return this.y;
    }
    
    public int getxVel() {
		return this.xVel;
	}
    
	public int getyVel() {
		return this.yVel;
	}
	
    public Sprite getSprite() {
    	return this.sprite;
    }
}

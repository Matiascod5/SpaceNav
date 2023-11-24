package com.mygdx.game;

//import java.util.Random;

import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
//import com.badlogic.gdx.graphics.g2d.SpriteBatch;
//import com.badlogic.gdx.math.Rectangle;

public abstract class Asteroides extends Enemigo{
	int x = getX();
	int y = getY();
	//private Sprite sprite;
	
	public Asteroides(Sprite sprite) {
		super(sprite, 1, 1);
	}

	//private int cantAsteroides;
	//private Sprite sprite;
	//private  ArrayList<Object> balls1 = new ArrayList<>();
	//private  ArrayList<Object> balls2 = new ArrayList<>();
	
	/*public void Asteroide(int x, int y, int size, int xVel, int yVel, Texture tx) {
		this.sprite = new Sprite(tx);
    	this.x = x; 
 	
        //validar que borde de esfera no quede fuera
    	if (x-size < 0) this.x = x+size;
    	if (x+size > Gdx.graphics.getWidth())this.x = x-size;
         
        this.y = y;
        //validar que borde de esfera no quede fuera
    	if (y-size < 0) this.y = y+size;
    	if (y+size > Gdx.graphics.getHeight())this.y = y-size;
    	
    	
        sprite.setPosition(x, y);
        this.xVel = xVel;
        this.yVel = yVel;
        
        Random r = new Random();
	    for (int i = 0; i < cantAsteroides; i++) {
	        Ball2 bb = new Ball2(r.nextInt((int)Gdx.graphics.getWidth()),	50+r.nextInt((int)Gdx.graphics.getHeight()-50),	20+r.nextInt(10), xVel+r.nextInt(4), yVel+r.nextInt(4), 
	  	            new Texture(Gdx.files.internal("aGreyMedium4.png")));	   
	  	    balls1.add(bb);
	  	    balls2.add(bb);
	  	}
	}*/
	
	/*
	public void crearAsteroide(Object asteroide) {
	    for (int i = 0; i < cantAsteroides; i++) {
	        //Ball2 bb = new Ball2(r.nextInt((int)Gdx.graphics.getWidth()),	50+r.nextInt((int)Gdx.graphics.getHeight()-50),	20+r.nextInt(10), xVel+r.nextInt(4), yVel+r.nextInt(4), 
	  	            //new Texture(Gdx.files.internal("aGreyMedium4.png")));	   
	  	    balls1.add(asteroide);
	  	    balls2.add(asteroide);
	  	}
	}*/
	
	//movimiento

	public void movimiento() {
        x += getxVel();
        y += getyVel();

        if (x+getxVel() < 0 || x+getxVel()+getSprite().getWidth() > Gdx.graphics.getWidth())
        	setxVel(getxVel() * -1);
        if (y+getyVel() < 0 || y+getyVel()+getSprite().getHeight() > Gdx.graphics.getHeight())
        	setyVel(getyVel() * -1);
        getSprite().setPosition(x, y);
    }
    public abstract void sonido();
    
    
    /*public void mostrarAsteroide(SpriteBatch batch) {
    	for (int i = 0; i < balls1.size(); i++) {
    	  	  //mostrarAsteroide();
    	  	    Ball2 b=balls1.get(i);
    	  	    b.draw(batch);
    	}
    }*/
    
    /*public void verificarColisionNave(Nave nave) {
    	for (int i = 0; i < balls1.size(); i++) {
    		Ball2 b=balls1.get(i);
    		if (nave.verificarColisionNave(b)) {
    			balls1.remove(i);
             	balls2.remove(i);
             	i--;
    		}
    	}
    }*/

}

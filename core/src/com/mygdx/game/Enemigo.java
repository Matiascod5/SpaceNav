package com.mygdx.game;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;


public abstract class Enemigo {
	private int x;
	private int y;
	private int xVel;
	private int yVel;
	private Sprite sprite;
	//private int cantEnemigos;
	//private  ArrayList<Enemigo> balls1 = new ArrayList<>();
	//private  ArrayList<Enemigo> balls2 = new ArrayList<>();
	
	public Enemigo(Sprite sprite, int xVel, int yVel) {
		Random r = new Random();
		this.sprite = sprite;
		//setSprite(sprite);
		this.x = r.nextInt((int)Gdx.graphics.getWidth());
		//setX(r.nextInt((int)Gdx.graphics.getWidth()));
		this.y = 50+r.nextInt((int)Gdx.graphics.getHeight()-50);
		//setY(50+r.nextInt((int)Gdx.graphics.getHeight()-50));
		this.xVel = xVel;
		this.yVel = yVel;
		
		int size = 20+r.nextInt(10);
		
		if (x-size < 0) this.x = x+size;
    	if (x+size > Gdx.graphics.getWidth())this.x = x-size;
         
        //validar que borde de esfera no quede fuera
    	if (y-size < 0) this.y = y+size;
    	if (y+size > Gdx.graphics.getHeight())this.y = y-size;
    	
        sprite.setPosition(x, y);
        this.setxVel(xVel);
        this.setyVel(yVel);
		
		/*
		setxVel(getxVel()+r.nextInt(4)); //OJO GETTERS
		setyVel(getyVel()+r.nextInt(4));
 	
        //validar que borde de esfera no quede fuera
    	if (getX()-size < 0) setX(getX()+size); // OJO LOS CAMBIOS
    	if (getX()+size > Gdx.graphics.getWidth())setX(getX()-size);
         
        //validar que borde de esfera no quede fuera
    	if (getY()-size < 0) setY(getY()+size); // OJO LOS CAMBIOS
    	if (getY()+size > Gdx.graphics.getHeight())setY(getY()+size);
    	
    	setSprite(sprite);
    	setPosition();
        //getSprite().setPosition(getX(), getY()); // OJO GETTERS
        
        //añadirEnemigo(this);
         * */
         
	}
	
	/*
	public void añadirEnemigo(Enemigo enemigo, ArrayList<Enemigo> balls1, ArrayList<Enemigo> balls2) {
	    for (int i = 0; i < cantEnemigos; i++) {
	        //Ball2 bb = new Ball2(r.nextInt((int)Gdx.graphics.getWidth()),	50+r.nextInt((int)Gdx.graphics.getHeight()-50),	20+r.nextInt(10), xVel+r.nextInt(4), yVel+r.nextInt(4), 
	  	            //new Texture(Gdx.files.internal("aGreyMedium4.png")));	   
	  	    balls1.add(enemigo);
	  	    balls2.add(enemigo);
	  	}
	}*/

	/*
	public void mostrarEnemigo(SpriteBatch batch){
		Sprite sprite = getSprite();
		sprite.draw(batch);
	}*/

/*
	public void movimiento() {
        x += getxVel();
        y += getyVel();

        if (x+getxVel() < 0 || x+getxVel()+sprite.getWidth() > Gdx.graphics.getWidth())
        	setxVel(getxVel() * -1);
        if (y+getyVel() < 0 || y+getyVel()+sprite.getHeight() > Gdx.graphics.getHeight())
        	setyVel(getyVel() * -1);
        sprite.setPosition(x, y);
    }*/
	
	/*
	public void mostrarEnemigo(SpriteBatch batch, ArrayList<Enemigo> balls1, ArrayList<Enemigo> balls2) {
    	for (int i = 0; i < balls1.size(); i++) {
    	  	  //mostrarAsteroide();
    	  	    Enemigo enemigo = balls1.get(i);
    	  	    Sprite b = enemigo.getSprite();
    	  	    b.draw(batch);
    	}
    }*/
	
	/*
	public void draw(SpriteBatch batch) {
    	sprite.draw(batch);
    }*/

	public abstract void movimiento();
	
	/*
	public void colisionesEnemigos(ArrayList<Enemigo> balls1, ArrayList<Enemigo> balls2) {
		for (int i=0;i<balls1.size();i++) {
		    Enemigo ball1 = balls1.get(i);   
		    for (int j=0;j<balls2.size();j++) {
		        Enemigo ball2 = balls2.get(j); 
		        if (i<j) {
		        	ball1.checkCollision(ball2);
		     
		        }
		    }
		} 

	}*/

	/*public void verificarColisionNave(Nave nave, ArrayList<Enemigo> balls1, ArrayList<Enemigo> balls2) {
    	for (int i = 0; i < balls1.size(); i++) {
    		Enemigo b=balls1.get(i);
    		if (nave.verificarColisionNave(b)) {
    			balls1.remove(i);
             	balls2.remove(i);
             	i--;
    		}
    	}
    }*/
	
	/*
	public void checkCollision(Enemigo b2) {
        if(sprite.getBoundingRectangle().overlaps(b2.sprite.getBoundingRectangle())){
        	// rebote
            if (getxVel() ==0) setxVel(getxVel() + b2.getxVel()/2);
            if (b2.getxVel() ==0) b2.setxVel(b2.getxVel() + getxVel()/2);
        	setxVel(- getxVel());
            b2.setxVel(-b2.getxVel());
            
            if (getyVel() ==0) setyVel(getyVel() + b2.getyVel()/2);
            if (b2.getyVel() ==0) b2.setyVel(b2.getyVel() + getyVel()/2);
            setyVel(- getyVel());
            b2.setyVel(- b2.getyVel()); 
        }
    }*/

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

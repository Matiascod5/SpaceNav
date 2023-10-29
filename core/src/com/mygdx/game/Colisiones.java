package com.mygdx.game;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.Enemigo;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Colisiones{
	private  ArrayList<Enemigo> balls1;
	private  ArrayList<Enemigo> balls2;
	private  ArrayList<Disparo> balas;
	private int cantEnemigos;

	public Colisiones(){
		this.balls1 = new ArrayList<>();
		this.balls2 = new ArrayList<>();
		this.balas = new ArrayList<>();
		this.cantEnemigos = 0;
	}

	public void añadirEnemigo(Enemigo enemigo) {
	    //for (int i = 0; i < cantEnemigos; i++) {
	        //Ball2 bb = new Ball2(r.nextInt((int)Gdx.graphics.getWidth()),	50+r.nextInt((int)Gdx.graphics.getHeight()-50),	20+r.nextInt(10), xVel+r.nextInt(4), yVel+r.nextInt(4), 
	  	            //new Texture(Gdx.files.internal("aGreyMedium4.png")));	   
	  	    balls1.add(enemigo);
	  	    balls2.add(enemigo);
			this.cantEnemigos++;
	  	//}
	}

	public void mostrarEnemigo(SpriteBatch batch){
		for (int i = 0 ; i < balls1.size() ; i++){
			Enemigo enemigo = balls1.get(i);
			Sprite sprite = enemigo.getSprite();
			sprite.draw(batch);
		}
		//Sprite sprite = getSprite();
		//sprite.draw(batch);
	}
    
    public void colisionesEnemigos() {
		for (int i=0;i<balls1.size();i++) {
		    Enemigo ball1 = balls1.get(i);   
		    for (int j=0;j<balls2.size();j++) {
		        Enemigo ball2 = balls2.get(j); 
		        if (i<j) {
		        	ball1.checkCollision(ball2);
		        }
		    }
		} 

	}

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
	
	public void checkCollision(Enemigo b1, Enemigo b2) {
        if(b1.getSprite().getBoundingRectangle().overlaps(b2.getSprite().getBoundingRectangle())){
        	// rebote
            if (b1.getxVel() ==0) b1.setxVel(b1.getxVel() + b2.getxVel()/2);
            if (b2.getxVel() ==0) b2.setxVel(b2.getxVel() + b1.getxVel()/2);
        	b1.setxVel(- b1.getxVel());
            b2.setxVel(-b2.getxVel());
            
            if (b1.getyVel() ==0) b1.setyVel(b1.getyVel() + b2.getyVel()/2);
            if (b2.getyVel() ==0) b2.setyVel(b2.getyVel() + b1.getyVel()/2);
            b1.setyVel(- b1.getyVel());
            b2.setyVel(- b2.getyVel()); 
        }
    }

	public void actualizarEnemigos(SpriteBatch batch){
		for (int i = 0 ; i < balls1.size() ; i++){
			Enemigo b = balls1.get(i);
			b.movimiento();
			//b.movimiento(b.getSprite(), b.getX(), b.getY());
			//Sprite a = b.getSprite();
			//a.draw(batch);
			mostrarEnemigo(batch);
		 }
	}

	public boolean verificarColisionEnemigoBala(int score, Sound explosionSound, SpriteBatch batch){
		for (int i = 0; i < balas.size(); i++){
			Disparo b = balas.get(i);
			b.movimiento();
			b.verificarColisionEnemigo(b, balls1, balls2, score, explosionSound, batch);
		    if (b.verificarDestruccion()) {
		        balas.remove(b);
		        i--; //para no saltarse 1 tras eliminar del arraylist
				this.cantEnemigos--;
				if (cantEnemigos <= 0) return true;
		    }
		}

		return false;
	}

	public void actualizarBalas(SpriteBatch batch){
		for (int i = 0 ; i < balas.size() ; i++){
			Disparo b = balas.get(i);
			b.movimiento();
			b.mostrar(batch);
		}
	}

	public void agregarBala(Disparo bb) {
    	this.balas.add(bb);
    }

	public boolean colisionesNaveAsteroide(Nave nave) {
		for(int i = 0 ; i < balls1.size() ; i++){
			Enemigo b = balls1.get(i);
    		if(!nave.getHerido() && b.getArea().overlaps(nave.getSprite().getBoundingRectangle())){
        		// rebote
				float xVel = nave.getxVel();
				float yVel = nave.getyVel();
				int vidas = nave.getVidas();
            	if (xVel ==0) nave.setxVel(xVel += b.getxVel()/2);
            	if (b.getxVel() ==0) b.setxVel(b.getxVel() + (int)xVel/2);
            	xVel = - xVel;
            	b.setxVel(-b.getxVel());
            
            	if (yVel ==0) yVel += b.getyVel()/2;
            	if (b.getyVel() ==0) b.setyVel(b.getyVel() + (int)yVel/2);
            	yVel = - yVel;
            	b.setyVel(- b.getyVel());
            	// despegar sprites
           		int cont = 0;
            	while (b.getArea().overlaps(nave.getSprite().getBoundingRectangle()) && cont<xVel) {
            		nave.getSprite().setX(nave.getSprite().getX()+Math.signum(xVel));
            	} 
        		//actualizar vidas y herir
            	nave.setVidas(vidas--);;
            	nave.setHerido(true);
  		    	nave.setTiempoHerido(nave.getTiempoHeridoMax());
  		    	nave.getSonidoHerido().play();
				balls1.remove(i);
	        	balls2.remove(i);
	        	i--;
				this.cantEnemigos--;
            	if (nave.getVidas()<=0) return true;

			}
    	}
    return false;
	}
	
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
    }
	
	public Rectangle getArea() {
    	return sprite.getBoundingRectangle();
    }*/

}
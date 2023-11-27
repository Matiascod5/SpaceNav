package com.mygdx.game;
//import com.badlogic.gdx.math.Rectangle;
//import com.mygdx.game.Enemigo;

import java.util.ArrayList;
//import java.util.Random;

//import com.badlogic.gdx.Gdx;
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
		for (int i = 0 ; i<balls1.size() ; i++) {
		    Enemigo ball1 = balls1.get(i);   
		    for (int j=0;j<balls2.size();j++) {
		        Enemigo ball2 = balls2.get(j); 
		        if (i<j) {
		        	checkCollision(ball1, ball2);
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

	public boolean verificarColisionEnemigoBala(PantallaJuego game, int score, Sound explosionSound, SpriteBatch batch){
		for (int i = 0; i < balas.size(); i++){
			Disparo b = balas.get(i);
			b.movimiento();
			//verificarColisionEnemigo(b, balls1, balls2, score, explosionSound, batch);
			for (int j = 0; j < balls1.size(); j++) {    
		    if (verificarColision(balls1.get(j), b)) {          
		        explosionSound.play();
		        balls1.remove(j);
		        balls2.remove(j);
		        j--;
				this.cantEnemigos--;
		        //int k = game.getScore() + 10;
				game.sumarScore(10);
		    }   	  
		}
		b.mostrar(batch);
		    if (b.verificarDestruccion()) {
		        balas.remove(b);
		        i--; //para no saltarse 1 tras eliminar del arraylist
				//this.cantEnemigos--;
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
				vidas--;
            	nave.setVidas(vidas);;
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

	public void verificarColisionEnemigo(Disparo b, ArrayList<Enemigo> balls1, ArrayList<Enemigo> balls2, int score, Sound explosionSound, SpriteBatch batch){
		    //b.movimiento();
		for (int j = 0; j < balls1.size(); j++) {    
		    if (verificarColision(balls1.get(j), b)) {          
		        explosionSound.play();
		        balls1.remove(j);
		        balls2.remove(j);
		        j--;
				this.cantEnemigos--;
		        score +=10;
		    }   	  
		}
		b.mostrar(batch);
	}
	    
	public boolean verificarColision(Enemigo b2, Disparo b1) {
	    if(b1.getSprite().getBoundingRectangle().overlaps(b2.getArea())){
	     	// Se destruyen ambos
	        b1.setDestroyed(true);
	        return true;
	    }
	    return false;
    }

	public boolean verificarColisionNaveItem(Item b, Nave a) {
    	if(!a.getHerido() && b.getArea().overlaps(a.getSprite().getBoundingRectangle())){
        	// rebote
			//this = b.efectoEspecial(this);
			b.dispose();
            // despegar sprites
           int cont = 0;
            while (b.getArea().overlaps(a.getSprite().getBoundingRectangle()) && cont<a.getxVel()) {
               a.getSprite().setX(a.getSprite().getX()+Math.signum(a.getxVel()));
            } 
        	//actualizar vidas y herir
  		    //tiempoHerido=tiempoHeridoMax;
			
			a.setTiempoHerido(a.getTiempoHeridoMax());
  		    b.getSonidoItem().play();
			b.efectoEspecial(a);
			//b.setSpawn();
			return true;
        }
        return false;
    }

	public int getcantEnemigos(){
		return this.cantEnemigos;
	}

	public void setCantEnemigos(int cantEnemigos){
		this.cantEnemigos = cantEnemigos;
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
package com.mygdx.game;

import java.util.ArrayList;
//import com.mygdx.game.Disparo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

public class Nave {
	
	private TipoNave tipo;
	private int vidas;
	private float xVel;
	private float yVel;
	private int velExtraX;
	private int velExtraY;
	private boolean herido;
	private int tiempoHeridoMax;
	private Sprite spr;
	private Disparo disparo;
	private Sound sonidoHerido;
	private int tiempoHerido;
	private boolean destruido;
	//private int tipoDisparo;
	//public  ArrayList<Disparo> balas = new ArrayList<>();

	public Nave(int vidas, float xVel, float yVel, Sprite tx, Sound sonidoHerido, int tiempoHeridoMax, Disparo disparo, TipoNave tipo){
		this.vidas = vidas;
		this.xVel = xVel;
		this.yVel = yVel;
		this.velExtraX = 0;
		this.velExtraY = 0;
		this.herido = false;
		this.tiempoHeridoMax = tiempoHeridoMax; 
		this.spr = tx;
		//spr.setSize(2.0f,2.0f);
		this.sonidoHerido = sonidoHerido;
		this.destruido = false;
		this.disparo = disparo;
		this.tipo = tipo;
		setSpritePosition(Gdx.graphics.getWidth()/2-50,30);
		setSpriteBounds(Gdx.graphics.getWidth()/2-50,30,45,45);
	}
	
    public void movimiento(SpriteBatch batch, PantallaJuego juego) {
    	float x =  spr.getX();
        float y =  spr.getY();
        if (!herido) {
	        // que se mueva con teclado
	        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)){
				xVel = -3 - velExtraX; //yVel = 0;
			} 
	        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
				xVel = 3 + velExtraX; //yVel = 0;
			} 
        	if (Gdx.input.isKeyPressed(Input.Keys.DOWN)){
				yVel = -3 - velExtraY; //xVel = 0;
			} 
	        if (Gdx.input.isKeyPressed(Input.Keys.UP)){
				yVel = 3 + velExtraY; //xVel = 0;
			}
			if (!Gdx.input.isKeyPressed(Input.Keys.LEFT) && !Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
				xVel = 0;
			}
			if (!Gdx.input.isKeyPressed(Input.Keys.UP) && !Gdx.input.isKeyPressed(Input.Keys.DOWN)){
				yVel = 0;
			}
        	
	        
	        // que se mantenga dentro de los bordes de la ventana
	        if (x+xVel < 0 || x+xVel+spr.getWidth() > Gdx.graphics.getWidth())
	        	xVel*=-1;
	        if (y+yVel < 0 || y+yVel+spr.getHeight() > Gdx.graphics.getHeight())
	        	yVel*=-1;
	        
	        spr.setPosition(x+xVel, y+yVel);   
         
 		    spr.draw(batch);
        } else {
           spr.setX(spr.getX()+MathUtils.random(-2,2));
 		   spr.draw(batch); 
 		   spr.setX(x);
 		   tiempoHerido--;
 		   if (tiempoHerido<=0) herido = false;
 		 }
        // disparo
        /*
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {         
          Bullet  bala = new Bullet(spr.getX()+spr.getWidth()/2-5,spr.getY()+ spr.getHeight()-5,0,3,texturaDisparo);
	      juego.agregarBala(bala);
	      getSonidoDisparo().play();
        }*/
    }

	public void movimiento() {
	    getSprite().setPosition(getSprite().getX()+getxVel(), getSprite().getY()+getyVel());
	    if (getSprite().getX() < 0 || getSprite().getX()+getSprite().getWidth() > Gdx.graphics.getWidth()) {
	        setDestruido(true);
	    }
	    
		if (getSprite().getY() < 0 || getSprite().getY()+getSprite().getHeight() > Gdx.graphics.getHeight()) {
	        setDestruido(true);
	    }  
	}
    
    /*public void disparar(SpriteBatch batch, Colisiones Colisiones) {
		//x = sprite.getX() + sprite.getWidth()/2-5;
		//y = sprite.getY()+ sprite.getHeight()-5;
    	Disparo bala = new DisparoStarWars(spr.getX() + spr.getWidth()/2-5, spr.getY()+ spr.getHeight()-5);
	    Colisiones.agregarBala(bala);
		bala.mostrar(batch);
	    bala.getSonidoDisparo().play();
	   
    }*/

	/*public void disparar(SpriteBatch batch, Colisiones Colisiones) {
		//x = sprite.getX() + sprite.getWidth()/2-5;
		//y = sprite.getY()+ sprite.getHeight()-5;
		//float x, float y, int xSpeed, int ySpeed, Texture tx, Sound sonidoDisparo;
		//disparo.setX(getSprite().getX() + getSprite().getWidth()/2-5);
		//disparo.setY(getSprite().getY()+ getSprite().getHeight()-5);

		Disparo b = disparo.formatearDisparo(getSprite().getX() + getSprite().getWidth()/2-5, getSprite().getY()+ getSprite().getHeight()-5);


		//disparo.getSprite().setPosition(getSprite().getX() + getSprite().getWidth()/2-5, getSprite().getY()+ getSprite().getHeight()-5);
		//disparo.setDestroyed(false);
		
        //Disparo bala = new Disparo(getSprite().getX() + getSprite().getWidth()/2-5, getSprite().getY()+ getSprite().getHeight()-5, disparo.getxVel(), disparo.getyVel(), disparo.getSprite(), disparo.getSonidoDisparo());
	    Colisiones.agregarBala(b);
		b.mostrar(batch);
         
	    disparo.getSonidoDisparo().play();
	}*/

	//IMPORTANTEEEE public abstract void disparar(SpriteBatch batch, Colisiones colisiones);

	public void disparar(SpriteBatch batch, Colisiones Colisiones, Builder builder) {
		//x = sprite.getX() + sprite.getWidth()/2-5;
		//y = sprite.getY()+ sprite.getHeight()-5;
		Disparo bala = disparo.crearDisparo(getSprite().getX() + getSprite().getWidth()/2-5, getSprite().getY()+ getSprite().getHeight()-5);
        //Disparo bala = new Disparo(getSprite().getX() + getSprite().getWidth()/2-5, getSprite().getY()+ getSprite().getHeight()-5, this.xVel, this.yVel, this.spr, this.disparo);
	    //cambiar forma de disparar.
		Colisiones.agregarBala(bala);
		bala.mostrar(batch);
	    bala.getSonidoDisparo().play();
		//aqui esta el problema de la bala!!!!!!!!!!!!!!!
	   
    }
    
	
    public void mostrarDisparo(SpriteBatch batch, ArrayList<Disparo> balas) {
    	for (Disparo b : balas) {       
    		b.mostrar(batch);
  	    }
    }

	public void añadirVelocidad(int vel){
		this.velExtraX += vel;
		this.velExtraY += vel;
	}
    
	/*
    public boolean verificarColisionNave(Enemigo b) {
    	if(!herido && b.getArea().overlaps(spr.getBoundingRectangle())){
        	// rebote
            if (xVel ==0) xVel += b.getxVel()/2;
            if (b.getxVel() ==0) b.setxVel(b.getxVel() + (int)xVel/2);
            xVel = - xVel;
            b.setxVel(-b.getxVel());
            
            if (yVel ==0) yVel += b.getyVel()/2;
            if (b.getyVel() ==0) b.setyVel(b.getyVel() + (int)yVel/2);
            yVel = - yVel;
            b.setyVel(- b.getyVel());
            // despegar sprites
           int cont = 0;
            while (b.getArea().overlaps(spr.getBoundingRectangle()) && cont<xVel) {
               spr.setX(spr.getX()+Math.signum(xVel));
            } 
        	//actualizar vidas y herir
            vidas--;
            herido = true;
  		    tiempoHerido=tiempoHeridoMax;
  		    sonidoHerido.play();
            if (vidas<=0) 
            return true;
        }
        return false;
    }*/

	/*public boolean verificarColisionNaveItem(Item b) {
    	if(!herido && b.getArea().overlaps(spr.getBoundingRectangle())){
        	// rebote
			//this = b.efectoEspecial(this);
			b.dispose();
            // despegar sprites
           int cont = 0;
            while (b.getArea().overlaps(spr.getBoundingRectangle()) && cont<xVel) {
               spr.setX(spr.getX()+Math.signum(xVel));
            } 
        	//actualizar vidas y herir
            vidas--;
            herido = true;
  		    tiempoHerido=tiempoHeridoMax;
  		    sonidoHerido.play();
            if (vidas<=0) 
            return true;
        }
        return false;
    }*/
    
    //IMPORTANTEE public abstract void efectoEspecial();
    
	/*
    public boolean agregarBala(Disparo bb) {
    	return balas.add(bb);
    }*/
    
    public void setVidas(int vidas) {
		this.vidas = vidas;
	}
	
	public void setxVel(float xVel) {
		this.xVel = xVel;
	}
	
	public void setyVel(float yVel) {
		this.yVel = yVel;
	}

	public void setVelExtraX(int velExtraX) {
		this.velExtraX = velExtraX;
	}
	
	public void setVelExtraY(int velExtraY) {
		this.velExtraY = velExtraY;
	}
	
	public void setHerido(boolean herido) {
		this.herido = herido;
	}

	public void setTiempoHerido(int tiempoHerido) {
		this.tiempoHerido = tiempoHerido;
	}
	
	public void setTiempoHeridoMax(int tiempoHeridoMax) {
		this.tiempoHeridoMax = tiempoHeridoMax;
	}
	
	public void setSpriteTexture(Texture tx) {
		this.spr = new Sprite(tx);
	}
	
	public void setSpritePosition(int x, int y){
		this.spr.setPosition(x,y);
	}
	
	public void setSpriteBounds(int a, int b, int c, int d){
		this.spr.setBounds(a,b,c,d);
	}
	
	public void setSonidoHerido(Sound sonidoHerido){
		this.sonidoHerido = sonidoHerido;
	}
	
	public void setDestruido(boolean destruido){
		this.destruido = destruido;
	}

	public void setDisparo(Disparo disparo){
		this.disparo = disparo;
	}

	public void setTipoNave(TipoNave tipo){
		this.tipo = tipo;
	}
	
	public int getVidas() {
		return this.vidas;
	}
	
	public float getxVel() {
		return this.xVel;
	}
	
	public float getyVel() {
		return this.yVel;
	}

	public float getVelExtraX() {
		return this.velExtraX;
	}
	
	public float getVelExtraY() {
		return this.velExtraY;
	}
	
	public boolean getHerido() {
		return this.herido;
	}

	public int getTiempoHerido() {
		return this.tiempoHerido;
	}
	
	public int getTiempoHeridoMax() {
		return this.tiempoHeridoMax;
	}
	
	public Sprite getSprite() {
		return this.spr;
	}
	
    public int getX() {
    	return (int) spr.getX();
    }
    
    public int getY() {
    	return (int) spr.getY();
    }
    
    public int getWidth() {
    	return (int) spr.getWidth();
    }
    
    public int getHeight() {
    	return (int) spr.getHeight();
    }
	
	public Sound getSonidoHerido(){
		return this.sonidoHerido;
	}
	
	public boolean getDestruido(){
		return this.destruido;
	}

	public Disparo getDisparo(){
		return this.disparo;
	}

	public TipoNave getTipoNave(){
		return this.tipo;
	}
	
}

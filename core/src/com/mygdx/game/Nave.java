package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

public abstract class Nave {
	private int vidas;
	private float xVel;
	private float yVel;
	private Sprite spr;
	private Sound sonidoHerido;
	private Sound sonidoDisparo;
	private Texture texturaDisparo;
	private boolean herido;
	private int tiempoHeridoMax;
	private int tiempoHerido;
	
	public void setVidas(int vidas) {
		this.vidas = vidas;
	}
	
	public void setxVel(float xVel) {
		this.xVel = xVel;
	}
	
	public void setyVel(float yVel) {
		this.yVel = yVel;
	}
	
	public void setHerido(boolean herido) {
		this.herido = herido;
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
	
	public void setSonidoDisparo(Sound sonidoDisparo){
		this.sonidoDisparo = sonidoDisparo;
	}
	
	public void setTexturaDisparo(Texture texturaDisparo){
		this.texturaDisparo = texturaDisparo;
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
	
	public boolean getHerido() {
		return this.herido;
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
	
	public Sound getSonidoHerido(){
		return this.sonidoHerido;
	}
	
	public Sound getSonidoDisparo(){
		return this.sonidoDisparo;
	}
	
	/*public void getTexturaDisparo(Texture texturaDisparo){
		this.texturaDisparo = texturaDisparo;
	}*/
    
    public void movimiento(SpriteBatch batch, PantallaJuego juego) {
    	float x =  spr.getX();
        float y =  spr.getY();
        if (!herido) {
	        // que se mueva con teclado
	        if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) xVel--;
	        if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) xVel++;
        	if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) yVel--;     
	        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) yVel++;
        	
	        
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
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {         
          Bullet  bala = new Bullet(spr.getX()+spr.getWidth()/2-5,spr.getY()+ spr.getHeight()-5,0,3,txBala);
	      juego.agregarBala(bala);
	      getSonidoDisparo().play();
        }
    }
    
    public boolean verificarColisionNave() {
    	if(!herido && b.getArea().overlaps(spr.getBoundingRectangle())){
        	// rebote
            if (xVel ==0) xVel += b.getXSpeed()/2;
            if (b.getXSpeed() ==0) b.setXSpeed(b.getXSpeed() + (int)xVel/2);
            xVel = - xVel;
            b.setXSpeed(-b.getXSpeed());
            
            if (yVel ==0) yVel += b.getySpeed()/2;
            if (b.getySpeed() ==0) b.setySpeed(b.getySpeed() + (int)yVel/2);
            yVel = - yVel;
            b.setySpeed(- b.getySpeed());
            // despegar sprites
      /*      int cont = 0;
            while (b.getArea().overlaps(spr.getBoundingRectangle()) && cont<xVel) {
               spr.setX(spr.getX()+Math.signum(xVel));
            }   */
        	//actualizar vidas y herir
            vidas--;
            herido = true;
  		    tiempoHerido=tiempoHeridoMax;
  		    sonidoHerido.play();
            if (vidas<=0) 
          	    destruida = true; 
            return true;
        }
        return false;
    }
}

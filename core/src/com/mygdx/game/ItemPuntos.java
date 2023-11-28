package com.mygdx.game;

import java.util.Random;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class ItemPuntos implements Item{
    private int contador;
    private int puntos;
    private int x;
    private int y;
    private Sprite spr;
    private Sound sonidoItem;
    private static final ItemPuntos instance = new ItemPuntos();

    public ItemPuntos(){
        this.contador = 200;
        this.puntos = 100;
        setSpawn();
        this.spr= new Sprite(new Texture(Gdx.files.internal("100.png")));
        this.sonidoItem= Gdx.audio.newSound(Gdx.files.internal("Points.mp3"));
        spr.setPosition(x,y);
    }

     public void setSpawn(){
        Random r = new Random();
        this.x = r.nextInt((int)Gdx.graphics.getWidth());
        this.y = r.nextInt((int)Gdx.graphics.getHeight());

        int size = 20+r.nextInt(10);

        //validar que borde de esfera no quede fuera
    	if (x-size < 0) this.x = x+size;
    	if (x+size > Gdx.graphics.getWidth())this.x = x-size;
         
        //validar que borde de esfera no quede fuera
    	if (y-size < 0) this.y = y+size;
    	if (y+size > Gdx.graphics.getHeight())this.y = y-size;

    }
	 
	public boolean mover(){
        spr.setPosition(x, y);
        actualizarContador(contador);

        if (this.contador == 0){
            this.contador = 200;
            dispose();
            return false;
        } 
        return true;
    }

    public void draw(SpriteBatch batch){
    	spr.draw(batch);
    }  

    public void setPuntos(int puntos){
        this.puntos = puntos;
    }
    
    public void setX(int x){
        this.x = x;
    }

    public void setY(int y){
        this.y = y;
    }

    public void setSprite(Sprite spr){
        this.spr = spr;
    }

    public void setSonidoItem(Sound sonidoItem){
        this.sonidoItem = sonidoItem;
    }

    public int getPuntos(){
        return this.puntos;
    }

    public Rectangle getArea(){
    	return spr.getBoundingRectangle();
    }
    
	public Sound getSonidoItem(){
        return this.sonidoItem;
    }

    public int getY(){
        return this.y;
    }

    public int getX(){
        return this.x;
    } 

    public static ItemPuntos getInstance(){
        return instance;
    }

    public void actualizarContador(float delta){
        this.contador -= Gdx.graphics.getDeltaTime();
    }

    @Override
    public Object efectoEspecial(Nave nave, PantallaJuego game){
        ((PantallaJuego)game).sumarScore(puntos);
        //nave.añadirVelocidad(3);
        //nave.setVelExtraX(4);
        //nave.setVelExtraY(4);
        //nave.setDisparo(HeavyD);
        //nave.getDisparo().efectoEspecial;
        return game;
    }

    /** Called when this screen is no longer the current screen for a {@link Game}. */
	public void hide (){
    }

	public void dispose (){
    }
    
}

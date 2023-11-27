package com.mygdx.game;
import java.util.Random;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Heavymachingan implements Item{
    
    private int x;
    private int y;
    private int id;
    private Sprite spr;
    private Sound sonidoItem;

    private static final Heavymachingan instance = new Heavymachingan();
    
    private Heavymachingan(){
        
        setSpawn();
        this.spr= new Sprite(new Texture(Gdx.files.internal("PowerUp2.png")));
        this.sonidoItem= Gdx.audio.newSound(Gdx.files.internal("heavy-machine-gun.mp3"));
        spr.setPosition(x,y);
    }

	public Rectangle getArea() {
    	return spr.getBoundingRectangle();
    }

    public void show (){

    }
	 
	public boolean mover(){
        //x += getxVel();
        y -= 2;

        spr.setPosition(x, y);

        if (y <= 20){
            dispose();
            //setSpawn();
            return false;
        } 
        return true;
    }

    public void sonido(){
        
    }
    
	public void setSpawn(){
        Random r = new Random();
        this.x = r.nextInt((int)Gdx.graphics.getWidth());
        this.y = 750;

        int size = 20+r.nextInt(10);

        //validar que borde de esfera no quede fuera
    	if (x-size < 0) this.x = x+size;
    	if (x+size > Gdx.graphics.getWidth())this.x = x-size;
         
        //validar que borde de esfera no quede fuera
    	if (y-size < 0) this.y = y+size;
    	if (y+size > Gdx.graphics.getHeight())this.y = y-size;

        //spr.setPosition(x,y);
    }
    
	public Sound getSonidoItem(){
        return this.sonidoItem;
    }

    public void setSonidoItem(Sound sonidoItem){
        this.sonidoItem = sonidoItem;
    }

    public int getY(){
        return this.y;
    }

    public int getX(){
        return this.x;
    }

    public void draw(SpriteBatch batch) {
    	spr.draw(batch);
    }    

	/** Called when this screen is no longer the current screen for a {@link Game}. */
	public void hide (){

    }

	public void dispose (){

    }

    public void setId(){
        this.id = 1;
    }

    public int getId(){
        return this.id;
    }

    public int getxVel() {
		return xSpeed;
	}

	public int getyVel() {
		return ySpeed;
	}

    public static Heavymachingan getInstance(){
        return instance;
    }

    @Override
    public Nave efectoEspecial(Nave nave) {
        nave.setxVel(30);
        nave.setyVel(30);
        //nave.setDisparo(HeavyD);
        //nave.getDisparo().efectoEspecial;
        return nave;
    }
}
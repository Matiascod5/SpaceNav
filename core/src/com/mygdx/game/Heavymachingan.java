package com.mygdx.game;
import java.util.Random;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Heavymachingan implements Item{
    
    private int x;
    private int y;
    private int id;
    private Sprite spr;
    
    public Heavymachingan(){
        Random r = new Random();
        this.spr= new Sprite(new Texture(Gdx.files.internal("HeavyM.png")));

        int size = 20+r.nextInt(10);

        //validar que borde de esfera no quede fuera
    	if (x-size < 0) this.x = x+size;
    	if (x+size > Gdx.graphics.getWidth())this.x = x-size;
         
        //validar que borde de esfera no quede fuera
    	if (y-size < 0) this.y = y+size;
    	if (y+size > Gdx.graphics.getHeight())this.y = y-size;
        spr.setPosition(x, y);
    }

	public Rectangle getArea() {
    	return spr.getBoundingRectangle();
    }

    public void show (){

    }
	 
	public void mover(){
        x += getxVel();
        y += getyVel();

        spr.setPosition(x, y);
    }
	
	public void sonido(){
        
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

    @Override
    public Nave efectoEspecial(Nave nave) {
        throw new UnsupportedOperationException("Unimplemented method 'efectoEspecial'");
    }
}
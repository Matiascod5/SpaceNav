package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * <p>
 * Represents one of many application screens, such as a main menu, a settings menu, the game screen and so on.
 * </p>
 * <p>
 * Note that {@link #dispose()} is not called automatically.
 * </p>
 * @see Game */
public interface Item {

    public int xSpeed = 0;
    public int ySpeed = 2;

	public void show ();

	public void draw(SpriteBatch batch);
	
	public boolean mover();
	
	public void sonido();

	public Rectangle getArea(); 
	
	public Nave efectoEspecial(Nave nave);

	public Sound getSonidoItem();
   
    public void setSonidoItem(Sound sonidoItem);

	public int getY();

	public int getX();
   
	public int getId();

	public int getxVel();

	public int getyVel();

	/** Called when this screen is no longer the current screen for a {@link Game}. */
	public void hide ();

	/** Called when this screen should release all resources. */
	public void dispose ();
	
}

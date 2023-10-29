package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.math.Rectangle;

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
	
	public void mover();
	
	public void sonido();

	public Rectangle getArea(); 
	
	public Nave efectoEspecial(Nave nave);

	public int getId();

	public int getxVel();

	public int getyVel();

	/** Called when this screen is no longer the current screen for a {@link Game}. */
	public void hide ();

	/** Called when this screen should release all resources. */
	public void dispose ();
	
}

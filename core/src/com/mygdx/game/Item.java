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

	public void setSpawn();

	public boolean mover();

	public void draw(SpriteBatch batch);
    
    public void setX(int x);

    public void setY(int y);

    public void setSprite(Sprite spr);

    public void setSonidoItem(Sound sonidoItem);

    public Rectangle getArea();
    
	public Sound getSonidoItem();

    public int getY();

    public int getX();

    public Object efectoEspecial(Nave nave, PantallaJuego game);

    /** Called when this screen is no longer the current screen for a {@link Game}. */
	public void hide ();

	public void dispose ();
	
}

package com.mygdx.game;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface Menu {
    void mostrar(SpriteBatch spriteBatch, Screen game, SpaceNavigation juego, Nave nave);
    void setFondoSprite(Texture fondoSprite);
}


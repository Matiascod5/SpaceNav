package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface Menu {
    void mostrar(SpriteBatch spriteBatch);
    String seleccionarOpcion();
    void setFondoSprite(Texture fondoSprite);
}
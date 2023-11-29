package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;

public interface MenuBuilder {
    void construirOpciones(String[] opciones);
    void construirFondoSprite(Texture fondoSprite);
    Menu construirMenu();
}
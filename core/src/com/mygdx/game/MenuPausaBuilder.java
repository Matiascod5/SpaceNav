package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;

public class MenuPausaBuilder implements MenuBuilder {
    private String[] opciones;
    private Texture fondoSprite;

    @Override
    public void construirOpciones(String[] opciones) {
        this.opciones = opciones;
    }

    @Override
    public void construirFondoSprite(Texture fondoSprite) {
        this.fondoSprite = fondoSprite;
    }

    @Override
    public Menu construirMenu() {
        MenuPausa menuPausa = new MenuPausa(opciones);
        menuPausa.setFondoSprite(fondoSprite);
        return menuPausa;
    }
}
package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MenuNaves implements Menu {
    private String[] opciones;
    private Texture fondoSprite;

    public MenuNaves(String[] opciones) {
        this.opciones = opciones;
    }

    @Override
    public void mostrar(SpriteBatch spriteBatch) {
        // Lógica para renderizar el fondoSprite y las opciones sobre él
    }

    @Override
    public String seleccionarOpcion() {
        // Lógica para recibir la selección del jugador
        // Aquí simplemente se retorna un valor de ejemplo
        return "Opción seleccionada del Menú de Naves";
    }

    @Override
    public void setFondoSprite(Texture fondoSprite) {
        this.fondoSprite = fondoSprite;
    }
}
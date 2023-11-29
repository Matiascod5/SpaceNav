package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MenuPausa implements Menu {
    private String[] opciones;
    private Texture fondoSprite;

    public MenuPausa(String[] opciones) {
        this.opciones = opciones;
    }

    @Override
    public void mostrar(SpriteBatch spriteBatch) {
        
        // Lógica para renderizar el fondoSprite y las opciones sobre él
    }

    @Override
    public String seleccionarOpcion() {
        // Lógica para recibir la selección del jugador
        // Puedes usar la entrada del teclado, por ejemplo
        // Aquí simplemente se retorna un valor de ejemplo
        return "Opción seleccionada del Menú de Pausa";
    }

    @Override
    public void setFondoSprite(Texture fondoSprite) {
        this.fondoSprite = fondoSprite;
    }
}
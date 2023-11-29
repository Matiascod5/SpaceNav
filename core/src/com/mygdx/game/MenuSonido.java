package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MenuSonido implements Menu {
    private String[] opciones;
    private Texture fondoSprite;

    public MenuSonido(String[] opciones) {
        this.opciones = opciones;
    }

    @Override
    public void mostrar(SpriteBatch batch) {
        System.out.println("=== Menú de Sonido ===");
        for (int i = 0; i < opciones.length; i++) {
            System.out.println((i + 1) + ". " + opciones[i]);
        }
    }

    @Override
    public String seleccionarOpcion() {
        // Lógica para recibir la selección del jugador
        // Puedes usar la entrada del teclado, por ejemplo
        // Aquí simplemente se retorna un valor de ejemplo
        return "Opción seleccionada del Menú de Sonido";
    }

        @Override
    public void setFondoSprite(Texture fondoSprite) {
        this.fondoSprite = fondoSprite;
    }
}

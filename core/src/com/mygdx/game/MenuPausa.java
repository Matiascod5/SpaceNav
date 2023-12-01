package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MenuPausa implements Menu {
    private String[] opciones;
    private Texture fondoSprite;
    private BitmapFont font = new BitmapFont();
    private GlyphLayout layout = new GlyphLayout();
    private int opcionSeleccionada = 0;


    public MenuPausa(String[] opciones) {
        this.opciones = opciones;
    }

    @Override
    public void mostrar(SpriteBatch spriteBatch) {
        // Dibujar el fondo
        spriteBatch.draw(fondoSprite, (Gdx.graphics.getWidth() - fondoSprite.getWidth()) / 2, (Gdx.graphics.getHeight() - fondoSprite.getHeight()) / 2);

        // Dibujar el título "Pausa"
        font.getData().setScale(2f);
        layout.setText(font, "Pausa");
        font.draw(spriteBatch, "Pausa", (Gdx.graphics.getWidth() - layout.width) / 2, (Gdx.graphics.getHeight() + layout.height) / 2);

        // Dibujar las opciones
        float yOffset = (Gdx.graphics.getHeight() - fondoSprite.getHeight()) / 2 - layout.height * 2; // Ajustar la posición vertical según sea necesario
        for (String opcion : opciones) {
            layout.setText(font, opcion);
            font.draw(spriteBatch, opcion, (Gdx.graphics.getWidth() - layout.width) / 2, yOffset);
            yOffset -= layout.height;
        }
        // Resaltar la opción seleccionada
        float resaltadoY = (Gdx.graphics.getHeight() - fondoSprite.getHeight()) / 2 - layout.height * 2 - layout.height * opcionSeleccionada;
        font.setColor(Color.YELLOW); // Color de resaltado
        font.draw(spriteBatch, opciones[opcionSeleccionada], (Gdx.graphics.getWidth() - layout.width) / 2, resaltadoY);
        font.setColor(Color.WHITE);
    }

    @Override
    public String seleccionarOpcion() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            opcionSeleccionada = Math.max(0, opcionSeleccionada - 1);
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
            opcionSeleccionada = Math.min(opciones.length - 1, opcionSeleccionada + 1);
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            // Realizar la acción correspondiente a la opción seleccionada
            return ejecutarAccion();
        }

        return null;
    }

    private String ejecutarAccion() {
        switch (opcionSeleccionada) {
            case 0:
                // Resumir juego
                juegoPausado = false;
                return "Resumir juego";
            case 1:
                // Acción para la segunda opción
                return "Reiniciar juego";
            case 2:
                // Acción para la tercera opción
                return "Salir al menú principal";
            default:
                return null;
        }
    }

    @Override
    public void setFondoSprite(Texture fondoSprite) {
        this.fondoSprite = fondoSprite;
    }
}
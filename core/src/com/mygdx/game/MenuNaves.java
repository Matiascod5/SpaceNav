package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MenuNaves implements Menu {
    private String[] opciones;
    private Texture fondoSprite;
    private BitmapFont font = new BitmapFont();
    private GlyphLayout layout = new GlyphLayout();
    private int opcionSeleccionada = 0;
    private DirectorNave director = new DirectorNave();
	private NaveBuilder builder = new NaveBuilder();

    public MenuNaves(String[] opciones) {
        this.opciones = opciones;
        this.opcionSeleccionada = 0; // Inicializar la opción seleccionada
    }

    public void mostrar(SpriteBatch spriteBatch, Screen game, SpaceNavigation juego, Nave nave) {
        // Dibujar el fondo
        spriteBatch.begin();
        spriteBatch.draw(fondoSprite, (Gdx.graphics.getWidth() - fondoSprite.getWidth()) / 2, (Gdx.graphics.getHeight() - fondoSprite.getHeight()) / 2);

        // Dibujar el título "Naves"
        font.getData().setScale(2f);
        layout.setText(font, "Naves");
        font.draw(spriteBatch, "Naves", (Gdx.graphics.getWidth() - layout.width) / 2, (Gdx.graphics.getHeight() + layout.height) / 2);

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
        spriteBatch.end();
        seleccionarOpcion((PantallaMercado)game, juego, nave);
    }

    public String seleccionarOpcion(PantallaMercado game, SpaceNavigation juego, Nave nave) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            opcionSeleccionada = Math.max(0, opcionSeleccionada - 1);
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
            opcionSeleccionada = Math.min(opciones.length - 1, opcionSeleccionada + 1);
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            // Realizar la acción correspondiente a la opción seleccionada
            return ejecutarAccion(game, juego, nave);
        }

        return null;
    }
        private String ejecutarAccion(PantallaMercado game, SpaceNavigation juego, Nave nave) {
        switch (opcionSeleccionada) {
            case 0:
                // Resumir juego
                director.construirNaveDefault(builder);
			    nave = builder.getNave(); // editar nave aqui
                game.setJuegoPausado(false);
                Screen ss = new PantallaMenu(juego, nave);
				ss.resize(1200, 800);
				juego.setScreen(ss);
				game.dispose();
                return "NaveDeafult $done";
            case 1:
                director.construirNaveStarWars(builder);
			    nave = builder.getNave(); // editar nave aqui
                game.setJuegoPausado(false);
                Screen kk = new PantallaMenu(juego, nave);
				kk.resize(1200, 800);
				juego.setScreen(kk);
				game.dispose();
                return "NaveStarwars $done";
            case 2:
                // Acción para la tercera opción
                Screen jj = new PantallaMenu(juego, nave);
				jj.resize(1200, 800);
				juego.setScreen(jj);
				game.dispose();
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

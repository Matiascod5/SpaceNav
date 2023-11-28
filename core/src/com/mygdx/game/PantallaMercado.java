package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.ScreenUtils;

public class PantallaMercado implements Screen {

    private SpaceNavigation game;
    private OrthographicCamera camera;
    private Texture cursorTexture;
    private Sprite cursorSprite;
	private Texture backgroundTexture;
	private Sprite backgroundSprite;
    private float cursorX;
    private float cursorY;
    private int opcionSeleccionada = 1;
    private float[] optionY = {550,450,350,100};
    private int numOptions = optionY.length;

    public PantallaMercado(SpaceNavigation game) {
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1200, 800);

        cursorTexture = new Texture(Gdx.files.internal("CursorMercado.png"));
        cursorSprite = new Sprite(cursorTexture);

        cursorX = 10;
        cursorY = 550;
        cursorSprite.setPosition(cursorX, cursorY);
        
        // Carga la textura de fondo
        backgroundTexture = new Texture(Gdx.files.internal("FondoMercado.jpg"));

        // Configura el Sprite de fondo
        backgroundSprite = new Sprite(backgroundTexture);
        backgroundSprite.setSize(1200, 800); 
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);

        camera.update();
        game.getBatch().setProjectionMatrix(camera.combined);

        game.getBatch().begin();
        backgroundSprite.draw(game.getBatch());
        game.getFont().draw(game.getBatch(), "Pantalla Mercado ", 200, 800, 800, 1, true);

        game.getFont().draw(game.getBatch(), "Comprar Naves", 120, 600);
        game.getFont().draw(game.getBatch(), "Comprar Fondos", 120, 500);
        game.getFont().draw(game.getBatch(), "Comprar musica", 120, 400);

        game.getFont().draw(game.getBatch(), "Mejoras", 120, 700);
        game.getFont().draw(game.getBatch(), "Volver al menu principal", 130, 150);

        cursorSprite.draw(game.getBatch());

        game.getBatch().end();

        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            opcionSeleccionada--;
            if (opcionSeleccionada < 1) {
                opcionSeleccionada = numOptions; // Si es la primera opción, vuelve a la última
            }
            cursorY = optionY[opcionSeleccionada - 1];
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
            opcionSeleccionada++;
            if (opcionSeleccionada > numOptions) {
                opcionSeleccionada = 1; // Si es la última opción, vuelve a la primera
            }
            cursorY = optionY[opcionSeleccionada - 1];
        }

        cursorSprite.setPosition(cursorX, cursorY);

        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            // El usuario ha presionado la tecla "Enter", por lo que desea seleccionar la opción actual.
            if (opcionSeleccionada == 1) {
                Screen ss = new PantallaJuego(game, 1, 3, 0);
                ss.resize(1200, 800);
                game.setScreen(ss);
                dispose();
            } else if (opcionSeleccionada == 2) {
                // Realizar acciones relacionadas con la opción 2
                // ...
            } else if (opcionSeleccionada == 4) {
            	Screen ss = new PantallaMenu(game);
                ss.resize(1200, 800);
                game.setScreen(ss);
                dispose();
            }
        }
    }

    @Override
    public void show() {
        // TODO Auto-generated method stub

    }

    @Override
    public void resize(int width, int height) {
        // TODO Auto-generated method stub

    }

    @Override
    public void pause() {
        // TODO Auto-generated method stub

    }

    @Override
    public void resume() {
        // TODO Auto-generated method stub

    }

    @Override
    public void hide() {
        // TODO Auto-generated method stub

    }

    @Override
    public void dispose() {
        // TODO Auto-generated method stub

    }
}

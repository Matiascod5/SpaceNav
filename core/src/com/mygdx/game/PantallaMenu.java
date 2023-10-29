package com.mygdx.game;
import java.util.List;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.Color;
import java.util.ArrayList;

public class PantallaMenu implements Screen {

	private SpaceNavigation game;
	private OrthographicCamera camera;
    private Texture cursorTexture;
    private Sprite cursorSprite;
    private float cursorX;
    private float cursorY;
	private int opcionSeleccionada = 1;
	private float[] optionY = {175, 120, 75}; 
	private int numOptions = optionY.length; 
	private Texture backgroundTexture;
	private Sprite backgroundSprite;



	public PantallaMenu(SpaceNavigation game) {
		this.game = game;
        
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1200, 800);
        cursorTexture = new Texture(Gdx.files.internal("Flecha.png"));

        // Crear el Sprite de la flecha
        cursorSprite = new Sprite(cursorTexture);

        // Configurar la posición inicial del cursor
        cursorX = 150;
        cursorY = 175;
        cursorSprite.setPosition(cursorX, cursorY);
        
     // Carga la textura de fondo
        backgroundTexture = new Texture(Gdx.files.internal("FondoMenu.jpg"));

        // Configura el Sprite de fondo
        backgroundSprite = new Sprite(backgroundTexture);
        backgroundSprite.setSize(1200, 800); 
        
	}

	@Override
	public void render(float delta) {

		camera.update();
		game.getBatch().setProjectionMatrix(camera.combined);

		game.getBatch().begin();
		backgroundSprite.draw(game.getBatch());
		game.getFont().draw(game.getBatch(), "Bienvenido a Space Navigation !", 140, 400);
		game.getFont().draw(game.getBatch(), "Pincha en cualquier lado o presiona cualquier tecla para comenzar ...", 100, 300);
		game.getFont().draw(game.getBatch(), "Jugar", 200, 200);
		game.getFont().draw(game.getBatch(), "Mercado", 200, 150);
		game.getFont().draw(game.getBatch(), "Opciones", 200, 100);
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
		    	Screen ss = new PantallaJuego(game,1,3,0,1,1,10);
				ss.resize(1200, 800);
				game.setScreen(ss);
				dispose();
		    } else if (opcionSeleccionada == 2) {
		    	Screen ss = new PantallaMercado(game);
				ss.resize(1200, 800);
				game.setScreen(ss);
				dispose();
		    } else if (opcionSeleccionada == 3) {
		        // Realizar acciones relacionadas con la opción 3
		        // ...
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
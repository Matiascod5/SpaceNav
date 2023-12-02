package com.mygdx.game;
import java.util.List;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.Color;
import java.util.ArrayList;

public class PantallaOpciones implements Screen {

	private SpaceNavigation game;
	private OrthographicCamera camera;
    private Texture cursorTexture;
    private Sprite cursorSprite;
    private float cursorX;
    private float cursorY;
    private Music gameMusic;
	private int opcionSeleccionada = 1;
	private float[] optionY = {175, 120, 75, 30}; 
	private int numOptions = optionY.length; 
	private Texture backgroundTexture;
	private Sprite backgroundSprite;
	private float volumen = 1.0f;
	private boolean musicaActivada = true;
	private Nave nave;

	public PantallaOpciones(SpaceNavigation game, Nave naveNueva) {
		this.game = game;
		this.nave = naveNueva;
        
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1200, 800);
        cursorTexture = new Texture(Gdx.files.internal("Flecha.png"));
        
        musicaActivada = Configuracion.isMusicaActivada();

        if (musicaActivada) {
            gameMusic = Gdx.audio.newMusic(Gdx.files.internal("Opciones.mp3"));
            gameMusic.setLooping(true);
            gameMusic.setVolume(Configuracion.getVolumenMusica());
            gameMusic.play();
        }

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
		game.getFont().draw(game.getBatch(), "Opciones", 140, 600);
		game.getFont().draw(game.getBatch(), "Seleccione una opcion:", 100, 300);
		game.getFont().draw(game.getBatch(), "Subir volumen", 200, 200);
		game.getFont().draw(game.getBatch(), "Bajar volumen", 200, 150);
		game.getFont().draw(game.getBatch(), "Apagar/prender Musica:", 200, 100);
		game.getFont().draw(game.getBatch(), "Salir", 200, 50);
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
		    if (opcionSeleccionada == 1) {
		        // Aumentar el volumen de la música
		        if (gameMusic != null) {
		            Configuracion.setVolumenMusica(Configuracion.getVolumenMusica() + 0.1f);
		            if (Configuracion.getVolumenMusica() > 1.0f) {
		                Configuracion.setVolumenMusica(1.0f);
		            }
		            gameMusic.setVolume(Configuracion.getVolumenMusica());
		            // Guardar el valor en las preferencias
		            Preferences.setVolumenMusica(Configuracion.getVolumenMusica());
		        }
		    } else if (opcionSeleccionada == 2) {
		        // Disminuir el volumen de la música
		        if (gameMusic != null) {
		            Configuracion.setVolumenMusica(Configuracion.getVolumenMusica() - 0.1f);
		            if (Configuracion.getVolumenMusica() < 0.0f) {
		                Configuracion.setVolumenMusica(0.0f);
		            }
		            gameMusic.setVolume(Configuracion.getVolumenMusica());
		            // Guardar el valor en las preferencias
		            Preferences.setVolumenMusica(Configuracion.getVolumenMusica());
		        }
		    } else if (opcionSeleccionada == 3) {
		        // Cambiar la configuración de música
		        Configuracion.setMusicaActivada(!Configuracion.isMusicaActivada());
		        // Guardar el valor en las preferencias
		        Preferences.setMusicaActivada(Configuracion.isMusicaActivada());
		    } else if (opcionSeleccionada == 4) {
		        // Realizar acciones relacionadas con la opción 4
				Screen ss = new PantallaMenu(game, nave);
				ss.resize(1200, 800);
				game.setScreen(ss);
				dispose();
		        // ...
		    }
			else{
				
			}
		}
	}
	
	
	@Override
	public void show() {
		if (Preferences.getMusicaActivada()) {
	        Configuracion.setMusicaActivada(true); // Establece la configuración de música en verdadero si las preferencias la tienen habilitada.
	        gameMusic.play(); // Inicia la música solo si está habilitada en las preferencias.
	    } else {
	        Configuracion.setMusicaActivada(false); // Establece la configuración de música en falso si las preferencias la tienen deshabilitada.
	    }
	    Configuracion.setVolumenMusica(Preferences.getVolumenMusica());
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
		this.backgroundTexture.dispose();
		this.gameMusic.dispose();
	}
   
}
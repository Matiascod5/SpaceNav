package com.mygdx.game;

//import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
//import com.badlogic.gdx.utils.ScreenUtils;


public class PantallaJuego implements Screen{

	private SpaceNavigation game;
	private int score;
	private int ronda;
	private boolean itemSpawn = false;

	private OrthographicCamera camera;	
	private SpriteBatch batch;
	//private Sound explosionSound;
	//private Music gameMusic;
	//private int cantAsteroides;
	private Random numRandom;
	private Item item;
	private boolean musicaActivada = true;
	private Colisiones Colisiones = new Colisiones();
	private Niveles nivel = new Niveles();
	private boolean juegoPausado = false;
	//private NivelesStrategy strategy = new NivelEspacio();

	private MenuBuilder menuPausaBuilder = new MenuPausaBuilder();
    private DirectorMenu directorMenu = new DirectorMenu() ;
    private Menu menuPausa;

	private DirectorNave director = new DirectorNave();
	private NaveBuilder builder = new NaveBuilder();
	private Nave nave;

	public void setJuegoPausado(boolean juegoPausado){
		this.juegoPausado = juegoPausado;
	}

	public void spawnItem(){
		numRandom = new Random();
		int numAleatorio = numRandom.nextInt(3) + 1;
		if (numAleatorio == 1) this.item = Heavymachingan.getInstance();
		if (numAleatorio == 2) this.item = Escudo.getInstance();
		if (numAleatorio == 3) this.item = ItemPuntos.getInstance();

		this.itemSpawn = true;
	}

	public void gameOver(){
		if (score > game.getHighScore()){
  			game.setHighScore(score);
	    	Screen ss = new PantallaGameOver(game, nave);
  			ss.resize(1200, 800);
  			game.setScreen(ss);
  			dispose();
		}
		Screen ss = new PantallaGameOver(game, nave);
  		ss.resize(1200, 800);
  		game.setScreen(ss);
  		dispose();
	}

	public void winScreen(){
		Screen ss = new PantallaJuego(game,ronda+1, nave.getVidas(), score, nave);
		ss.resize(1200, 800);
		game.setScreen(ss);
		dispose();
	}

	public void dibujaEncabezado() {
		CharSequence str = "Vidas: "+nave.getVidas()+" Ronda: "+ronda;
		game.getFont().getData().setScale(2f);		
		game.getFont().draw(batch, str, 10, 30);
		game.getFont().draw(batch, "Score:"+this.getScore(), Gdx.graphics.getWidth()-500, 30);
		game.getFont().draw(batch, "HighScore:"+game.getHighScore(), Gdx.graphics.getWidth()/2-100, 30);
	}

	public void sumarScore(int score){
		this.score += score;
	}

	public int getScore(){
		return this.score;
	}

	public void seleccionarNivel(int ronda){
		if (ronda <= 2){
			nivel.setStrategy(new NivelEspacio());
		}
		else if (ronda <= 4){
			nivel.setStrategy(new NivelHielo());
		}
		else if (ronda <= 6){
			nivel.setStrategy(new NivelEspacio2());
		}
		else if (ronda <= 8){
			nivel.setStrategy(new NivelHielo2());
		}
		else{
			Random numRandom = new Random();
			int numAleatorio = numRandom.nextInt(2) + 1;
			if (numAleatorio == 1) nivel.setStrategy(new NivelEspacio2());
			if (numAleatorio == 2) nivel.setStrategy(new NivelHielo2());
		}
	}

	public PantallaJuego(SpaceNavigation game, int ronda, int vidas, int score, Nave naveNueva){
		this.game = game;
		this.ronda = ronda;
		this.score = score;
		this.musicaActivada = Preferences.getMusicaActivada();

		if(naveNueva == null){
			director.construirNaveDefault(builder);
			this.nave = builder.getNave(); // editar nave aqui
		}
		else{
			this.nave = naveNueva;
		}

		directorMenu.construirMenuPausa(menuPausaBuilder);
		this.menuPausa = menuPausaBuilder.construirMenu();

		batch = game.getBatch();
		camera = new OrthographicCamera();	
		//camera.setToOrtho(false, 800, 640);
		camera.setToOrtho(false, 1200, 800);
		//inicializar assets; musica de fondo y efectos de sonido

		seleccionarNivel(ronda);

		nivel.crearNivel(Colisiones, musicaActivada, batch, ronda);
	}

	@Override
	public void render(float delta) {
		if (!juegoPausado) {
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			//ScreenUtils.clear(0, 0, 0.2f, 1);
			
			batch.begin();

			nivel.getStrategy().mostrarFondo(batch);
			
			dibujaEncabezado();
			if (!nave.getHerido()) {
				// colisiones entre balas y asteroides y su destruccion 
				Colisiones.verificarColisionEnemigoBala(this, score, batch, nivel); //winScreen();
				
				//actualizar movimiento de asteroides dentro del area
				Colisiones.actualizarEnemigos(batch);

				//colisiones entre asteroides y sus rebotes 
				Colisiones.colisionesEnemigos();

				//dibujar asteroides y manejar colision con nave
				Colisiones.colisionesNaveAsteroide(nave);

				if (itemSpawn == false){
					numRandom = new Random();
					int numeroAleatorio = numRandom.nextInt(100) + 1;
					if (numeroAleatorio == 4) spawnItem();
				}
				else{
					if (!item.mover()){
						itemSpawn = false;
						item.setSpawn();
					} 
					if (Colisiones.verificarColisionNaveItem(item, nave, this)){
						item.setSpawn();
						item.dispose();
						itemSpawn = false;
					} 
					item.draw(batch);
				}
			}
			
			if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
				nave.disparar(batch, Colisiones, builder);
			}
			
			Colisiones.actualizarBalas(batch);

			nave.movimiento(batch, this);

			Colisiones.mostrarEnemigo(batch);

			if (nave.getVidas() <= 0) gameOver();

			if (Colisiones.getcantEnemigos() <= 0) winScreen();

			batch.end();
			
			if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
				setJuegoPausado(true);
			}	 

		}
		else{
			menuPausa.mostrar(batch, this, game, nave);
		}
	}
	
	@Override
	public void show() {
		if (Preferences.getMusicaActivada()) {
	        Configuracion.setMusicaActivada(true); // Establece la configuración de música en verdadero si las preferencias la tienen habilitada.
	        //gameMusic.play(); // Inicia la música solo si está habilitada en las preferencias.
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
		//this.explosionSound.dispose();
		nivel.getStrategy().apagarExplosion();
		nivel.getStrategy().apagarMusica();
		//this.gameMusic.dispose();
	}
   
}

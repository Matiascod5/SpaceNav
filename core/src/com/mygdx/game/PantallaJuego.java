package com.mygdx.game;

//import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
//import com.badlogic.gdx.utils.ScreenUtils;


public class PantallaJuego implements Screen {

	private SpaceNavigation game;
	private int score;
	private int ronda;
	private boolean itemSpawn = false;

	private OrthographicCamera camera;	
	private SpriteBatch batch;
	private Sound explosionSound;
	//private Music gameMusic;
	private int cantAsteroides;
	private Random numRandom;
	private Item item;
	private boolean musicaActivada = true;
	private Colisiones Colisiones = new Colisiones();
	private Niveles nivel = new Niveles();
	//private NivelesStrategy strategy = new NivelEspacio();

	private DirectorNave director = new DirectorNave();
	private NaveBuilder builder = new NaveBuilder();
	private Nave nave;

	public void spawnItem(){
		numRandom = new Random();
		int numAleatorio = numRandom.nextInt(2) + 1;
		if (numAleatorio == 1) this.item = Heavymachingan.getInstance();
		if (numAleatorio == 2) this.item = Escudo.getInstance();

		this.itemSpawn = true;
	}


	public PantallaJuego(SpaceNavigation game, int ronda, int vidas, int score){
		this.game = game;
		this.ronda = ronda;
		this.score = score;
		musicaActivada = Configuracion.isMusicaActivada();

		director.construirNaveStarWars(builder);
		nave = builder.getNave(); // editar nave aqui

		batch = game.getBatch();
		camera = new OrthographicCamera();	
		//camera.setToOrtho(false, 800, 640);
		camera.setToOrtho(false, 1200, 800);
		//inicializar assets; musica de fondo y efectos de sonido


		//explosionSound = Gdx.audio.newSound(Gdx.files.internal("explosion.ogg"));
		//explosionSound.setVolume(1,0.5f);

		//Spawn enemigos
		/* 
		for (int i = 0; i < cantAsteroides; i++) {
	        Enemigo bb = new asteroide_SMALL();	   
	  	    Colisiones.añadirEnemigo(bb);
	  	}*/

		if (ronda < 3)
			nivel.setStrategy(new NivelEspacio());
		else nivel.setStrategy(new NivelHielo());

		nivel.crearNivel(Colisiones, musicaActivada, batch);
	}

	public void gameOver(){
		if (score > game.getHighScore()){
  			game.setHighScore(score);
	    	Screen ss = new PantallaGameOver(game);
  			ss.resize(1200, 800);
  			game.setScreen(ss);
  			dispose();
		}
	}

	public void winScreen(){
		Screen ss = new PantallaJuego(game,ronda+1, nave.getVidas(), score);
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

	@Override
	public void render(float delta) {
		  Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		  //ScreenUtils.clear(0, 0, 0.2f, 1);
		  
          batch.begin();

		  nivel.getStrategy().mostrarFondo(batch);
          
		  dibujaEncabezado();
	      if (!nave.getHerido()) {
		    // colisiones entre balas y asteroides y su destruccion 
			Colisiones.verificarColisionEnemigoBala(this, score, explosionSound, batch, nivel); //winScreen();
			
		    //actualizar movimiento de asteroides dentro del area
			Colisiones.actualizarEnemigos(batch);

		    //colisiones entre asteroides y sus rebotes 
			Colisiones.colisionesEnemigos();

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
				if (Colisiones.verificarColisionNaveItem(item, nave)){
					item.setSpawn();
					item.dispose();
					itemSpawn = false;
				} 
				item.draw(batch);
		  	}
		}
			if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
	    		nave.disparar(batch, Colisiones);
	      	}
	      
		Colisiones.actualizarBalas(batch);
	    nave.movimiento(batch, this);


		Colisiones.mostrarEnemigo(batch);

	    //dibujar asteroides y manejar colision con nave
	    Colisiones.colisionesNaveAsteroide(nave);

		if (nave.getVidas() <= 0) gameOver();

		if (Colisiones.getcantEnemigos() <= 0) winScreen();

	    batch.end();
		  
	    if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
			Screen ss = new PantallaPausa(game, this);
			ss.resize(1200, 800);
			game.setScreen(ss);
			dispose();
		}
	      
	    	 
	}

	public void sumarScore(int score){
		this.score += score;
	}

	public int getScore(){
		return this.score;
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

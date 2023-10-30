package com.mygdx.game;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;


public class PantallaJuego implements Screen {

	private SpaceNavigation game;
	private OrthographicCamera camera;	
	private SpriteBatch batch;
	private Sound explosionSound;
	private Music gameMusic;
	private int score;
	private int ronda;
	//private int velXAsteroides; 
	//private int velYAsteroides; 
	private int cantAsteroides;
	//private Texture fondo;
	private Random numRandom;
	private Item item;
	private boolean itemSpawn = false;
	private Heavymachingan heavy = new Heavymachingan();
	private Nave nave = new Nave_StarWars(); // editar nave aqui
	//private asteroid asteroides = new asteroid();
	//private  ArrayList<Enemigo> balls1 = new ArrayList<>();
	//private  ArrayList<Enemigo> balls2 = new ArrayList<>();
	//private  ArrayList<Disparo> balas = new ArrayList<>();
	private Texture backgroundTexture;
	private Sprite backgroundSprite;
	private boolean musicaActivada = true;

	private Colisiones Colisiones = new Colisiones();

	public void spawnItem(){
		numRandom = new Random();
		int numAleatorio = numRandom.nextInt(2) + 1;
		if (numAleatorio == 1) this.item = new Heavymachingan();
		if (numAleatorio == 2) this.item = new Escudo();

		this.itemSpawn = true;
	}


	public PantallaJuego(SpaceNavigation game, int ronda, int vidas, int score, int cantAsteroides){
		this.game = game;
		this.ronda = ronda;
		this.score = score;
		/*
     // Carga la textura de fondo
        backgroundTexture = new Texture(Gdx.files.internal("FondoMenu.jpg"));

        // Configura el Sprite de fondo
        backgroundSprite = new Sprite(backgroundTexture);
        backgroundSprite.setSize(1200, 800); 
*/
		batch = game.getBatch();
		camera = new OrthographicCamera();	
		//camera.setToOrtho(false, 800, 640);
		camera.setToOrtho(false, 1200, 800);
		//inicializar assets; musica de fondo y efectos de sonido
		explosionSound = Gdx.audio.newSound(Gdx.files.internal("explosion.ogg"));
		explosionSound.setVolume(1,0.5f);
		

		boolean musicaActivada = Configuracion.isMusicaActivada();

	    if (musicaActivada) {
	    	gameMusic = Gdx.audio.newMusic(Gdx.files.internal("MusicaPelea.mp3")); //
			gameMusic.setLooping(true);
			gameMusic.setVolume(0.5f);
			gameMusic.play();
	    }

		//Colisiones.setCantEnemigos(cantAsteroides);
		
		for (int i = 0; i < cantAsteroides; i++) {
	        Enemigo bb = new asteroide_SMALL();	   
	  	    Colisiones.añadirEnemigo(bb);
	  	}
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
		Screen ss = new PantallaJuego(game,ronda+1, nave.getVidas(), score, cantAsteroides+10);
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
		  
			//backgroundSprite.draw(game.getBatch());
          
		  dibujaEncabezado();
	      if (!nave.getHerido()) {
		    // colisiones entre balas y asteroides y su destruccion 
			Colisiones.verificarColisionEnemigoBala(this, score, explosionSound, batch); //winScreen();
			
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
				item.draw(batch);
				if (!item.mover()) itemSpawn = false;
				if (Colisiones.verificarColisionNaveItem(item, nave)){
					item.dispose();
					itemSpawn = false;
				} 
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
    
	/*
    public void agregarBala(Disparo bb) {
    	this.balas.add(bb);
    }*/
	
	@Override
	public void show() {
		if (Configuracion.isMusicaActivada()) {
	        gameMusic.play();
	    }
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
		this.explosionSound.dispose();
		this.gameMusic.dispose();
	}
   
}

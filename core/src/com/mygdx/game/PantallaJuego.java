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
	//private int numRandom = 0;
	//private Heavymachingan heavy = new Heavymachingan();
	private Nave nave = new nave_default();
	//private asteroid asteroides = new asteroid();
	private  ArrayList<Enemigo> balls1 = new ArrayList<>();
	private  ArrayList<Enemigo> balls2 = new ArrayList<>();
	private  ArrayList<Disparo> balas = new ArrayList<>();


	public PantallaJuego(SpaceNavigation game, int ronda, int vidas, int score, int velXAsteroides, int velYAsteroides, int cantAsteroides){
		this.game = game;
		this.ronda = ronda;
		this.score = score;
		//this.velXAsteroides = velXAsteroides;
		//this.velYAsteroides = velYAsteroides;
		this.cantAsteroides = cantAsteroides;
		
		batch = game.getBatch();
		camera = new OrthographicCamera();	
		camera.setToOrtho(false, 800, 640);
		//inicializar assets; musica de fondo y efectos de sonido
		explosionSound = Gdx.audio.newSound(Gdx.files.internal("explosion.ogg"));
		explosionSound.setVolume(1,0.5f);
		gameMusic = Gdx.audio.newMusic(Gdx.files.internal("piano-loops.wav")); //
		
		gameMusic.setLooping(true);
		gameMusic.setVolume(0.5f);
		gameMusic.play();
		
	    // cargar imagen de la nave, 64x64   
	    
        //crear asteroides
        //Random r = new Random();
	    for (int i = 0; i < cantAsteroides; i++) {
	        Enemigo bb = new asteroide_SMALL();	   
	  	    balls1.add(bb);
	  	    balls2.add(bb);
	  	}
	}
    
	public void dibujaEncabezado() {
		CharSequence str = "Vidas: "+nave.getVidas()+" Ronda: "+ronda;
		game.getFont().getData().setScale(2f);		
		game.getFont().draw(batch, str, 10, 30);
		game.getFont().draw(batch, "Score:"+this.score, Gdx.graphics.getWidth()-150, 30);
		game.getFont().draw(batch, "HighScore:"+game.getHighScore(), Gdx.graphics.getWidth()/2-100, 30);
	}
	@Override
	public void render(float delta) {
		  Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
          batch.begin();
          //Texture background = new Texture(Gdx.files.internal("Fondo 1.png"));
          //batch.draw(background, 0, 0);
		  dibujaEncabezado();
	      if (!nave.getHerido()) {
		      // colisiones entre balas y asteroides y su destruccion 
			  /*
			  	Random rand = new Random();
			  	int numRandom = rand.nextInt(1-1000);
				if(numRandom == 4){
					Heavymachingan h = heavy;
					h.mover();
				}*/
			  
				//Disparo b = balas.get(i);
				//b.movimiento();
				for (int i = 0; i < balas.size(); i++){
					Disparo b = balas.get(i);
					b.movimiento();
					b.verificarColisionEnemigo(b, balls1, balls2, score, explosionSound, batch);
		    		if (b.verificarDestruccion()) {
		        		balas.remove(b);
		        		i--; //para no saltarse 1 tras eliminar del arraylist
		    		}
				}

			  
	    	  /*
	    	  for (int i = 0; i < balas.size(); i++) {
		            Bullet b = balas.get(i);
		            b.update();
		            for (int j = 0; j < balls1.size(); j++) {    
		              if (b.checkCollision(balls1.get(j))) {          
		            	 explosionSound.play();
		            	 balls1.remove(j);
		            	 balls2.remove(j);
		            	 j--;
		            	 score +=10;
		              }   	  
		  	        }
		                
		         //   b.draw(batch);
		            if (b.isDestroyed()) {
		                balas.remove(b);
		                i--; //para no saltarse 1 tras eliminar del arraylist
		            }
		      }*/
		      //actualizar movimiento de asteroides dentro del area

			  for (int i = 0 ; i < balls1.size() ; i++){
				Enemigo b = balls1.get(i);
				b.movimiento();
				//b.movimiento(b.getSprite(), b.getX(), b.getY());
				//Sprite a = b.getSprite();
				//a.draw(batch);
				b.mostrarEnemigo(batch);
			  }
			  /*
		      for (Enemigo ball : balls1) {
		          ball.movimiento(ball.getSprite());
		          //ball.draw(batch);
		      }*/
		      //colisiones entre asteroides y sus rebotes  
		      for (int i=0;i<balls1.size();i++) {
		    	Enemigo ball1 = balls1.get(i);   
		        for (int j=0;j<balls2.size();j++) {
		          Enemigo ball2 = balls2.get(j); 
		          if (i<j) {
		        	  ball1.checkCollision(ball2);
		     
		          }
		        }
		      } 
	      }
	      
	      if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
	    	nave.disparar(batch, this);
	      }
	      
		  for (int i = 0 ; i < balas.size() ; i++){
			Disparo b = balas.get(i);
			b.movimiento();
			b.mostrar(batch);
		  }
	      nave.mostrarDisparo(batch, balas);
	      
	      nave.movimiento(batch, this);
	      //dibujar asteroides y manejar colision con nave
	      //enemigos.mostrarAsteroides();
	      //enemigos.verificarColisionNave();
	      
	      for (int i = 0; i < balls1.size(); i++) {
	    	  //mostrarAsteroide();
	    	    Enemigo b=balls1.get(i);
	    	    //b.movimiento(b.getSprite());
	    	    b.draw(batch);
		          //perdió vida o game over
	              if (nave.verificarColisionNave(b)) {
		            //asteroide se destruye con el choque             
	            	 balls1.remove(i);
	            	 balls2.remove(i);
	            	 i--;
              }   	  
  	        }
	      
	      if (nave.getDestruido()) {
  			if (score > game.getHighScore())
  				game.setHighScore(score);
	    	Screen ss = new PantallaGameOver(game);
  			ss.resize(1200, 800);
  			game.setScreen(ss);
  			dispose();
  		  }
	      batch.end();
	      //nivel completado
	      /*if (balls1.size()==0) {
			Screen ss = new PantallaJuego(game,ronda+1, nave.getVidas(), score, 
					velXAsteroides+3, velYAsteroides+3, cantAsteroides+10);
			ss.resize(1200, 800);
			game.setScreen(ss);
			dispose();
		  }*/
	      if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
				//Screen ss = new PantallaMercado(game);
				//ss.resize(1200, 800);
				//game.setScreen(ss);
				dispose();
			}
	    	 
	}
    
	
    public void agregarBala(Disparo bb) {
    	this.balas.add(bb);
    }
	
	@Override
	public void show() {
		
		// TODO Auto-generated method stub
		gameMusic.play();
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

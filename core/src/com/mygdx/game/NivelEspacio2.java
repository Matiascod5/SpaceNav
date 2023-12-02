package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class NivelEspacio2 implements NivelesStrategy{
    private Music gameMusic = Gdx.audio.newMusic(Gdx.files.internal("MusicaPelea.mp3"));
    private Sound explosionSound = Gdx.audio.newSound(Gdx.files.internal("explosion.ogg"));
    private Sprite backgroundSprite = new Sprite(new Texture(Gdx.files.internal("Espacio.png")));
    private int cantEnemigos;

    public void aumentarDificultad(int ronda){
        this.cantEnemigos += ronda * 2;
    }

    public void crearEnemigos(Colisiones colisiones){
        for (int i = 0; i < this.cantEnemigos/3; i++) {
	        Enemigo bb = new asteroide_SMALL();	   
	  	    colisiones.añadirEnemigo(bb);
	  	}

        for (int i = this.cantEnemigos/3; i < this.cantEnemigos; i++){
            Enemigo bb = new asteroide_BIG();	   
	  	    colisiones.añadirEnemigo(bb);
        }

    }

    public void Fondo(SpriteBatch batch){
        backgroundSprite.setSize(1200, 800);
    }

    public void Musica(boolean musicaActivada){
        if (musicaActivada) {
            gameMusic.setLooping(true);
            gameMusic.setVolume(Configuracion.getVolumenMusica());
            gameMusic.play();
        }
    }

    public void apagarMusica(){
        gameMusic.dispose();
    }

    public void Explosion(){
        explosionSound.play();
    }

    public void apagarExplosion(){
        explosionSound.dispose();
    }

    public void mostrarFondo(SpriteBatch batch){
        backgroundSprite.draw(batch);
    }

    public void borrarFondo(SpriteBatch batch){
        //backgroundSprite.draw(batch);
    }
    
}

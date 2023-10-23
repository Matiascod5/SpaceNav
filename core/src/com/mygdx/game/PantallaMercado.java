package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;


public class PantallaMercado implements Screen {

	private SpaceNavigation game;
	private OrthographicCamera camera;

	public PantallaMercado(SpaceNavigation game) {
		this.game = game;
        
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1200, 800);
	}

	@Override
	public void render(float delta) {
		ScreenUtils.clear(0, 0, 0.2f, 1);

		camera.update();
		game.getBatch().setProjectionMatrix(camera.combined);

		game.getBatch().begin();
		game.getFont().draw(game.getBatch(), "Pantalla Mercado ", 200, 800,800,1,true);
		game.getFont().draw(game.getBatch(), "Mejoras", 120, 700);
		game.getFont().draw(game.getBatch(), "Reiniciar", 100, 250);
		game.getFont().draw(game.getBatch(), "Volver al menu principal", 100, 200);
	
		game.getBatch().end();
		
		if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
			Screen ss = new PantallaMenu(game);
			ss.resize(1200, 800);
			game.setScreen(ss);
			dispose();
		}

		if (Gdx.input.isTouched() || Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
			Screen ss = new PantallaJuego(game,1,3,0,1,1,10);
			ss.resize(1200, 800);
			game.setScreen(ss);
			dispose();
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
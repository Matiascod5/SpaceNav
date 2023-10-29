package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;

package com.mygdx.game;

import java.util.ArrayList;

public class disparo_default extends Disparo{
	float x = getX();
	float y = getY();
	
	
	
	public disparo_default() {
		super( x+ getWidth()/2-5,getY()+ getHeight()-5,0,3,new Texture(Gdx.files.internal("Rocket2.png")), Gdx.audio.newSound(Gdx.files.internal("pop-sound.mp3")), new Texture(Gdx.files.internal("Rocket2.png")));
		/*setxVel(0);
		setyVel(0);
		setSprite(new Texture(Gdx.files.internal("Rocket2.png")));
		getSprite().setPosition(0, 0);
		setSonidoDisparo(Gdx.audio.newSound(Gdx.files.internal("pop-sound.mp3")));
		setTexturaDisparo(new Texture(Gdx.files.internal("Rocket2.png")));*/
	}

	public void efectoEspecial(){

	}

	public void movimiento(){

	}
}

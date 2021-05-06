package com.mygdx.game.flappybird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class Jogo extends ApplicationAdapter {
	SpriteBatch batch;
	Texture passaro;
	Texture fundo;

	private  float larguraDispositivo;
	private  float alturaDispositivo;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		fundo = new Texture("fundo.png"); //informa o item fundo.png.

		larguraDispositivo = Gdx.graphics.getWidth();
		alturaDispositivo = Gdx.graphics.getHeight();

	}

	@Override
	public void render () {
		//informa para renderizar o item dentro da tela do celular.
		batch.begin();

		batch.draw(fundo, 100, 100, larguraDispositivo, alturaDispositivo); //desenha o objeto,

		batch.end(); //encerra o processo.
	}
	
	@Override
	public void dispose () {

	}
}

package com.mygdx.game.flappybird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import javax.xml.soap.Text;

public class Jogo extends ApplicationAdapter {

	private  float larguraDispositivo;
	private  float alturaDispositivo;
	private float posicaoInicialVerticalPassaro = 0;
	private float gravidade = 0;
	private  float variacao = 0;
	private Texture canoBaixo;
	private  Texture canoTopo;

	private  float posicaoCano;
	private int movimentaY = 0;
	private int movimentaX = 0;
	private Texture[]passaros;
	private Texture fundo;
	private SpriteBatch batch;


	@Override
	public void create () {
		batch = new SpriteBatch();
		passaros = new Texture[3];
		passaros[0] = new Texture("passaro1.png");
		passaros[1] = new Texture("passaro2.png");
		passaros[2] = new Texture("passaro3.png");
		fundo = new Texture("fundo.png");

		//fundo = new Texture("fundo.png"); //informa o item fundo.png.
		//passaro = new Texture("passaro1.png");//informa o passaro.png

		larguraDispositivo = Gdx.graphics.getWidth();
		alturaDispositivo = Gdx.graphics.getHeight();
		posicaoInicialVerticalPassaro = alturaDispositivo / 2;

		posicaoCano = alturaDispositivo /2;
		canoBaixo = new Texture("cano_baixo.png");
		canoTopo = new Texture("Cano_topo.png");

	}

	@Override
	public void render () {
		//informa para renderizar o item dentro da tela do celular.
		batch.begin();

		if(variacao > 3)
			variacao = 0;
		boolean toqueTela = Gdx.input.justTouched();
		if (Gdx.input.justTouched()){
			gravidade = -25;
		}
		if (posicaoInicialVerticalPassaro > 0 || toqueTela)
			posicaoInicialVerticalPassaro = posicaoInicialVerticalPassaro - gravidade;
		batch.draw(fundo,0,0,larguraDispositivo,alturaDispositivo);
		batch.draw(passaros[(int)variacao],30,posicaoInicialVerticalPassaro);
		batch.draw(canoBaixo, posicaoCano, 0);
		batch.draw(canoTopo, posicaoCano, alturaDispositivo/2);

		variacao += Gdx.graphics.getDeltaTime() * 10;

		gravidade++;
		movimentaX++; //eixo X.
		movimentaY++; //eixo Y.
		batch.end(); //encerra o processo.


		//batch.draw(fundo, 0, 0, 0, 0); //desenha o objeto.
		//batch.draw(passaro, movimentaX ,movimentaY, 0,0);


	}

	@Override
	public void dispose () {

	}
}

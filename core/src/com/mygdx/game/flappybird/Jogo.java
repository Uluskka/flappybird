package com.mygdx.game.flappybird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.Random;

import javax.print.attribute.standard.SheetCollate;
import javax.xml.soap.Text;

public class Jogo extends ApplicationAdapter {

	//variaveis utilizadas no projeto.

	private float larguraDispositivo;
	private float alturaDispositivo;
	private float posicaoInicialVerticalPassaro = 0;
	private float variacao = 0;
	private float posicaoCanoHorizontal;
	private float posicaoCanoVertical;
	private float espacoEntreCanos;


	//private  float posicaoCano;
	private int pontos = 0;
	private int gravidade = 0;

	private Texture[]passaros;
	private Texture fundo;
	private Texture canoBaixo;
	private Texture canoTopo;
	private SpriteBatch batch;

	BitmapFont textoPontucao;
	private boolean passouCano = false;
	private Random random;

	private ShapeRenderer shapeRenderer;
	private Circle circuloPassaro;
	private Rectangle retanguloCanoCima;
	private Rectangle retanguloCanoBaixo;

	@Override
	public void create () {
		
		inicializaTexturas();
		inicializaObjetos();
	}

	private void inicializaObjetos() {
		//passa as informacoes para inicilizar os objetos na tela.
		random = new Random();
		batch = new SpriteBatch();

		larguraDispositivo = Gdx.graphics.getWidth();
		alturaDispositivo = Gdx.graphics.getHeight();
		posicaoInicialVerticalPassaro = alturaDispositivo / 2;
		posicaoCanoHorizontal = larguraDispositivo;
		espacoEntreCanos = 350;

		textoPontucao = new BitmapFont(); //gera minha pontucao.
		textoPontucao.setColor(Color.WHITE);
		textoPontucao.getData().setScale(10);

		//collider
		shapeRenderer = new ShapeRenderer();
		circuloPassaro = new Circle();
		retanguloCanoCima = new Rectangle();
		retanguloCanoBaixo = new Rectangle();

	}

	private void inicializaTexturas(){

		//Materializa as imagens na tela do aplicativo.
		passaros = new Texture[3];
		passaros[0] = new Texture("passaro1.png");
		passaros[1] = new Texture("passaro2.png");
		passaros[2] = new Texture("passaro3.png");

		fundo = new Texture("fundo.png");
		canoBaixo = new Texture("cano_baixo_maior.png");
		canoTopo = new Texture("cano_topo_maior.png");

	}

	@Override
	public void render () {

		//informa para renderizar o item dentro da tela do celular.
		verificaEstadoJogo();
		desenharTexturas();
		detectarColisao();
		validaPontos();

	}

	private void detectarColisao() {
		//desenha o shape dos itens desejados.
		circuloPassaro.set(50 + passaros[0].getWidth() / 2,
				posicaoInicialVerticalPassaro + passaros[0].getHeight() / 2, passaros[0].getWidth() /2);

		retanguloCanoBaixo.set(posicaoCanoHorizontal,
				alturaDispositivo / 2 - canoBaixo.getHeight() - espacoEntreCanos / 2 + posicaoCanoVertical,
				canoBaixo.getWidth(), canoBaixo.getHeight());

		retanguloCanoCima.set(posicaoCanoHorizontal,
				alturaDispositivo / 2 - canoTopo.getHeight() - espacoEntreCanos / 2 + posicaoCanoVertical,
				canoTopo.getWidth(), canoTopo.getHeight());

		//identifica se bateu no cano.
		boolean bateuCanoCima = Intersector.overlaps(circuloPassaro, retanguloCanoCima);
		boolean bateuCanoBaixo = Intersector.overlaps(circuloPassaro, retanguloCanoBaixo);

		if (bateuCanoBaixo || bateuCanoCima){
			Gdx.app.log("Log","Bateu");

		}
	}

	private void validaPontos() {
		//informa e soma os pontos.
		if (posicaoCanoHorizontal < 50 - passaros[0].getWidth()){
			if (!passouCano){
				pontos++;
				passouCano = true;
			}
		}

	}

	private void verificaEstadoJogo() {
		//movimenta os cano.
		posicaoCanoHorizontal -= Gdx.graphics.getDeltaTime() * 200;
		if(posicaoCanoHorizontal < - canoBaixo.getHeight()){
			posicaoCanoHorizontal = larguraDispositivo;
			posicaoCanoVertical = random.nextInt(400) -200;
			passouCano = false;

			boolean toqueTela = Gdx.input.justTouched();
			if (Gdx.input.justTouched()){
				gravidade = -25;
			}
			if (posicaoInicialVerticalPassaro > 0 || toqueTela)
				posicaoInicialVerticalPassaro = posicaoInicialVerticalPassaro - gravidade;

			variacao += Gdx.graphics.getDeltaTime() * 10;
			if(variacao > 3)
				variacao = 0;

			gravidade++;


		}
	}

	private void desenharTexturas() {

		//desenha e renderiza as imagens do projeto.
		batch.begin();

		batch.draw(fundo,0,0,larguraDispositivo,alturaDispositivo);
		batch.draw(passaros[(int)variacao],50,posicaoInicialVerticalPassaro);
		batch.draw(canoBaixo, posicaoCanoHorizontal, alturaDispositivo / 2 - canoBaixo.getHeight() - espacoEntreCanos / 2 + posicaoCanoVertical);
		batch.draw(canoTopo, posicaoCanoHorizontal, alturaDispositivo / 2 + espacoEntreCanos / 2 + posicaoCanoVertical);
		textoPontucao.draw(batch, String.valueOf(pontos), larguraDispositivo / 2, alturaDispositivo -100);

		batch.end(); //encerra o processo.
	}

	@Override
	public void dispose () {

	}
}

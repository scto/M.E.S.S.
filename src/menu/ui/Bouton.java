package menu.ui;

import java.util.Random;

import jeu.CSG;
import jeu.Physic;
import jeu.Stats;
import menu.screens.AbstractScreen;
import menu.screens.Menu;
import menu.tuto.OnClick;
import assets.AssetMan;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import elements.particular.particles.ParticlePanneau;
import elements.particular.particles.Particles;

public class Bouton {

	private BitmapFont font;
	private String texte;
	private boolean versDroite = false, rapetisser = false, fade = false;
	private float vitesse = 50;
	private AbstractScreen parent;
	public Sprite sprite;
	public OnClick click;
	private boolean faitBouger = false;
	private static final Vector2 tmpPos = new Vector2(), tmpGeneralDirection = new Vector2();
	private int cptPosition = 0;
	private static final Random rand = new Random();
	private final Vector2 generator = new Vector2();
	private int generatorSide = 0;
	private final float speed = Stats.u;

	public Bouton(String s, boolean panneau, BitmapFont font, int srcWidth, int srcHeight, int srcX, int srcY, AbstractScreen parent, OnClick click, boolean faitBouger) {
		sprite = new Sprite();
		sprite.setBounds(srcX, srcY, srcWidth, srcHeight);
		this.font = font;
		this.parent = parent;
		texte = s;
		this.click = click;
		this.faitBouger = faitBouger;
		initGenerator();
	}

	private void initGenerator() {
		generator.x = sprite.getX();
		generator.y = sprite.getY() - ParticlePanneau.LARGEUR;
		Particles.BOUTONS.clear();
	}
	
	public Bouton(String s, boolean panneau, BitmapFont font, int srcWidth, int srcHeight, int srcX, int srcY, Menu parent, OnClick click, boolean faitBouger) {
		sprite = new Sprite();
		sprite.setBounds(srcX, srcY, srcWidth, srcHeight);
		this.font = font;
		this.parent = parent;
		texte = s;
		this.click = click;
		this.faitBouger = faitBouger;
		initGenerator();
	}
	
	public Bouton(String s, boolean panneau, BitmapFont font, int srcWidth, int srcHeight, int srcX, int srcY, AbstractScreen parent) {
		sprite = new Sprite();
		sprite.setBounds(srcX, srcY, srcWidth, srcHeight);
		this.font = font;
		this.parent = parent;
		texte = s;
		initGenerator();
	}

	public Bouton(String s, BitmapFont font, int srcWidth, int srcHeight, int srcX, int srcY, OnClick onClick) {
		sprite = new Sprite();
		sprite.setBounds(srcX, srcY, srcWidth, srcHeight);
		this.font = font;
		texte = s;
		click = onClick;
		initGenerator();
	}

	public void setClick(OnClick click) {
		this.click = click;
	}

	public void draw(SpriteBatch batch) {
//		for (int i = 0; i < 100; i++) {
//			moveGenerator(speed);
//			Particles.ajoutPanneau(generator, tmpGeneralDirection);
//		}
		batch.setColor(0,0,0,1);
		batch.draw(AssetMan.dust, sprite.getX(), sprite.getY() - ParticlePanneau.LARGEUR, sprite.getWidth(), sprite.getHeight() + ParticlePanneau.DOUBLE_LARGEUR);
		batch.setColor(AssetMan.WHITE);
		
		font.draw(batch, texte, 
				((sprite.getX() + (sprite.getWidth()/2)) - font.getBounds(texte).width/2),
				(sprite.getY() + font.getBounds(texte).height + sprite.getHeight()/2 - font.getBounds(texte).height/2) );
		
		if (Gdx.input.justTouched() && Physic.pointIn(sprite)) {
			if (faitBouger) {
				if (parent != null)
					parent.touche();
				fade = false;
				versDroite = true;
			} else {
				if (click != null) click.onClick();
			}
		}
		act();
	}
	
	private void moveGenerator(float speed) {
		switch (generatorSide) {
		case 0:
			generator.x += speed;
			if (generator.x > sprite.getX() + sprite.getWidth()) {
				generatorSide++;
				generator.x = sprite.getX() + sprite.getWidth();
			}
			break;
		case 1:
			generator.y += speed;
			if (generator.y > sprite.getY() + sprite.getHeight()) {
				generatorSide++;
				generator.y = sprite.getY() + sprite.getHeight();
			}
			break;
		case 2:
			generator.x -= speed;
			if (generator.x < sprite.getX()) {
				generatorSide++;
				generator.x = sprite.getX();
			}
			break;
		case 3:
			generator.y -= speed;
			if (generator.y < sprite.getY() - ParticlePanneau.LARGEUR) {
				generatorSide = 0;
				generator.y = sprite.getY() - ParticlePanneau.LARGEUR;
			}
			break;
		}
	}

	public void initVectorsParticule() {
		switch (cptPosition) {
		case 1:
		case 2:
		case 3:
		case 4:
			// ligne du haut
			tmpPos.y = sprite.getY() + sprite.getHeight();
			tmpPos.x = (sprite.getX() + (rand.nextFloat() * (sprite.getWidth()+ParticlePanneau.TRIPLE_LARGEUR))) - ParticlePanneau.DOUBLE_LARGEUR;
			tmpGeneralDirection.y = 1;
			tmpGeneralDirection.x = 0;
			break;
		case 5:
			// ligne de droite
			tmpPos.y = sprite.getY() + (rand.nextFloat() * sprite.getHeight());
			tmpPos.x = sprite.getX() + sprite.getWidth() + ParticlePanneau.LARGEUR;
			tmpGeneralDirection.y = 0;
			tmpGeneralDirection.x = 1;
			break;
		case 6:
		case 7:
		case 8:
		case 9:
			// ligne du bas
			tmpPos.y = sprite.getY() - ParticlePanneau.LARGEUR;
			tmpPos.x = (sprite.getX() + (rand.nextFloat() * (sprite.getWidth()+ParticlePanneau.TRIPLE_LARGEUR))) - ParticlePanneau.DOUBLE_LARGEUR;
			tmpGeneralDirection.y = -1;
			tmpGeneralDirection.x = 0;
			break;
		case 10:
			// ligne de gauche
			tmpPos.y = sprite.getY() + (rand.nextFloat() * sprite.getHeight());
			tmpPos.x = sprite.getX() - ParticlePanneau.DOUBLE_LARGEUR;
			tmpGeneralDirection.y = 0;
			tmpGeneralDirection.x = -1;
			break;
		}
		if (++cptPosition > 10)
			cptPosition = 1;
	}

	public void act() {
		float delta = Gdx.graphics.getDeltaTime();
		if (versDroite || fade || rapetisser)
			incrementVitesse();
		if (versDroite)
			sprite.setX(sprite.getX() + delta * getVitesse());
		if (fade)
			sprite.setX(sprite.getX() - delta * getVitesse());
		if (rapetisser) {
			setTexte("");
			sprite.setScale((delta * getVitesse()));
		}
		
		if (versDroite && sprite.getX() > CSG.screenWidth) {
			click.onClick();
		}
	}
	
	public float getVitesse() {		return vitesse;	}
	
	private float incrementVitesse() {
		vitesse = vitesse + (vitesse/6);
		return vitesse;
	}
	
	public void setTexte(String texte) {		this.texte = texte;	}
	
	public void setVersDroite(boolean b) {		versDroite = b;	}
	
	public void setFade(boolean b) {		fade = b;	}
	
	public void reset() {
		versDroite = false;
		fade = false;
		vitesse = 50;
		rapetisser = false;
	}
	
	public void setRapetisser(boolean b) {		rapetisser = b;	}

	public BitmapFont font() {
		return font;
	}

	public void camMoveY(float y) {
		sprite.setY(sprite.getY()+y);
	}
	
	public void camMoveX(float x) {
		sprite.setY(sprite.getY()+x);
	}

	public void camZoom(float f) {
		sprite.scale(f);
		font.scale(f);
	}
	
}

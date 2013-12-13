package menu.ui;

import java.util.Random;

import menu.Menu;
import menu.OnClick;
import jeu.CSG;
import jeu.Physique;
import jeu.Stats;
import assets.AssetMan;
import assets.particules.Particules;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.moribitotech.mtx.AbstractScreen;

public class Bouton {

	private BitmapFont font;
	private String texte;
	private boolean versDroite = false, rapetisser = false, fade = false;
	private float vitesse = 50;
	private static final float PADDING = Stats.U * 2, DEMI_PADDING = PADDING / 2;
	private final AbstractScreen parent;
	private Sprite sprite;
	private OnClick click;
	private boolean faitBouger = false;
	private static final Vector2 tmpPos = new Vector2(), tmpGeneralDirection = new Vector2();
	private int cptPosition = 0;
	private static final Random rand = new Random();

	public Bouton(String s, boolean panneau, BitmapFont font, int srcWidth, int srcHeight, int srcX, int srcY, AbstractScreen parent, OnClick click, boolean faitBouger) {
//		sprite = new Sprite(CSG.assetMan.bouton);
		sprite = new Sprite();
		sprite.setBounds(srcX, srcY, srcWidth, srcHeight);
		this.font = font;
		this.parent = parent;
		texte = s;
		this.click = click;
		this.faitBouger = faitBouger;
	}
	
	public Bouton(String s, boolean panneau, BitmapFont font, int srcWidth, int srcHeight, int srcX, int srcY, Menu parent, OnClick click, boolean faitBouger) {
//		sprite = new Sprite(CSG.assetMan.bouton);
		sprite = new Sprite();
		sprite.setBounds(srcX, srcY, srcWidth, srcHeight);
		this.font = font;
		this.parent = parent;
		texte = s;
		this.click = click;
		this.faitBouger = faitBouger;
	}
	
	public Bouton(String s, boolean panneau, BitmapFont font, int srcWidth, int srcHeight, int srcX, int srcY, AbstractScreen parent) {
//		sprite = new Sprite(CSG.assetMan.bouton);
		sprite = new Sprite();
		sprite.setBounds(srcX, srcY, srcWidth, srcHeight);
		this.font = font;
		this.parent = parent;
		texte = s;
	}

	public void setClick(OnClick click) {
		this.click = click;
	}

	public void draw(SpriteBatch batch) {
		initVectorsParticule();
		Particules.ajoutPanneau(tmpPos, tmpGeneralDirection);
		batch.draw(AssetMan.noir, sprite.getX() + DEMI_PADDING, sprite.getY() + DEMI_PADDING, sprite.getWidth() - PADDING, sprite.getHeight() - PADDING);
		font.draw(batch, texte, 
				((sprite.getX() + (sprite.getWidth()/2)) - font.getBounds(texte).width/2),
				(sprite.getY() + font.getBounds(texte).height + sprite.getHeight()/2 - font.getBounds(texte).height/2) );
		
		if (Gdx.input.justTouched() && Physique.pointIn(sprite)) {		
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
	
	private void initVectorsParticule() {
		switch (cptPosition) {
		case 1:
		case 2:
		case 3:
			// ligne du haut
			tmpPos.y = sprite.getY() + sprite.getHeight();
			tmpPos.x = sprite.getX() + (rand.nextFloat() * sprite.getWidth());
			tmpGeneralDirection.y = 1;
			tmpGeneralDirection.x = 0;
			break;
		case 4:
			// ligne de droite
			tmpPos.y = sprite.getY() + (rand.nextFloat() * sprite.getHeight());
			tmpPos.x = sprite.getX() + sprite.getWidth();
			tmpGeneralDirection.y = 0;
			tmpGeneralDirection.x = 1;
			break;
		case 5:
		case 6:
		case 7:
			// ligne du bas
			tmpPos.y = sprite.getY();
			tmpPos.x = sprite.getX() + (rand.nextFloat() * sprite.getWidth());
			tmpGeneralDirection.y = -1;
			tmpGeneralDirection.x = 0;
			break;
		case 8:
			// ligne de gauche
			tmpPos.y = sprite.getY() + (rand.nextFloat() * sprite.getHeight());
			tmpPos.x = sprite.getX();
			tmpGeneralDirection.y = 0;
			tmpGeneralDirection.x = -1;
			break;
		}
		if (++cptPosition > 8)
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
		
		if (versDroite && sprite.getX() > CSG.LARGEUR_ECRAN) {
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
	
}

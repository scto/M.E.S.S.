package menu;

import jeu.Physique;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.moribitotech.mtx.AbstractScreen;

public class Bouton {

	private BitmapFont font, fontShadow;
	private String texte;
	private boolean versDroite = false, rapetisser = false, fade = false;
	private float vitesse = 50;
	private final AbstractScreen parent;
	private Sprite sprite;
	private OnClick click;
	private boolean faitBouger = false;

	public Bouton(String s, boolean panneau, BitmapFont menuFont, int srcWidth, int srcHeight, int srcX, int srcY, AbstractScreen parent, OnClick click, boolean faitBouger) {
		sprite = new Sprite(CSG.assetMan.bouton);
		sprite.setBounds(srcX, srcY, srcWidth, srcHeight);
		this.font = menuFont;
		fontShadow = new BitmapFont(font.getData(), font.getRegion(), font.isFlipped());
		fontShadow.setColor(Color.BLACK);
		this.parent = parent;
		texte = s;
		this.click = click;
		this.faitBouger = faitBouger;
	}
	
	public Bouton(String s, boolean panneau, BitmapFont menuFont, int srcWidth, int srcHeight, int srcX, int srcY, AbstractScreen parent) {
		sprite = new Sprite(CSG.assetMan.bouton);
		sprite.setBounds(srcX, srcY, srcWidth, srcHeight);
		this.font = menuFont;
		fontShadow = new BitmapFont(font.getData(), font.getRegion(), font.isFlipped());
		fontShadow.setColor(Color.BLACK);
		this.parent = parent;
		texte = s;
	}
	
	public void setClick(OnClick click) {
		this.click = click;
	}

	public void draw(SpriteBatch batch) {
		if (texte.equals(AbstractScreen.BACK)) {
			if(Gdx.input.justTouched()) {
				System.err.println(Physique.pointIn(sprite));
				
			}
		}
		sprite.draw(batch);
		fontShadow.draw(batch, texte, ((sprite.getX() + (sprite.getWidth()/2)) - font.getBounds(texte).width/2) + CSG.LARGEUR_ECRAN/100,
				(sprite.getY() + font.getBounds(texte).height + sprite.getHeight()/2 - font.getBounds(texte).height/2) - CSG.LARGEUR_ECRAN/150);
		font.draw(batch, texte, ((sprite.getX() + (sprite.getWidth()/2)) - font.getBounds(texte).width/2),
				(sprite.getY() + font.getBounds(texte).height + sprite.getHeight()/2 - font.getBounds(texte).height/2) );
		
		if (Gdx.input.justTouched() && Physique.pointIn(sprite)) {
			
			if (faitBouger) {
				parent.touche();
				fade = false;
				versDroite = true;
			} else {
				if (click != null) click.onClick();
			}
		}
		act();
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
	
	
}

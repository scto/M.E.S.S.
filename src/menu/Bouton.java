package menu;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Bouton extends Actor{

	private BitmapFont font, fontShadow;
	private String texte;
	private boolean versDroite = false, rapetisser = false, fade = false;
	private float vitesse = 50;
	
	public Bouton(BitmapFont menuFont, boolean panneau) {
		this.font = menuFont;
		fontShadow = new BitmapFont(font.getData(), font.getRegion(), font.isFlipped());
		fontShadow.setColor(Color.BLACK);
	}
	

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		batch.draw(CSG.assetMan.bouton, getX(), getY(), getWidth(), getHeight());
		fontShadow.draw(batch, texte, ((getX() + (getWidth()/2)) - font.getBounds(texte).width/2) + CSG.LARGEUR_ECRAN/100, (getY() + font.getBounds(texte).height + getHeight()/2 - font.getBounds(texte).height/2) - CSG.LARGEUR_ECRAN/150);
		font.draw(batch, texte, ((getX() + (getWidth()/2)) - font.getBounds(texte).width/2), (getY() + font.getBounds(texte).height + getHeight()/2 - font.getBounds(texte).height/2) );
		// La font est affichee centree
	}
	@Override
	public void act(float delta) {
		super.act(delta);
		if (versDroite || fade || rapetisser)
			incrementVitesse();
		if (versDroite)
			setX(getX() + delta * getVitesse());
		if (fade)
			setX(getX() - delta * getVitesse());
		if (rapetisser) {
			setTexte("");
			if (getWidth() > 0) {								// Teste pour ne pas qu'il continue une fois qu'il est cense avoir disparu et donc se retourne
				setWidth(getWidth() - (delta * getVitesse()));
				setX(getX() + ((delta * getVitesse()) / 2));
			}
			if (getHeight() > 0) {
				setHeight(getHeight() - (delta * getVitesse()));
				setY(getY() + ((delta * getVitesse()) / 2));
			}
		}
	}
	
	public float getVitesse() {		return vitesse;	}
	
	private float incrementVitesse() {
		vitesse = vitesse + (vitesse/6);
		return vitesse;
	}
	
	public void setTexte(String texte) {		this.texte = texte;	}
	
	public boolean aFini() {
		if (versDroite & getX() > CSG.LARGEUR_ECRAN)
			return true;
		return false;
	}
	
	public void setVersDroite(boolean b) {
		versDroite = b;
	}
	
	public void setFade(boolean b) {		fade = b;	}
	
	public void reset() {
		versDroite = false;
		fade = false;
		vitesse = 50;
		rapetisser = false;
	}
	
	public void setRapetisser(boolean b) {
		rapetisser = b;
	}
	
	
}

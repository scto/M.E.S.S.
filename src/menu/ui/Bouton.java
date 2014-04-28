package menu.ui;

import jeu.CSG;
import jeu.EndlessMode;
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
import com.badlogic.gdx.utils.Array;

public class Bouton {

	private BitmapFont font;
	private String texte;
	private boolean versDroite = false, rapetisser = false, fade = false, clicked = false;
	private float vitesse = 50, clickTime = 0;
	private AbstractScreen parent;
	public Sprite sprite;
	public OnClick click;
	private boolean faitBouger = false;
	Array<Barre> barres = new Array<Barre>();
	private float height, width, x, y;
	private static final float PADDING_BARRE = Stats.U;
	private final float originalWidth, originalHeight;
	

	public Bouton(String s, boolean panneau, BitmapFont font, int srcWidth, int srcHeight, int srcX, float f, AbstractScreen parent, OnClick click, boolean faitBouger) {
		sprite = new Sprite();
		sprite.setBounds(srcX, f, srcWidth, srcHeight);
		this.originalHeight = srcHeight;
		this.originalWidth = srcWidth;
		init(s, font, parent, srcX, f);
		this.click = click;
		this.faitBouger = faitBouger;
	}

	private void init(String s, BitmapFont font, AbstractScreen parent, float x, float y) {
		this.font = font;
		this.parent = parent;
		texte = s;
		updateBorders(font, x, y, originalWidth, originalHeight);
	}

	private void updateBorders(BitmapFont font, float x, float y, float originalWidth, float originalHeight) {
		height = font.getBounds(texte).height;
		width = font.getBounds(texte).width;
//		width *= 1.2f;
		y = (y + (originalHeight/2)) - (height/2);
		x = (x + (originalWidth/2)) - (width/2);
		x -= PADDING_BARRE;
		y -= PADDING_BARRE;
		width += PADDING_BARRE * 2;
		height += PADDING_BARRE * 2;
		setBarres(x, y);
	}

	private void setBarres(float x, float y) {
		barres.clear();
		// top 
		horizontalBarre(x, y + height - Barre.HEIGHT);
		// bottom
		horizontalBarre(x, y);
		// left
		verticalBarre(x - Barre.HALF_HEIGHT, y);
		verticalBarre(x + width + Barre.HEIGHT, y);
		Barre.nbr = 0;
		this.x = x - Barre.HALF_HEIGHT;
		this.y = y;
	}
	
	private void verticalBarre(float x, float y) {
		final float distanceACouvrir = height;
		final int nbrBarre = (int) (((distanceACouvrir * 0.8f) / Barre.HEIGHT));
		final float distanceCouverte = nbrBarre * Barre.HEIGHT;
		final float ecartTotal = distanceACouvrir - distanceCouverte;
		final float ecart = ecartTotal / (nbrBarre-1);
		float tmpX = 0;
		for (int i = 0; i < nbrBarre; i++) {
//		for (float tmpX = 0; tmpX < height - Barre.HEIGHT;) {
			barres.add(Barre.POOL.obtain().init(x, y + tmpX));
			tmpX += (Barre.HEIGHT + ecart);
//			tmpX += Stats.uSur4;
		}
	}

	private void horizontalBarre(float x, float y) {
		final float distanceACouvrir = width + Barre.HEIGHT;
		final int nbrBarre = (int) (((distanceACouvrir * 0.7f) / Barre.HEIGHT));
		final float distanceCouverte = nbrBarre * Barre.HEIGHT;
		final float ecartTotal = distanceACouvrir - distanceCouverte;
		final float ecart = ecartTotal / (nbrBarre-1);
		float tmpX = -Barre.HALF_HEIGHT;
//		for (float tmpX = 0; tmpX < width;) {
		for (int i = 0; i < nbrBarre; i++) {
			barres.add(Barre.POOL.obtain().init(x + tmpX, y));
//			tmpX += Barre.HEIGHT;
//			tmpX += Stats.uSur4;
			tmpX += (Barre.HEIGHT + ecart);
		}
	}
	
	public Bouton(String s, boolean panneau, BitmapFont font, int srcWidth, int srcHeight, int srcX, int srcY, Menu parent, OnClick click, boolean faitBouger) {
		sprite = new Sprite();
		sprite.setBounds(srcX, srcY, srcWidth, srcHeight);
		this.click = click;
		this.faitBouger = faitBouger;
		this.originalHeight = srcHeight;
		this.originalWidth = srcWidth;
		init(s, font, parent, srcX, srcY);
	}
	
	public Bouton(String s, boolean panneau, BitmapFont font, int srcWidth, int srcHeight, int srcX, int srcY, AbstractScreen parent) {
		sprite = new Sprite();
		sprite.setBounds(srcX, srcY, srcWidth, srcHeight);
		this.originalHeight = srcHeight;
		this.originalWidth = srcWidth;
		init(s, font, parent, srcX, srcY);
	}

	public Bouton(String s, BitmapFont font, int srcWidth, int srcHeight, int srcX, int srcY, OnClick onClick) {
		sprite = new Sprite();
		sprite.setBounds(srcX, srcY, srcWidth, srcHeight);
		click = onClick;
		this.originalHeight = srcHeight;
		this.originalWidth = srcWidth;
		init(s, font, null, srcX, srcY);
	}

	public void setClick(OnClick click) {
		this.click = click;
	}

	public void draw(SpriteBatch batch) {
		drawBackground(batch);
		
		font.draw(batch, texte, getX(),	getY());
		
		for (Barre b : barres) {
			b.draw(batch);
		}
		
		if (Gdx.input.justTouched()) {
			if (Physic.pointIn(sprite)) {
				if (faitBouger) {
					if (parent != null)
						parent.touche();
					fade = false;
					versDroite = true;
					clicked(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY());
				} else {
					impulse(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY());
					if (click != null)
						click.onClick();
				}
			}
		}
		act();
	}

	private void impulse(int x, int y) {
		tmpTouched.x = x;
		tmpTouched.y = y;
		for (Barre b : barres)
			b.impulse(tmpTouched);
	}

	private static final Vector2 tmpTouched = new Vector2();
	private void clicked(int x, int y) {
		clicked = true;
		tmpTouched.x = x;
		tmpTouched.y = y;
		for (Barre b : barres)
			b.explode(tmpTouched);
	}

	private float getY() {
		return sprite.getY() + font.getBounds(texte).height + sprite.getHeight()/2 - font.getBounds(texte).height/2;
	}

	private float getX() {
		return (sprite.getX() + (sprite.getWidth()/2)) - font.getBounds(texte).width/2;
	}

	private void drawBackground(SpriteBatch batch) {
		batch.setColor(AssetMan.BLACK);
//		batch.draw(AssetMan.debris, sprite.getX(), sprite.getY() - ParticlePanneau.LARGEUR, sprite.getWidth(), sprite.getHeight() + ParticlePanneau.DOUBLE_LARGEUR);
		batch.draw(AssetMan.debris, x, y, width + PADDING_BARRE, height);
		batch.setColor(AssetMan.WHITE);
	}

	public void act() {
		if (clicked)
			clickTime += EndlessMode.delta;
		if (clickTime > .2f)
			click.onClick();
				
//		float delta = Gdx.graphics.getDeltaTime();
//		if (versDroite || fade || rapetisser)
//			incrementVitesse();
//		if (versDroite)
//			sprite.setX(sprite.getX() + delta * getVitesse());
//		if (fade)
//			sprite.setX(sprite.getX() - delta * getVitesse());
//		if (rapetisser) {
//			setTexte("");
//			sprite.setScale((delta * getVitesse()));
//		}
//		
//		if (versDroite && sprite.getX() > CSG.screenWidth) {
//			click.onClick();
//		}
	}
	
	public float getVitesse() {		return vitesse;	}
	
	private float incrementVitesse() {
		vitesse = vitesse + (vitesse/6);
		return vitesse;
	}
	
	public void setTexte(String texte) {
		// center
		this.texte = texte;
		width = font.getBounds(texte).width;
		width += PADDING_BARRE * 2;
		setBarres(getX() - PADDING_BARRE, y);
	}
	
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

	public static void testClick(Bouton b, float xOffset) {
		if (b != null && Physic.isPointInRect(Gdx.input.getX() + xOffset, CSG.SCREEN_HEIGHT - Gdx.input.getY(), 0, b.sprite.getY() - Stats.U, CSG.gameZoneWidth, b.sprite.getHeight() + Stats.UU)) {
			if (b.click != null)
				b.click.onClick();
		}
	}
}



//private void moveGenerator(float speed) {
//	switch (generatorSide) {
//	case 0:
//		generator.x += speed;
//		if (generator.x > sprite.getX() + sprite.getWidth()) {
//			generatorSide++;
//			generator.x = sprite.getX() + sprite.getWidth();
//		}
//		break;
//	case 1:
//		generator.y += speed;
//		if (generator.y > sprite.getY() + sprite.getHeight()) {
//			generatorSide++;
//			generator.y = sprite.getY() + sprite.getHeight();
//		}
//		break;
//	case 2:
//		generator.x -= speed;
//		if (generator.x < sprite.getX()) {
//			generatorSide++;
//			generator.x = sprite.getX();
//		}
//		break;
//	case 3:
//		generator.y -= speed;
//		if (generator.y < sprite.getY() - ParticlePanneau.LARGEUR) {
//			generatorSide = 0;
//			generator.y = sprite.getY() - ParticlePanneau.LARGEUR;
//		}
//		break;
//	}
//}

//
//public void initVectorsParticule() {
//	switch (cptPosition) {
//	case 1:
//	case 2:
//	case 3:
//	case 4:
//		// ligne du haut
//		tmpPos.y = sprite.getY() + sprite.getHeight();
//		tmpPos.x = (sprite.getX() + (rand.nextFloat() * (sprite.getWidth()+ParticlePanneau.TRIPLE_LARGEUR))) - ParticlePanneau.DOUBLE_LARGEUR;
//		tmpGeneralDirection.y = 1;
//		tmpGeneralDirection.x = 0;
//		break;
//	case 5:
//		// ligne de droite
//		tmpPos.y = sprite.getY() + (rand.nextFloat() * sprite.getHeight());
//		tmpPos.x = sprite.getX() + sprite.getWidth() + ParticlePanneau.LARGEUR;
//		tmpGeneralDirection.y = 0;
//		tmpGeneralDirection.x = 1;
//		break;
//	case 6:
//	case 7:
//	case 8:
//	case 9:
//		// ligne du bas
//		tmpPos.y = sprite.getY() - ParticlePanneau.LARGEUR;
//		tmpPos.x = (sprite.getX() + (rand.nextFloat() * (sprite.getWidth()+ParticlePanneau.TRIPLE_LARGEUR))) - ParticlePanneau.DOUBLE_LARGEUR;
//		tmpGeneralDirection.y = -1;
//		tmpGeneralDirection.x = 0;
//		break;
//	case 10:
//		// ligne de gauche
//		tmpPos.y = sprite.getY() + (rand.nextFloat() * sprite.getHeight());
//		tmpPos.x = sprite.getX() - ParticlePanneau.DOUBLE_LARGEUR;
//		tmpGeneralDirection.y = 0;
//		tmpGeneralDirection.x = -1;
//		break;
//	}
//	if (++cptPosition > 10)
//		cptPosition = 1;
//}


//private void initGenerator() {
//	generator.x = sprite.getX();
//	generator.y = sprite.getY() - ParticlePanneau.LARGEUR;
//	Particles.BOUTONS.clear();
//}
package menu;

import jeu.Endless;
import jeu.Physique;
import vaisseaux.armes.Armes;
import vaisseaux.bonus.Bonus;
import vaisseaux.bonus.BonusStop;
import vaisseaux.bonus.BonusTemps;
import vaisseaux.bonus.XP;
import vaisseaux.ennemis.Ennemis;
import vaisseaux.ennemis.particuliers.EnnemiBouleQuiSArrete;
import vaisseaux.ennemis.particuliers.EnnemiDeBase;
import vaisseaux.ennemis.particuliers.EnnemiPorteNef;
import vaisseaux.ennemis.particuliers.EnnemiToupie;
import vaisseaux.joueur.VaisseauType1;
import assets.AssetMan;
import assets.animation.AnimationVaisseau;
import bloom.Bloom;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.moribitotech.mtx.AbstractScreen;

public class Tutorial extends AbstractScreen {
	
	private static String hello = "Hello my friend !";
	private static String tutorial = "Stay a while and listen.";
	private static String clicToNext = "Click for the next message";
	private static String doubleClicToPass = "Double tap to skip.";
	// step 1
	private static String thisIsYourShip = "You =>";
	private static String touchToMove = "Touch the screen to move";
	private static String thisIsAnEnemi = "Shoot him !";
	private EnnemiDeBase ennemi1DeBase;
	// step 2
	private Bonus xp;
	private static String gg = "Catch the XP" ;	
	private static String gg2 = "It will help you improve your ship !";
	// step 3
	private EnnemiBouleQuiSArrete ennemi2BouleQuiSArrete;
	private BonusTemps bonusTemps;
	private static String txtBonusTemps = "This one will slow time";
	// step 5
	private static String jauge = " ";
	private static String doubleClicTemps = "Double tap to activate";
	// step 6
	private EnnemiPorteNef ennemi3PorteNef;
	// step 7
	private static String txtStop = "Stop the world";
	private BonusStop bonusStop;
	// step 8 
	private EnnemiToupie ennemi4Toupie;
	// step 9
	private static String twoFingers = "Use two fingers";
	
	private static String goodJob = "Good job !";
	
	private float monter = 0;
	
	private Bloom bloom = new Bloom();
	private SpriteBatch batch;
	private VaisseauType1 vaisseau = new VaisseauType1();
	private static float chronoRalentir = 0;
	private boolean activerRalentissement = false;
	private float vientDEtreTouche = -10;
	private static final int X_CHRONO = CSG.DIXIEME_LARGEUR/3;
	private static final int X_SLOW = (CSG.DEMI_LARGEUR_ECRAN/6)*5;
	private static final int HAUTEUR_POLICE = CSG.DIXIEME_HAUTEUR/3;
	public float maintenant = 0;
	// *************************  J  A  U  G  E  *************************
	private TextureRegion rougefonce;
	private static final int MAX_LARGEUR_JAUGE = CSG.LARGEUR_ECRAN/6;
	private static final int DIXIEME_LARGEUR_JAUGE = MAX_LARGEUR_JAUGE/10;
	private static final int HAUTEUR_JAUGE = CSG.HAUTEUR_ECRAN/60;
	private static final int X_XP = X_SLOW * 2;
	// *************************  D  P  A  D  ****************************
	private static final TextureRegion fleche = CSG.getAssetMan().getAtlas().findRegion("fleche");
	private static final float HAUTEUR_FLECHE = CSG.HAUTEUR_ECRAN/30;
	private static final float DEMI_HAUTEUR_FLECHE = HAUTEUR_FLECHE/2;
	private static final float LARGEUR_FLECHE = HAUTEUR_FLECHE;
	private static final float DEMI_LARGEUR_FLECHE = LARGEUR_FLECHE/2;
	private int step = 0;
	public static BitmapFont font;
	private boolean alterner = true;
	private static float nbBonusStop = 0;
	private static boolean activerStop = false;
	
	public Tutorial(Game game) {
		super(game,"");
		 setUpScreenElements();
	}

	private void setUpScreenElements() {
		CSG.resetLists();
		vaisseau.initialiser();
		batch = new SpriteBatch();
		maintenant = 0;
		// **************************************** F O N T 
		font = new BitmapFont();
		float x = CSG.LARGEUR_ECRAN/220;
		float y = CSG.HAUTEUR_ECRAN/400;
		if (x < 1) x = 1.01f;
		if (y < 1) y = 1.01f;
		font.setScale(x, y);
		font.setColor(.99f, .85f, 0f, 1);
		rougefonce = CSG.getAssetMan().getAtlas().findRegion("rougefonce");
		activerStop = false;
		nbBonusStop = 0;
	}

	
	@Override
	public void render(float delta) {
		maintenant += delta;
		if (CSG.profil.bloom)		bloom.capture();
		else						Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		if(maintenant < 4)	batch.setColor(1,1,1,maintenant * 0.25f);
		CSG.renderBackground(batch);
		affichageNonPerdu();
		
		switch(step) {
		case 0:
			monter += delta * 5;
			Endless.delta = 0;
			step0();
			break;
		case 1:
			monter += delta * 5;
			if (ennemi1DeBase == null) {
				ennemi1DeBase = EnnemiDeBase.pool.obtain();
				Ennemis.liste.add(ennemi1DeBase);
				ennemi1DeBase.position.x = CSG.DEMI_LARGEUR_ECRAN/2;
			}
			step1();
			break;
		case 2:
			monter += delta * 5;
			step2();
			break;
		case 3: // c'est le moment ou le vaisseau apparait
			break;
		case 4: // c'est quand le vaisseau de la boule qui tir drop son bonus de temps
			monter += delta * 5;
			step4();
			break;
		case 5: // on a pris le bonus temps, on attire l'attention sur la jauge
			monter += delta * 5;
			step5();
			break;
		case 7: // on a tu� le porte nef et y'a le bonus temps
			monter += delta*5;
			step7();
			break;
		case 8: // On a prit le super bonus ! :)
			monter += delta*5;
			step8();
			break;
		case 9:
			step9();
			break;
		}
		batch.end();
		super.render(delta);
		if (CSG.profil.bloom) bloom.render();
		update(delta);
		if (Gdx.input.isKeyPressed(Keys.BACK)) {
			Menu menu = new Menu(getGame());
			getGame().setScreen(menu);
		}
	}
	
	private void step8() {
		font.draw(batch, twoFingers, CSG.DEMI_LARGEUR_ECRAN - font.getBounds(twoFingers).width/2, CSG.DEMI_HAUTEUR_ECRAN);
	}

	private void step7() {
		font.draw(batch, txtStop, bonusStop.posX - font.getBounds(txtStop).width/2, bonusStop.posY + Bonus.LARGEUR_COLLISION);
	}

	private void step9() {
		font.draw(batch, goodJob, CSG.DEMI_LARGEUR_ECRAN - font.getBounds(goodJob).width/2, CSG.DEMI_HAUTEUR_ECRAN);
	}

	// on pointe sur la jauge
	private void step5() {
		fade(1.4f);
		font.draw(batch, jauge, CSG.DEMI_LARGEUR_ECRAN - font.getBounds(doubleClicTemps).width/2, 5*HAUTEUR_POLICE + font.getBounds(jauge).height);
		font.draw(batch, doubleClicTemps, CSG.DEMI_LARGEUR_ECRAN - font.getBounds(doubleClicTemps).width/2, 3*HAUTEUR_POLICE + font.getBounds(jauge).height);
		batch.draw(fleche, CSG.DEMI_LARGEUR_ECRAN - DEMI_LARGEUR_FLECHE, 1 * HAUTEUR_POLICE,
				DEMI_LARGEUR_FLECHE, DEMI_HAUTEUR_FLECHE, LARGEUR_FLECHE, HAUTEUR_FLECHE + DEMI_HAUTEUR_FLECHE, 1, 1, 270);
	}

	private void step4() {
		fade(1.5f);
		if(bonusTemps != null)
			font.draw(batch, txtBonusTemps, bonusTemps.posX - font.getBounds(txtBonusTemps).width/2, bonusTemps.posY + Bonus.LARGEUR*4);
	}

	private void step2() {
		fade(1.0f);
		font.draw(batch, gg, xp.posX - font.getBounds(gg).width/2, xp.posY + Bonus.LARGEUR*4);
		font.draw(batch, gg2, xp.posX - font.getBounds(gg2).width/2, xp.posY + Bonus.LARGEUR*2);
	}

	private void step1() {
		fade(1.5f);
		font.draw(batch, thisIsYourShip, VaisseauType1.position.x - font.getBounds(thisIsYourShip).width, VaisseauType1.position.y + VaisseauType1.HAUTEUR);
		font.draw(batch, touchToMove, VaisseauType1.position.x - font.getBounds(touchToMove).width / 2, VaisseauType1.position.y + VaisseauType1.HAUTEUR * 2);
		font.draw(batch, thisIsAnEnemi, ennemi1DeBase.position.x - font.getBounds(thisIsAnEnemi).width/2, ennemi1DeBase.position.y + EnnemiDeBase.HAUTEUR * 3);
	}

	private void update(float delta) {
		if (Gdx.input.isTouched()){
			if(step > 0) 	vaisseau.mouvements();
			if((Gdx.input.isTouched(1) | Gdx.input.isKeyPressed(Keys.A)) & nbBonusStop > 0)		activerStop = true;
		} else {
			AnimationVaisseau.droit();
		}
		if (Gdx.input.justTouched()) {
			if ((step == 1 | step >= 9) & (vientDEtreTouche + .4f > maintenant))	menu();
			if (step == 0 | step >= 9) { // car au step 1 on doit buter l'ennemi
				incStep();
				// double clic 
				vientDEtreTouche = maintenant;
			}
		}
		
		
		if (step == 1 && ennemi1DeBase != null && ennemi1DeBase.mort | Ennemis.liste.size == 0) {
			Bonus.liste.clear();
			xp = XP.pool.obtain();
			Bonus.liste.add(xp);
			if(ennemi1DeBase.mort) {
				xp.posX = ennemi1DeBase.position.x + EnnemiDeBase.DEMI_LARGEUR;
				xp.posY = ennemi1DeBase.position.y + EnnemiDeBase.DEMI_LARGEUR;
			} else {
				xp.posX = CSG.DEMI_LARGEUR_ECRAN;
				xp.posY = CSG.HAUTEUR_ECRAN;
			}
			incStep();
		}
		if (step == 2 && Bonus.liste.size == 0) {
			ennemi2BouleQuiSArrete = EnnemiBouleQuiSArrete.pool.obtain();
			Ennemis.liste.add(ennemi2BouleQuiSArrete);
			ennemi2BouleQuiSArrete.position.x = CSG.LARGEUR_ECRAN/3;
			incStep();
		}
		if (step == 3 && ennemi2BouleQuiSArrete != null && ennemi2BouleQuiSArrete.mort | Ennemis.liste.size == 0) {
			Bonus.liste.clear();
			bonusTemps = BonusTemps.pool.obtain();
			Bonus.liste.add(bonusTemps);
			if(ennemi2BouleQuiSArrete.mort) {
				bonusTemps.posX = ennemi2BouleQuiSArrete.position.x + EnnemiBouleQuiSArrete.DEMI_LARGEUR;
				bonusTemps.posY = ennemi2BouleQuiSArrete.position.y + EnnemiBouleQuiSArrete.DEMI_LARGEUR;
			} else {
				bonusTemps.posX = CSG.DEMI_LARGEUR_ECRAN;
				bonusTemps.posY = CSG.HAUTEUR_ECRAN;
			}
			incStep();
		}
		if (step == 4) { // C'est la phase du bonus temps
			if (Bonus.liste.size == 0) { // si il a �t� pris
				chronoRalentir = 3;
				incStep();
			}
		}
		if (step == 5) { // C'est la phase ou on peut faire le double clic
			if(Gdx.input.justTouched()){
				if (vientDEtreTouche + .4f > maintenant) {
					activerRalentissement = true;
					ennemi3PorteNef = EnnemiPorteNef.pool.obtain();
					ennemi3PorteNef.position.x = CSG.LARGEUR_ECRAN;
					Ennemis.liste.add(ennemi3PorteNef);
					incStep();
				}
				vientDEtreTouche = maintenant;
			}
		}
		if (step == 6 && ennemi3PorteNef.mort | Ennemis.liste.size == 0) {
			Bonus.liste.clear();
			bonusStop = BonusStop.pool.obtain();
			if(ennemi3PorteNef.mort) {
				bonusStop.posX = ennemi3PorteNef.position.x + EnnemiPorteNef.DEMI_LARGEUR - Bonus.DEMI_LARGEUR;
				bonusStop.posY = ennemi3PorteNef.position.y + EnnemiPorteNef.DEMI_LARGEUR - Bonus.DEMI_LARGEUR;
			} else {
				bonusStop.posX = CSG.DEMI_LARGEUR_ECRAN;
				bonusStop.posY = CSG.HAUTEUR_ECRAN;
			}
			Bonus.liste.add(bonusStop);
			incStep();
		}
		if (step == 7 & Bonus.liste.size == 0) {
			nbBonusStop = 3;
			incStep();
		}
	
		if (step == 8){
			if(ennemi4Toupie == null){
				ennemi4Toupie = EnnemiToupie.pool.obtain();
				Ennemis.liste.add(ennemi4Toupie);
			} else {
				if(ennemi4Toupie.mort | Ennemis.liste.size==0) incStep();
			}
		}
		// ************* T I R ***** C O L L I S I O N S ****************
		if (alterner & step > 0) {
			Physique.testCollisions();
			vaisseau.tir();
		}
		alterner = !alterner;
		
		// bullet time !
		if (activerRalentissement) {
			chronoRalentir -= delta/3;
			Endless.delta = delta/3;
			if(chronoRalentir < 0){
				activerRalentissement = false;
				chronoRalentir = 0;
			}
		} else {
			Endless.delta = delta;
		}
	}

	private void menu() {
		Menu menu = new Menu(getGame());
		getGame().setScreen(menu);
	}

	private void incStep() {
		step++;
		monter = 0;
	}

	private void step0() {
		fade(2);
		font.draw(batch, hello, CSG.LARGEUR_ECRAN/80, ((CSG.HAUTEUR_ECRAN/5) * 4) + monter );
		font.draw(batch, tutorial, CSG.LARGEUR_ECRAN/80, ((CSG.HAUTEUR_ECRAN/5) * 3.5f) + monter );
		font.draw(batch, clicToNext, CSG.LARGEUR_ECRAN/80, ((CSG.HAUTEUR_ECRAN/5) * 2.5f) + monter );
		font.draw(batch, doubleClicToPass, CSG.LARGEUR_ECRAN/80, ((CSG.HAUTEUR_ECRAN/5) * 2f) + monter );

	}

	private void fade(float duree) {
		if(monter < 5 * duree)	font.setColor(.99f, .85f, 0f, monter * 1 / (5 * duree));
	}
	private float prevDelta;
	private void affichageNonPerdu() {
		if(activerStop == false) {
			if(CSG.profil.particules){
				Bonus.affichageEtMouvement(batch);
				Ennemis.affichageEtMouvement(batch);
				vaisseau.draw(batch);
				Armes.affichageEtMouvement(batch);
			} else {
				Bonus.affichageEtMouvement(batch);
				Ennemis.affichageEtMouvementSansParticules(batch);
				vaisseau.drawSansParticules(batch);
				Armes.affichageEtMouvementSansParticules(batch);
			}
		} else {
			prevDelta = Endless.delta;
			Endless.delta = 0;
			if(CSG.profil.particules){
				Ennemis.affichage(batch);
				Endless.delta = prevDelta;
				Armes.affichage(batch);
				vaisseau.draw(batch);
			} else {
				Ennemis.affichageSansParticules(batch);
				Endless.delta = prevDelta;
				Armes.affichageSansParticules(batch);
				vaisseau.drawSansParticules(batch);
			}
			nbBonusStop -= Endless.delta;
			if (nbBonusStop < 0) activerStop = false;
		}
		// ****************************************************************************************
		// ***************                   P   O   L   I   C   E                  ***************
		// ****************************************************************************************
		
		if (step > 1) CSG.menuFontPetite.draw(batch, CSG.profil.champXp, X_XP, HAUTEUR_POLICE);
		
		// *************************************
		// ******** J  A  U  G  E  *************
		// *************************************
		batch.draw(rougefonce, X_SLOW, HAUTEUR_POLICE - HAUTEUR_JAUGE, DIXIEME_LARGEUR_JAUGE * chronoRalentir, HAUTEUR_JAUGE);
		// *************************************
		// ****  A F F I C H E R   D P A D  ****
		// *************************************
		if (CSG.profil.typeControle == CSG.CONTROLE_DPAD & Gdx.input.isTouched()){
			//			F L E C H E   D R O I T E
			batch.draw(fleche, VaisseauType1.prevX + DEMI_LARGEUR_FLECHE, CSG.HAUTEUR_ECRAN - (VaisseauType1.prevY + DEMI_HAUTEUR_FLECHE), DEMI_LARGEUR_FLECHE, DEMI_HAUTEUR_FLECHE, LARGEUR_FLECHE, HAUTEUR_FLECHE, 1, 1, 0);
			//			F L E C H E   G A U C H E
			batch.draw(fleche, VaisseauType1.prevX - (LARGEUR_FLECHE+DEMI_LARGEUR_FLECHE), CSG.HAUTEUR_ECRAN - (VaisseauType1.prevY + DEMI_HAUTEUR_FLECHE), DEMI_LARGEUR_FLECHE, DEMI_HAUTEUR_FLECHE, LARGEUR_FLECHE, HAUTEUR_FLECHE, 1, 1, 180);
			//			F L E C H E   H A U T
			batch.draw(fleche, VaisseauType1.prevX - DEMI_LARGEUR_FLECHE, CSG.HAUTEUR_ECRAN - (VaisseauType1.prevY - DEMI_HAUTEUR_FLECHE), DEMI_LARGEUR_FLECHE, DEMI_HAUTEUR_FLECHE, LARGEUR_FLECHE, HAUTEUR_FLECHE, 1, 1, 90);
			//			F L E C H E   B A S
			batch.draw(fleche, VaisseauType1.prevX - DEMI_LARGEUR_FLECHE, CSG.HAUTEUR_ECRAN - (VaisseauType1.prevY + DEMI_HAUTEUR_FLECHE + HAUTEUR_FLECHE), DEMI_LARGEUR_FLECHE, DEMI_HAUTEUR_FLECHE, LARGEUR_FLECHE, HAUTEUR_FLECHE, 1, 1, 270);
		}
		// *************************************
		// ****  A F F I C H E R   S T O P  ****
		// *************************************
		if(step > 7) batch.draw(AssetMan.bonusetoile, X_CHRONO, HAUTEUR_POLICE*2, Bonus.DEMI_LARGEUR, Bonus.DEMI_LARGEUR);
	}
}

package elements.generic;

import jeu.CSG;
import jeu.EndlessMode;
import jeu.Stats;
import assets.AssetMan;
import assets.SoundMan;
import assets.animation.AnimPlayer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import elements.generic.enemies.Enemy;
import elements.generic.weapons.Weapons;
import elements.generic.weapons.player.ArmeAdd;
import elements.generic.weapons.player.WeaponManager;
import elements.particular.particles.OvaleParticuleGenerator;
import elements.particular.particles.Particles;

public final class Player extends Element {

	public static final int LARGEUR = (int) Stats.LARGEUR_JOUEUR, DEMI_LARGEUR = LARGEUR/2, LARGEUR_ADD = LARGEUR/3, DEMI_LARGEUR_ADD = LARGEUR_ADD/2;
	public static final int HAUTEUR = (int) ((float)LARGEUR * 1.3f), DEMI_HAUTEUR = HAUTEUR / 2, HAUTEUR_MAX_ADD = HAUTEUR + DEMI_HAUTEUR, DEMI_HAUTEUR_ADD = DEMI_HAUTEUR / 2;
	public static final int DECALAGE_ADD = LARGEUR + DEMI_LARGEUR - LARGEUR_ADD;
	public static final int DECALAGE_TIR_ADD_X_GAUCHE = -DEMI_LARGEUR - DEMI_LARGEUR_ADD + ArmeAdd.DEMI_LARGEUR;
	public static final int DECALAGE_TIR_ADD_X_DROITE = DECALAGE_ADD - DEMI_LARGEUR_ADD + ArmeAdd.DEMI_LARGEUR;
	private static final int LIMITE_X_GAUCHE = 0 - DEMI_LARGEUR, LIMITE_X_DROITE = CSG.gameZoneWidth - DEMI_LARGEUR, LIMITE_Y_GAUCHE = 0 - DEMI_HAUTEUR, LIMITE_Y_DROITE = CSG.SCREEN_HEIGHT - DEMI_HAUTEUR;
	private static final float DEGRE_PRECISION_DEPLACEMENT = (CSG.screenWidth + CSG.SCREEN_HEIGHT) / 600;
	private static float vitesseMax = 0;
	public static WeaponManager weapon = CSG.profile.getArmeSelectionnee();
	public static float xCenter = 0, yCenter = 0, prochainTir = 0, prochainTirAdd = 0;
	public static final Vector2 POS = new Vector2();
	public static float prevX, prevY, destX, destY;
	private static float vitesseFoisdelta = 0, tmpCalculDeplacement = 0, originalAccelX = 0, originalAccelY = 0;
	public static boolean leftAdd = false, rightAdd = false, leftAdd2 = false, rightAdd2 = false;
	public static float addX, addY, centerLeft1AddX, centerAdd1Y, centerRight1AddX, centerLeft2AddX, centerAdd2Y, centerRight2AddX;
	public static boolean alterner = true;
	public static float angleAdd = -90, angleAddDroite = -90;
	public static boolean bouclier = false;
	public static float alphaShield = .5f;
	private boolean sensAlpha = true;
	
	public Player() {
		super();
		initialiser();
	}

	/**
	 * positionne et initialise tout
	 */
	public void initialiser() {
		bouclier = false;
		reInit();
		rightAdd2 = false;
	    rightAdd = false;
	    leftAdd2 = false;
	    leftAdd = false;
	    angleAdd = 80;
	    prochainTirAdd = 0;
	}

	/**
	 * Positionne mais ne reset ni adds ni shield
	 */
	public void reInit() {
		POS.x = CSG.gameZoneHalfWidth - DEMI_LARGEUR;
		POS.y = HAUTEUR/2;
		prochainTir = 0;
		prevX = POS.x;
		prevY = POS.y;
		vitesseMax = (Stats.V_JOUEUR);
		xCenter = POS.x + DEMI_LARGEUR;
		yCenter = POS.y + DEMI_HAUTEUR;
		if (CSG.profile.typeControle == CSG.CONTROLE_ACCELEROMETRE) {
			originalAccelX = Gdx.input.getAccelerometerX();
			originalAccelY = Gdx.input.getAccelerometerY();
		}
		bouclierHS = false;
		tpsBouclierHs = 0;
	}
	
	private static final OvaleParticuleGenerator bouclierParticules = new OvaleParticuleGenerator(HAUTEUR);
	private static boolean bouclierHS = false;
	private static float tpsBouclierHs = 0;
	private float r = 1, g = 1, b = 1;
	private int cpt = 0;
	public void draw(SpriteBatch batch) {
		Particles.ajoutFlammes(this);
		if (CSG.alternateGraphics) {
			if (++cpt > 60) {
				cpt = 0;
				r = CSG.R.nextFloat();
				g = CSG.R.nextFloat();
				b = CSG.R.nextFloat();
			}
			switch (AnimPlayer.etat) {
			case 0:		case 1:		Particles.addThrusterParticle(POS.x + LARGEUR, pos.y + DEMI_HAUTEUR_ADD, false);	break;
			case 3:		case 4:		Particles.addThrusterParticle(POS.x, pos.y + DEMI_HAUTEUR_ADD, true);				break;
			}
			batch.setColor(r,g,b,1);
			batch.draw(AssetMan.player, POS.x, POS.y, LARGEUR, HAUTEUR);
			batch.setColor(AssetMan.WHITE);
		} else {
			batch.draw(AnimPlayer.getTexture(), POS.x, POS.y, LARGEUR, HAUTEUR);
		}
		
		shield();
		// ** ** A D D S
		if (leftAdd) 	batch.draw(AssetMan.addShip, addX - DEMI_LARGEUR, addY - DEMI_HAUTEUR, DEMI_LARGEUR_ADD, DEMI_HAUTEUR/2, LARGEUR_ADD, DEMI_HAUTEUR, 2f, 0.5f, angleAdd, false);
		if (rightAdd) 	batch.draw(AssetMan.addShip, addX + DECALAGE_ADD, addY - DEMI_HAUTEUR, DEMI_LARGEUR_ADD, DEMI_HAUTEUR/2, LARGEUR_ADD, DEMI_HAUTEUR, 2f, 0.5f, angleAddDroite, false);
		if (leftAdd2) 	batch.draw(AssetMan.addShip, addX - LARGEUR, addY - HAUTEUR, DEMI_LARGEUR_ADD, DEMI_HAUTEUR/2, LARGEUR_ADD, DEMI_HAUTEUR, 2f, 0.5f, angleAdd, false);
		if (rightAdd2) 	batch.draw(AssetMan.addShip, addX + DECALAGE_ADD + DEMI_LARGEUR, addY - HAUTEUR, DEMI_LARGEUR_ADD, DEMI_HAUTEUR/2, LARGEUR_ADD, DEMI_HAUTEUR, 2f, 0.5f, angleAddDroite, false);
	}

	private void shield() {
		if (bouclier) {
			bouclierParticules.add(xCenter , yCenter - Stats.U);
			colorShield();
		} else if (bouclierHS) {
			colorShield();
			tpsBouclierHs += EndlessMode.delta;
			if (tpsBouclierHs > 1f)
				bouclierHS = false;
			bouclierParticules.grow(EndlessMode.unPlusDelta3);
			bouclierParticules.add(xCenter , yCenter - Stats.U);
		}
	}

	private void colorShield() {
		if (alphaShield > .95f) sensAlpha = false;
		if (alphaShield < .55f) sensAlpha = true;
		
		if (sensAlpha) alphaShield += EndlessMode.delta15;
		else alphaShield -= EndlessMode.delta15;
	}

	private int getTouchY() {
		return CSG.SCREEN_HEIGHT - Gdx.input.getY();
	}

	private float getTouchX() {
		return(Gdx.input.getX() - DEMI_LARGEUR);
//		return EndlessMode.getCam().position.x + (Gdx.input.getX() - DEMI_LARGEUR);
	}
	
	private float clicX = 0, clicY = 0, originalClicX = CSG.gameZoneHalfWidth, originalClicY = CSG.halfHeight, wantedMvtX, wantedMvtY, chronoDroit;
	/**
	 * Fait aller le vaisseau � l'endroit cliqu�.
	 * Si il peut se teleporter il y va directement -- Sinon il se d�place suivant sa vitesse max
	 */
	public static float camXmoinsDemiEcran = //EndlessMode.getCam().position.x - 
			CSG.screenHalfWidth;
	public void mouvements() {
		xCenter = POS.x + DEMI_LARGEUR;
		yCenter = POS.y + DEMI_HAUTEUR;
		switch (CSG.profile.typeControle) {

		case CSG.CONTROLE_DPAD:
//			destX = Gdx.input.getX() - prevX;
//			destY = -(Gdx.input.getY() - prevY);
//			destX *= 4 * CSG.profile.sensitivity;
//			destY *= 4 * CSG.profile.sensitivity;
//			mvtLimiteVitesse(destX, destY);
//			break;
		case CSG.CONTROLE_TOUCH_NON_RELATIVE: 
		case CSG.CONTROLE_TOUCH_RELATIVE: // recup d'autres float

//			destX = Gdx.input.getX() - prevX;
//			destY = -(Gdx.input.getY() - prevY);
//			
//			destX /= (EndlessMode.delta );
//			destY /= (EndlessMode.delta );
//			
//			prevX = originalAccelX;
//			prevY = originalAccelY;
//			
//			originalAccelX = Gdx.input.getX();
//			originalAccelY = Gdx.input.getY();
//			
//			if (Gdx.input.justTouched()) {
//				prevX = destX = originalAccelX;
//				prevY = destY = originalAccelY;
//			} 
//			alterner = !alterner;
//			mvtLimiteVitesse(destX, destY);
			if (Gdx.input.justTouched()) {
				originalClicX = getTouchX();
				originalClicY = getTouchY();
				break;
			}
			
			clicX = getTouchX();			
			clicY = getTouchY();
			
			wantedMvtX = (originalClicX - clicX); 
			wantedMvtY = (originalClicY - clicY);
			
			POS.x -= wantedMvtX * CSG.profile.sensitivity;
			POS.y -= wantedMvtY * CSG.profile.sensitivity;
			
			if (wantedMvtX > 0) {
				chronoDroit = 0;
				AnimPlayer.versLaGauche();
				
//				tmp = (EndlessMode.getCam().position.x + CSG.screenHalfWidth) - xCenter;
//				if (EndlessMode.getCam().position.x > CSG.screenHalfWidth) {
//					EndlessMode.getCam().position.x -= tmp * EndlessMode.delta;
//				}
			} else if (wantedMvtX < 0) { 
				
//				if (xCenter > EndlessMode.getCam().position.x) {
//					EndlessMode.getCam().position.x += tmp * EndlessMode.delta;
//				}
				AnimPlayer.versLaDroite();
				chronoDroit = 0; 
			} else {
				chronoDroit += EndlessMode.delta;
				if (chronoDroit > 0.3f)
					AnimPlayer.droit();
			}
			originalClicX = getTouchX();
			originalClicY = getTouchY();
			routineAdds();
//			mvtLimiteVitesse(wantedMvtX, wantedMvtY);
			break;
		}
		limitesEtCentre();
	}

	/**
	 * Le vaisseau se deplace vers les coordonn�es pass�es avec un cap � sa vitesse max
	 * @param x
	 * @param y
	 */
	public void mvtLimiteVitesse(float x, float y) {
		// haut gauche : +x -y

		tmpCalculDeplacement = ((x * x) + (y * y)) * EndlessMode.delta * EndlessMode.delta;
		if(tmpCalculDeplacement < DEGRE_PRECISION_DEPLACEMENT){
			AnimPlayer.droit();
			return;
		}
		tmpCalculDeplacement = (float) Math.sqrt(tmpCalculDeplacement);

		vitesseFoisdelta = vitesseMax * EndlessMode.delta;
		// Si on va trop vite
		if (tmpCalculDeplacement > vitesseFoisdelta) {
			x = x * (vitesseFoisdelta / tmpCalculDeplacement);
			y = y * (vitesseFoisdelta / tmpCalculDeplacement);
		} 
		
		if (x < 0) {
			AnimPlayer.versLaGauche();
//			EndlessMode.mvtCamPositive(EndlessMode.delta * x * 0.35f);
		}
		if (x > 0) {
			AnimPlayer.versLaDroite();
//			EndlessMode.mvtCamNegative(EndlessMode.delta * x * 0.35f);
		}
		
		POS.x += (x * EndlessMode.delta);
		POS.y += (y * EndlessMode.delta);	// Maj position

		routineAdds();
	}
	
	private void routineAdds() {
		addX = POS.x;
		if (addY > POS.y + HAUTEUR_MAX_ADD) {
			addY = POS.y + HAUTEUR_MAX_ADD;
			angleAdd = -80;
			angleAddDroite = -100; 
		} else if (addY < POS.y) {
			addY = POS.y;
			angleAdd = 80;
			angleAddDroite = 100;
		} else {		// L'angle a une amplitude de 160° qui varie à gauche entre -80 et 80 et à droite entre -100 et 100 car on tourne dans l'autre sens 
			angleAdd = addY - POS.y;
			angleAdd /= HAUTEUR_MAX_ADD;
			angleAdd *= 160;
			angleAddDroite = -angleAdd - 100; // Lui tourne dans l'autre sens (d'où le -) et doit décaler de 110 et pas 80 (C'est logique avec le petit dessin devant soi :) ). Ca fait 5 lignes, une multiplication, une division et une soustraction en moins par rapport à l'original
			angleAdd -= 80;
			angleAdd = -angleAdd;	// Car il fait son flip autour de 0
			angleAddDroite = -angleAddDroite; // Bon il doit y avoir moyen de se passer de ses inversions !
		}
		centerAdd2Y = (addY - HAUTEUR) + DEMI_HAUTEUR_ADD;
		centerRight2AddX = (addX + DECALAGE_ADD + DEMI_LARGEUR) + DEMI_LARGEUR_ADD;
		
		centerAdd2Y = (addY - HAUTEUR) + DEMI_HAUTEUR_ADD;
		centerLeft2AddX = (addX - LARGEUR) + DEMI_LARGEUR_ADD;
		
		centerRight1AddX = (addX + DECALAGE_ADD) + DEMI_LARGEUR_ADD;
		centerAdd1Y = (addY - DEMI_HAUTEUR) + DEMI_HAUTEUR_ADD;
		
		centerLeft1AddX = (addX - DEMI_LARGEUR) + DEMI_LARGEUR_ADD;
		centerAdd1Y = (addY - DEMI_HAUTEUR) + DEMI_HAUTEUR_ADD;
	}

	/**
	 * Le vaisseau va aux coordonn�es pass�es
	 * @param x
	 * @param y
	 */
	@SuppressWarnings("unused")
	private void mvtTeleport(int x, int y) {
		POS.x = x;
		POS.y = y;
	}
	
	/**
	 * oblige le vaisseau a rester dans les limites de l'�cran
	 */
	private void limitesEtCentre() {
		xCenter = POS.x + DEMI_LARGEUR;
		yCenter = POS.y + DEMI_HAUTEUR;
		
		if (POS.x < LIMITE_X_GAUCHE) 		POS.x = LIMITE_X_GAUCHE;
		else if (POS.x > LIMITE_X_DROITE) 	POS.x = LIMITE_X_DROITE;
		if (POS.y < LIMITE_Y_GAUCHE) 		POS.y = LIMITE_Y_GAUCHE;
		else if (POS.y > LIMITE_Y_DROITE)	POS.y = LIMITE_Y_DROITE;
		
		if (EndlessMode.getCam().position.x > CSG.gameZoneWidth - CSG.screenHalfWidth)
			EndlessMode.getCam().position.x = CSG.gameZoneWidth - CSG.screenHalfWidth;
		if (EndlessMode.getCam().position.x < CSG.screenHalfWidth)
			EndlessMode.getCam().position.x = CSG.screenHalfWidth;
	}

	/**
	 * verifie si le vaisseau peut tirer ou pas. Tir au cas ou.
	 * Vu qu'on appele tir une frame sur deux adapter la cadence en cons�quence
	 * @param listeTir
	 */
	public void tir(){
		prochainTir = weapon.init(prochainTir);
		// ** ** A D D S
		if (EndlessMode.now > prochainTirAdd) {
			if (leftAdd) {
				ArmeAdd.add(addX + DECALAGE_TIR_ADD_X_GAUCHE , addY - DEMI_HAUTEUR, angleAdd);
				if (CSG.profile.cadenceAdd > 3) 
					ArmeAdd.add(addX + DECALAGE_TIR_ADD_X_GAUCHE , addY - DEMI_HAUTEUR, angleAdd + 10);
			}
			if (rightAdd) {
				ArmeAdd.add(addX + DECALAGE_TIR_ADD_X_DROITE , addY - DEMI_HAUTEUR, angleAddDroite);
				if (CSG.profile.cadenceAdd > 3) 
					ArmeAdd.add(addX + DECALAGE_TIR_ADD_X_DROITE , addY - DEMI_HAUTEUR, angleAddDroite - 10);
			}
			if (leftAdd2) {
				ArmeAdd.add(addX + DECALAGE_TIR_ADD_X_GAUCHE - DEMI_LARGEUR , addY - HAUTEUR, angleAdd);
				if (CSG.profile.cadenceAdd > 6)
					ArmeAdd.add(addX + DECALAGE_TIR_ADD_X_GAUCHE - DEMI_LARGEUR , addY - HAUTEUR, angleAdd+10);
			}
			if (rightAdd2) {
				ArmeAdd.add(addX + DECALAGE_TIR_ADD_X_DROITE + DEMI_LARGEUR , addY - HAUTEUR, angleAddDroite);
				if (CSG.profile.cadenceAdd > 6)
					ArmeAdd.add(addX + DECALAGE_TIR_ADD_X_DROITE + DEMI_LARGEUR , addY - HAUTEUR, angleAddDroite-10);
			}
			prochainTirAdd = EndlessMode.now + ArmeAdd.CADENCETIR;
		}
	}
	
	public static void rajoutAdd() {
		if (!Player.leftAdd) {
			leftAdd = true;
		} else if (!Player.rightAdd) {
			rightAdd = true;
		} else if (!Player.leftAdd2) {
			leftAdd2 = true;
		} else if (!Player.rightAdd2) {
			rightAdd2 = true;
		}
	}
	
	public static void changerArme(){
		weapon = WeaponManager.changerArme(weapon);
		CSG.profile.setArmeSelectionnee(weapon.getLabel());
	}

	public static void touched() {
		if (EndlessMode.konamiCode) return;
		if (!bouclier) {
			if (!bouclierHS)
				EndlessMode.lost();
		} else {
			bouclierHS = true;
			tpsBouclierHs = 0;
			SoundMan.playBruitage(SoundMan.bigExplosion);
			bouclier = false;
			Weapons.shieldHs();
		}
	}

	@Override
	public int getWidth() {		return LARGEUR;	}

	@Override
	public int getHeight() {		return HAUTEUR;	}

	public void accelerometre() {
		mvtLimiteVitesse(-((Gdx.input.getAccelerometerX()-originalAccelX) * 140 * CSG.profile.sensitivity), -((Gdx.input.getAccelerometerY()-originalAccelY) * 140 * CSG.profile.sensitivity));
		limitesEtCentre();
	}

	public static void removeLeftAdd1() {
		Particles.explosionGreen(addX - DEMI_LARGEUR, addY - DEMI_HAUTEUR, 10);
		leftAdd = false;
	}
	public static void enleverAddDroite1() {	
		Particles.explosionGreen(addX + DECALAGE_ADD, addY - DEMI_HAUTEUR, 10);
		rightAdd = false;	
	}
	public static void removeLeftAdd2() {	
		Particles.explosionGreen(addX - LARGEUR, addY - HAUTEUR, 10);
		leftAdd2 = false;	
	}
	public static void enleverAddDroite2() {	
		Particles.explosionGreen(addX + DECALAGE_ADD + DEMI_LARGEUR, addY - HAUTEUR, 10);
		rightAdd2 = false;
	}

	public static void activateShield() {
		bouclierParticules.init(HAUTEUR);
		bouclier = true;
	}

	@Override
	public int getHalfWidth() {
		return DEMI_LARGEUR;
	}

	@Override
	public int getHalfHeight() {
		return DEMI_HAUTEUR;
	}

	@Override
	protected TextureRegion getTexture() {
		return AssetMan.blueBullet;
	}

	public static void touchedEnnemy(Enemy enemy) {
		if (!bouclier) {
			if (!bouclierHS)
				EndlessMode.lost();
		} else {
			bouclierHS = true;
			tpsBouclierHs = 0;
			SoundMan.playBruitage(SoundMan.bigExplosion);
			bouclier = false;
			enemy.stillAlive(Enemy.superBomb);
		}
	}

}
//case CSG.CONTROLE_TOUCH_NON_RELATIVE: 
//clicX = getTouchX();			
//clicY = getTouchY();
//destX = clicX - (POS.x - (camXmoinsDemiEcran) );
//destX *= 10000;
//destY = clicY - POS.y;
//destY *= 10000;
//// ****** NORMALISATION DE LA VITESSE SUIVANT LA LIMITE
//tmpCalculDeplacement = ((destX * destX) + (destY * destY)) * EndlessMode.delta * EndlessMode.delta;
//tmpCalculDeplacement = (float) Math.sqrt(tmpCalculDeplacement);
//vitesseFoisdelta = vitesseMax * EndlessMode.delta;
//// Si on va trop vite
//if (tmpCalculDeplacement > vitesseFoisdelta) {
//	destX = destX * (vitesseFoisdelta / tmpCalculDeplacement);
//	destY = destY * (vitesseFoisdelta / tmpCalculDeplacement);
//} 
//// CALCUL AFFICHAGE
//if (destX < -.9f) {
//	AnimationVaisseau.versLaGauche();
////	if (EndlessMode.getCam().position.x > CSG.screenHalfWidth)
////		EndlessMode.mvtCamSuivantDeplacement();
//		
//} else if (destX > .9f) {
//	AnimationVaisseau.versLaDroite();
////	if (EndlessMode.getCam().position.x < CSG.DEMI_CAMERA) 		
////		EndlessMode.mvtCamSuivantDeplacement();
//}
//else 					AnimationVaisseau.droit();
//
//if (POS.y < clicY && (POS.y + destY * EndlessMode.delta) > clicY || POS.y > clicY && (POS.y + destY * EndlessMode.delta) < clicY)		POS.y = clicY;
//else 				POS.y += destY * EndlessMode.delta;
//routineAdds();
//// On est à gauche		&		on est à droite // Si on était à gauche et qu'après on se retrouve à droite ou inversement on met directement la position à l'endroit ou on a cliqué
//if (POS.x - camXmoinsDemiEcran < clicX & ( (POS.x - camXmoinsDemiEcran) + destX * EndlessMode.delta) > clicX 
//		|| POS.x - camXmoinsDemiEcran > clicX & ((POS.x - camXmoinsDemiEcran) + destX * EndlessMode.delta) < clicX){ 
//	POS.x = clicX + camXmoinsDemiEcran;
//} else {
//	POS.x += destX * EndlessMode.delta;
//}
//
//routineAdds();
//break;
//clicX = getTouchX();			
//clicY = getTouchY();
//
//
//wantedMvtX = (originalClicX - clicX) - mvtSinceTouchX; 
//wantedMvtY = (originalClicY - clicY) - mvtSinceTouchY;
//
//// tmp
//POS.x -= wantedMvtX*1.5f;
//POS.y -= wantedMvtY;
//
//if (EndlessMode.getCam().position.x > CSG.screenHalfWidth && EndlessMode.getCam().position.x < CSG.DEMI_CAMERA) {
//	EndlessMode.getCam().position.x -= wantedMvtX; 
//}
//originalClicX = getTouchX();
//originalClicY = getTouchY();
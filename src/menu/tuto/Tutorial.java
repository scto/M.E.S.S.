package menu.tuto;

import jeu.CSG;
import jeu.EndlessMode;
import jeu.Physique;
import jeu.Stats;
import jeu.Strings;
import objets.armes.Armes;
import objets.bonus.Bonus;
import objets.bonus.BonusBombe;
import objets.bonus.BonusStop;
import objets.bonus.XP;
import objets.ennemis.Ennemis;
import objets.ennemis.particuliers.nv1.DeBase;
import objets.joueur.VaisseauJoueur;
import assets.particules.Particules;
import bloom.Bloom;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Tutorial implements Screen {
	
	private SpriteBatch batch = CSG.batch;
	private Bloom bloom;
	private float xP1 = CSG.LARGEUR_ECRAN;
	private float alphaBackground = 0.02f, vitesseMontee = 200f, alphaShip = 0.02f, timerPhase = 0;
	private VaisseauJoueur vaisseau = new VaisseauJoueur();
	private int phase = 0;
	private boolean alterner = false;
	private DeBase deBase1 = DeBase.POOL.obtain(), deBase2 = DeBase.POOL.obtain();
	
	private Action apparitationBackground = new Action() {
		public void action() {
			alphaBackground *= 1 + Gdx.graphics.getDeltaTime();
			if (alphaBackground > 1)
				alphaBackground = 1;
			if (phase == 0 && VaisseauJoueur.position.y < VaisseauJoueur.HAUTEUR - 2) {
				if (VaisseauJoueur.position.y < VaisseauJoueur.HAUTEUR)
					VaisseauJoueur.position.y += vitesseMontee * Gdx.graphics.getDeltaTime();
				vitesseMontee /= 1 + (Gdx.graphics.getDeltaTime() * 1.5f);
			}
		}
	};
	private Action txtTouchToMoveShip = new Action() {
		public void action() {
			batch.begin();
			CSG.menuFont.setColor(1, 1, .8f, alphaShip);
			CSG.menuFont.draw(batch, Strings.SHIP, CSG.DEMI_LARGEUR_ZONE_JEU - (CSG.menuFont.getBounds(Strings.SHIP).width / 2), CSG.DEMI_HAUTEUR_ECRAN);
			CSG.menuFont.setColor(originalColor);
			batch.end();
			alphaShip *= 1 + Gdx.graphics.getDeltaTime();
			if (alphaShip > 1)
				alphaShip = 1;
		}
	};
	private Action bougerVaisseau = new Action() {		public void action() {
		batch.begin();
		EndlessMode.mouvement();
		EndlessMode.strScore = String.valueOf(EndlessMode.score);
		batch.end();
		}	};
	private Action tirVaisseau = new Action() {		public void action() {
		if (alterner)
			vaisseau.tir();
		alterner = !alterner;
		}
	};
	private Action txtPresentationTuto = new Action() {		public void action() {
		batch.begin();
		CSG.menuFontPetite.draw(batch, Strings.P1, xP1, CSG.menuFontPetite.getBounds(Strings.P1).height + 2);
		batch.end();
		xP1 -= Gdx.graphics.getDeltaTime() * 40;
			if (xP1 < (-CSG.menuFontPetite.getBounds(Strings.P1).width)) {
				xP1 = CSG.LARGEUR_ECRAN;
			}
		}
	};
	private Action ajout2EnnemisDeBase = new Action() {
		public void action() {	
			if (Ennemis.LISTE.size == 0) {
				Ennemis.LISTE.add(deBase1);
				deBase1.position.x = CSG.DEMI_LARGEUR_ZONE_JEU - Stats.LARGEUR_DE_BASE;
				Ennemis.LISTE.add(deBase2);
				deBase2.position.x = CSG.DEMI_LARGEUR_ZONE_JEU + Stats.LARGEUR_DE_BASE;
			}
	}};
	private Action txtShootEnnemi = new Action() {
		public void action() {	
			batch.begin();
			CSG.menuFont.draw(batch, Strings.SHOOTHIM, deBase1.position.x - (CSG.menuFont.getBounds(Strings.SHOOTHIM).width/2), deBase1.position.y + deBase1.getHauteur() * 2);
			batch.end();
	}};
	private Action afficherPoints = new Action() {
		public void action() {	
			batch.begin();
			CSG.menuFontPetite.draw(batch, EndlessMode.strScore, EndlessMode.cam.position.x + EndlessMode.X_CHRONO, EndlessMode.HAUTEUR_POLICE);
			batch.end();
	}};
	private Action afficherGgEtAddXp = new Action() {
		public void action() {
			if (EndlessMode.score == 0) 
				Bonus.list.add(XP.pool.obtain());
			batch.begin();
			CSG.menuFont.draw(batch, Strings.GG, EndlessMode.cam.position.x + EndlessMode.X_CHRONO, EndlessMode.HAUTEUR_POLICE * 3);
			batch.end();
	}};
	private Action enleverBonusSaufXp = new Action() {
		@Override
		public void action() {
			for (int i = 0; i < Bonus.list.size; i++)
				if (! (Bonus.list.get(i) instanceof XP))
					Bonus.list.removeIndex(i);
		}
	};
	private Action ajouterStopTime = new Action() {
		@Override
		public void action() {
			Bonus.list.clear();
			BonusStop.pool.obtain().init(CSG.DEUX_CINQUIEME_ECRAN, CSG.HAUTEUR_ECRAN);
			BonusStop.pool.obtain().init(CSG.TROIS_CINQUIEME_ECRAN, CSG.HAUTEUR_ECRAN);
			BonusStop.pool.obtain().init(CSG.QUATRE_CINQUIEME_ECRAN, CSG.HAUTEUR_ECRAN);
		}
	};
	private Action txtStopTime = new Action() {
		@Override
		public void action() {
			batch.begin();
			if (Bonus.list.size > 0)
				CSG.menuFont.draw(batch, Strings.TAKE_BONUS, Bonus.list.get(0).posX - (CSG.menuFont.getBounds(Strings.TAKE_BONUS).width/2), Bonus.list.get(0).posY + deBase1.getHauteur() * 2);
			batch.end();
		}
	};
	private Action ajoutPleinEnnemis = new Action() {
		@Override
		public void action() {
			for (int i = 0; i < 16; i++)
				Ennemis.LISTE.add(DeBase.POOL.obtain());
		}
	};
	private Color originalColor = CSG.menuFont.getColor();
	
	private Phase p0 = new Phase(apparitationBackground);
	
	private Phase p1MouvementDemande = new Phase(	apparitationBackground,
			txtTouchToMoveShip);
	
	private Phase p2MouvementPermis = new Phase(	apparitationBackground,	bougerVaisseau);
	
	private Phase p3Tir = new Phase(				apparitationBackground,	bougerVaisseau,	tirVaisseau);
	
	private Phase p4ApparitionEnnemi = new Phase(	apparitationBackground,	bougerVaisseau,	tirVaisseau,
			ajout2EnnemisDeBase,
			txtShootEnnemi);
	
	private Phase p5EnnemisDescendant = new Phase(	apparitationBackground,	bougerVaisseau,	tirVaisseau, afficherPoints,
			txtShootEnnemi);
	
	private Phase p6EnnemisMorts = new Phase(		apparitationBackground,	bougerVaisseau,	tirVaisseau, afficherPoints,
			afficherGgEtAddXp,
			enleverBonusSaufXp);
	
	private Phase p7AjoutTime = new Phase(		apparitationBackground,	bougerVaisseau,	tirVaisseau, afficherPoints,
			ajouterStopTime);
	
	private Phase p8txtTime = new Phase(			apparitationBackground,	bougerVaisseau,	tirVaisseau, afficherPoints,
			txtStopTime);
	
	private Phase p9ajoutEnnemis = new Phase(			apparitationBackground,	bougerVaisseau,	tirVaisseau, afficherPoints,
			ajoutPleinEnnemis);
	
	private Phase p10 = new Phase(			apparitationBackground,	bougerVaisseau,	tirVaisseau, afficherPoints
			);
	
	private final Phase[] phases = {
			p0,
			p1MouvementDemande, 
			p2MouvementPermis, 
			p3Tir, 
			p4ApparitionEnnemi, 
			p5EnnemisDescendant, 
			p6EnnemisMorts,
			p7AjoutTime,
			p8txtTime,
			p9ajoutEnnemis,
			p10};

	public Tutorial(Game game) {
		bloom = CSG.bloom;
		VaisseauJoueur.position.x = CSG.DEMI_LARGEUR_ZONE_JEU - VaisseauJoueur.DEMI_LARGEUR;
		VaisseauJoueur.position.y = -VaisseauJoueur.HAUTEUR;
		CSG.reset();
		EndlessMode.cam.position.set(CSG.LARGEUR_ZONE_JEU/2, CSG.HAUTEUR_ECRAN / 2, 0);
	}

	@Override
	public void render(float delta) {
		EndlessMode.cam.update();
		batch.setProjectionMatrix(EndlessMode.cam.combined);
		
		CSG.begin(delta);
		batch.begin();
		
		background(delta);
		vaisseau.draw(batch);
		Ennemis.affichageEtMouvement(batch);
		Bonus.drawAndMove(batch);
		Armes.affichageEtMouvement(batch);
		Particules.render(batch);
		batch.end();
		bloom.render();
		
		phases[phase].act();
		timerPhase += delta;
		triggers(delta);
		Physique.testCollisions();
	}

	private void triggers(float delta) {
		// Quand le vaisseau est en place
		switch (phase) {
		case 0:		if (VaisseauJoueur.position.y > VaisseauJoueur.HAUTEUR - 2 && phase == 0)	nextPhase();	break;
		case 1:		if (Gdx.input.justTouched())												nextPhase();	break;
		case 2:		if (timerPhase > .8f)														nextPhase();	break;
		case 3:		if (timerPhase > .8f)														nextPhase();	break;
		case 4:		if (timerPhase > .8f && Ennemis.LISTE.size < 2)								nextPhase();	break;
		case 5:		if (Bonus.list.size > 0)													nextPhase();	break;
		case 6:		if (timerPhase > 3f)														nextPhase();	break;
		case 7:		if (Bonus.list.size > 0)													nextPhase();	break;
		case 8:		if (Bonus.list.size == 0)													nextPhase();	break;
		case 9:		if (Ennemis.LISTE.size > 1)													nextPhase();	break;
		}
	}

	private void nextPhase() {
		timerPhase = 0;
		phase++;
		System.out.println(phase);
	}
	private void background(float delta) {
		batch.setColor(1, 1, 1, alphaBackground);
		Particules.background(batch);
		batch.setColor(1,1,1,1);
	}
	@Override
	public void resize(int width, int height) {	}
	@Override
	public void show() {	}
	@Override
	public void hide()  {	}
	@Override
	public void pause()  {	}
	@Override
	public void resume()  {	}
	@Override
	public void dispose()  {}
}

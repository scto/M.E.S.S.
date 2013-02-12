package jeu;
/**
 */
import menu.CSG;
import menu.Menu;
import physique.Physique;
import sons.SoundMan;
import vaisseaux.armes.Armes;
import vaisseaux.bonus.Bonus;
import vaisseaux.ennemis.Ennemis;
import vaisseaux.joueur.VaisseauType1;
import affichage.ParallaxBackground;
import affichage.animation.AnimationVaisseau;
import bloom.Bloom;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Classe principale gérant le mode infini du jeu
 * @author Julien
 *
 */
public class Endless implements Screen {
	
	public static long debut;
	private Game game;
	// ** ** affichage
	private BitmapFont font;
	private SpriteBatch batch;
	private GL20 gl;
	private ParallaxBackground rbg = CSG.getBackground();
	private VaisseauType1 vaisseau = new VaisseauType1();
	private Chrono chrono;
	//private CollisionTester collision;
	private boolean alterner = true;
	private boolean alternerApparition = true;
	private boolean perdu = false;
	//private long temps = 0;
	private String champChrono = "Top départ !";
	private static float chronoRalentir = 0;
	private static String champChronoRalentir = "zZz ";
	private boolean activerRalentissement = false;
	private long vientDEtreTouche = 0;
	private Bloom bloom = new Bloom();
	private static final int X_CHRONO = CSG.HAUTEUR_ECRAN - (CSG.HAUTEUR_ECRAN/30);
	
	// Attention au delta
	public static float delta = 0;

	public Endless(Game game) {
		super();
		batch = new SpriteBatch();
		this.game = game;
		debut = System.currentTimeMillis();
		font = new BitmapFont();
        font.setColor(Color.GREEN);

        gl = Gdx.graphics.getGL20();
		gl.glViewport(0, 0, CSG.LARGEUR_ECRAN, CSG.HAUTEUR_ECRAN);
		CSG.resetLists();
        
        chrono = new Chrono(2000);
        chrono.demarrer(this);

        Gdx.graphics.setVSync(false);
        
        SoundMan.playMusic();
	}

	@Override
	public void render(float delta) {
		// ** ** clear screen
		gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		bloom.capture();
		rbg.render();
		batch.begin();
		Endless.delta = delta;
		if(!perdu){
			// bullet time !
			if (activerRalentissement) {
				chronoRalentir -= delta/3;
				Endless.delta /= 3;
				champChronoRalentir = chronoRalentir + "s";
				if(chronoRalentir < 0){
					activerRalentissement = false;
					chronoRalentir = 0;
					// pour mettre champ txt à jour
					ralentir(0);
				}
			}
			// ** ** batch
			Bonus.affichageEtMouvement(batch);
			Ennemis.affichageEtMouvement(batch);
			vaisseau.draw(batch);
			Armes.affichageEtMouvement(batch);
		} else {
			Ennemis.affichage(batch);
			Armes.affichage(batch);
			vaisseau.draw(batch);
		}		
		font.draw(batch, champChrono, 0, X_CHRONO);
		font.draw(batch, String.valueOf(Gdx.graphics.getFramesPerSecond()), 300, 300);
		font.draw(batch, CSG.profil.champXp, CSG.LARGEUR_ECRAN - CSG.DEMI_LARGEUR_ECRAN, X_CHRONO);
		font.draw(batch, champChronoRalentir, CSG.LARGEUR_ECRAN - CSG.CINQUIEME_ECRAN, X_CHRONO);
		batch.end();
		bloom.render();
		// ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** 
		// ** ** UPDATE inliné. Gain en moyenne : + 2.5fps sur 450 (test sur 8 fois sur 5 min)
		// ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** 
		if (!perdu) {
			if(Gdx.input.justTouched()) {
				// Si oui c'est un double tap
				if (System.currentTimeMillis() - vientDEtreTouche < 500) activerRalentissement = true;
				vientDEtreTouche = System.currentTimeMillis();
			}
			if (Gdx.input.isTouched()) {
				vaisseau.mouvements();
			} else {
				AnimationVaisseau.droit();
			}
//			else {
//				affichage.ParallaxBackground.changerOrientation(0);
//			}
			if (alterner) {
				if (alternerApparition)
					Ennemis.possibleApparition(chrono.getTempsEcoule());
//					perdu = 
						Physique.testCollisions(vaisseau);
				// ** ** tir joueur
				vaisseau.tir();
				alternerApparition = !alternerApparition;
			}
			alterner = !alterner;
		} else {
			if(Gdx.input.justTouched()){
				Menu menu = new Menu(game);
				game.setScreen(menu);
			}
		}
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void show() {
	}

	@Override
	public void hide() {
	}

	@Override
	public void pause() {
		CSG.profilManager.persist();
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {

	}

	public boolean getPerdu() {
		return perdu;
	}

	/**
	 * Le chrono met à jour le temps. C'est ainsi car ça évite de lui demander à chaque frame et de devoir créer un string.
	 * J'aurai aussi pu stocker un champ string dans le chrono mais alors il fallait appeler la méthode à chaque fois quand même
	 * @param nbSecondes
	 */
	public void updateTemps(long nbSecondes) {
		champChrono = "Time : " + nbSecondes;
	}

	public static void ralentir(float i) {
		chronoRalentir += i;
		champChronoRalentir = "zZz " + chronoRalentir;
	}

}

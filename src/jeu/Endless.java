package jeu;

import menu.CSG;
import menu.Menu;
import physique.Physique;
import vaisseaux.armes.Armes;
import vaisseaux.bonus.Bonus;
import vaisseaux.ennemis.Ennemis;
import vaisseaux.ennemis.particuliers.EnnemiPorteNef;
import vaisseaux.joueur.VaisseauType1;
import affichage.ParallaxBackground;
import bloom.Bloom;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Classe principale gérant le mode infini du jeu
 * @author Julien
 *
 */
public class Endless implements Screen {
	
	private Game game;
	public static long debut;
	// ** ** affichage
	private BitmapFont font;
	private SpriteBatch batch;
	private GL20 gl;
	// ** ** vaisseaux et armes
	private VaisseauType1 vaisseau = new VaisseauType1();
	// OLD private List<Armes> listeTirs = new ArrayList<Armes>();
	private boolean pause = true;
	private ParallaxBackground rbg = CSG.getBackground();
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

	public Endless(Game game) {
		super();
		batch = new SpriteBatch();
		this.game = game;
		debut = System.currentTimeMillis();
		font = new BitmapFont();
        font.setColor(Color.GREEN);

        gl = Gdx.graphics.getGL20();
		gl.glViewport(0, 0, CSG.LARGEUR_ECRAN, CSG.HAUTEUR_ECRAN);
		pause = true;
		CSG.resetLists();
        
        chrono = new Chrono(2000);
        chrono.demarrer(this);

       // Gdx.graphics.setVSync(false);
	}

	@Override
	public void render(float delta) {
		// ** ** clear screen
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		bloom.capture();
		rbg.render(delta);
		if(!pause){
			batch.begin();
			if(!perdu){
				// bullet time !
				if (activerRalentissement) {
					chronoRalentir -= delta;
					delta /= 3;
					champChronoRalentir = chronoRalentir + "s";
					if(chronoRalentir < 0){
						activerRalentissement = false;
						chronoRalentir = 0;
						// pour mettre champ txt à jour
						ralentir(0);
					}
				}
				// ** ** batch
				Bonus.affichageEtMouvement(batch, delta);
				Ennemis.affichageEtMouvement(batch, delta);
				vaisseau.draw(batch, delta);
				Armes.affichageEtMouvement(batch, delta);
			} else {
				Ennemis.affichage(batch, delta);
				Armes.affichage(batch, delta);
				vaisseau.draw(batch, delta);
			}		
			font.draw(batch, champChrono, 0, CSG.HAUTEUR_ECRAN - 5);
			font.draw(batch, String.valueOf(Gdx.graphics.getFramesPerSecond()), 300, 300);
			font.draw(batch, CSG.profil.champXp, CSG.LARGEUR_ECRAN - CSG.DEMI_LARGEUR_ECRAN, CSG.HAUTEUR_ECRAN - 5);
			font.draw(batch, champChronoRalentir, CSG.LARGEUR_ECRAN - CSG.CINQUIEME_ECRAN, CSG.HAUTEUR_ECRAN - 5);
			batch.end();
			// FAIRE CLASSE UI POUR PAR EXEMPLE STOCKER -5
			// ** ** update
			update(delta);
		} else { // C'est en pause
			if (Gdx.input.justTouched())
				pause = false;
		}
		bloom.render();
	}

	private void update(float delta) {
		// ** ** partie mouvement joueur
		if (!perdu) {
			if(Gdx.input.justTouched()) {
				Ennemis.liste.add(EnnemiPorteNef.pool.obtain());
				// Si oui c'est un double tap
				if (System.currentTimeMillis() - vientDEtreTouche < 500) {
					activerRalentissement = true;
				}
				vientDEtreTouche = System.currentTimeMillis();
			}
			if (Gdx.input.isTouched()) {
				if (!pause)			vaisseau.mouvements(delta);
				else				pause = false;
			} else {
				affichage.ParallaxBackground.changerOrientation(0);
			}
			if (alterner) {
				if (alternerApparition)
					Ennemis.possibleApparition(chrono.getTempsEcoule());
				perdu = Physique.testCollisions(vaisseau);
				// ** ** tir joueur
				vaisseau.tir(delta);
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
		//Profil.sauver();
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
		champChrono = "Time : " + nbSecondes + "s";
	}

	public static void ralentir(float i) {
		chronoRalentir += i;
		champChronoRalentir = "zZz " + chronoRalentir + "s";
	}

}

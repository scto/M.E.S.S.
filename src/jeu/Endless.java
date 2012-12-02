package jeu;

import menu.CSG;
import menu.Menu;
import physique.Physique;
import vaisseaux.XP;
import vaisseaux.armes.Armes;
import vaisseaux.ennemis.Ennemis;
import vaisseaux.ennemis.Progression;
import vaisseaux.joueur.VaisseauType1;
import affichage.ParallaxBackground;
import affichage.ParallaxLayer;
import affichage.TexMan;
import affichage.Ui;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

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
	private ParallaxBackground rbg;
	private Chrono chrono;
	//private CollisionTester collision;
	private boolean alterner = true;
	private boolean alternerApparition = true;
	private boolean perdu = false;
	//private long temps = 0;
	private String champChrono = "Top départ !";

	public Endless(Game game) {
		super();
		batch = new SpriteBatch();
		this.game = game;
		debut = System.currentTimeMillis();
		font = new BitmapFont();
        font.setColor(Color.GREEN);
		rbg = new ParallaxBackground(new ParallaxLayer[]{new ParallaxLayer(TexMan.trFond1,new Vector2(),new Vector2(0, 0)), new ParallaxLayer(TexMan.trFond2,new Vector2(0.1f,0.1f),new Vector2(0,0)),
	      }, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(),new Vector2(0,150));

        gl = Gdx.graphics.getGL20();
		gl.glViewport(0, 0, CSG.LARGEUR_ECRAN, CSG.HAUTEUR_ECRAN);

		//Reset
		Armes.liste.clear();
		Armes.listeTirsDesEnnemis.clear();
		Ennemis.liste.clear();
        chrono = new Chrono(2000);
        chrono.demarrer(this);
        XP.liste.clear();
        Progression.reset();
//        collision = new CollisionTester(vaisseau);
//        collision.demarrer();
        Gdx.graphics.setVSync(false);
	}

	@Override
	public void render(float delta) {

		// ** ** clear screen
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		rbg.render(delta);

		batch.begin();
		XP.affichage(batch);
		if(!perdu){
			// ** ** batch
			Ennemis.affichageEtMouvement(batch);
			vaisseau.draw(batch);
			Armes.affichageEtMouvement(batch);
		} else {
			Ennemis.affichage(batch);
			Armes.affichage(batch);
			vaisseau.draw(batch);
		}
		// ** ** update
		update();
		// FAIRE CLASSE UI POUR PAR EXEMPLE STOCKER -5
		font.draw(batch,champChrono,0,CSG.HAUTEUR_ECRAN-5);
		font.draw(batch, String.valueOf(Gdx.graphics.getFramesPerSecond()), 300, 300);
		font.draw(batch, CSG.profil.champXp, CSG.LARGEUR_ECRAN - CSG.DEMI_LARGEUR_ECRAN, CSG.HAUTEUR_ECRAN);
		batch.end();
	}

	private void update() {
		// ** ** partie mouvement joueur
		if (!perdu) {
			if (Gdx.input.isTouched()) {
				if (!pause)			vaisseau.mouvements();
				else				pause = false;
			} else {
				affichage.ParallaxBackground.changerOrientation(0);
			}
			if (alterner) {
				if (alternerApparition)
					Ennemis.possibleApparition(chrono.getTempsEcoule());
				perdu = Physique.testCollisions(vaisseau);
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
		champChrono = "Temps : " + nbSecondes + "s";
	}

}

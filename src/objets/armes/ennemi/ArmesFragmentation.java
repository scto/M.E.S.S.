package objets.armes.ennemi;

import jeu.CSG;
import jeu.Stats;
import assets.SoundMan;
import assets.animation.AnimationMeteorite;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.badlogic.gdx.utils.Pools;

public class ArmesFragmentation extends ArmeEnnemi implements Poolable{
	
	// ** ** caracteristiques g�n�rales
	public static final int LARGEUR= CSG.LARGEUR_ECRAN / 10;
	public static final int DEMI_LARGEUR = LARGEUR/2;
	public static Pool<ArmesFragmentation> pool = Pools.get(ArmesFragmentation.class);
	// ** ** variable utilitaire
	private boolean gauche = true;
	private boolean bas = true;

	@Override
	public boolean mouvementEtVerif() {
		if (AnimationMeteorite.TPS_ANIM_TOTAL < maintenant) {
			SoundMan.playBruitage(SoundMan.explosionPetite);
			// E X P L O S I O N
			for(int i = (int) (Math.random() * 7); i < 10; i++){
				ArmesFragmentee a = ArmesFragmentee.pool.obtain();
				if (gauche) {
					if(bas)	a.init(position.x + DEMI_LARGEUR, position.y + DEMI_LARGEUR, (float)Math.random(), (float)-Math.random());
					else	a.init(position.x + DEMI_LARGEUR, position.y + DEMI_LARGEUR, (float)Math.random(), (float)Math.random());
				} else {
					if(bas)	a.init(position.x + DEMI_LARGEUR, position.y + DEMI_LARGEUR, (float)-Math.random(), (float)-Math.random());
					else	a.init(position.x + DEMI_LARGEUR, position.y + DEMI_LARGEUR, (float)-Math.random(), (float)Math.random());
					bas = !bas;
				}
				gauche = !gauche;
			}
			return false;
		} 
		return super.mouvementEtVerif();
	}

	@Override
	public TextureRegion getTexture() {		return AnimationMeteorite.getTexture(maintenant);	}
	@Override
	protected float getVitesse() {		return Stats.V_ARME_FRAG;	}
	@Override
	public int getLargeur() {		return LARGEUR;	}
	@Override
	public int getHauteur() {		return LARGEUR;	}
	@Override
	public float getDemiHauteur() {		return DEMI_LARGEUR;	}
	@Override
	public float getDemiLargeur() {		return DEMI_LARGEUR;	}
	@Override
	public void free() {		pool.free(this);	}

}

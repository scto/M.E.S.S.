package objets.armes.joueur;

import jeu.EndlessMode;

public abstract class ManagerArme {
	
	public float init(float prochainTir) {
		if (EndlessMode.maintenant > prochainTir) {
			init();
			return EndlessMode.maintenant + getCadenceTir();
		}
		return prochainTir;
	}

	protected abstract float getCadenceTir();

	protected abstract void init();

	public abstract String getLabel();

	public static ManagerArme changerArme(ManagerArme arme) {
		if (arme.getLabel() == ArmesBalayage.LABEL) return new ManagerArmeDeBase();
		if (arme.getLabel() == ArmeHantee.LABEL) return new ManagerArmeBalayage();
		if (arme.getLabel() == ArmesTrois.LABEL) return new ManagerArmeHantee();
		return new ManagerArmeTrois();
	}

}

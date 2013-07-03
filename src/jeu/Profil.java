package jeu;

import menu.CSG;
import vaisseaux.TypesArmes;
import vaisseaux.armes.Armes;
import vaisseaux.armes.joueur.ArmeAdd;
import vaisseaux.armes.joueur.ArmeHantee;
import vaisseaux.armes.joueur.ArmesBalayage;
import vaisseaux.armes.joueur.ArmesDeBase;
import vaisseaux.armes.joueur.ArmesTrois;
import vaisseaux.ennemis.Ennemis;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.Json.Serializable;
import com.badlogic.gdx.utils.OrderedMap;

/**
 * Classe servant de profil, pour le moment �a n'en g�re que un seul et dans les preferences
 * @author Julien
 */
public class Profil implements Serializable{
	// -- -- String qui servent de clefs
	private static final String STR_CADENCE_ADD = "vitesse", STR_ARME_SELECT = "kjhuk", STR_ARME_DE_BASE_NV = "adbnv";
	private static final String STR_ARME_BALAYAGE_NV = "abnv", STR_ARME_TROIS_NV = "tricheur", STR_ARME_HANTEE_NV = "trichur";
	private static final String strXP = "XP", STR_VOLUME_ARME = "sjciuendk", STR_VOLUME_MUSIQUE = "sjciuend";
	private static final String STR_VOLUME_BRUITAGES = "sjciuen", STR_TYPE_CONTROLE = "sfdsfiuen", STR_BLOOM = "bloom";
	private static final String STR_PARTICULES = "particules", STR_INTENSITE_BLOOM = "intensitebloom";
	// -- -- initialisation des champs
	public int cadenceAdd, typeControle, NvArmeDeBase, NvArmeBalayage, NvArmeTrois,	NvArmeHantee, xpDispo;
	public float volumeArme, volumeMusique, volumeBruitages, intensiteBloom;
	private String armeSelectionnee;
	public boolean bloom, particules;
	// -- -- string d'affichage
	public String champXp = " XP : " + xpDispo;
	public static boolean premiereFois = false;
	public static final int NV_ARME_MAX = 6;
	
	/**
	 * Valeurs par defaut si pas de profil
	 */
	public Profil() {
		NvArmeBalayage = 1;
		NvArmeDeBase = 1;
		NvArmeTrois = 1;
		NvArmeHantee = 1;
		cadenceAdd = 1;
		xpDispo = 0;
		volumeArme = .5f;
		volumeBruitages = 1;
		volumeMusique = 1;
		bloom = false; // Provoque dans de rares cas des bugs d'affichages, donc false
		armeSelectionnee = TypesArmes.ArmeDeBase.toString();
		typeControle = CSG.CONTROLE_TOUCH_RELATIVE;
		intensiteBloom = 2.0f;
		premiereFois = true;
	}

	/**
	 * Sauvegarde
	 */
	@Override
	public void write(Json json) {
		json.writeValue(STR_ARME_BALAYAGE_NV, NvArmeBalayage);
		json.writeValue(STR_ARME_DE_BASE_NV, NvArmeDeBase);
		json.writeValue(STR_ARME_TROIS_NV, NvArmeTrois);
		json.writeValue(STR_ARME_HANTEE_NV, NvArmeHantee);
		json.writeValue(STR_CADENCE_ADD, cadenceAdd);
		json.writeValue(strXP, xpDispo);
		json.writeValue(STR_ARME_SELECT, armeSelectionnee);
		json.writeValue(STR_VOLUME_ARME, volumeArme);
		json.writeValue(STR_VOLUME_BRUITAGES, volumeBruitages);
		json.writeValue(STR_VOLUME_MUSIQUE, volumeMusique);
		json.writeValue(STR_TYPE_CONTROLE, typeControle);
		json.writeValue(STR_BLOOM, bloom);
		json.writeValue(STR_PARTICULES, particules);
		json.writeValue(STR_INTENSITE_BLOOM, intensiteBloom);
	}

	@Override
	public void read(Json json, OrderedMap<String, Object> jsonData) {
		premiereFois = false;
		xpDispo = json.readValue(strXP, Integer.class, jsonData);
		cadenceAdd = json.readValue(STR_CADENCE_ADD, Integer.class, jsonData);
		NvArmeTrois = json.readValue(STR_ARME_TROIS_NV, Integer.class, jsonData);
		NvArmeHantee = json.readValue(STR_ARME_HANTEE_NV, Integer.class, jsonData);
		NvArmeDeBase = json.readValue(STR_ARME_DE_BASE_NV, Integer.class, jsonData);
		NvArmeBalayage = json.readValue(STR_ARME_BALAYAGE_NV, Integer.class, jsonData);
		armeSelectionnee = json.readValue(STR_ARME_SELECT, String.class, jsonData);
		volumeArme = json.readValue(STR_VOLUME_ARME, Float.class, jsonData);
		volumeBruitages = json.readValue(STR_VOLUME_BRUITAGES, Float.class, jsonData);
		volumeMusique = json.readValue(STR_VOLUME_MUSIQUE, Float.class, jsonData);
		champXp = "XP : " + xpDispo;
		typeControle = json.readValue(STR_TYPE_CONTROLE, Integer.class, jsonData);
		bloom = json.readValue(STR_BLOOM, Boolean.class, jsonData);
		particules = json.readValue(STR_PARTICULES, Boolean.class, jsonData);
		intensiteBloom = json.readValue(STR_INTENSITE_BLOOM, Float.class, jsonData);
	}

	/**
	 * decremente l'xp disponible. Attention on doit verifier avant si on peut faire le up
	 */
	public void upCadenceAdd() {
		xpDispo -= getCoutCadenceAdd();
		cadenceAdd++;
		ArmeAdd.determinerCadenceTir();
		champXp = "XP : " + xpDispo;
	}
	
	public int getCoutCadenceAdd() {
		return (cadenceAdd+cadenceAdd) * cadenceAdd * cadenceAdd * 100;
	}
	
	/**
	 * Renvoie l'arme actuellement selectionnee.
	 */
	public TypesArmes getArmeSelectionnee() {
		return convertArme(armeSelectionnee);
	}

	/**
	 * decremente l'xp du coup de l'amelioration et augmente le niveau de l'arme selectionnee.
	 */
	public void upArme() {
		switch(convertArme(armeSelectionnee)) {
		case ArmeBalayage:
			if (NvArmeBalayage < NV_ARME_MAX) {
				xpDispo -= getCoutUpArme();
				NvArmeBalayage++;
				ArmesBalayage.updateDimensions();
			} break;
		case ArmeDeBase:
			if (NvArmeDeBase < NV_ARME_MAX) {
				xpDispo -= getCoutUpArme();
				NvArmeDeBase++;
				ArmesDeBase.updateDimensions();
			} break;
		case ArmeHantee:
			if (NvArmeHantee < NV_ARME_MAX) {
				xpDispo -= getCoutUpArme();
				NvArmeHantee++;
				ArmeHantee.updateDimensions();
			} break;
		case ArmeTrois:
			if (NvArmeTrois < NV_ARME_MAX) {
				xpDispo -= getCoutUpArme();
				NvArmeTrois++;
				ArmesTrois.updateDimensions();
			} break;
		}

		champXp = "XP : " + xpDispo;
	}
	
	public int getCoutUpArme() {
		int nv = 1;	
		switch(convertArme(armeSelectionnee)) {
		case ArmeBalayage:	nv = NvArmeBalayage;	break;
		case ArmeDeBase:	nv = NvArmeDeBase;		break;
		case ArmeHantee:	nv = NvArmeHantee;		break;
		case ArmeTrois:		nv = NvArmeTrois;		break;
		}
		return (nv+nv) * nv * nv * 100;
	}

	public void addXp(int i) {
		xpDispo += i;
		champXp = "XP : " + xpDispo;
	}

	public void setArmeSelectionnee(TypesArmes typeArme) {
		armeSelectionnee = typeArme.toString();
	}
	
	/**
	 * Convertit la string en arme correspondante
	 * @param arme
	 * @return <code>TypesArmes</code>
	 */
	private static TypesArmes convertArme(String arme){
		if (arme.equals(TypesArmes.ArmeDeBase.toString()))		return TypesArmes.ArmeDeBase;
		if (arme.equals(TypesArmes.ArmeTrois.toString()))		return TypesArmes.ArmeTrois;
		if (arme.equals(TypesArmes.ArmeBalayage.toString()))	return TypesArmes.ArmeBalayage;
		return TypesArmes.ArmeHantee;
	}

	public String getNomControle() {
		switch (typeControle) {
		case CSG.CONTROLE_DPAD:					return "D-Pad";
		case CSG.CONTROLE_TOUCH_NON_RELATIVE:	return "Touch";
		case CSG.CONTROLE_ACCELEROMETRE:		return "Accelerometer";
		case CSG.CONTROLE_TOUCH_RELATIVE: 		return "Relative Touch";
		}
		return "Oups ! 404";
	}

	public void chgControle() {
		typeControle++;
		if (CSG.CONTROLE_MAX < typeControle)	typeControle = 0;
	}

	/**
	 * Augmente et persiste
	 */
	public void augmenterVolumeArme() {
		if (volumeArme < 1)			volumeArme += 0.1f;
		CSG.profilManager.persist();
	}

	/**
	 * Diminue et persiste
	 */
	public void diminuerVolumeArme() {
		if (volumeArme > 0)			volumeArme -= 0.1f;
		CSG.profilManager.persist();
	}

	/**
	 * Diminue et persiste
	 */
	public void diminuerVolumeBruitage() {
		if (volumeBruitages > 0)	volumeBruitages -= 0.1f;
		CSG.profilManager.persist();
	}
	
	/**
	 * Augmente et persiste
	 */
	public void augmenterVolumeBruitage() {
		if (volumeBruitages < 1)	volumeBruitages += 0.1f;
		CSG.profilManager.persist();
	}

	/**
	 * Augmente et persiste
	 */
	public void augmenterVolumeMusique() {
		if (volumeMusique < 1)		volumeMusique += 0.1f;
		CSG.profilManager.persist();
	}
	
	/**
	 * Augmente et persiste
	 */
	public void diminuerVolumeMusique() {
		if (volumeMusique > 0)		volumeMusique -= 0.1f;
		CSG.profilManager.persist();
	}

	public void switchParticules() {
		if (Gdx.graphics.isGL20Available()) {
			particules = !particules;
			Armes.initEffets();
			Ennemis.switchParticules();
		}
	}

}

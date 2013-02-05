package jeu;

import menu.CSG;
import vaisseaux.TypesArmes;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.Json.Serializable;
import com.badlogic.gdx.utils.OrderedMap;

/**
 * Classe servant de profil, pour le moment ça n'en gère que un seul et dans les preferences
 * @author Julien
 */
public class Profil implements Serializable{
	// -- -- String qui servent de clefs
	private static final String STR_VITESSE = "vitesse";
	private static final String STR_ARME_SELECT = "kjhuk";
	private static final String STR_ARME_DE_BASE_NV = "adbnv";
	private static final String STR_ARME_BALAYAGE_NV = "abnv";
	private static final String strXP = "XP";
	private static final String STR_VOLUME_ARME = "sjciuendk";
	private static final String STR_VOLUME_MUSIQUE = "sjciuend";
	private static final String STR_VOLUME_BRUITAGES = "sjciuen";
	private static final String STR_TYPE_CONTROLE = "sfdsfiuen";
	// -- -- initialisation des champs
	public int bonusVitesse;
	public int typeControle;
	public int NvArmeDeBase;
	public int NvArmeBalayage;
	public int xpDispo;
	public float volumeArme;
	public float volumeMusique;
	public float volumeBruitages;
	private String armeSelectionnee;
	// -- -- string d'affichage
	public String champXp = " XP : " + xpDispo;
	
	
	/**
	 * Valeurs par défaut si pas de profil
	 */
	public Profil() {
		NvArmeBalayage = 1;
		NvArmeDeBase = 1;
		bonusVitesse = 100;
		xpDispo = 0;
		volumeArme = 1;
		volumeBruitages = 1;
		volumeMusique = 1;
		armeSelectionnee = TypesArmes.ArmeDeBase.toString();
		typeControle = CSG.CONTROLE_TOUCH_NON_RELATIVE;
	}

	@Override
	public void write(Json json) {
		json.writeValue(STR_ARME_BALAYAGE_NV, NvArmeBalayage);
		json.writeValue(STR_ARME_DE_BASE_NV, NvArmeDeBase);
		json.writeValue(STR_VITESSE, bonusVitesse);
		json.writeValue(strXP, xpDispo);
		json.writeValue(STR_ARME_SELECT, armeSelectionnee);
		json.writeValue(STR_VOLUME_ARME, volumeArme);
		json.writeValue(STR_VOLUME_BRUITAGES, volumeBruitages);
		json.writeValue(STR_VOLUME_MUSIQUE, volumeMusique);
		json.writeValue(STR_TYPE_CONTROLE, typeControle);
	}

	@Override
	public void read(Json json, OrderedMap<String, Object> jsonData) {
		xpDispo = json.readValue(strXP, Integer.class, jsonData);
		bonusVitesse = json.readValue(STR_VITESSE, Integer.class, jsonData);
		NvArmeDeBase = json.readValue(STR_ARME_DE_BASE_NV, Integer.class, jsonData);
		NvArmeBalayage = json.readValue(STR_ARME_BALAYAGE_NV, Integer.class, jsonData);
		armeSelectionnee = json.readValue(STR_ARME_SELECT, String.class, jsonData);
		volumeArme = json.readValue(STR_VOLUME_ARME, Float.class, jsonData);
		volumeBruitages = json.readValue(STR_VOLUME_BRUITAGES, Float.class, jsonData);
		volumeMusique = json.readValue(STR_VOLUME_MUSIQUE, Float.class, jsonData);
		champXp = "XP : " + xpDispo;
		typeControle = json.readValue(STR_TYPE_CONTROLE, Integer.class, jsonData);
	}

	
	/**
	 * décremente l'xp disponible et augmente le bonus de vitesse de 20.
	 * fait appel à sauver à la fin
	 */
	public void upVitesse() {
		xpDispo -= getCoutVitesse();
		bonusVitesse += 250;
	}
	
	/**
	 * Détermine le cout pour améliorer la vitesse
	 * @return le cout
	 */
	public int getCoutVitesse() {
		return (int)(bonusVitesse * bonusVitesse/50);
	}
	
	/**
	 * Renvoie l'arme actuellement selectionnée. Va lire ca dans le fichier stocké
	 * @return
	 */
	public TypesArmes getArmeSelectionnee() {
		return convertArme(armeSelectionnee);
	}

	/**
	 * décremente l'xp du coup de l'amélioration et augmente le niveau de l'arme selectionnée.
	 * Fait appel à suaver() deux fois.
	 */
	public void upArme() {
		xpDispo -= getCoutUpArme() ;
		if (convertArme(armeSelectionnee).equals(TypesArmes.ArmeBalayage)) NvArmeBalayage++;
		if (convertArme(armeSelectionnee).equals(TypesArmes.ArmeDeBase)) NvArmeDeBase++;
	}
	
	/**
	 * détermine le cout de l'arme selectionnée en se basant sur son niveau
	 * @return le cout
	 */
	public int getCoutUpArme() {
		int nv = 0;	
		if (convertArme(armeSelectionnee).equals(TypesArmes.ArmeBalayage))
			nv = NvArmeBalayage;
		if (convertArme(armeSelectionnee).equals(TypesArmes.ArmeDeBase))
			nv = NvArmeDeBase;
		return nv*nv*100;
	}

	/**
	 * Ajoute le montant passé à l'xp dispo et met à jour la string champxp
	 * @param xp à ajouter
	 */
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
		if (arme.equals(TypesArmes.ArmeDeBase.toString()))	return TypesArmes.ArmeDeBase;
		return TypesArmes.ArmeBalayage;
	}

	public String getNomControle() {
		switch (typeControle) {
		case CSG.CONTROLE_TOUCH_RELATIVE:
			return "Relative touch";
		case CSG.CONTROLE_TOUCH_NON_RELATIVE:
			return "Touch";
		}
		return "Oups ! 404";
	}

	public void chgControle() {
		typeControle++;
		if (CSG.CONTROLE_MAX < typeControle)
			typeControle = 0;
	}
}

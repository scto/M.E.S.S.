package jeu;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.Json.Serializable;
import com.badlogic.gdx.utils.JsonValue;

import elements.generic.weapons.player.ArmeAdd;
import elements.generic.weapons.player.SpaceInvaderManager;
import elements.generic.weapons.player.SpaceInvaderWeapon;
import elements.generic.weapons.player.SunManager;
import elements.generic.weapons.player.SunWeapon;
import elements.generic.weapons.player.TWeapon;
import elements.generic.weapons.player.BlueSweepWeapon;
import elements.generic.weapons.player.Fireball;
import elements.generic.weapons.player.WeaponManager;
import elements.generic.weapons.player.BlueSweepWeaponManager;
import elements.generic.weapons.player.FireballManager;
import elements.generic.weapons.player.TWeaponManager;
import elements.generic.weapons.player.PinkWeaponManager;
import elements.generic.weapons.player.PinkWeapon;
import elements.particular.particles.individual.weapon.GreenAddParticle;

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
	private static final String STR_MANUAL_BONUS = "particules", STR_INTENSITE_BLOOM = "intensitebloom", BFG = "bfg", STR_ARME_SUN = "pointculture", STR_SPACE_INVADER_WEAPON = "spaceInvader", STR_SCREENSHAKE = "screens";
	private static final float STEP_VOL = .1f;
	// -- -- initialisation des champs
	public short cadenceAdd = 1, typeControle = CSG.CONTROLE_TOUCH_RELATIVE, NvArmeDeBase = 2, NvArmeBalayage = 2, lvlPinkWeapon = 2, NvArmeHantee = 2, NvArmeSun = 2, NvSpaceInvadersWeapon = 2;
	public int xpDispo;
	public float weaponVolume = 0.5f, musicVolume = 0.5f, effectsVolume = 0.5f, intensiteBloom = 2.1f, sensitivity = 1.5f;
	public String armeSelectionnee;
	public boolean bloom, manualBonus, bfg, screenshake;
	// -- -- string d'affichage
	public String champXp = " XP : " + xpDispo;
	public static boolean premiereFois = false;
	public static final int NV_ARME_MAX = 8;
	public static final int NV_MIN_SUN = 6;
	
	/**
	 * Valeurs par defaut si pas de profil
	 */
	public Profil() {
		NvArmeBalayage = 1;
		NvArmeDeBase = 1;
		lvlPinkWeapon = 1;
		NvArmeHantee = 1;
		NvArmeSun = 1;
		cadenceAdd = 1;
		NvSpaceInvadersWeapon = 1;
		xpDispo = (int) (200 * CSG.mulSCORE);
		effectsVolume = 1;
		weaponVolume = effectsVolume / 3;
		musicVolume = 1;
		bloom = true; // Provoque dans de rares cas des bugs d'affichages
		armeSelectionnee = PinkWeapon.LABEL;
		typeControle = CSG.CONTROLE_TOUCH_RELATIVE;
		intensiteBloom = 2.4f;
		premiereFois = true;
		manualBonus = false;
		sensitivity = 1.5f;
		bfg = false;
		screenshake = true;
	}

	/**
	 * Sauvegarde
	 */
	@Override
	public void write(Json json) {
		json.writeValue(STR_SPACE_INVADER_WEAPON, NvSpaceInvadersWeapon);
		json.writeValue(STR_ARME_SUN, NvArmeSun);
		json.writeValue(STR_ARME_BALAYAGE_NV, NvArmeBalayage);
		json.writeValue(STR_ARME_DE_BASE_NV, NvArmeDeBase);
		json.writeValue(STR_ARME_TROIS_NV, lvlPinkWeapon);
		json.writeValue(STR_ARME_HANTEE_NV, NvArmeHantee);
		json.writeValue(STR_CADENCE_ADD, cadenceAdd);
		json.writeValue(strXP, xpDispo);
		json.writeValue(STR_ARME_SELECT, armeSelectionnee);
		json.writeValue(STR_VOLUME_ARME, sensitivity);
		
		json.writeValue(STR_VOLUME_BRUITAGES, effectsVolume);
		json.writeValue(STR_VOLUME_MUSIQUE, musicVolume);
		json.writeValue(STR_TYPE_CONTROLE, typeControle);
		json.writeValue(STR_BLOOM, bloom);
		json.writeValue(STR_MANUAL_BONUS, manualBonus);
		json.writeValue(STR_SCREENSHAKE, screenshake);
		json.writeValue(STR_INTENSITE_BLOOM, intensiteBloom);
		json.writeValue(BFG, bfg);
	}

//	@Override
//	public void read(Json json, OrderedMap<String, Object> jsonData) {
//		premiereFois = false;
//		xpDispo = json.readValue(strXP, Integer.class, jsonData);
//		cadenceAdd = json.readValue(STR_CADENCE_ADD, Integer.class, jsonData);
//		NvArmeTrois = json.readValue(STR_ARME_TROIS_NV, Integer.class, jsonData);
//		NvArmeHantee = json.readValue(STR_ARME_HANTEE_NV, Integer.class, jsonData);
//		NvArmeDeBase = json.readValue(STR_ARME_DE_BASE_NV, Integer.class, jsonData);
//		NvArmeBalayage = json.readValue(STR_ARME_BALAYAGE_NV, Integer.class, jsonData);
//		armeSelectionnee = json.readValue(STR_ARME_SELECT, String.class, jsonData);
//		volumeArme = json.readValue(STR_VOLUME_ARME, Float.class, jsonData);
//		volumeBruitages = json.readValue(STR_VOLUME_BRUITAGES, Float.class, jsonData);
//		volumeMusique = json.readValue(STR_VOLUME_MUSIQUE, Float.class, jsonData);
//		champXp = "XP : " + xpDispo;
//		typeControle = json.readValue(STR_TYPE_CONTROLE, Integer.class, jsonData);
//		bloom = json.readValue(STR_BLOOM, Boolean.class, jsonData);
//		manualBonus = json.readValue(STR_MANUAL_BONUS, Boolean.class, jsonData);
//		intensiteBloom = json.readValue(STR_INTENSITE_BLOOM, Float.class, jsonData);
//	}
	@Override
	public void read(Json json, JsonValue jsonData) {
		premiereFois = false;
		xpDispo = json.readValue(strXP, Integer.class, jsonData);
		if (xpDispo < 0) {
			xpDispo = (-xpDispo) + 5000;
		}
		xpDispo = Math.abs(xpDispo);
		cadenceAdd = json.readValue(STR_CADENCE_ADD, Integer.class, jsonData).shortValue();
		lvlPinkWeapon = json.readValue(STR_ARME_TROIS_NV, Integer.class, jsonData).shortValue();
		NvArmeHantee = json.readValue(STR_ARME_HANTEE_NV, Integer.class, jsonData).shortValue();
		NvArmeDeBase = json.readValue(STR_ARME_DE_BASE_NV, Integer.class, jsonData).shortValue();
		NvArmeBalayage = json.readValue(STR_ARME_BALAYAGE_NV, Integer.class, jsonData).shortValue();
		armeSelectionnee = json.readValue(STR_ARME_SELECT, String.class, jsonData);
		sensitivity = json.readValue(STR_VOLUME_ARME, Float.class, jsonData);
		effectsVolume = json.readValue(STR_VOLUME_BRUITAGES, Float.class, jsonData);
		musicVolume = json.readValue(STR_VOLUME_MUSIQUE, Float.class, jsonData);
		weaponVolume = effectsVolume / 3;
		champXp = "XP : " + xpDispo;
		typeControle = json.readValue(STR_TYPE_CONTROLE, Integer.class, jsonData).shortValue();
		bloom = json.readValue(STR_BLOOM, Boolean.class, jsonData);
		manualBonus = json.readValue(STR_MANUAL_BONUS, Boolean.class, jsonData);
		intensiteBloom = json.readValue(STR_INTENSITE_BLOOM, Float.class, jsonData);
		if (sensitivity < 1f)
			sensitivity = 1f;
		if (json.readValue(BFG, Boolean.class, jsonData) != null)
			bfg = json.readValue(BFG, Boolean.class, jsonData);
		else
			bfg = false;
		if (json.readValue(STR_ARME_SUN, Integer.class, jsonData) != null) 	NvArmeSun = json.readValue(STR_ARME_SUN, Integer.class, jsonData).shortValue();
		else 																NvArmeSun = 1;
		if (json.readValue(STR_SPACE_INVADER_WEAPON, Integer.class, jsonData) != null) 	NvSpaceInvadersWeapon = json.readValue(STR_SPACE_INVADER_WEAPON, Integer.class, jsonData).shortValue();
		else 																			NvSpaceInvadersWeapon = 1;
		typeControle = CSG.CONTROLE_TOUCH_RELATIVE;
		if (json.readValue(STR_SCREENSHAKE, Boolean.class, jsonData) != null)
			bfg = json.readValue(STR_SCREENSHAKE, Boolean.class, jsonData);
		else
			bfg = true;
		checkAchievementWeapons();
	}

	/**
	 * decremente l'xp disponible. Attention on doit verifier avant si on peut faire le up
	 */
	public void upCadenceAdd() {
		xpDispo -= getCoutCadenceAdd();
		cadenceAdd++;
		ArmeAdd.determinerCadenceTir();
		GreenAddParticle.COLOR = ArmeAdd.COLORS[CSG.R.nextInt(ArmeAdd.COLORS.length)];
		champXp = "XP : " + xpDispo;
	}
	
	public int getCoutCadenceAdd() {
		return (int) (((cadenceAdd+cadenceAdd) * cadenceAdd * cadenceAdd * 120) * CSG.mulSCORE);
	}
	
	/**
	 * Renvoie l'arme actuellement selectionnee.
	 */
	public WeaponManager getArmeSelectionnee() {
		return convertArme(armeSelectionnee);
	}

	/**
	 * decremente l'xp du coup de l'amelioration et augmente le niveau de l'arme selectionnee.
	 */
	public void upArme() {
		if (armeSelectionnee.equals(BlueSweepWeapon.LABEL) && NvArmeBalayage < NV_ARME_MAX) {
			xpDispo -= getCoutUpArme();
			NvArmeBalayage++;
			BlueSweepWeapon.updateDimensions();
			champXp = "XP : " + xpDispo;
		} else if (armeSelectionnee.equals(Fireball.LABEL) && NvArmeDeBase < NV_ARME_MAX) {
			xpDispo -= getCoutUpArme();
			NvArmeDeBase++;
			Fireball.updateDimensions();
			champXp = "XP : " + xpDispo;
		} else if (armeSelectionnee.equals(TWeapon.LABEL) && NvArmeHantee < NV_ARME_MAX) {
			xpDispo -= getCoutUpArme();
			NvArmeHantee++;
			TWeapon.upgraded();
			champXp = "XP : " + xpDispo;
		} else if (armeSelectionnee.equals(PinkWeapon.LABEL) && lvlPinkWeapon < NV_ARME_MAX) {
			xpDispo -= getCoutUpArme();
			lvlPinkWeapon++;
			champXp = "XP : " + xpDispo;
		} else if (armeSelectionnee.equals(SunWeapon.LABEL) && NvArmeSun < NV_ARME_MAX) {
			xpDispo -= getCoutUpArme();
			NvArmeSun++;
			SunWeapon.updateDimensions();
			champXp = "XP : " + xpDispo;
		} else if (armeSelectionnee.equals(SpaceInvaderWeapon.LABEL) && NvSpaceInvadersWeapon < NV_ARME_MAX) {
			xpDispo -= getCoutUpArme();
			NvSpaceInvadersWeapon++;
			champXp = "XP : " + xpDispo;
		}
		checkAchievementWeapons();
	}

	private void checkAchievementWeapons() {
		if (areWeaponsLvl6())
			CSG.google.unlockAchievementGPGS(Strings.ACH_LVL6);
		if (areWeaponsUnlocked())
			CSG.google.unlockAchievementGPGS(Strings.ACH_UNLOCK_SUN);
		if (NvArmeBalayage >= 8 || NvArmeDeBase >= 8 || NvArmeHantee >= 8 || NvArmeSun >= 8 || lvlPinkWeapon >= 8 || NvSpaceInvadersWeapon >= 8)
			CSG.google.unlockAchievementGPGS(Strings.ACH_LVL8);
	}

	public boolean areWeaponsUnlocked() {
		return NvArmeBalayage >= NV_MIN_SUN && NvArmeDeBase >= NV_MIN_SUN && NvArmeHantee >= NV_MIN_SUN && lvlPinkWeapon >= NV_MIN_SUN;
	}

	public boolean areWeaponsLvl6() {
		return NvArmeBalayage >= 6 || NvArmeDeBase >= 6 || NvArmeHantee >= 6 || NvArmeSun >= 6 || lvlPinkWeapon >= 6 || NvSpaceInvadersWeapon >= 6;
	}
	
	public int getCoutUpArme() {
		int nv = 1;
		if (BlueSweepWeapon.LABEL.equals(armeSelectionnee))				nv = NvArmeBalayage;
		else if (Fireball.LABEL.equals(armeSelectionnee))				nv = NvArmeDeBase;
		else if (TWeapon.LABEL.equals(armeSelectionnee))				nv = NvArmeHantee;
		else if (PinkWeapon.LABEL.equals(armeSelectionnee))				nv = lvlPinkWeapon;
		else if (SunWeapon.LABEL.equals(armeSelectionnee))				nv = NvArmeSun;
		else if (SpaceInvaderWeapon.LABEL.equals(armeSelectionnee))		nv = NvSpaceInvadersWeapon;
		return (int) (((nv+nv) * nv * nv * 120) * CSG.mulSCORE);
	}

	public void addXp(int i) {
		xpDispo += i;
		champXp = "XP : " + xpDispo;
	}

	public void setArmeSelectionnee(String s) {
		armeSelectionnee = s;
	}
	
	/**
	 * Convertit la string en arme correspondante
	 * @param arme
	 * @return <code>TypesArmes</code>
	 */
	private static WeaponManager convertArme(String arme) {
		if (BlueSweepWeapon.LABEL.equals(arme))			return new BlueSweepWeaponManager();
		if (Fireball.LABEL.equals(arme))	   			return new FireballManager();
		if (PinkWeapon.LABEL.equals(arme))	   		 	return new PinkWeaponManager();
		if (SunWeapon.LABEL.equals(arme))				return new SunManager();
		if (SpaceInvaderWeapon.LABEL.equals(arme))		return new SpaceInvaderManager();
		return new TWeaponManager();
	}

//	public String getNomControle() {
//		switch (typeControle) {
////		case CSG.CONTROLE_DPAD:					return "D-Pad";
//		case CSG.CONTROLE_DPAD:					return "Relative Touch";
////		case CSG.CONTROLE_TOUCH_NON_RELATIVE:	return "Touch";
////		case CSG.CONTROLE_TOUCH_NON_RELATIVE:	return "Relative Touch";
////		case CSG.CONTROLE_ACCELEROMETRE:		return "Accelerometer";
////		case CSG.CONTROLE_TOUCH_RELATIVE: 		return "Relative Touch";
//		}
//		return "Oups ! 404";
//	}

//	public void chgControle() {
//		typeControle++;
////		if (CSG.CONTROLE_MAX < typeControle)
////			typeControle = 1;
//		if (typeControle != CSG.CONTROLE_TOUCH_RELATIVE)
//			typeControle = CSG.CONTROLE_TOUCH_RELATIVE;
//		else
//			typeControle = CSG.CONTROLE_ACCELEROMETRE;
//	}

	/**
	 * Diminue et persiste
	 */
	public void diminuerVolumeBruitage() {
		if (effectsVolume > 0) {
			effectsVolume -= STEP_VOL;
			weaponVolume = effectsVolume / 3;
		}
		CSG.profilManager.persist();
	}
	
	/**
	 * Augmente et persiste
	 */
	public void augmenterVolumeBruitage() {
		if (effectsVolume < 1) {
			effectsVolume += STEP_VOL;
			weaponVolume = effectsVolume / 3;
		}
		CSG.profilManager.persist();
	}

	/**
	 * Augmente et persiste
	 */
	public void augmenterVolumeMusique() {
		if (musicVolume < 1)
			musicVolume += STEP_VOL;
		CSG.profilManager.persist();
	}
	
	/**
	 * Augmente et persiste
	 */
	public void diminuerVolumeMusique() {
		if (musicVolume > 0)
			musicVolume -= STEP_VOL;
		CSG.profilManager.persist();
	}

	public void upSensitivity() {
		sensitivity += .1f;
	}
	
	public void downSensitivity() {
		sensitivity -= .1f;
		if (sensitivity < 1f)
			sensitivity = 1f;
	}
	public void upBloom() {
		intensiteBloom += .2f;
	}
	
	public void downBloom() {
		intensiteBloom -= .2f;
		if (intensiteBloom < .4f)
			intensiteBloom = .4f;
	}

	public String getSensitivityString() {
		return EndlessMode.DF.format(sensitivity);
	}
	
	public String getBloomString() {
		return EndlessMode.DF.format(intensiteBloom);
	}
}

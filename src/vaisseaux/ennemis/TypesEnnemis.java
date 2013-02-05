package vaisseaux.ennemis;
/**
 * Définit les differents ennemis et leurs couts
 * @author Julien
 *
 */
public enum TypesEnnemis {
	EnnemiDeBase(1),
	EnnemiZigZag(3),
	EnnemiDeBaseQuiTir(5),
	EnnemiBouleQuiSArrete(7),
	EnnemiPorteNef(11);

	public final int COUT;
	public final static TypesEnnemis[] LISTE_ENNEMIS = {EnnemiPorteNef, EnnemiBouleQuiSArrete, EnnemiDeBaseQuiTir, EnnemiZigZag, EnnemiDeBase};
	
	private TypesEnnemis(int coutPoints) {
		COUT = coutPoints;
	}
}

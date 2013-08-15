package vaisseaux;

import com.badlogic.gdx.math.Vector2;

public interface PatternHorizontalPositionnable {

	int getNbEnnemisAvant();
	int getDemiLargeur();
	void setPosXInitiale(float emplacementX);
	Vector2 getPosition();
	float getPosXInitiale();
	int getLargeur();
	void incNbEnnemisAvant();

}

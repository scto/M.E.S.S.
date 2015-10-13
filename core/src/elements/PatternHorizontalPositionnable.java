package elements;

import com.badlogic.gdx.math.Vector2;

public interface PatternHorizontalPositionnable {

	int getNbEnnemisAvant();
	int getHalfWidth();
	void setPosXInitiale(float emplacementX);
	Vector2 getPosition();
	float getPosXInitiale();
	int getWidth();
	void incNbEnnemisAvant();
	int getHeight();

}

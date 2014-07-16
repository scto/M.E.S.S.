package elements.generic.components;

import jeu.Stats;

public enum Dimensions {
	
	BALL(3.5f), BASIC(2.7f, 3.78f), CRUSADER(6.75f, 8.4375f), CYLON(4.86f), DIABOLO(3.64f), GROUP(4.2f), INSECT(10.8f), KINDER(4.86f, 7.29f), LASER(8.1f), PLANE(8.1f, 12.15f),
	ROUND_N_ROUND(4.05f, 6.2775f), SHOOTER(4, 6), SHOOTER_FRAG(SHOOTER, 1.2f), VICIOUS(INSECT, 0.75f), ZIGZAG(3.645f, 4.374f), BALL_SIDE_SHOOT(4.5f), 
	FIREBALL(2, 3f), FRAG_METEORITE(1.75f), INSECT_WEAPON(2), KINDER_WEAPON(2), LASER_WEAPON(2), METEORITE(3), MINE(3f), PINK_BULLET(2.5f), RAINBOW(1.75f, 2.15f), SMALL_FIREBALL(FIREBALL, 0.6f),
	TOURNANTE(1.8f), VICIOUS_BULLET(1.9f, 1.9f * 1.75f), DRONE_WEAPON(1.5f, 3.0f), BLUE_SWEEP_WEAPON(1), FIREBALL_PLAYER(2), PINK_WEAPON(2), SPACE_INVADER_WEAPON(6, 18), SUN_WEAPON(2), T_WEAPON(2, 2.6f), CYAN_BULLET(1.9f),
	ORANGE_BULLET(1.95f), ADD_SAT(3), BOSS_MINE(5, 14), BOSS_QUAD(18.9f, 9.45f), SAT(16.2f), FRAG_WEAPON(1,2);
	
	public float width, height, halfWidth, halfHeight, quartWidth, threeQuarterWidth;

	private Dimensions(float width) {
		this.width = width * Stats.U;
		this.height = width * Stats.U;
		otherStats();
	}
	
	private Dimensions(float width, float height) {
		this.width = width * Stats.U;
		this.height = height * Stats.U;
		otherStats();
	}

	private Dimensions(Dimensions original, float ratio) {
		width = original.width * ratio;
		height = original.height * ratio;
		otherStats();
	}

	/**
	 * @param width
	 * @param height
	 */
	protected void otherStats() {
		halfWidth = width/2;
		halfHeight = height/2;
		quartWidth = width / 4;
		threeQuarterWidth = quartWidth * 3;
	}
	
}

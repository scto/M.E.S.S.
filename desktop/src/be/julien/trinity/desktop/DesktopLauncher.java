package be.julien.trinity.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import jeu.CSG;
import jeu.TalkToTheWorld;

public class DesktopLauncher implements TalkToTheWorld {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = 480;
        config.height = 800;
		new LwjglApplication(new CSG(new DesktopLauncher()), config);
	}

	@Override
	public boolean getSignedIn() {
		return false;
	}

	@Override	public void login() {	}
	@Override	public void logOut() {	}
	@Override	public void submitScore(String leaderboard, int score) {	}
	@Override	public void getAchievements() {	}
	@Override	public void getScores() {	}
	@Override	public void unlockAchievementGPGS(String achievementId) {	}
	@Override	public void displayToast(String s) {	}
	@Override	public void bragTwitter(String s) {	}
	@Override	public void bragFacebook(String s) {	}
	@Override	public void followTwitter() {	}
	@Override	public void buyUsABeer() {	}
	@Override	public void toast(String string) {	}
	@Override	public void buyXp() {	}
	@Override	public void showAds(boolean show) {	}
	@Override	public void displayInterstitial() {	}
	@Override	public void loadInterstitial() {	}
	@Override	public void otherGames() {	}
}

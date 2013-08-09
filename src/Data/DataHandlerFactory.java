package Data;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.GdxRuntimeException;

/**
 * Trouvé sur github
 * https://github.com/mrafayaleem/libgdx
 */
public class DataHandlerFactory {

	private static final String androidClassname = "com.badlogic.gdx.sqlite.android.AndroidDatabaseManager";
	private static final String desktopClassname = "be.julien.cheapestshootinggame.DesktopDatabaseManager";//com.badlogic.gdx.sqlite.desktop.DesktopDatabaseManager";

	private static DataManager databaseManager = null;

	public static DataHandler getNewDatabaseHandler(String dbName, int dbVersion, String dbCreateQuery, String dbOnUpgradeQuery) {
		if(databaseManager == null) {
			switch(Gdx.app.getType()) {
			case Android:
				try {
					databaseManager = (DataManager)Class.forName(androidClassname).newInstance();
				} catch (Throwable ex) {
					throw new GdxRuntimeException("Error geting database handler: " + androidClassname, ex);
				}
				break;
			case Desktop:
				try {
					databaseManager = (DataManager)Class.forName(desktopClassname).newInstance();
				} catch (Throwable ex) {
					throw new GdxRuntimeException("Error geting database handler: " + desktopClassname, ex);
				}
				break;
			default:
				break;
			}
		}
		return databaseManager.getNewDatabaseHandler(dbName, dbVersion, dbCreateQuery, dbOnUpgradeQuery);
	}

}

package Data;

import menu.CSG;
import vaisseaux.TypesArmes;

import com.badlogic.gdx.Gdx;

public class Database {

	DataHandler dbHandler;

	   public static final String TABLE_SAVE = "ArmesJoueur";
	   public static final String ID_ARME = "_id";
	   public static final String NV_ARME = "niveau";

	   private static final String DATABASE_NAME = "shootinggame.db";
	   private static final int DATABASE_VERSION = 1;
	   private int reponse = 0;

	   // Database creation sql statement
	   private static final String DATABASE_CREATE = "create table if not exists " + TABLE_SAVE + "(" + ID_ARME + " VARCHAR(60) primary key, " 
	   + NV_ARME + " integer);";//, " + COLUMN_NB_SELECION + " integer);";
	   
	   public Database() {
	      Gdx.app.log("DatabaseTest", "creation started");
	      dbHandler = DataHandlerFactory.getNewDatabaseHandler(DATABASE_NAME, DATABASE_VERSION, DATABASE_CREATE, null);
	      dbHandler.setupDatabase();
	      dbHandler.openOrCreateDatabase();
	      dbHandler.execSQL(DATABASE_CREATE);
	      Gdx.app.log("DatabaseTest", "created successfully");   
	      
	      recup();

	      DataCursor cursor;

	      cursor = dbHandler.rawQuery("SELECT * FROM " + TABLE_SAVE);
	      while(cursor.next()) {
	         Gdx.app.log("0-  ", String.valueOf(cursor.getString(0) + "  1-  " + String.valueOf(cursor.getString(1))) );
	      }
	      
	      dbHandler.closeDatabae();
	      dbHandler = null;
	      Gdx.app.log("DatabaseTest", "dispose");
	   }

	private void recup() {
		dbHandler.execSQL("INSERT INTO " + TABLE_SAVE + " (" + ID_ARME + "," + NV_ARME + ") VALUES ('" + TypesArmes.ArmeDeBase + "'," + CSG.profil.NvArmeDeBase + ");");
	    dbHandler.execSQL("INSERT INTO " + TABLE_SAVE + " (" + ID_ARME + "," + NV_ARME + ") VALUES ('" + TypesArmes.ArmeBalayage + "'," + CSG.profil.NvArmeBalayage + ");");
	    dbHandler.execSQL("INSERT INTO " + TABLE_SAVE + " (" + ID_ARME + "," + NV_ARME + ") VALUES ('" + TypesArmes.ArmeHantee + "'," + CSG.profil.NvArmeHantee + ");");
	    dbHandler.execSQL("INSERT INTO " + TABLE_SAVE + " (" + ID_ARME + "," + NV_ARME + ") VALUES ('" + TypesArmes.ArmeTrois + "'," + CSG.profil.NvArmeTrois + ");");
	}

	public int getNvArme(TypesArmes arme) {
		reponse = -1;
		DataCursor cursor;
		dbHandler = DataHandlerFactory.getNewDatabaseHandler(DATABASE_NAME, DATABASE_VERSION, DATABASE_CREATE, null);
		dbHandler.openOrCreateDatabase();
		cursor = dbHandler.rawQuery("SELECT " + NV_ARME + " FROM " + TABLE_SAVE + " where " + ID_ARME + "='" + arme+"';");
		reponse = cursor.getInt(0);
		dbHandler.closeDatabae();
	    dbHandler = null;
		return reponse;
	}
}

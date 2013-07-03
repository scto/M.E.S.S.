package Data;

/**
 * Trouvé sur github
 * https://github.com/mrafayaleem/libgdx
 */
public interface DataHandler {

		public void setupDatabase();	
		public void openOrCreateDatabase();
		public void closeDatabae();

		public void execSQL(String sql);
		public DataCursor rawQuery(String sql);

}

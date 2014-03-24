package jeu.db;

public interface DataCursor {

	byte[] getBlob(int columnIndex);


	boolean getBoolean(int columnIndex);
	double getDouble(int columnIndex);
	float getFloat(int columnIndex);

	int getInt(int columnIndex);

	long getLong(int columnIndex);

	short getShort(int columnIndex);

	String getString(int columnIndex);

	boolean next();

	int getCount();

	void close();

}

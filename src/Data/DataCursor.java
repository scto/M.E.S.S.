package Data;

/**
 * Trouv� sur github
 * https://github.com/mrafayaleem/libgdx
 */
public interface DataCursor {
	
	public byte[] getBlob(int columnIndex);
	public double getDouble(int columnIndex);
	public float getFloat(int columnIndex);
	public int getInt(int columnIndex);
	public long getLong(int columnIndex);
	public short getShort(int columnIndex);
	public String getString(int columnIndex);

	public boolean next();
	public int getCount();
	public void close();

}

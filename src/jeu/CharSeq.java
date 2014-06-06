package jeu;

public class CharSeq implements CharSequence {
	
	private final char[] str;
	

	public CharSeq(char[] str) {
		super();
		this.str = str;
	}

	public char[] getStr() {
		return str;
	}

	@Override
	public char charAt(int index) {
		return str[index];
	}
	
	public void charSet(int index, char c) {
		str[index] = c;
	}

	@Override
	public int length() {
		return str.length;
	}

	@Override
	public CharSequence subSequence(int start, int end) {
		return null;
	}

	public void charSet(int index, int tmpInt) {
		switch(tmpInt) {
		case 0: charSet(index, '0'); break;
		case 1: charSet(index, '1'); break;
		case 2: charSet(index, '2'); break;
		case 3: charSet(index, '3'); break;
		case 4: charSet(index, '4'); break;
		case 5: charSet(index, '5'); break;
		case 6: charSet(index, '6'); break;
		case 7: charSet(index, '7'); break;
		case 8: charSet(index, '8'); break;
		case 9: charSet(index, '9'); break;
		}
	}

}

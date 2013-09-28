package Images_matrizes;


public class Cor {
	
	private static int RED = 0;
	private static int GREEN =1;
	private static int BLUE = 2;
	
	private static int cor[] = new int[3];

	public static void Cor(int r, int g, int b)
	{
	    cor[RED]   = r;
	    cor[GREEN] = g;
	    cor[BLUE]  = b;
	}

	
	public int getRED() {
		return cor[RED];
	}

	public  void setRED(int rED) {
		cor[RED] = rED;
	}

	public  int getGREEN() {
		return cor[GREEN];
	}

	public  void setGREEN(int gREEN) {
		cor[GREEN] = gREEN;
	}

	public  int getBLUE() {
		return cor[BLUE];
	}

	public void setBLUE(int bLUE) {
		cor[BLUE] = bLUE;
	}
	
	
}

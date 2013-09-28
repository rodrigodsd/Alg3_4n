package Images_matrizes;


public class Reta extends Shape {
	private static int x0,y0,x1,y1;
	
	public Reta(Imagem imagem) {
		super(imagem);
		// TODO Auto-generated constructor stub
	}
	public void setVertices(Ponto p1, Ponto p2){
		x0 = p1.getX();
		y0 = p1.getY();
		x1 = p2.getX();
		y1 = p2.getY();
	}
	@Override
	public void draw(Cor cor)
	{
		
	    int e2;
	    float dx = Math.abs(x1-x0);
	    float dy = Math.abs(y1-y0);

	    int sx = (x0 <= x1) ? 1 : -1; 
	    int sy = (y0 <= y1) ? 1 : -1; 

	    int err = (int) (dx - dy);

	    for (;;)
	    {	
	        imagem.setPixel(new Ponto(x0,y0),cor);
	        
	        if ((x0 == x1) && (y0 == y1))
	            break;
	        
	        e2 = err * 2;

	        if (e2 > -dy) {
	            err -= dy;
	            x0 += sx;
	        }

	        if (e2 < dx) {
	            err += dx;
	            y0 += sy;
	        }
	    }
	}
}

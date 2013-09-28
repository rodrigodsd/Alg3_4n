package Images_matrizes;


public class Retangulo extends Shape{
	private Reta reta = new Reta(imagem);
	private static Ponto p1,p2,p3,p4;
	
	public Retangulo(Imagem imagem) {
		super(imagem);
	}

	public void setVertices(Ponto p1, Ponto p2)
	{	
		this.p1 = p1;
		this.p2 = p2;
		this.p3 = new Ponto(p2.getX(),p1.getY());
		this.p4 = new Ponto(p1.getX(), p2.getY());
		
	}
	@Override
	public void draw(Cor cor) {
		
		reta.setVertices(p1, p3);
		reta.draw(cor);
		reta.setVertices(p3, p2);
		reta.draw(cor);
		reta.setVertices(p2, p4);
		reta.draw(cor);
		reta.setVertices(p4, p1); 
		reta.draw(cor);
	}
}

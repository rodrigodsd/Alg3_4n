package Images_matrizes;


public class Triangulo extends Shape {
	private Reta reta = new Reta(imagem);
	private static Ponto p1,p2,p3;
	
	public Triangulo(Imagem imagem) {
		super(imagem);
		
	}

	public void setVertices(Ponto p1, Ponto p2 ,Ponto p3)
	{	
		this.p1 = p1;
		this.p2 = p2;
		this.p3 = p3;		
	}
	@Override
	public void draw(Cor cor) {
		
		reta.setVertices(p1, p2);
		reta.draw(cor);
		reta.setVertices(p2, p3);
		reta.draw(cor);
		reta.setVertices(p3, p1);
		reta.draw(cor);
	}

}

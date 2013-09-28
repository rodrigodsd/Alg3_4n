package Images_matrizes;

import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.StringTokenizer;


public class Main {

	private static Scanner sc = new Scanner(System.in);
	private static Imagem imagem;
	private static Cor cor;
	private String nomeArquivo;
	
	public static void main(String[] args) {
		
		    for(;;)
		    {
		    	System.out.println("\nDigite o comando: ");
		    	String comando = sc.nextLine();
		    	String[] array = comando.split(" ");
		    	String opcao = array[0];
		    	switch (opcao) {
		    	case "imagem":
			    	imagem = new Imagem(Integer.valueOf(array[2]),Integer.valueOf(array[1]), array[3]);
			    	imagem.pintaMatriz();
			    		break;
		    	case "salvar":
		    		imagem.gera_imagem(array[1]);
		    		break;	
		    	case "cor":
		    		cor= new Cor();
		    		imagem.setCor(cor);
		    		imagem.ajustaCor(array);
		    		break;	
		    	case "circulo":
		    		Circulo circ = new Circulo(imagem);
		    		Ponto ponto = new Ponto((int)Integer.valueOf(array[1]),(int)Integer.valueOf(array[2]));
		    		circ.setCentro(ponto);
		    		circ.setRaio(Integer.valueOf(array[3]));	
		    		circ.draw(cor);
		    		break;	
		    	case "reta":
		    		Reta reta = new Reta(imagem);
		    		Ponto ponto1 = new Ponto((int)Integer.valueOf(array[1]),(int)Integer.valueOf(array[2]));
		    		Ponto ponto2 = new Ponto((int)Integer.valueOf(array[3]),(int)Integer.valueOf(array[4]));
		    		reta.setVertices(ponto1, ponto2);
		    		reta.draw(cor);
		    		break;
		    	
		    	case "retangulo":
		    		Retangulo retangulo = new Retangulo(imagem);
		    		Ponto ponto3 = new Ponto((int)Integer.valueOf(array[1]),(int)Integer.valueOf(array[2]));
		    		Ponto ponto4 = new Ponto((int)Integer.valueOf(array[3]),(int)Integer.valueOf(array[4]));
		    		retangulo.setVertices(ponto3, ponto4);
		    		retangulo.draw(cor);
		    		break;
		    	case "triangulo":
		    		Triangulo triangulo = new Triangulo(imagem);
		    		Ponto ponto5 = new Ponto((int)Integer.valueOf(array[1]),(int)Integer.valueOf(array[2]));
		    		Ponto ponto6 = new Ponto((int)Integer.valueOf(array[3]),(int)Integer.valueOf(array[4]));
		    		Ponto ponto7 = new Ponto((int)Integer.valueOf(array[5]),(int)Integer.valueOf(array[6]));
		    		triangulo.setVertices(ponto5, ponto6, ponto7);
		    		triangulo.draw(cor);
		    		break;
		    	
		    	case "ajuda":
		    		ajuda();

		    		break;
		    	default:
		    		break;
		    	}
		    }
	}
	


/*public static void linha()
	{
	    int x0,y0,x1,y1;
	    System.out.println("x0: "); x0 = sc.nextInt();
	    System.out.println("y0: "); y0 = sc.nextInt();
	    System.out.println("x1: "); x1 = sc.nextInt();
	    System.out.println("y1: "); y1 = sc.nextInt();

	    draw_line(x0,y0,x1,y1);
	}

public static void retangulo()
	{
	    int x0,y0,x1,y1;
	    System.out.println("x0: "); x0 = sc.nextInt();
	    System.out.println("y0: "); y0 = sc.nextInt();
	    System.out.println("x1: "); x1 = sc.nextInt();
	    System.out.println("y1: "); y1 = sc.nextInt();

	    draw_rectangle(x0,y0,x1,y1);
	}

void triangulo()
{
    int x0,y0,x1,y1,x2,y2;
    System.out.println("x0: "); x0 = sc.nextInt();
    System.out.println("y0: "); y0 = sc.nextInt();
    System.out.println("x1: "); x1 = sc.nextInt();
    System.out.println("y1: "); y1 = sc.nextInt();
    System.out.println("x2: "); x2 = sc.nextInt();
    System.out.println("y2: "); y2 = sc.nextInt();

    draw_triangle(x0,y0,x1,y1,x2,y2);
}
public static void circulo()
	{
	    int x0,y0,r;
	    System.out.println("x0: "); x0 = sc.nextInt();
	    System.out.println("y0: "); y0 = sc.nextInt();
	    System.out.println("r: "); r = sc.nextInt();

	    draw(x0,y0,r);
	}*/


public static void ajuda()
	{
	    System.out.println("Comandos:\n");
	    System.out.println("\tajuda\n");
	    System.out.println("\t\tMostra essa tela de ajuda.\n");
	    System.out.println("\tlinha x0 y0 x1 y1\n");
	    System.out.println("\t\tDesenha uma linha de (x0,y0) ate (x1,y1)\n");
	    System.out.println("\tcirculo x0 y0 raio\n");
	    System.out.println("\t\tDesenha um circulo centrado em (x0,y0) de raio 'raio'\n");
	    System.out.println("\tretangulo x0 y0 x1 y1\n");
	    System.out.println("\t\tDesenha um retangulo com os angulos externos (x0,y0) e (x1,y1)\n");
	    System.out.println("\ttriangulo x0 y0 x1 y1 x2 y2\n");
	    System.out.println("\t\tDesenha um triangulo com os vertices dados.\n");
	    System.out.println("\tcor cinza\n");
	    System.out.println("\tcor r g b\n");
	    System.out.println("\t\tAjusta a cor ou a escala de cinza, de acordo com o tipo de imagem.\n");
	    System.out.println("\tgerar nome_arquivo\n");
	    System.out.println("\t\tGrava a imagem no arquivo 'nome_arquivo.ppm' e finaliza o programa.\n");
	}





}

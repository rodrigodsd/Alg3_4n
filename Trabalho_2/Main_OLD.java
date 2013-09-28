package Images_matrizes;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main_OLD {

	//variáveis globais
	private static int[][][] imagem =  new int[200][200][200];
	private static int R=0 ,G=0 ,B=0 ;
	private static int x_max = 0;
	private static int y_max = 0;
	private static Scanner sc = new Scanner(System.in);
		
	public static void main(String[] args) {

		 int comando = 0,quantidade = 0,i,posx = 0,posy = 50;
			//inicia_matriz();
			System.out.println(" \n\n Digite a quantidade de imagens que quer gerar : ");
			quantidade = sc.nextInt();
			for( i = 0 ; i < quantidade ; i++){
			
				System.out.println("-----------------------------------------------\n");
				System.out.println("\n 1 - Retangulo \n 2 - Triangulo \n 3 - Circulo \n 4 - Reta \n 5 - Gera Imagem \n 6 - Exit\n\n  ");
				comando = sc.nextInt();
				if(posx > (x_max - 50)){
					posx = 0;
					
					if(posy > (y_max - 50)){
						posy = 0;
					}
					posy = posy + 80;
				}
				
				posx = posx + 50;
				switch (comando){
					case 1:
						cores();
						//printf("\n x = %d  y = %d",posx,posy);
						cria_retangulo(posx ,posy ,1);
						break;
					case 2:
						cores();
						//printf("\n x = %d  y = %d",posx,posy);
						cria_triangulo(posx + 30 , posy, 1);
						break;
					case 3:
						cores();
						cria_circulo(posx  ,posy);
						break;
					case 4:
						cores();
						cria_traco(posx  + 40 , posy);
						break;
					case 5:
						gera_imagem();
						break;
					case 6:
						i = quantidade;
						break;
					default:
						System.out.println("\n Opcao invalida");
						break;
				}		
			}
			gera_imagem();
	}

	/*public static void inicia_matriz(){
		int i,t = 0,lines = 0,columms = 0;
		while((t != 2) && (t != 1)){
			System.out.println("\n Digite o tamanho da imagem  :  \n 1 - 1024 x 768 \n 2 - 512 x 512 \n   ");
			t = sc.nextInt();
			
			if(t == 1){
				lines = 1024;
				columms = 768;
			}
			else if(t == 2){
				lines = 512;
				columms = 512;

			}else{
				System.out.println("\n opcao invalida\n ");
			}
		}
		x_max = lines;
		y_max = columms;

		imagem = new int[y_max][x_max][];
		//limpa imagem (fundo preto)
		for(i = 0 ;i < x_max ; i++){
				memset(imagem[i] , 0 , y_max);

		}
	}*/
	
public static	void  cores(){
		int cor;
		System.out.println("\n--- COR --- \n \n  1 - Vermelho \n  2 - Verde \n  3 - Azul \n  4 - Amarelo \n  5 - Rosa \n 6 - Cria Cor \n\n");
		cor = sc.nextInt();
		switch (cor){
			case 1:
				R = 255;
				G = 0;
				B = 0;
				break;
			case 2:
				R = 0;
				G = 255;
				B = 0;
				break;
			case 3:
				R = 0;
				G = 0;
				B = 255;
				break;
			case 4:
				//yellow
				R = 255;
				G = 0;
				B = 255;
				break;
			case 5:
				//pink
				R = 253;
				G = 233;
				B = 16;
				break;
			case 6:
				System.out.println("\n Digite o R(red) : ");
				R = sc.nextInt();
				while(R > 255){
				System.out.println("\n Digite o R(red) : ");
				R = sc.nextInt();
				}
				System.out.println("\n Digite o G(Green) : ");
				G = sc.nextInt();
				while(G > 255){
				System.out.println("\n Digite o G(Green) : ");
				G = sc.nextInt();
				}
				System.out.println("\n Digite o B(Blue) : ");
				B = sc.nextInt();
				while(B > 255){
				System.out.println("\n Digite o B(Blue) : ");
				B = sc.nextInt();
				}
				
				break;
			default:
				//white
				R = 255;
				G = 255;
				B = 255;
				System.out.println("Default color");
				break;					
		}
	}
/*
public 	int rand_range(int min , int max){
		 srand(time(NULL));	
	     int rd = rand()%(max + min);
	     return min+rd;
	}*/

public static void cria_circulo(int posx,int posy){
		float i,x,y;
		int raio = 20;
		//desenha circulo
		//posx = rand_range(raio , (x_max - raio));
		//posy = rand_range(raio , (x_max - raio));
		
		for(i = 0 ; i <= 90 ; i++){
			 y = (float) Math.sin((double)(i * Math.PI / 180));
			 x = (float) Math.cos(i * Math.PI / 180);
			//desenha na matriz red
			imagem[(int)(x * raio + posx)][(int)(y * raio + posy)][0] = R;
			imagem[(int)(-x * raio + posx)][(int)(-y * raio + posy)][0] = R;
			imagem[(int)(x * raio + posx)][(int)(-y * raio + posy)][0] = R;
			imagem[(int)(-x * raio + posx)][(int)(y * raio + posy)][0] = R;
			//desenha na matriz green
			imagem[(int)(x * raio + posx)][(int)(y * raio + posy)][1] = G;
			imagem[(int)(-x * raio + posx)][(int)(-y * raio + posy)][1] = G;
			imagem[(int)(x * raio + posx)][(int)(-y * raio + posy)][1] = G;
			imagem[(int)(-x * raio + posx)][(int)(y * raio + posy)][1] = G;
			//desenha na matriz blue
			imagem[(int)(x * raio + posx)][(int)(y * raio + posy)][2] = B;
			imagem[(int)(-x * raio + posx)][(int)(-y * raio + posy)][2] = B;
			imagem[(int)(x * raio + posx)][(int)(-y * raio + posy)][2] = B;
			imagem[(int)(-x * raio + posx)][(int)(y * raio + posy)][2] = B;
		}
	}

public static void cria_traco(int x,int y){
	int tam =25; 
	cria_reta(( x - tam),( y - tam),( x - (tam*2)),( y ));//diagonal esquerda
	
}

public static void cria_reta(int x1, int y1, int x2 , int y2){

	 // if point x1, y1 is on the right side of point x2, y2, change them  
    if ((x1 - x2) > 0) {cria_reta(x2, y2, x1, y1); return;}  
    // test inclination of line  
    // function Math.abs(y) defines absolute value y  
    if (Math.abs(y2 - y1) > Math.abs(x2 - x1)) {  
        // line and y axis angle is less then 45 degrees  
        // that swhy go on the next procedure  
        bresteepline(y1, x1, y2, x2); return;   
    }  
    // line and x axis angle is less then 45 degrees, so x is guiding  
    // auxiliary variables  
    int x = x1, y = y1, sum = x2 - x1, Dx = 2 * (x2 - x1), Dy = Math.abs(2 * (y2 - y1));  
    int prirastokDy = ((y2 - y1) > 0) ? 1 : -1;  
    // draw line  
    for (int i = 0; i <= x2 -x1; i++) {  
    	imagem[x][y][0] =R;//red;
		imagem[x][y][1] = G;//green;
		imagem[x][y][2] =B; //blue;  
        x++;  
        sum -= Dy;  
        if (sum < 0) {y = y + prirastokDy; sum += Dx;}  
    }  
}  
  
public static void bresteepline(int x3, int y3, int x4, int y4) {  
    // if point x3, y3 is on the right side of point x4, y4, change them  
    if ((x3 - x4) > 0) {bresteepline(x4, y4, x3, y3); return;}  
  
    int x = x3, y = y3, sum = x4 - x3,  Dx = 2 * (x4 - x3), Dy = Math.abs(2 * (y4 - y3));  
    int prirastokDy = ((y4 - y3) > 0) ? 1 : -1;  
  
    for (int i = 0; i <= x4 -x3; i++) {  
    	imagem[x][y][0] =R;//red;
		imagem[x][y][1] = G;//green;
		imagem[x][y][2] =B; //blue; 
        x++;  
            sum -= Dy;  
        if (sum < 0) {y = y + prirastokDy; sum += Dx;}  
    }  
}  
public static void cria_retangulo(int x , int y , int p){
	//450,300 ponto inferior esquerdo e 400,400 ponto superior direito
	int i;	           
	          //x1      //y1     //x0     //y0
	cria_reta((x-10) , (y-40) , (x-10) , (y+40));//horizontal
	cria_reta((x-10) , (y-40),  (x+10) , (y-40));//vertical
	cria_reta((x+10) , (y-40) , (x+10) , (y+40));//horizontal
	cria_reta((x-10) , (y+40) , (x+10) , (y+40));//vertical
	//pinta retangulo
	if( p == 1){
		
		for(i = 0 ; i <= 10 ; i++){
				cria_reta((x-10 +i) , (y-40) , (x-10+i) , (y+40));//horizontal
				cria_reta((x+10 -i) , (y-40) , (x+10-i) , (y+40));//horizontal
		}
	}
}

public static void cria_triangulo(int x ,int y,int p){
	int i,tam=25;
	cria_reta(( x - tam),( y - tam),( x - tam),( y + tam));//base
	cria_reta(( x - tam),( y - tam),( x - (tam*2)),( y ));//diagonal esquerda
	cria_reta(( x - tam),( y + tam),( x - (tam*2)),( y ));//diagonal direita
		
		//pinta triangulo
		if( p == 1){
		
		for(i = 0 ; i <= tam ; i++){
			
			cria_reta(( x - tam -i),( y - tam +i),( x - tam -i),( y + tam -i));//base
			
		}
	}
	
}

public static void gera_imagem(){
	int x,y;
	try {
		File file = new File( "C:/Users/Rodrigo/Desktop/imagem.ppm");
		//escreve no arquivo
		FileWriter fw = new FileWriter(file, true);

		BufferedWriter bw = new BufferedWriter(fw);

		bw.write("Texto a ser escrito no txt");

		bw.newLine();


		if(file.exists()){

			//monta o cabeçalho do arquivo pbm
			bw.write("P3\n");
			bw.write("%d %d\n",x_max,y_max);
			bw.write("255\n");

			//Imprime a matriz com os desenhos  no arquivo

			for(x = 0 ; x < x_max ; x++){	
				for( y = 0 ; y < y_max ; y++){	

					bw.write( imagem[x][y][0]+" "+imagem[x][y][1]+" "+imagem[x][y][2]);

				}
			}
		}
		bw.close();
		fw.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

}

}

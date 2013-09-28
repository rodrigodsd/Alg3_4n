package Images_matrizes;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Imagem {
	private static int IMG_HEIGHT;
	private static int IMG_WIDTH;
	private static String IMG_MODE;
	private static int img[][][];
	private static Scanner sc = new Scanner(System.in);
	private static Cor cor;


	public Imagem(int iMG_HEIGHT, int iMG_WIDTH, String iMG_MODE) {
		
		this.IMG_HEIGHT = iMG_HEIGHT;
		this.IMG_WIDTH = iMG_WIDTH;
		this.IMG_MODE = iMG_MODE;
		img = new int[IMG_HEIGHT][IMG_WIDTH][3];
	}
	
	public void pintaMatriz(){
		for(int x = 0 ; x < IMG_HEIGHT ; x++){	
			for(int  y = 0 ; y < IMG_WIDTH ; y++){	

				img[x][y][0] = 0;
				img[x][y][1] = 0;
				img[x][y][2] = 0;
				
			}
		}
	}
	

	public void setPixel(Ponto ponto, Cor cor){	
		
		int x = ponto.getX();
		int y = ponto.getY();

		if (x < IMG_WIDTH && y < IMG_HEIGHT) {
			img[x][y][0] = cor.getRED();
			img[x][y][1] = cor.getGREEN();
			img[x][y][2] = cor.getBLUE();
			
		}
	}
	
	public static void ajustaCor(String[] array){
		int gray;
		if(array.length == 2){
			IMG_MODE = "g";
			gray = Integer.valueOf(array[1]) ;
			cor.setRED(gray);
			cor.setGREEN(gray);
			cor.setBLUE(gray);
		}else{
			IMG_MODE = "c";
			cor.setRED(Integer.valueOf(array[1]));
			cor.setGREEN(Integer.valueOf(array[2]));
			cor.setBLUE(Integer.valueOf(array[3]));
		}

		//set_color((char)r,(char)g,(char)b);
	}
	public  void gera_imagem(String nome){
		int x,y;
		try {
			File file = new File( "C:/Users/Rodrigo/Desktop/imagem.ppm");
			//escreve no arquivo
			FileWriter fw = new FileWriter(file, true);

			BufferedWriter bw = new BufferedWriter(fw);

			//bw.write("Texto a ser escrito no txt");
			//bw.newLine();

			if(file.exists()){

				//monta o cabeçalho do arquivo pbm
				if(IMG_MODE == "g"){
					bw.write("P2\n");
					bw.write(IMG_HEIGHT +" "+ IMG_WIDTH +"\n" );
					bw.write("255\n");
				}else{
					bw.write("P3\n");
					bw.write(IMG_HEIGHT +" "+ IMG_WIDTH +"\n" );
					bw.write("255\n");
				}
				
				//Imprime a matriz com os desenhos  no arquivo

				for(x = 0 ; x < IMG_HEIGHT ; x++){	
					bw.write("\n");
					for( y = 0 ; y < IMG_WIDTH ; y++){	
						if(IMG_MODE == "g"){
							bw.write(img[x][y][0]+" ");
						}else{
							bw.write(img[x][y][0]+" "+img[x][y][1]+" "+img[x][y][2]+"  ");
						}
						
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

	public void setCor(Cor cor2) {
		this.cor = cor2;
		
	}
}

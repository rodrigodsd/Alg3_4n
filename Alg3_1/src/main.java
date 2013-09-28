
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

/*Implementar um jogo de cartas com 4 jogadores, onde um jogador joga de cada vez, em uma ordem específica (escolhida pelo desenvolvedor).

Regras do jogo:
	- O baralho de jogo é um baralho comum, com 4 naipes (Copas, Ouros, Espadas e Paus), e 13 
	valores (A, 2, 3, 4, 5, 6, 7, 8, 9, 10 (ou X), J, Q, K). 
	- No início do jogo as cartas devem ser "embaralhadas", cada jogador recebe 5 cartas 
	e uma carta deve ser colocada na "pilha" de descartes.
	- Na sua vez de jogar, o jogador pode descartar uma carta cujo número ou naipe sejam
	 iguais ao da carta no topo da pilha de descartes.
	- Caso o jogador não possa descartar uma carta, ele deve comprar uma carta da pilha
	 de compras e passar a vez.
	- Vence o jogo o jogador que ficar sem cartas primeiro.*/

public class main {

	private static Pilha pilha;
	private static ArrayList<Jogador> arrJogador = new ArrayList<Jogador>(); 
	private static int cartaReal,cartaRealTopo,descarte = 0;
	private static Scanner sc = new Scanner(System.in);
	private static Tradutor tr = new Tradutor();
	private static int qtdJog;

	public static void main(String[] args) {

		pilha = new Pilha(54);
		criaJogadores();
		initBaralho();
		initJogadores();
		imprime_jogadores();

		int numJog = 0;
		System.out.println("\n\n\nInicio do jogo!!!");
		while(!pilha.isEmpty() ){
			
			int jogada;
			Jogador jogador = arrJogador.get(numJog);
			imprime_jogador(numJog);
			System.out.print("\n  Descarte : ");
			if(descarte == 0){
				System.out.println("Vazio");
			}else{
				System.out.println(tr.traduz_carta(descarte));
			}

			System.out.println("\n 1 - Descartar uma carta \n 2 - Compra  \n 3 - Passa a vez ");
			jogada = sc.nextInt();

			switch (jogada) {
			case 1:
				escolhe_carta(jogador);
				break;
			case 2:
				jogador.compraCarta(pilha.pop());
				break;
			case 3:
				if (numJog == qtdJog - 1 ) {
					numJog = 0;
				}else{
					numJog++;
				}
				break;

			default:
				break;
			}
			
			if(jogador.hasCarta()){
				System.out.println("VOCE GANHOU!!!!!");
				break;
			}else if (pilha.isEmpty()){
				System.out.println("Acabou as cartas");
				imprime_jogadores();
			}
		}

	}

	private static void escolhe_carta(Jogador jogador) {

		int carta =1 ;

		while(carta != 0 ){
			System.out.println("\n - Digite a posicao carta: ou  0 - Para sair ");
			carta = sc.nextInt();

			if(!pilha.isEmpty() && descarte != 0){
				if(testaCarta(jogador.getCarta(carta)) != 3){
					descarte = jogador.descartaCarta(carta);
					break;
				}else{
					System.out.println("A carta é diferente");
				}
			}else{
				descarte = jogador.descartaCarta(carta);
				break;
			}
		}

	}

	private static void initJogadores(){
		for (Jogador j: arrJogador) {
			for(int i = 1 ; i < 6 ; i++ ){
				j.compraCarta(pilha.pop());

			}
		}

	}	

	private static void criaJogadores() {
		System.out.println("Quantos jogadores");
		 qtdJog = sc.nextInt();
		for(int i = 0 ; i < qtdJog ; i++){
			arrJogador.add( i ,new Jogador(i));
		}
	}

	private static void imprime_jogadores(){

		for (Jogador j  : arrJogador) {
			System.out.print(j.getNome());
			j.printCartas();
		}
	}

	private static void imprime_jogador(int num){
		System.out.print(arrJogador.get(num).getNome());
		arrJogador.get(num).printCartas();
	}

	private static void initBaralho(){

		int i = 1;
		while(i < 53){
			//implementar randomico para embaralhar as cartas
			// printf("\n Carta %s  \n ",i);
			pilha.push(i);	
			i++;
		}
		shuffleArray(pilha.getMemo());

	}
	public static void shuffleArray(int[] a) {
	    int n = a.length;
	    Random random = new Random();
	    random.nextInt();
	    for (int i = 0; i < n; i++) {
	      int change = i + random.nextInt(n - i);
	      swap(a, i, change);
	    }
	  }

	  private static void swap(int[] a, int i, int change) {
	    int helper = a[i];
	    a[i] = a[change];
	    a[change] = helper;
	  }
	

	public static int testaCarta( int carta ){


		//printf("\n carta Topo %d carta %d",descarte,carta);
		if(descarte == carta){
			System.out.println("A carta tem o mesmo valor  da carta no topo da pilha");
			return 1;
		}else if(carta == 0){
			System.out.println("Carta Zero");
		}else if(carta < 14 && carta > 0 && descarte < 14 && descarte > 0 ){
			System.out.println("Carta do mesmo Naipe");
			return 2;
		}else if(carta < 27 && carta > 13 && descarte < 27 && descarte > 13 ){
			System.out.println("Carta do mesmo Naipe");
			return 2;
		}else if(carta < 40 && carta > 26 && descarte < 40 && descarte > 26 ){
			System.out.println(" Carta do mesmo Naipe");
			return 2;
		}else if(carta < 53 && carta > 39 && descarte < 53 && descarte > 39 ){
			System.out.println("Carta do mesmo Naipe");
			return 2;
		}else{
			return 3;	
		}
		return 0;

	}

	public static int compra(){
		int carta = pilha.pop();
		return carta;	
	}

	public static void descarta( int carta){
		descarte = carta;	
	}


	public static void destroi_pilha(){

		for (Object i : pilha.getMemo()) {
			i = null;
		}              
	}


}

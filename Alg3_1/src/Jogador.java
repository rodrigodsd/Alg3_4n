import java.util.ArrayList;
import java.util.List;


public class Jogador {
	
	private ArrayList<Integer> cartas = new ArrayList(54);
	private int numJog ;
	private String nome;
	private Tradutor tr = new Tradutor();

	public Jogador(int numJog) {
		this.numJog = numJog + 1;
		this.nome = "Jogador-" + ((int) numJog + (int)1);
	}

	public String getNome() {
		return nome;
	}

	public int getNumJog() {
		return numJog;
	}

	public Boolean hasCartas() {
	
		if(cartas.isEmpty()){
			System.out.println(" - O jogador " + numJog + " não possui cartas" );
		}
		return true;
		
	}
	
	public Boolean hasCarta(){
		if(cartas.isEmpty()){
			return true;
		}else{
			return false;
		}
	}
	
	public int getCarta(int carta){
		return cartas.get(carta);
	}
	
	public void compraCarta(int carta){
		cartas.add(carta);
	
	}
	
	public int descartaCarta(int carta){
		
		return cartas.remove(carta);
	}
	
	public void printCartas(){
	
		for (int i : cartas) {
			
			System.out.print(" - " + cartas.indexOf(i) +":"+ tr.traduz_carta(i));
		}
		System.out.print("\n");
	}
}

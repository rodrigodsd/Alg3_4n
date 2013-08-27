
public class Tradutor {
	
	public String  traduz_carta(int carta){

		int cartaReal = 0;
		if(carta == 0){
			return "Carta inválida";

		}
		if(carta < 14 && carta > 0 ){
			return "("+carta+")"+cartaReal+"-Ouro" ;

		}
		if(carta > 13 && carta < 27){
			cartaReal = carta - 13;
			return "("+carta+")"+cartaReal+"-Espadas";

		}
		if(carta > 26 && carta < 40 ){
			cartaReal = carta - 26;
			return "("+carta+")"+cartaReal+"-Paus";

		}
		if(carta > 39 && carta < 53 ){
			cartaReal = carta - 39;
			return "("+carta+")"+cartaReal+"-Copas" ;
		}
		return "Erro";	

	}

}

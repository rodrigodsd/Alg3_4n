import java.util.Arrays;
import java.util.Random;


public class Pilha {
	
	private static int memo[];  
    private int topo;  
    private int max;
	
    public Pilha(int max) {

		this.topo = -1;
		this.max  = max;
		this.memo = new int[max];
		
	}

	public int[] getMemo() {
		return memo;
	}

	public void setMemo(int[] memo) {
		this.memo = memo;
	}

	public int getTopo() {
		return topo;
	}

	public void setTopo(int topo) {
		this.topo = topo;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	} 
	
	public boolean isEmpty(){
		return ( topo == -1 );
	}
	
	public boolean isFull() {  
        return (topo == max-1);  
    } 
	
	public void push(int x) {  
        if(!isFull()) {  
            topo++;  
            memo[topo] = x;  
        }  
        else {  
            System.out.println("Pilha Cheia");  
        }  
    }
	
	public int pop() {  
        if(!isEmpty()) {  
            return (int) memo[topo--];  
        }  
        else {  
            return 0;  
        }  
    } 
	
	public Object top() {  
        if(!isEmpty()) {  
            return memo[topo];  
        }  
        else {  
            return null;  
        }  
    }  
	
	public void print() {  
        if(!isEmpty()) {  
            String resp="";  
            for(int i=0; i<=topo; i++) {  
                resp += memo[i] + ", ";  
            }  
            System.out.println(resp);  
        }  
        else {  
        	System.out.println("Pilha Vazia");  
        }  
    } 
	
	public int getCarta(){
		if(!isEmpty()) {  
            return (int) memo[topo - 1];  
        }  
        else {  
            return 0;  
        }  
	}
	
}

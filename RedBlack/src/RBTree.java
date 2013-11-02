import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


public class RBTree<K extends Comparable<? super K>,V>
{
    public static final boolean VERIFY_RBTREE = true;
    private static final int INDENT_STEP = 4;

    public Node<K,V> root;

    public RBTree() {
        root = null;
        verifyProperties();
    }
    public void verifyProperties() {
        if (VERIFY_RBTREE) {
            verifyProperty1(root);
            verifyProperty2(root);
            // Property 3 is implicit
            verifyProperty4(root);
            verifyProperty5(root);
        }
    }
    private static void verifyProperty1(Node<?,?> n) {
        assert nodeColor(n) == Color.RED || nodeColor(n) == Color.BLACK;
        if (n == null) return;
        verifyProperty1(n.left);
        verifyProperty1(n.right);
    }
    private static void verifyProperty2(Node<?,?> root) {
        assert nodeColor(root) == Color.BLACK;
    }
    private static Color nodeColor(Node<?,?> n) {
        return n == null ? Color.BLACK : n.color;
    }
    private static void verifyProperty4(Node<?,?> n) {
        if (nodeColor(n) == Color.RED) {
            assert nodeColor(n.left)   == Color.BLACK;
            assert nodeColor(n.right)  == Color.BLACK;
            assert nodeColor(n.parent) == Color.BLACK;
        }
        if (n == null) return;
        verifyProperty4(n.left);
        verifyProperty4(n.right);
    }
    private static void verifyProperty5(Node<?,?> root) {
        verifyProperty5Helper(root, 0, -1);
    }

    private static int verifyProperty5Helper(Node<?,?> n, int blackCount, int pathBlackCount) {
        if (nodeColor(n) == Color.BLACK) {
            blackCount++;
        }
        if (n == null) {
            if (pathBlackCount == -1) {
                pathBlackCount = blackCount;
            } else {
                assert blackCount == pathBlackCount;
            }
            return pathBlackCount;
        }
        pathBlackCount = verifyProperty5Helper(n.left,  blackCount, pathBlackCount);
        pathBlackCount = verifyProperty5Helper(n.right, blackCount, pathBlackCount);
        return pathBlackCount;
    }
    private Node<K,V> lookupNode(K key) {
        Node<K,V> n = root;
        while (n != null) {
            int compResult = key.compareTo(n.key);
            if (compResult == 0) {
                return n;
            } else if (compResult < 0) {
                n = n.left;
            } else {
                assert compResult > 0;
                n = n.right;
            }
        }
        return n;
    }
    public String lookup(K key) {
        Node<K,V> n = lookupNode(key);
        return n == null ? null : n.value;
    }
    private void rotateLeft(Node<K,V> n) {
        Node<K,V> r = n.right;
        replaceNode(n, r);
        n.right = r.left;
        if (r.left != null) {
            r.left.parent = n;
        }
        r.left = n;
        n.parent = r;
    }

    private void rotateRight(Node<K,V> n) {
        Node<K,V> l = n.left;
        replaceNode(n, l);
        n.left = l.right;
        if (l.right != null) {
            l.right.parent = n;
        }
        l.right = n;
        n.parent = l;
    }
    private void replaceNode(Node<K,V> oldn, Node<K,V> newn) {
        if (oldn.parent == null) {
            root = newn;
        } else {
            if (oldn == oldn.parent.left)
                oldn.parent.left = newn;
            else
                oldn.parent.right = newn;
        }
        if (newn != null) {
            newn.parent = oldn.parent;
        }
    }
    public void insert(K key, String value) {
        Node<K,V> insertedNode = new Node<K,V>(key, value, Color.RED, null, null);
        if (root == null) {
            root = insertedNode;
        } else {
            Node<K,V> n = root;
            while (true) {
                int compResult = key.compareTo(n.key);
                if (compResult == 0) {
                    n.value = value;
                    return;
                } else if (compResult < 0) {
                    if (n.left == null) {
                        n.left = insertedNode;
                        break;
                    } else {
                        n = n.left;
                    }
                } else {
                    assert compResult > 0;
                    if (n.right == null) {
                        n.right = insertedNode;
                        break;
                    } else {
                        n = n.right;
                    }
                }
            }
            insertedNode.parent = n;
        }
        insertCase1(insertedNode);
        verifyProperties();
    }
    private void insertCase1(Node<K,V> n) {
        if (n.parent == null)
            n.color = Color.BLACK;
        else
            insertCase2(n);
    }
    private void insertCase2(Node<K,V> n) {
        if (nodeColor(n.parent) == Color.BLACK)
            return; // Tree is still valid
        else
            insertCase3(n);
    }
    void insertCase3(Node<K,V> n) {
        if (nodeColor(n.uncle()) == Color.RED) {
            n.parent.color = Color.BLACK;
            n.uncle().color = Color.BLACK;
            n.grandparent().color = Color.RED;
            insertCase1(n.grandparent());
        } else {
            insertCase4(n);
        }
    }
    void insertCase4(Node<K,V> n) {
        if (n == n.parent.right && n.parent == n.grandparent().left) {
            rotateLeft(n.parent);
            n = n.left;
        } else if (n == n.parent.left && n.parent == n.grandparent().right) {
            rotateRight(n.parent);
            n = n.right;
        }
        insertCase5(n);
    }
    void insertCase5(Node<K,V> n) {
        n.parent.color = Color.BLACK;
        n.grandparent().color = Color.RED;
        if (n == n.parent.left && n.parent == n.grandparent().left) {
            rotateRight(n.grandparent());
        } else {
            assert n == n.parent.right && n.parent == n.grandparent().right;
            rotateLeft(n.grandparent());
        }
    }
    public void delete(K key) {
        Node<K,V> n = lookupNode(key);
        if (n == null)
            return;  // Key not found, do nothing
        if (n.left != null && n.right != null) {
            // Copy key/value from predecessor and then delete it instead
            Node<K,V> pred = maximumNode(n.left);
            n.key   = pred.key;
            n.value = pred.value;
            n = pred;
        }

        assert n.left == null || n.right == null;
        Node<K,V> child = (n.right == null) ? n.left : n.right;
        if (nodeColor(n) == Color.BLACK) {
            n.color = nodeColor(child);
            deleteCase1(n);
        }
        replaceNode(n, child);

        verifyProperties();
    }
    private static <K extends Comparable<? super K>,V> Node<K,V> maximumNode(Node<K,V> n) {
        assert n != null;
        while (n.right != null) {
            n = n.right;
        }
        return n;
    }
    private void deleteCase1(Node<K,V> n) {
        if (n.parent == null)
            return;
        else
            deleteCase2(n);
    }
    private void deleteCase2(Node<K,V> n) {
        if (nodeColor(n.sibling()) == Color.RED) {
            n.parent.color = Color.RED;
            n.sibling().color = Color.BLACK;
            if (n == n.parent.left)
                rotateLeft(n.parent);
            else
                rotateRight(n.parent);
        }
        deleteCase3(n);
    }
    private void deleteCase3(Node<K,V> n) {
        if (nodeColor(n.parent) == Color.BLACK &&
            nodeColor(n.sibling()) == Color.BLACK &&
            nodeColor(n.sibling().left) == Color.BLACK &&
            nodeColor(n.sibling().right) == Color.BLACK)
        {
            n.sibling().color = Color.RED;
            deleteCase1(n.parent);
        }
        else
            deleteCase4(n);
    }
    private void deleteCase4(Node<K,V> n) {
        if (nodeColor(n.parent) == Color.RED &&
            nodeColor(n.sibling()) == Color.BLACK &&
            nodeColor(n.sibling().left) == Color.BLACK &&
            nodeColor(n.sibling().right) == Color.BLACK)
        {
            n.sibling().color = Color.RED;
            n.parent.color = Color.BLACK;
        }
        else
            deleteCase5(n);
    }
    private void deleteCase5(Node<K,V> n) {
        if (n == n.parent.left &&
            nodeColor(n.sibling()) == Color.BLACK &&
            nodeColor(n.sibling().left) == Color.RED &&
            nodeColor(n.sibling().right) == Color.BLACK)
        {
            n.sibling().color = Color.RED;
            n.sibling().left.color = Color.BLACK;
            rotateRight(n.sibling());
        }
        else if (n == n.parent.right &&
                 nodeColor(n.sibling()) == Color.BLACK &&
                 nodeColor(n.sibling().right) == Color.RED &&
                 nodeColor(n.sibling().left) == Color.BLACK)
        {
            n.sibling().color = Color.RED;
            n.sibling().right.color = Color.BLACK;
            rotateLeft(n.sibling());
        }
        deleteCase6(n);
    }
    private void deleteCase6(Node<K,V> n) {
        n.sibling().color = nodeColor(n.parent);
        n.parent.color = Color.BLACK;
        if (n == n.parent.left) {
            assert nodeColor(n.sibling().right) == Color.RED;
            n.sibling().right.color = Color.BLACK;
            rotateLeft(n.parent);
        }
        else
        {
            assert nodeColor(n.sibling().left) == Color.RED;
            n.sibling().left.color = Color.BLACK;
            rotateRight(n.parent);
        }
    }
    public void print() {
        printHelper(root, 0);
    }

    private static void printHelper(Node<?,?> n, int indent) {
        if (n == null) {
            System.out.print("<empty tree> \n");
            return;
        }
        if (n.right != null) {
            printHelper(n.right, indent + INDENT_STEP);
        }
        for (int i = 0; i < indent; i++)
            System.out.print(" ");
        if (n.color == Color.BLACK)
            System.out.println(n.key);
        else
            System.out.println("<" + n.key + ">");
        if (n.left != null) {
            printHelper(n.left, indent + INDENT_STEP);
        }
    }
    public static void main(String[] args) {
    	RBTree<Integer,Integer> t = new RBTree<Integer,Integer>();
    	t.print();

    	try {
    		File arquivo = new File( "D:/Meus Documentos/SENAC/7º Semestre/Algoritimos de programação 3/import_2.txt" );
    		BufferedReader bf = new BufferedReader(new FileReader(arquivo));
    		while(bf.ready()){
    			String[] array = bf.readLine().split("=");
    			int key = Integer.valueOf(array[0]);
    			String valor = array[1];
    			t.insert(key,valor);
    		}

    	} catch (FileNotFoundException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	} catch (IOException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}

    	t.print();
  
    	Scanner sc = new Scanner(System.in);

    	for(;;)
    	{
    		System.out.println("\nDigite o comando ou ajuda ");
    		String comando = sc.nextLine();
    		String[] array = comando.split(" ");
    		String opcao = array[0];
    		switch (opcao) {
    		case "insere":
    			//insere 14 testeInsere
    			try{  
    			    int x = Integer.parseInt(array[1]);  
    			 String y = array[2];
    			 t.insert(x, y);
    			}catch(NumberFormatException e){  
    			  System.out.println("A chave deve ser um numero");
    			}
    			break;
    		case "exclui":
    			//exclui 14
    			try{  
    			    int x = Integer.parseInt(array[1]);  
    			    t.delete(x);
    			}catch(NumberFormatException e){  
    			  System.out.println("A chave deve ser um numero");
    			}
    			break;	
    		case "consulta":
    			//consulta 14
    			try{  
    			    int x = Integer.parseInt(array[1]);  
    			    System.out.println("Chave : " + x + "  Valor : " + t.lookup(x));
    			    
    			}catch(NumberFormatException e){  
    			  System.out.println("A chave deve ser um numero");
    			}
    			break;
    		case "imprime":
    			t.print();
    			break;	
    		case "ajuda":
    			ajuda();
    			break;
    		default:
    			break;
    		}
    	}
    	
    	}
    	public static void ajuda()
    	{
    		System.out.println("Comandos:\n");
    		System.out.println("\tajuda\n");
    		System.out.println("\t\tMostra essa tela de ajuda.\n");
    		System.out.println("\tinsere <Chave> <Valor>\n");
    		System.out.println("\t\tInsere um nodo com uma chave e valor\n");
    		System.out.println("\tExclui <Chave>\n");
    		System.out.println("\t\tExclui um nodo com a chave informada\n");
    		System.out.println("\tconsulta <chave>\n");
    		System.out.println("\t\tConsulta e mostra o valor de um nodo \n");
    		System.out.println("\timprime\n");
    		System.out.println("\t\tImprime a arvore . OBS: Os nodes entre <> são vermelhos \n");
    		
    	}
    }


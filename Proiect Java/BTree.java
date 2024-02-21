package main_pack;

import java.util.ArrayList;
import java.util.List;

public class BTree {

    private int T;

    public class Node {
        int n;
        Programari key[] = new Programari[2 * T - 1];
        Node child[] = new Node[2 * T];
        boolean leaf = true;

        public int Find(Programari k) {
            for (int i = 0; i < this.n; i++) {
                if (this.key[i].equals(k)) {
                    return i;
                }
            }
            return -1;
        }
    }

    public BTree(int t) {
        T = t;
        root = new Node();
        root.n = 0;
        root.leaf = true;
    }

    private static Node root;

  
    /*
     * preluarea listei din arbore 
     */
    public static List<Programari> preia_lista() {
        List<Programari> nodeList = new ArrayList<>();
        getList(root, nodeList);
        return nodeList;
    }
/*
 * preluarea listei din arbore 
 */
    private static void getList(Node x, List<Programari> nodeList) {
        if (x != null) {
            for (int i = 0; i < x.n; i++) {
                nodeList.add(x.key[i]);
            }
            if (!x.leaf) {
                for (int i = 0; i < x.n + 1; i++) {
                	getList(x.child[i], nodeList);
                }
            }
        }
    }

    private void Split(Node x, int pos, Node y) {
        Node z = new Node();
        z.leaf = y.leaf;
        z.n = T - 1;
        for (int j = 0; j < T - 1; j++) {
            z.key[j] = y.key[j + T];
        }
        if (!y.leaf) {
            for (int j = 0; j < T; j++) {
                z.child[j] = y.child[j + T];
            }
        }
        y.n = T - 1;
        for (int j = x.n; j >= pos + 1; j--) {
            x.child[j + 1] = x.child[j];
        }
        x.child[pos + 1] = z;

        for (int j = x.n - 1; j >= pos; j--) {
            x.key[j + 1] = x.key[j];
        }
        x.key[pos] = y.key[T - 1];
        x.n = x.n + 1;
    }
    /*
     * inserarea listei din file in arbore 
     * 
     */
    public void insertList(List<Programari> programariList) {
        for (Programari programare : programariList) {
            Insert(programare);
      }
    }
    /*
     * inserearea key in arbore 
     */
    public void Insert(Programari key) {
        Node r = root;
        if (r.n == 2 * T - 1) {
            Node s = new Node();
            root = s;
            s.leaf = false;
            s.n = 0;
            s.child[0] = r;
            Split(s, 0, r);
            insertValue(s, key);
        } else {
            insertValue(r, key);
        }
        salvare_tr();
    }
/*
 * inserarea valorii in arbore 
 * @param
 * Node x , Programari k
 */
    private void insertValue(Node x, Programari k) {
        if (x.leaf) {
            int i = 0;
            for (i = x.n - 1; i >= 0 && k.getId() < x.key[i].getId(); i--) {
                x.key[i + 1] = x.key[i];
            }
            x.key[i + 1] = k;
            x.n = x.n + 1;
        } else {
            int i = 0;
            for (i = x.n - 1; i >= 0 && k.getId() < x.key[i].getId(); i--) {
            }
            i++;
            Node tmp = x.child[i];
            if (tmp.n == 2 * T - 1) {
                Split(x, i, tmp);
                if (k.getId() > x.key[i].getId()) {
                    i++;
                }
            }
            insertValue(x.child[i], k);
        }
    }
    //--------------------------------------------Delet
    /*
     * functia de stergere dupa id 
     * @param id
     */
    public void deleteById(int id) {
        root = deleteById(root, id);
        removeDuplicates(root);
    }

    private Node deleteById(Node x, int id) {
        if (x != null) {
            int i;
            for (i = 0; i < x.n && id > x.key[i].getId(); i++) ;

            if (i < x.n && id == x.key[i].getId()) {
                removeFromNode(x, i);
            } else {
                boolean isLast = (i == x.n);
                x.child[i] = deleteById(isLast ? x.child[i] : x.child[i + 1], id);
            }
        }
        return x;
    }
/*
 * sterge nodul din BTree 
 * @param Node x int index
 */
    private void removeFromNode(Node x, int index) {
        if (x.leaf) {
            for (int j = index; j < x.n - 1; j++) {
                x.key[j] = x.key[j + 1];
            }
            x.n--;
        } else {
            Node predecessor = findPredecessor(x, index);
            x.key[index] = predecessor.key[predecessor.n - 1];
            predecessor.key[predecessor.n - 1] = null; 
            predecessor.n--;
        }
    }
    /*
     * cauta predecesorul nodului x 
     * @param Node x int index 
     */

    private Node findPredecessor(Node x, int index) {
        Node currentNode = x.child[index];
        while (!currentNode.leaf) {
            currentNode = currentNode.child[currentNode.n];
        }
        return currentNode;
    }

    /*
     * sterge nodurile dupplicate 
     * 
     */
    public void removeDuplicates(Node x) {
        if (x != null) {
            List<Integer> keysToRemove = new ArrayList<>();

           
            for (int i = 0; i < x.n - 1; i++) {
                for (int j = i + 1; j < x.n; j++) {
                    if (x.key[i] == x.key[j]) {
                        keysToRemove.add(j);
                    }
                }
            }

            for (Integer index : keysToRemove) {
                removeFromNode(x, index);
            }

            
            for (int i = 0; i <= x.n; i++) {
                removeDuplicates(x.child[i]);
            }
        }
    }

//------------------------------------------------------------------------
    /*
     * cautare in arbore dupa id 
     * @param int id;
     * 
     */
    public int Cauta_id(int id) {
        return caut_tot_arbore(root, id);
    }

    private int caut_tot_arbore(Node x, int id) {
        if (x != null) {
            for (int i = 0; i < x.n; i++) {
                if (x.key[i].getId() == id) {
                    return id; 
                }
            }
            if (!x.leaf) {
                for (int i = 0; i < x.n + 1; i++) {
                    int result = caut_tot_arbore(x.child[i], id);
                    if (result != 0) {
                        return result; 
                    }
                }
            }
        }
        return 0; 
    }
    /*
     * functie de stergere dupa id 
     * @param int id 
     */
    public static void deleter(int id) {
         Prog_Manager pr = new Prog_Manager();
        @SuppressWarnings("static-access")
		List<Programari> programariList = pr.deserializeProgramari();
      
       
        System.out.println("Programari inainte de stergere:");
        for (Programari programare : programariList) {
            System.out.println(programare);
        }

       
        pr.Delete_ById(id);

        
        System.out.println("\nProgramari dupa stergere:");
        for (Programari programare : programariList) {
            System.out.println(programare);
        }

       
      // pr.serializeProgramari(programariList);

        BTree bTree = new BTree(3);
        bTree.insertList(programariList);

        
       // System.out.println("\nArborele BTree:");
       // bTree.Show();
    }
    
    /*
     * Preluare listei din arbore 
     * return List<>
     */
    public List<Programari> getList() {
        List<Programari> programariList = new ArrayList<>();
        retrieveList(root, programariList);
        return programariList;
    }

   
    private void retrieveList(Node x, List<Programari> programariList) {
        if (x != null) {
            for (int i = 0; i < x.n; i++) {
                programariList.add(x.key[i]);
            }
            if (!x.leaf) {
                for (int i = 0; i <= x.n; i++) {
                    retrieveList(x.child[i], programariList);
                }
            }
        }
    }

/*
 * afisarea listei 
 * 
 */
    public void Show() {
        Show(root);
    }
    /*
     * stocarea arborelui in fisier
     */
    @SuppressWarnings("static-access")
	public void salvare_tr()
    {
    	List<Programari> arr = new ArrayList<Programari>();
    	arr = BTree.preia_lista();
    	Prog_Manager pr = new Prog_Manager();
    	pr.serializeProgramari(arr);
    }

    private void Show(Node x) {
        if (x != null) {
            for (int i = 0; i < x.n; i++) {
                System.out.println(x.key[i]);
            }
            if (!x.leaf) {
                for (int i = 0; i < x.n + 1; i++) {
                    Show(x.child[i]);
                }
            }
        }
    }
    /*
     * citirea obiectelor din file 
     */
    public static BTree Citire_file()
    {	
    	BTree bt = new BTree(3);
    
    
    Prog_Manager pr = new Prog_Manager();
    @SuppressWarnings("static-access")
	List<Programari> arr = pr.deserializeProgramari();     
    
    bt.insertList(arr);
		
    	return bt;
    }
/*
 * functia main pentru testare
 */
    public static void main(String[] args) {
       
    	
    	   
       @SuppressWarnings("unused")
	Programari p1 = new Programari("st", "st", "st", "st", "st", 3, "st", "st");
      
     
      // System.out.println( bt.Cauta_id(225));
    //  deleter(392);
        
        
      BTree bt = new BTree(3);
      bt = BTree.Citire_file();
      
      List<Programari> arr  = bt.getList();
      
      bt.Show();
      bt.Insert(p1);
      bt.Cauta_id(225);
        
       
   }
}

package main_pack;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

enum Color {
    RED, BLACK
}

class Node implements Serializable {
    private static final long serialVersionUID = 1L;

    Pacient data;
    Node parent;
    Node left;
    Node right;
    Color color;

    public Node(Pacient data) {
        this.data = data;
        this.color = Color.RED;
    }
}

public class RedBlackTree implements Serializable {
    private static final long serialVersionUID = 1L;

    private static Node root;
    private static final Node NIL = new Node(null);

    public RedBlackTree() {
        root = NIL;
    }

    public static void insert(Pacient data) {
        Node node = new Node(data);
        insertNode(node);
    }

    private static void insertNode(Node z) {
        Node y = NIL;
        Node x = root;

        while (x != NIL) {
            y = x;
            if (z.data.hashCode() < x.data.hashCode()) {
                x = x.left;
            } else {
                x = x.right;
            }
        }

        z.parent = y;

        if (y == NIL) {
            root = z;
        } else if (z.data.hashCode() < y.data.hashCode()) {
            y.left = z;
        } else {
            y.right = z;
        }

        z.left = NIL;
        z.right = NIL;
        z.color = Color.RED;

        fixInsert(z);
        salvare_lista();
    }

    private static void fixInsert(Node z) {
        while (z.parent.color == Color.RED && z.parent.parent != null) {
            if (z.parent == z.parent.parent.left) {
                Node y = z.parent.parent.right;

                if (y != null && y.color == Color.RED) {
                    z.parent.color = Color.BLACK;
                    y.color = Color.BLACK;
                    z.parent.parent.color = Color.RED;
                    z = z.parent.parent;
                } else {
                    if (z == z.parent.right) {
                        z = z.parent;
                        leftRotate(z);
                    }

                    z.parent.color = Color.BLACK;
                    if (z.parent.parent != null) {
                        z.parent.parent.color = Color.RED;
                        rightRotate(z.parent.parent);
                    }
                }
            } else {
                Node y = z.parent.parent.left;

                if (y != null && y.color == Color.RED) {
                    z.parent.color = Color.BLACK;
                    y.color = Color.BLACK;
                    z.parent.parent.color = Color.RED;
                    z = z.parent.parent;
                } else {
                    if (z == z.parent.left) {
                        z = z.parent;
                        rightRotate(z);
                    }

                    z.parent.color = Color.BLACK;
                    if (z.parent.parent != null) {
                        z.parent.parent.color = Color.RED;
                        leftRotate(z.parent.parent);
                    }
                }
            }
        }

        root.color = Color.BLACK;
    }

    private static void leftRotate(Node x) {
        Node y = x.right;
        x.right = y.left;

        if (y.left != NIL) {
            y.left.parent = x;
        }

        y.parent = x.parent;

        if (x.parent == NIL) {
            root = y;
        } else if (x == x.parent.left) {
            x.parent.left = y;
        } else {
            x.parent.right = y;
        }

        y.left = x;
        x.parent = y;
    }

    private static void rightRotate(Node y) {
        Node x = y.left;
        y.left = x.right;

        if (x.right != NIL) {
            x.right.parent = y;
        }

        x.parent = y.parent;

        if (y.parent == NIL) {
            root = x;
        } else if (y == y.parent.right) {
            y.parent.right = x;
        } else {
            y.parent.left = x;
        }

        x.right = y;
        y.parent = x;
    }

   
    private void inOrderTraversal(Node root) {
        if (root != NIL) {
            inOrderTraversal(root.left);
            System.out.println(root.data);
            inOrderTraversal(root.right);
        }
    }

    public void printInOrder() {
        inOrderTraversal(root);
    }
    
    public static List<Pacient> Preluare_Lista() {
        List<Pacient> listaPacienti = new ArrayList<>();
        inOrderTraversal(root, listaPacienti);
        return listaPacienti;
    }

    private static void inOrderTraversal(Node root, List<Pacient> listaPacienti) {
        if (root != NIL) {
            inOrderTraversal(root.left, listaPacienti);
            listaPacienti.add(root.data);
            inOrderTraversal(root.right, listaPacienti);
        }
    }

    public static void InsertList(List<Pacient> pacientList) {
        for (Pacient pacient : pacientList) {
            insert(pacient);
        }
    }
    
    
    public static  RedBlackTree makeTree()
    {
    	RedBlackTree rb = new RedBlackTree();

        PacientManager p1 = new PacientManager();
        p1.deserializePacienti();
        List<Pacient> arr = p1.getPacientiList();
        InsertList(arr);
        return rb;
    	
    }
    
    public static Pacient cautaPacient(String nume, String prenume, int ani) {
        Pacient searchKey = new Pacient(nume, prenume, ani, "", "", "");

        Node rezultat = search(root, searchKey);
      if(rezultat != null)
        return searchKey;
      return null;
    }

    private static Node search(Node root, Pacient searchKey) {
        if (root == NIL || searchKey.equals(root.data)) {
            return root;
        }

        if (searchKey.hashCode() < root.data.hashCode()) {
            return search(root.left, searchKey);
        } else {
            return search(root.right, searchKey);
        }
    }
    
    public static void salvare_lista()
    {
    	 List<Pacient> ar2 = Preluare_Lista();
         
         PacientManager p3 = new PacientManager();
         p3.serializePacienti(ar2);
       
    }
    public void StergePacient(String nume, String prenume, int ani) {
        Pacient deleteKey = new Pacient(nume, prenume, ani, "", "", "");
       delete(deleteKey);
        salvare_lista();
       
    }


    public void delete(Pacient deleteKey) {
        root = deleteNode(root, deleteKey);
        if (root != NIL) {
            root.color = Color.BLACK;
        }
        salvare_lista();
    }

    private Node deleteNode(Node root, Pacient deleteKey) {
        if (root == NIL) {
            return NIL;
        }

        if (deleteKey.hashCode() < root.data.hashCode()) {
            root.left = deleteNode(root.left, deleteKey);
            System.out.println("left");
        } else if (deleteKey.hashCode() > root.data.hashCode()) {
            root.right = deleteNode(root.right, deleteKey);
            System.out.println("right");
        } else {
            if (root.left == NIL || root.right == NIL) {
            	System.out.println("else return");
                return (root.left != NIL) ? root.left : root.right;
            }
            System.out.println("dupa if");
            Node successor = Find_Successor(root.right);
            root.data = successor.data;
            
            root.right = deleteNode(root.right, successor.data);
            System.out.println("dupa delet");
          
            if (successor.parent != null) {
                if (successor == successor.parent.left) {
                    successor.parent.left = successor.right;
                    System.out.println("if succ left");
                } else {
                    successor.parent.right = successor.right;
                    System.out.println("else succ left");
                }
                if (successor.right != NIL) {
                    successor.right.parent = successor.parent;
                    System.out.println("if succ nill");
                }
            }
        }

        return root;
    }

    
    private Node Find_Successor(Node node) {
        while (node.left != NIL) {
            node = node.left;
        }
        return node;
    }
    
    

    @SuppressWarnings("static-access")
	public static void main(String[] args) {
    	RedBlackTree redBlackTree = new RedBlackTree();
        redBlackTree = makeTree();

        System.out.println();
        Pacient p2 = new Pacient("str", "str", 2, "str", "str", "str");
       // redBlackTree.insert(p2);

        Pacient p1 = cautaPacient("b", "g", 1);

        System.out.println(p1.toString());

        System.out.println();

        redBlackTree.StergePacient("str","str",2);
        redBlackTree.printInOrder();
        
      

    }
}

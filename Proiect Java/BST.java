package main_pack;

import java.util.ArrayList;
import java.util.List;

class TreeNode {
    Medic medic;
    TreeNode left, right;

    public TreeNode(Medic medic) {
        this.medic = medic;
        this.left = this.right = null;
    }
}

public class BST {
    private static TreeNode root;

    public BST() {
        this.root = null;
    }

    
    public static void insert(Medic medic) {
        root = insertRec(root, medic);
        salvare_list();
    }

    private static TreeNode insertRec(TreeNode root, Medic medic) {
        if (root == null) {
            root = new TreeNode(medic);
            return root;
        }

        if (medic.getNume().compareTo(root.medic.getNume()) < 0) {
            root.left = insertRec(root.left, medic);
        } else if (medic.getNume().compareTo(root.medic.getNume()) > 0) {
            root.right = insertRec(root.right, medic);
        }

        return root;
    }

    public void inorder() {
        inorderRec(root);
    }

    private void inorderRec(TreeNode root) {
        if (root != null) {
            inorderRec(root.left);
            System.out.println(root.medic);
            inorderRec(root.right);
        }
    }
    public static void insertList(List<Medic> medici) {
        for (Medic medic : medici) {
            insert(medic);
        }
    }
    
    public Medic cautaMedic(String nume, String prenume, String tipMedic) {
        return cautaMedicRec(root, nume, prenume, tipMedic);
    }

    private Medic cautaMedicRec(TreeNode root, String nume, String prenume, String tipMedic) {
        if (root == null || root.medic == null) {
            return null;
        }

        int comparatieNume = nume.compareTo(root.medic.getNume());
        int comparatiePrenume = prenume.compareTo(root.medic.getPrenume());
        int comparatieTipMedic = tipMedic.compareTo(root.medic.getTipmedic());

        if (comparatieNume == 0 && comparatiePrenume == 0 && comparatieTipMedic == 0) {
            return root.medic;
        } else if (comparatieNume < 0 || (comparatieNume == 0 && comparatiePrenume < 0)
                || (comparatieNume == 0 && comparatiePrenume == 0 && comparatieTipMedic < 0)) {
            return cautaMedicRec(root.left, nume, prenume, tipMedic);
        } else {
            return cautaMedicRec(root.right, nume, prenume, tipMedic);
        }
    }
    public static List<Medic> Preluare_Lista() {
        List<Medic> listaMedici = new ArrayList<>();
        getAllMediciRec(root, listaMedici);
        return listaMedici;
    }

    private static void getAllMediciRec(TreeNode root, List<Medic> listaMedici) {
        if (root != null) {
            getAllMediciRec(root.left, listaMedici);
            listaMedici.add(root.medic);
            getAllMediciRec(root.right, listaMedici);
        }
    }
    
    public void delete(String nume, String prenume, String tipMedic) {
        root = deleteRec(root, nume, prenume, tipMedic);
        salvare_list();
    }
    

    private TreeNode deleteRec(TreeNode root, String nume, String prenume, String tipMedic) {
        if (root == null) {
            return root;
        }

        int comparatieNume = nume.compareTo(root.medic.getNume());
        int comparatiePrenume = prenume.compareTo(root.medic.getPrenume());
        int comparatieTipMedic = tipMedic.compareTo(root.medic.getTipmedic());

       
        if (comparatieNume < 0 || (comparatieNume == 0 && comparatiePrenume < 0)
                || (comparatieNume == 0 && comparatiePrenume == 0 && comparatieTipMedic < 0)) {
            root.left = deleteRec(root.left, nume, prenume, tipMedic);
        }
        
        else if (comparatieNume > 0 || (comparatieNume == 0 && comparatiePrenume > 0)
                || (comparatieNume == 0 && comparatiePrenume == 0 && comparatieTipMedic > 0)) {
            root.right = deleteRec(root.right, nume, prenume, tipMedic);
        }
       
        else {
          
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

           
            root.medic = minValue(root.right);

           
            root.right = deleteRec(root.right, root.medic.getNume(), root.medic.getPrenume(), root.medic.getTipmedic());
        }

        return root;
    }

    private Medic minValue(TreeNode root) {
        Medic minValue = root.medic;
        while (root.left != null) {
            minValue = root.left.medic;
            root = root.left;
        }
        return minValue;
    }
   @SuppressWarnings("static-access")
public static void salvare_list()
    {

  	 List<Medic> ar2 = Preluare_Lista();
       
       MedicManager p3 = new MedicManager();
       p3.serializeMedici(ar2);
       
    }
    public static  BST makeTree()
    {
    	BST bt = new BST();

        MedicManager p1 = new MedicManager();
      
        @SuppressWarnings("static-access")
		List<Medic> arr =  p1.deserializeMedici();
        insertList(arr);
        return bt;
    	
    }
    public static void main(String[] args) {
       
        @SuppressWarnings("unused")
		Medic medic = new Medic("str", "str", "str", "str", "str", "str");
         BST med = new BST();
       med = makeTree();
     // med.insert(medic);
      // med.inorder();
       
       Medic med1 = med.cautaMedic("str","str", "str");
       System.out.println();
      // System.out.println(med1.toString());
      // med.delete("str", "str", "str");
       med.inorder();
       
    }
}
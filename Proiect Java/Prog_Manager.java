package main_pack;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import java.io.Serializable;

import javax.swing.DefaultComboBoxModel;

public class Prog_Manager implements Serializable {
	
	  private static final String FILE_PATH = "/Users/Andrei_Sviridov/eclipse-workspace/Project/src/main_pack/Prog.txt";
	  public static void serializeProgramari(List<Programari> programariList) {
		    ObjectOutputStream oos = null;
		    try {
		        oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH));
		        oos.writeObject(programariList);
		    } catch (IOException e) {
		        // ...
		    } finally {
		        if (oos != null) {
		            try {
		                oos.close();
		            } catch (IOException e) {
		                e.printStackTrace();
		            }
		        }
		    }
		}


	    @SuppressWarnings("unchecked")
		public static List<Programari> deserializeProgramari() {
	        List<Programari> programariList = new ArrayList<>();
	        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
	            programariList = (List<Programari>) ois.readObject();
	        } catch (IOException | ClassNotFoundException e) {
	            e.printStackTrace();
	        }
	        return programariList;
	    }

	    public static List<Programari> getProgramariList() {
	        return deserializeProgramari();
	    }

	    public static int Search_ById(int id) {
	        List<Programari> programariList = deserializeProgramari();
	        for (Programari programare : programariList) {
	            if (programare.getId() == id) {
	                return programare.getId();
	            }
	        }
	        return 0;
	    }

	    public static void Delete_ById(int id) {
	        List<Programari> programariList = deserializeProgramari();
	        Programari programareToRemove = null;
	        for (Programari programare : programariList) {
	            if (programare.getId() == id) {
	                programareToRemove = programare;
	                break;
	            }
	        }
	        if (programareToRemove != null) {
	            programariList.remove(programareToRemove);
	            serializeProgramari(programariList);
	        }
	    }

	    public static int generateId() {
	  
	        return (int) (Math.random() * 900) + 100;
	    }
public static void main(String[] argv)
{
	Programari prog = new Programari("tipmed", "nume", "prenume", "nume_p", "prenume_p", 13, "gender", "data");
	 List<Programari> programariList = Prog_Manager.deserializeProgramari();
	 programariList.add(prog);
   
     if (programariList.isEmpty()) {
         System.out.println("Nu există obiecte salvate în fișier.");
     } else {
         System.out.println("Obiectele salvate în fișier:");
         for (Programari programare : programariList) {
             System.out.println(programare.toString());
         }
          Prog_Manager.serializeProgramari(programariList);
     }
     
     
 }
	
	

}



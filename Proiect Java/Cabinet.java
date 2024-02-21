package main_pack;

public class Cabinet {

	public static void main(String[] args) {
		 
		PacientManager pacientManager = new PacientManager();
	        pacientManager.deserializePacienti();

	        
	      // Medic medic1 = new Medic("Doctor1", "Medic1", "Specializare1", "str", "str", "str");
	       // Pacient pacient1 = new Pacient("Pacient1", "Prenume1", 30, "Istoric1", medic1, "Programare1");

	      //  Medic medic2 = new Medic("Doctor2", "Medic2", "Specializare2", "str", "str", "str");
	      //  Pacient pacient2 = new Pacient("Pacient2", "Prenume2", 25, "Istoric2", medic2, "Programare2");

	       // pacientManager.adaugaPacient(pacient1);
	      //  pacientManager.adaugaPacient(pacient2);

	      // pacientManager.afiseazaListaPacienti();
	       
	      //  Pacient pacientCautat = pacientManager.cautaPacient("Pacient1", 30);
	      //  if (pacientCautat != null) {
	      //      System.out.println("Pacientul gasit: " + pacientCautat.toString());
	      //  }

	       // pacientManager.stergePacient("Pacient2", 25);
	    
	        
//	        MedicManager.deserializeMedici();
//	        
//	        List<Medic> listaMedici = new ArrayList<>();
//	       
//	        MedicManager.adaugaMedic(medic1, listaMedici);
//	        MedicManager.adaugaMedic(medic2, listaMedici);
//
//	        System.out.println("Lista initiala: " + listaMedici);
//	        Medic medicCautat = MedicManager.cautaMedic("Doctor1", "Medic1", listaMedici);
//	        System.out.println("Medicul cautat: " + medicCautat);
//
//	        MedicManager.stergeMedic("Doctor1", "Medic1", listaMedici);
//	        System.out.println("Lista dupa stergere: " + listaMedici);

	}

}

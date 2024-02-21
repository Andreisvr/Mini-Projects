package main_pack;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clasa PacientManager gestionează serializarea, deserializarea, adăugarea, căutarea, ștergerea și afișarea obiectelor de tip Pacient.
 */
public class PacientManager {

    /** Calea către fișierul pentru stocarea obiectelor serializate de tip Pacient. */
    private static final String FILENAME = "/Users/Andrei_Sviridov/eclipse-workspace/Proiect/src/main_pak/file.txt";

    /** Lista de obiecte de tip Pacient gestionate de PacientManager. */
    private List<Pacient> pacienti;

    /**
     * Construiește un PacientManager cu o listă goală de Pacienți.
     */
    public PacientManager() {
        this.pacienti = new ArrayList<>();
    }

    /**
     * Obține lista de Pacienți gestionată de PacientManager.
     *
     * @return Lista de Pacienți.
     */
    public List<Pacient> getPacientiList() {
        return pacienti;
    }

    /**
     * Serializează o listă de obiecte de tip Pacient și le salvează într-un fișier.
     *
     * @param pacienti Lista de obiecte de tip Pacient de serializat.
     */
    public void serializePacienti(List<Pacient> pacienti) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILENAME))) {
            oos.writeObject(pacienti);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deserializază obiecte de tip Pacient dintr-un fișier.
     */
    @SuppressWarnings("unchecked")
    public void deserializePacienti() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILENAME))) {
            pacienti = (List<Pacient>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Adaugă un obiect de tip Pacient la lista de Pacienți și serializează lista actualizată.
     *
     * @param pacient Obiectul de tip Pacient de adăugat.
     */
    public void adaugaPacient(Pacient pacient) {
        pacienti.add(pacient);
        serializePacienti(pacienti);
    }

    /**
     * Caută un Pacient cu numele, prenumele și vârsta specificate în lista de Pacienți.
     *
     * @param nume    Numele Pacientului de căutat.
     * @param prenume Prenumele Pacientului de căutat.
     * @param ani     Vârsta Pacientului de căutat.
     * @return Obiectul Pacient găsit sau null dacă nu este găsit.
     */
    public Pacient cautaPacient(String nume, String prenume, int ani) {
        for (Pacient pacient : pacienti) {
            if (pacient.getNume().equals(nume) && pacient.getAni() == ani && pacient.getPrenume().equals(prenume)) {
                return pacient;
            }
        }
        return null;
    }

    /**
     * Șterge un Pacient cu numele, prenumele și vârsta specificate din lista de Pacienți și serializează lista actualizată.
     *
     * @param nume    Numele Pacientului de șters.
     * @param prenume Prenumele Pacientului de șters.
     * @param ani     Vârsta Pacientului de șters.
     */
    public void stergePacient(String nume, String prenume, int ani) {
        Pacient pacient = cautaPacient(nume, prenume, ani);
        if (pacient != null) {
            pacienti.remove(pacient);
            serializePacienti(pacienti);
            System.out.println("Pacient șters cu succes."); // Pacient șters cu succes.
        } else {
            System.out.println("Pacientul nu a fost găsit."); // Pacientul nu a fost găsit.
        }
    }

    /**
     * Afișează lista de Pacienți.
     */
    public void afiseazaListaPacienti() {
        System.out.println("Lista de pacienți:");
        for (Pacient pacient : pacienti) {
            System.out.println(pacient.toString());
        }
    }
}

package main_pack;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clasa MedicManager gestionează serializarea, deserializarea, adăugarea, căutarea și ștergerea obiectelor de tip Medic.
 */
public class MedicManager {

    /** Calea către fișierul pentru stocarea obiectelor serializate de tip Medic. */
    private static final String FILENAME = "/Users/Andrei_Sviridov/eclipse-workspace/Project/src/main_pack/medici.txt";

    /**
     * Serializează o listă de obiecte de tip Medic și le salvează într-un fișier.
     *
     * @param medici Lista de obiecte de tip Medic de serializat.
     */
    public static void serializeMedici(List<Medic> medici) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILENAME))) {
            oos.writeObject(medici);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deserializază obiecte de tip Medic dintr-un fișier.
     *
     * @return Lista de obiecte de tip Medic deserializate.
     */
    @SuppressWarnings("unchecked")
    public static List<Medic> deserializeMedici() {
        List<Medic> medici = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILENAME))) {
            medici = (List<Medic>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return medici;
    }

    /**
     * Adaugă un obiect de tip Medic la lista de Medici și serializează lista actualizată.
     *
     * @param medic  Obiectul de tip Medic de adăugat.
     * @param medici Lista de Medici.
     */
    public static void adaugaMedic(Medic medic, List<Medic> medici) {
        medici.add(medic);
        serializeMedici(medici);
    }

    /**
     * Caută un Medic cu numele și prenumele specificate în lista de Medici.
     *
     * @param nume    Numele Medicului de căutat.
     * @param prenume Prenumele Medicului de căutat.
     * @param medici  Lista de Medici.
     * @return Obiectul Medic găsit sau null dacă nu este găsit.
     */
    public static Medic cautaMedic(String nume, String prenume, List<Medic> medici) {
        for (Medic medic : medici) {
            if (medic.getNume().equals(nume) && medic.getPrenume().equals(prenume)) {
                return medic;
            }
        }
        return null;
    }

    /**
     * Șterge un Medic cu numele și prenumele specificate din lista de Medici și serializează lista actualizată.
     *
     * @param nume    Numele Medicului de șters.
     * @param prenume Prenumele Medicului de șters.
     * @param medici  Lista de Medici.
     */
    public static void stergeMedic(String nume, String prenume, List<Medic> medici) {
        Medic medic = cautaMedic(nume, prenume, medici);
        if (medic != null) {
            medici.remove(medic);
            serializeMedici(medici);
            System.out.println("Medic șters cu succes."); // Medic șters cu succes.
        } else {
            System.out.println("Medicul nu a fost găsit."); // Medicul nu a fost găsit.
        }
    }
}

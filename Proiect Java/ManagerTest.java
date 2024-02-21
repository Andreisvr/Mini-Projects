package main_pack;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ManagerTest {

    private MedicManager medicManager;
    private PacientManager pacientManager;

    @Before
    public void setup() {
        medicManager = new MedicManager();
        pacientManager = new PacientManager();
    }

    @SuppressWarnings("static-access")
	@Test
    public void testCautaMedic() {
        // Creare listă de medici pentru testare
        List<Medic> medici = new ArrayList<>();
        medici.add(new Medic("Nume1", "Prenume1", "Cardiolog", "Istoric1", "Data1", "M"));
        medici.add(new Medic("Nume2", "Prenume2", "Ortoped", "Istoric2", "Data2", "F"));
        medici.add(new Medic("Nume3", "Prenume3", "Pediatru", "Istoric3", "Data3", "M"));

        // Adăugare medici în lista gestionată de manager
        medicManager.serializeMedici(medici);

        // Testare căutare medic existent
        Medic medicGasit = medicManager.cautaMedic("Nume2", "Prenume2", medicManager.deserializeMedici());
        assertEquals("Nume2", medicGasit.getNume());
        assertEquals("Prenume2", medicGasit.getPrenume());
        assertEquals("Ortoped", medicGasit.getTipmedic());

        // Testare căutare medic inexistent
        Medic medicNegasit = medicManager.cautaMedic("NumeX", "PrenumeX", medicManager.deserializeMedici());
        assertNull(medicNegasit);
    }

    @Test
    public void testCautaPacient() {
        // Creare listă de pacienți pentru testare
        List<Pacient> pacienti = new ArrayList<>();
        pacienti.add(new Pacient("Nume1", "Prenume1", 25, "Istoric1", "Programare1", "M"));
        pacienti.add(new Pacient("Nume2", "Prenume2", 30, "Istoric2", "Programare2", "F"));
        pacienti.add(new Pacient("Nume3", "Prenume3", 40, "Istoric3", "Programare3", "M"));

        // Adăugare pacienți în lista gestionată de manager
        pacientManager.serializePacienti(pacienti);

        // Testare căutare pacient existent
        Pacient pacientGasit = pacientManager.cautaPacient("Nume2", "Prenume2", 30);
        assertEquals("Nume2", pacientGasit.getNume());
        assertEquals("Prenume2", pacientGasit.getPrenume());
        assertEquals(30, pacientGasit.getAni());

        // Testare căutare pacient inexistent
        Pacient pacientNegasit = pacientManager.cautaPacient("NumeX", "PrenumeX", 99);
        assertNull(pacientNegasit);
    }
}

package main_pack;

import java.io.Serializable;

/**
 * Clasa Pacient reprezintă un pacient și conține informații despre nume, prenume, vârstă, istoric medical și medicul asociat.
 */
public class Pacient implements Serializable {

    private static final long serialVersionUID = 1L;
	private String nume;        
    private String prenume;    
    private int ani;             
    private String istoric;   
    private String gen;
    private String programare;

    /**
     * Constructorul clasei Pacient.
     *
     * @param nume     Numele pacientului
     * @param prenume  Prenumele pacientului
     * @param ani      Vârsta pacientului
     * @param istoric  Istoricul medical al pacientului
     * @param medic    Medicul asociat pacientului
     */
    public Pacient(String nume, String prenume, int ani, String istoric,String programare,String gen) {
        this.nume = nume;
        this.prenume = prenume;
        this.ani = ani;
        this.istoric = istoric;
        this.gen = gen;
        this.programare = programare;
    }

    /**
     * Metoda toString oferă o reprezentare text a obiectului Pacient.
     *
     * @return Șir de caractere care reprezintă obiectul Pacient.
     */
   
    /**
     * Metoda get pentru obținerea numelui pacientului.
     *
     * @return Numele pacientului.
     */
    public String getNume() {
        return nume;
    }

    @Override
	public String toString() {
		return "Pacient [nume=" + nume + ", prenume=" + prenume + ", ani=" + ani + ", istoric=" + istoric + ", gen="
				+ gen + ", programare=" + programare + "]";
	}

	/**
     * Metoda set pentru actualizarea numelui pacientului.
     *
     * @param nume Noul nume pentru pacient.
     */
    public void setNume(String nume) {
        this.nume = nume;
    }

    /**
     * Metoda get pentru obținerea prenumelui pacientului.
     *
     * @return Prenumele pacientului.
     */
    public String getPrenume() {
        return prenume;
    }

    /**
     * Metoda set pentru actualizarea prenumelui pacientului.
     *
     * @param prenume Noul prenume pentru pacient.
     */
    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    /**
     * Metoda get pentru obținerea vârstei pacientului.
     *
     * @return Vârsta pacientului.
     */
    public int getAni() {
        return ani;
    }
    /**
     * Metoda get pentru obținerea data pacientului.
     *
     * @return Vârsta pacientului.
     */
    public String getProgramare() {
		return programare;
	}

	/**
     * Metoda set pentru actualizarea datei pacientului.
     *
     * @param ani Noua vârstă pentru pacient.
     */
	public void setProgramare(String programare) {
		this.programare = programare;
	}

	/**
     * Metoda set pentru actualizarea vârstei pacientului.
     *
     * @param ani Noua vârstă pentru pacient.
     */
    public void setAni(int ani) {
        this.ani = ani;
    }

    /**
     * Metoda get pentru obținerea istoricului medical al pacientului.
     *
     * @return Istoricul medical al pacientului.
     */
    public String getIstoric() {
        return istoric;
    }

    /**
     * Metoda set pentru actualizarea istoricului medical al pacientului.
     *
     * @param  Noul istoric medical pentru pacient.
     */
    public void setIstoric(String istoric) {
        this.istoric = istoric;
    }

    /**
     * Metoda get pentru obținerea gen asociat pacientului.
     *
     * @return genul asociat pacientului.
     */
    public String getgen() {
        return gen;
    }

    /**
     * Metoda set pentru actualizarea gen asociat pacientului.
     *
     * @param medic Noul gen asociat pacientului.
     */
    public void setGen(String gen) {
        this.gen = gen;
    }
}

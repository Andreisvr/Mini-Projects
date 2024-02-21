package main_pack;

import java.io.Serializable;

/**
 * Clasa Medic reprezintă un medic și conține informații despre nume, prenume și specializare.
 */
public class Medic implements Serializable{

    private static final long serialVersionUID = 1L;
	private String nume;           
    private String prenume;        
    private String tipmedic;
    private String istoric;
    private String data;
    private String gen;
	

    /**
     * Constructorul clasei Medic.
     *
     * @param nume          Numele medicului
     * @param prenume       Prenumele medicului
     * @param specializare  Specializarea medicului
     * @param gen 
     * @param data 
     * @param istoric 
     */
    public Medic(String nume, String prenume, String tipmedic, String istoric, String data, String gen) {
		super();
		this.nume = nume;
		this.prenume = prenume;
		this.tipmedic = tipmedic;
		this.istoric = istoric;
		this.data = data;
		this.gen = gen;
	}


	

    /**
     * Metoda toString oferă o reprezentare text a obiectului Medic.
     *
     * @return Șir de caractere care reprezintă obiectul Medic.
     */
    @Override
	public String toString() {
		return "Medic [nume=" + nume + ", prenume=" + prenume + ", tipmedic=" + tipmedic + ", istoric=" + istoric
				+ ", data=" + data + ", gen=" + gen + "]";
	}


    /**
     * Metoda get pentru obținerea numelui medicului.
     *
     * @return Numele medicului.
     */
   


	public String getNume() {
		return nume;
	}



    /**
     * Metoda set pentru actualizarea numelui medicului.
     *
     * @param nume Noul nume pentru medic.
     */

	public void setNume(String nume) {
		this.nume = nume;
	}


	/**
     * Metoda set pentru a prelua prenumelui medicului.
     *
     */
   

	public String getPrenume() {
		return prenume;
	}



	/**
     * Metoda set pentru actualizarea prenumelui medicului.
     *
     * @param prenume Noul prenume pentru medic.
     */
   
	public void setPrenume(String prenume) {
		this.prenume = prenume;
	}



    /**
     * Metoda get pentru obținerea specializării medicului.
     *
     * @return Specializarea medicului.
     */
    

	public String getTipmedic() {
		return tipmedic;
	}



    /**
     * Metoda set pentru actualizarea specializării medicului.
     *
     * @param specializare Noua specializare pentru medic.
     */
   

	public void setTipmedic(String tipmedic) {
		this.tipmedic = tipmedic;
	}



    /**
     * Metoda get pentru obținerea specializării medicului.
     *
     * @return Specializarea medicului.
     */
	public String getIstoric() {
		return istoric;
	}

	 /**
     * Metoda set pentru actualizarea istoric medicului.
     *
     * @param specializare Noua specializare pentru medic.
     */
	public void setIstoric(String istoric) {
		this.istoric = istoric;
	}




    /**
     * Metoda get pentru obținerea data medicului.
     *
     * @return Specializarea medicului.
     */
	public String getData() {
		return data;
	}

	/**
     * Metoda set pentru actualizarea data medicului.
     *
     * @param specializare Noua specializare pentru medic.
     */
	public void setData(String data) {
		this.data = data;
	}


    /**
     * Metoda get pentru obținerea gen medicului.
     *
     * @return Specializarea medicului.
     */
    
	public String getGen() {
		return gen;
	}

	/**
     * Metoda set pentru actualizarea gen medicului.
     *
     * @param specializare Noua specializare pentru medic.
     */

	public void setGen(String gen) {
		this.gen = gen;
	}


    

  
    

}


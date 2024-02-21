package main_pack;

import java.io.Serializable;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import org.hamcrest.Matcher;

public class Programari  implements Serializable{
	
	private String nume_m;
	private String prenume_m;
	private String tipMed;
	
	private String nume_p;
	private String prenume_p;
	private int ani;
	private String gen_p;
	private String data;
	private int id;
	
	
	
	
	@Override
	public String toString() {
		return "Programari [nume_m=" + nume_m + ", prenume_m=" + prenume_m + ", prenume_p=" + prenume_p + ", nume_p="
				+ nume_p + ", ani=" + ani + ", tipMed=" + tipMed + ", gen_p=" + gen_p + ", data=" + data + ", id=" + id
				+ "]";
	}
	
	public Programari(String tipMed, String nume_m, String prenume_m, String nume_p, String prenume_p, int ani,
			String gen_p, String data) {
		super();
		this.nume_m = nume_m;
		this.prenume_m = prenume_m;
		this.tipMed = tipMed;
		this.nume_p = nume_p;
		this.prenume_p = prenume_p;
		this.ani = ani;
		this.gen_p = gen_p;
		this.data = data;
		this.id =generateId() ;
	}

	public String getNume_m() {
		return nume_m;
	}
	public void setNume_m(String nume_m) {
		this.nume_m = nume_m;
	}
	public String getPrenume_m() {
		return prenume_m;
	}
	public void setPrenume_m(String prenume_m) {
		this.prenume_m = prenume_m;
	}
	public String getPrenume_p() {
		return prenume_p;
	}
	public void setPrenume_p(String prenume_p) {
		this.prenume_p = prenume_p;
	}
	public String getNume_p() {
		return nume_p;
	}
	public void setNume_p(String nume_p) {
		this.nume_p = nume_p;
	}
	public int getAni() {
		return ani;
	}
	public void setAni(int ani) {
		this.ani = ani;
	}
	public String getTipMed() {
		return tipMed;
	}
	public void setTipMed(String tipMed) {
		this.tipMed = tipMed;
	}
	public String getGen_p() {
		return gen_p;
	}
	public void setGen_p(String gen_p) {
		this.gen_p = gen_p;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	 public static int generateId() {
		  
	        return (int) (Math.random() * 900) + 100;
	    }
	
	 private static String isValidDateFormat(String input) {
	        String regex = "^(0[1-9]|[12][0-9]|3[01])\\.(0[1-9]|1[0-2])\\.\\d{4}$";
	        Pattern pattern = Pattern.compile(regex);
	        java.util.regex.Matcher matcher = pattern.matcher(input);
	        if(matcher.matches())
	        	return input;
	      return "not";
	    }
}

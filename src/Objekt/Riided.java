package Objekt;

public class Riided {
	private String nimetus; // isendivaljad
	private String liik;
	private String hooaeg;
	private String syndmus;
	private String pilt;
	

	/**
	 * Konstruktor
	 */
	Riided(String nimetus, String liik, String hooaeg, String syndmus) {
		this.nimetus = nimetus;
		this.liik = liik;
		this.hooaeg = hooaeg;
		this.syndmus = syndmus;
		this.syndmus = pilt;
	}
	public Riided(){

	}


	public String getNimetus() {
		return nimetus;
	}

	public void setNimetus(String nimetus) {
		this.nimetus = nimetus;
	}

	public String getLiik() {
		return liik;
	}

	public void setLiik(String liik) {
		this.liik = liik;
	}

	public String getHooaeg() {
		return hooaeg;
	}

	public void setHooaeg(String hooaeg) {
		this.hooaeg = hooaeg;
	}

	public String getSyndmus() {
		return syndmus;
	}

	public void setSyndmus(String syndmus) {
		this.syndmus = syndmus;
	}
	
	public String getPilt() {
		return pilt;
	}

	public void setPilt(String pilt) {
		this.pilt = pilt;
	}

}
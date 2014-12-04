package Failid;

import java.io.*;
import java.util.ArrayList;

import Objekt.Riided;

/**
 * Klass, kus luuakse programmi failikirjutaja ja faililugeja
 * 
 * @author Eva Tiits
 */
public class Failid {
	//defineeritakse fail
	public static File file;
	/**
	 * Meedtod, kus luuakse faililugeja ja loetakse fail sisse ning tehakse
	 * Riiete objektideks
	 * 
	 * @return riideList- Arraylist Riided objektidest
	 */
	public static ArrayList<Riided> reader(String fNimi) {
		//luuakse fail
		file = new File(fNimi);
		// Arraylist kuhu pannakse kõik Riided esemed objektidena
		ArrayList<Riided> riidedList = new ArrayList<Riided>();
		// failirida
		String rida;

		try {// proovib
			// avame faili lugemise jaoks
			BufferedReader in = new BufferedReader(new FileReader(file));

			// loeme failist rida haaval; igast reast tehakse objekt ja
			// lisatakse Arraylisti
			// loetakse kuni failis tuleb tühi rida
			while ((rida = in.readLine()) != null) {
				String[] massiiv = rida.split(",");
				Riided ese = new Riided();
				ese.setNimetus(massiiv[0]);
				ese.setLiik(massiiv[1]);
				ese.setHooaeg(massiiv[2]);
				ese.setSyndmus(massiiv[3]);
				ese.setPilt(massiiv[4]);
				riidedList.add(ese);
			}
		}
		catch (FileNotFoundException e) {// kui ei saadus faili lugeda, trükib teate
			System.out.println("Faili ei leitud: \n" + e.getMessage());
			riidedList = null;
		} 
		catch (Exception e) {// kui tuli mõni muu error, trükib teate
			System.out.println("Mingi muu error: " + e.getMessage());
		}
		
		// tagastab, kui saadi fail sisse lugeda
		return riidedList;
	}

	/**
	 * Meedtod, kus luuakse failikirjutaja
	 */
	public static FileWriter writer(String fNimi) {
		//luuakse fail
		file = new File(fNimi);
		try {// proovib
			// kirjutaja lisab alati uue rea
			FileWriter writer = new FileWriter(file, true);
			// tagastab, kui saadi kirjutada
			return writer;
		}
		catch (IOException e) {// kui ei saadus faili lugeda, trükib teate
			System.out.println("Faili ei saa kirjutada!");
			System.exit(1);// ja programmi töö lõpetatakse
		}
		return null;// midagi peab tagastama
	}

}

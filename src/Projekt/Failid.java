package Projekt;

import java.io.*;
import java.util.ArrayList;

class Failid {
	
	public static ArrayList<Riided> reader() {
		// punkt t�histab jooksvat kataloogi
        // kataloogitee URL-kodeeritult (t�hikud asenduvad %20-ga, n�iteks)
        //String kataloogitee = kontekst.getResource(".").getPath();
        
        // otsime kataloogist vastavanimelist faili
       // File file = new File(kataloogitee + failiNimi);
		File file = new File("file.txt");
		ArrayList<Riided>riidedList=new ArrayList<Riided>();
		
		try {
			// avame faili lugemise jaoks
			BufferedReader in = new BufferedReader(new FileReader(file));
			String rida;
			

			// loeme failist rida haaval Objektide Arraysse
			while ((rida = in.readLine()) != null) {
				String [] massiiv=rida.split(",");
				Riided ese = new Riided();
				ese.setNimetus(massiiv[0]);
				ese.setLiik(massiiv[1]);
				ese.setHooaeg(massiiv[2]);
				ese.setSyndmus(massiiv[3]);
				riidedList.add(ese);
			}
		} catch (FileNotFoundException e) {
			System.out.println("Faili ei leitud: \n" + e.getMessage());
			riidedList = null;
		} catch (Exception e) {
			System.out.println("Mingi muu error: " + e.getMessage());
		}
		return riidedList;
	}

	static FileWriter writer() {// Meetod, mis loob failikirjutaja
		try {// proovib
			FileWriter writer = new FileWriter("file.txt", true); // kuna true, lisab nimetatud faili andmed juurde										
			return writer;// kui saadi kirjutada
		} catch (IOException e) {// kui faili ei saa kirjutada
			System.out.println("Faili ei saa kirjutada!");// tr�kib selle
			System.exit(1);// ja programmi t�� l�petatakse
		}
		return null;// midagi peab tagastama
	}





}

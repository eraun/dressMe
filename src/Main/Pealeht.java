package Main;

import java.awt.*;
import javax.swing.*;
import Lehed.Esileht;

/**
 * Peaklass, kus luuakse programmi alusraam
 * 
 * @author Eva Tiits
 */

public class Pealeht {
	// pealehe raam
	static JFrame raam;
	public static int L = 500;
	public static int P = 700;

	/**
	 * Peameetod
	 */
	public static void main(String[] args) {
		// raami kirjeldamine
		raam = new JFrame();
		raam.setTitle("dressMe");
		raam.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		raam.setSize(L, P);
		raam.setVisible(true);
		// Esilehe konteineri raamile lisamine
		Vahetus(new Esileht());

	}

	/**
	 * Konteineri sisu vahetamise meetod
	 */
	public static void Vahetus(Container nimi) {
		raam.setSize(nimi.getPreferredSize());
		raam.setContentPane(nimi);
		raam.setVisible(true);

	}
}

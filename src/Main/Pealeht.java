package Main;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

import Lehed.Esileht;

/**
 * Peaklass, kus luuakse raam
 */

public class Pealeht {
	/**
	 * Pealehe raam
	 */
	static JFrame raam;

	/**
	 * Peaklassi konstruktor
	 */
	public Pealeht() {
	}

	/**
	 * Peameetod
	 */
	public static void main(String[] args) {

		raam = new JFrame();
		raam.setTitle("dressMe");
		raam.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		raam.setSize(500, 600);
		raam.setVisible(true);
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

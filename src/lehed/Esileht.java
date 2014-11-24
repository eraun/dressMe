package lehed;

import java.awt.*;

import javax.swing.*;

import Failid.Failid;
import Main.Pealeht;
import Objekt.Riided;

import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Klass kus luuakse esileht
 */
public class Esileht extends JPanel implements ActionListener {

	/**
	 * Pealkiri
	 */
	public String sonum = "dressME";

	/**
	 * Pealkirja x-koordinaat
	 */
	public int x;

	/**
	 * Peakirja y-koordinaat
	 */
	public int y;

	/**
	 * Nupp, mis viib riideid sisestama.
	 */
	public JButton nupp1 = new JButton("Lisa uus");// nupud
	/**
	 * Nupp, mis viib riideid valima.
	 */
	public JButton nupp2 = new JButton("Riietust valima");// nupud
	public static ArrayList<Riided> rList = new ArrayList<Riided>();

	/**
	 * Konstruktor esilehe kujundamiseks. Tehakse nuppude paneel, m‰‰ratakse
	 * paigutus, lisatakse nupud, m‰‰ratakse raami suurus, omistab nuppudele
	 * tegevused.
	 */
	public Esileht() {

		JPanel jpButtons = new JPanel();
		jpButtons.add(nupp1);
		jpButtons.add(nupp2);
		// jpButtons.add(nupp2);

		this.setPreferredSize(new Dimension(500, 600));
		this.setLayout(new BorderLayout());
		this.add(jpButtons, BorderLayout.SOUTH); // kus asub nupu paneel

		nupp1.addActionListener(this);
		nupp2.addActionListener(this);
		// nupp2.addActionListener(this);

	}

	/**
	 * Konstrukor pealkirja vıtmiseks.
	 */
	// public Esileht(String sonum){
	// this.sonum=sonum;
	// }
	//
	// public String getMessage(){
	// return sonum;
	//
	// }
	//
	// public void setMessage(String sonum){
	// this.sonum=sonum;
	// repaint();
	// }

	/**
	 * Pealkirja joonistamine ja asukoha arvutamine.
	 */
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.setBackground(Color.CYAN);
		g.setFont(new Font("Arial", Font.BOLD, 52));
		FontMetrics font = g.getFontMetrics();

		int pikkus = font.stringWidth(sonum);
		int korgus = font.getAscent();

		x = getWidth() / 2 - pikkus / 2;
		y = getHeight() / 2 + korgus / 2;

		g.drawString(sonum, x, y);

	}

	// @Override
	// public void actionPerformed(ActionEvent arg0) {
	// // TODO Auto-generated method stub
	//
	// }

	/**
	 * Nuppudele tegevuste m‰‰ramine
	 */
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == nupp1) {
			Pealeht.Vahetus(new Sisestus());
		}
		if (e.getSource() == nupp2) {
			try {
				Pealeht.Vahetus(new Valik());
				rList = Failid.reader();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
	}

}
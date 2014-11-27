package Lehed;

import java.awt.*;
import javax.swing.*;
import Failid.Failid;
import Main.Pealeht;
import Objekt.Riided;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Klass, kus luuakse programmi esileht ja selle funktsionaalsus
 * 
 * @author Eva Tiits
 */
public class Esileht extends JPanel implements ActionListener {

	// avakuva nimi
	public String sonum = "dressME";
	// nuppude paneel
	public JPanel nuppudePaneel = new JPanel();
	// nuppude lisamine
	public JButton lisaUus = new JButton("Lisa uus");
	public JButton valik = new JButton("Riietust valima");
	// arraylist riide objektidest
	public static ArrayList<Riided> rList;

	/**
	 * Meetod, mis lisab raamile paneeli, seab selle kujunduse ja lisab nupud
	 */
	public Esileht() {

		// konteineri kirjeldamine
		this.setPreferredSize(new Dimension(Pealeht.L, Pealeht.P));
		this.setLayout(new BorderLayout());
		// paneeli lisamine
		this.add(nuppudePaneel, BorderLayout.SOUTH);
		// nuppude lisamine paneelile
		nuppudePaneel.add(lisaUus);
		nuppudePaneel.add(valik);

		// nuppudele tegevuse m‰‰ramine
		lisaUus.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// lisaUus nupu vajutusel minnakse Sisestus lehele
				Pealeht.Vahetus(new Sisestus());
			}
		});

		valik.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// valik nupu vajutusel minnakse Valik lehele
				try {
					Pealeht.Vahetus(new Valik());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				// valik nupu vajutusel loetakse fail sisse
				rList = Failid.reader();
			}
		});
	}

	/**
	 * Pealkirja joonistamine ja asukoha arvutamine.
	 */
	protected void paintComponent(Graphics g) {

		// teskti v‰limuse kirjeldamine
		super.paintComponent(g);
		this.setBackground(Color.CYAN);
		g.setFont(new Font("Arial", Font.BOLD, 52));
		FontMetrics font = g.getFontMetrics();
		// teskti asukoha arvutamine
		int pikkus = font.stringWidth(sonum);
		int korgus = font.getAscent();
		int x = getWidth() / 2 - pikkus / 2;
		int y = getHeight() / 2 + korgus / 2;
		// teksti joonistamine
		g.drawString(sonum, x, y);

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}
}
package Lehed;

import java.awt.Color;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Main.Pealeht;

/**
 * Klass, kus luuakse Riietus valiku aken ja kombineeritakse riiete valik,
 * vastavalt tingimustele
 * 
 * @author Eva Tiits
 */
public class Valik extends JPanel implements ActionListener {
	
	// paneelide massiiv
	JPanel[][] paneel1;

	// kuupäeva sisestus
	public JLabel today;

	// kapi sisu vaatamine
	public JButton kapiSisu = new JButton("Vaata kappi");
	public JLabel kusimus1;

	// liigi valik
	public String[] liigid2 = { "", "Pidulik riietus", "Vabaaja riietus",
			"Töö riietus" };
	public JComboBox syndmusteC = new JComboBox(liigid2);
	public String valitud;

	// riietuse soovitus nupp ja vastus
	public JLabel kusimus2;
	public JButton kombineeri = new JButton("DressMe");
	public JLabel vastus;

	// nupp mis viib esilehele
	public JButton tagasi = new JButton("Esilehele");

	// pildid
	public JLabel pilt1;
	public JLabel pilt2;

	/**
	 * Meetod, kus luuakse Riietus valiku akna kujundus ja nupud
	 * 
	 */
	public Valik() throws IOException {

		// konteineri kirjeldamine
		this.setPreferredSize(new Dimension(Pealeht.L, Pealeht.P));
		
		// defineeritakse 4 paneeli (2 rida ja 2 tulpa)
		int r = 2;
		int v = 2;
		paneel1 = new JPanel[r][v];
		setLayout(new GridLayout(r, v));
		for (int f = 0; f < r; f++) {
			for (int g = 0; g < v; g++) {
				paneel1[f][g] = new JPanel();
				add(paneel1[f][g]);
			}
		}
		
		//kapi sisu vaatamise nupu lisamine
		paneel1[0][0].add(kapiSisu, kapiSisu.CENTER);
		
		//soovitud riietuse syndmuse valiku lisamine
		kusimus1 = new JLabel("Mis puhuks soovid riietust valida?");
		kusimus1.setFont(new Font("Serif", Font.BOLD, 14));
		paneel1[0][0].add(kusimus1);
		paneel1[0][0].add(syndmusteC);
		
		//esialgne valik on tühi
		syndmusteC.setSelectedIndex(0);
		
		//riietuse kombineerimise nupu ja teksti lisamine
		paneel1[0][0].add(kombineeri);
		kusimus2 = new JLabel("Mis võiks selga panna?");
		kusimus2.setForeground(Color.BLUE);
		kusimus2.setFont(new Font("Serif", Font.BOLD, 15));
		paneel1[0][0].add(kusimus2);
		
		//aastaaja ja kuupäeva lisamine ja kuvamine
		String aa[] = aastaaeg();
		today = new JLabel("Praegu on " + aa[0].toUpperCase() + "  (" + aa[1]
				+ ")");
		today.setFont(new Font("Serif", Font.BOLD, 12));
		paneel1[0][1].add(today);
		
		//esilehele viiva nupu lisamine
		paneel1[0][1].add(tagasi);

		//piltide lisamine (esialgu pilte ei kuvata)
		pilt1 = new JLabel();
		paneel1[1][0].add(pilt1);
		pilt1.setVisible(false);
		pilt2 = new JLabel();
		paneel1[1][1].add(pilt2);
		pilt2.setVisible(false);
		
		// nuppudele tegevuse määramine

		kapiSisu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//nupule vajutades kuvatakse sisestatud riiete nimekiri (nimetused)
				if (e.getSource() == kapiSisu) {
					JOptionPane.showMessageDialog(paneel1[0][1], kapiSisu(),
							"Sinu RIIDEKAPP", JOptionPane.PLAIN_MESSAGE);
				}
			}

		});

		kombineeri.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == kombineeri) {
					//kui soovitud riietuse syndmust, et valita siis kuvatakse teade, 
					//et see on kohustuslik
					if (syndmusteC.getSelectedItem() == "") {
						JOptionPane.showMessageDialog(paneel1[0][0],
								"Palun vali riietuse liik!", "Tähelepanu!",
								JOptionPane.WARNING_MESSAGE);
					} 
					//kui syndmus valitud, kombineeritakse riietus ja pildid ning kuvatakse need
					else {
						String[] kombo = kombineeri();
						//küsimus vahetatakse vastuse vastu
						kusimus2.setText(kombo[0]);
						pilt1.setIcon(new ImageIcon(((new ImageIcon(kombo[1]))
								.getImage()).getScaledInstance(250, 250,
								java.awt.Image.SCALE_SMOOTH)));
						pilt1.setVisible(true);
						pilt2.setIcon(new ImageIcon(((new ImageIcon(kombo[2]))
								.getImage()).getScaledInstance(250, 250,
								java.awt.Image.SCALE_SMOOTH)));
						pilt2.setVisible(true);
						//lõpetamisel taastatakse algne tühi syndmuse vakik
						syndmusteC.setSelectedIndex(0);
					}
				}
			}

		});

		tagasi.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// tagasi nupu vajutusel minnakse Esilehele
				if (e.getSource() == tagasi) {
					Pealeht.Vahetus(new Esileht());
				}
			}

		});

	}

	/**
	 * Meetod, kus käiakse läbi kõik sisestatud Riided
	 * @return nimi - kõigi riiete nimetused
	 * 
	 */
	public static String kapiSisu() {
		String nimi = "";
		for (int i = 0; i < Esileht.rList.size(); i++) {
			nimi += (Esileht.rList.get(i).getNimetus() + "\r\n");

		}
		return nimi;
	}
	/**
	 * Meetod, kus leitakse tänane kuupäev ja aastaaeg
	 * @return aastaeg[] - aastaaeg, kuupäev ja objekti aastaaja tähis
	 * 
	 */
	public static String[] aastaaeg() {
		//aastaaegade massiiv
		String seasons[] = { "talv", "talv", "kevad", "kevad", "kevad", "suvi",
				"suvi", "suvi", "sügis", "sügis", "sügis", "talv" };
		//kuupäev
		java.util.Date date = Calendar.getInstance().getTime();
		//kuupäeva formaatimine
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
		//võetakse massiivist see element, mitmes käesolev kuu on
		String season = seasons[(Calendar.getInstance().get(Calendar.MONTH))];
		String kp = ("" + sdf.format(date) + "");
		//objekti jaoks aastaja tähise leidmine
		String nr;
		if (season.equals("kevad")) {
			nr = "1";
		} else if (season.equals("suvi")) {
			nr = "2";
		} else if (season.equals("sügis")) {
			nr = "3";
		} else
			nr = "4";
		String aastaaeg[] = { season, kp, nr };
		
		return aastaaeg;
	}
	
	/**
	 *Meetod, mis kombineerib vastavalt käesolevale aastaajale 
	 *ja valitud sündmusele sobiva riideesemete kombinatsiooni
	 * @return komplekt - genereertitud eseme(te) nimetus(ed) ja piltide asukohad
	 */
	public String[] kombineeri() {
		//võetakse valitud sündmuse esitäht, ehk selle tähis objektis
		if (syndmusteC.getSelectedItem() != "") {
			valitud = Character.toString(((String) syndmusteC.getSelectedItem()).charAt(0));
		}
		//moodustatakse Arraylist tingimustele vastavatest (sündmus ja aastaaeg) 
		//objektide numbritest Riiete Arraylistis
		ArrayList<Integer> sobivad = new ArrayList<>();
		for (int i = 0; i < Esileht.rList.size(); i++) {
			if ((Esileht.rList.get(i).getHooaeg().contains(Valik.aastaaeg()[2]) == true)
					&& (Esileht.rList.get(i).getSyndmus().contains(valitud) == true)) {
				sobivad.add(i);
			}
		}
		//luuakse massiiv vastuseks
		String[] komplekt = new String[3];
		
		//Randomiga valitakse üks ese loodud sovivate Arraylistist
		Random ran = new Random();
		int random = sobivad.get(ran.nextInt(sobivad.size()));
		//saadakse saadud eseme liik
		String ranLiik = Esileht.rList.get(random).getLiik();
		
		//tehakse läbi tsükkel, mis otsib saadud liigile paarilise
		//N: kui saai ülemine osa, siis ostitakse randomiga, kuni saadakse alumine ja vastupidi
		// kleidi korral lisatakse see kohe vastuse massiivi
		boolean lopp = false;
		while (!lopp) {
			switch (ranLiik) {
			default:
			case "1":
				ranLiik = "3";
				break;
			case "3":
				int random2 = sobivad.get(ran.nextInt(sobivad.size()));
				//kontrollitakse kas randomiga leitud teine ese on sobiv:
				if (Esileht.rList.get(random2).getLiik().equals("2") == true) {
					//komplekti kokku saades lisatakse info vastuse massiivi
					komplekt[0] = ("<html>"+ Esileht.rList.get(random).getNimetus()
							+ " ja <br> "
							+ Esileht.rList.get(random2).getNimetus() + "</html>")
							.toUpperCase();
					komplekt[1] = Esileht.rList.get(random).getPilt();
					komplekt[2] = Esileht.rList.get(random2).getPilt();
					//lõpetatakse tsükkel
					lopp = true;
					break;
				} 
				//kui saadud liik ei sobinud genereeritakse uuesti, 
				//kuni saadakse sobiv
				else {
					ranLiik = "3";
					break;
				}
			case "2":
				ranLiik = "4";
				break;
			case "4":
				int random3 = sobivad.get(ran.nextInt(sobivad.size()));
				//kontrollitakse kas randomiga leitud teine ese on sobiv:
				if (Esileht.rList.get(random3).getLiik().equals("1") == true) {
					//komplekti kokku saades lisatakse info vastuse massiivi
					komplekt[0] = ("<html>"
							+ Esileht.rList.get(random).getNimetus()
							+ " ja <br> "
							+ Esileht.rList.get(random3).getNimetus() + "</html>")
							.toUpperCase();
					komplekt[1] = Esileht.rList.get(random).getPilt();
					komplekt[2] = Esileht.rList.get(random3).getPilt();
					//lõpetatakse tsükkel
					lopp = true;
					break;
				} else {
					ranLiik = "4";
					break;
				}

			case "12":
				//kleidi korral lisatakse info vastuse massiivi
				komplekt[0] = (Esileht.rList.get(random).getNimetus())
						.toUpperCase();
				komplekt[1] = Esileht.rList.get(random).getPilt();
				komplekt[2] = "";
				lopp = true;
				break;
			}
		}
		return komplekt;

	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
	}

}

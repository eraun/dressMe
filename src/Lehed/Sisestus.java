package Lehed;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Failid.Failid;
import Main.Pealeht;

/**
 * Klass, kus luuakse sisestusvorm ja kirjutatake andmed faili, 
 * kutsudes v�lja failikirjutaja class-ist Failid
 * 
 * @author Eva Tiits
 */
public class Sisestus extends JPanel implements ActionListener {
	
	//paneel, kuhu luuakse sisestusvorm
	JPanel vormiPaneel = new JPanel();
	
	//nimetuse sisestus
	public JLabel kusimus1;
	public JTextField sisNimetus = new JTextField(20);
	
	//liigi valiku dropdown-men��
	public JLabel kusimus2;
	public String[] liigid = { "", "p�ksid", "pluus", "seelik", "kleit", "kampsun" };
	public JComboBox liikideC = new JComboBox(liigid);
	
	//hooaja valiku checkboxid
	public JLabel kusimus3;
	public JCheckBox kevad;
	public JCheckBox suvi;
	public JCheckBox sygis;
	public JCheckBox talv;
	
	//s�ndmuse valiku checkboxid
	public JLabel kusimus4;
	public JCheckBox pidu;
	public JCheckBox vaba;
	public JCheckBox kontor;
	
	//pildi lisamise nupp
	public JButton pilt = new JButton("Lisa pilt");
	//kui pilti ei lisata, siis salvestatakse faili default pilt
	public String riidePilt = "C:/Users/Eva/Documents/pildid/default.jpg";
	
	//paneel, kuhu lisatakse nupud
	JPanel nupuPaneel = new JPanel();

	//andmete salvestamise nupp
	public JButton salvesta = new JButton("Salvesta");
	//salvestuse �nnestumise tagasiside
	public JLabel tagasiside;
	
	//nupp mis viib esilehele
	public JButton tagasi = new JButton("Esilehele");

	/**
	 * Meetod, kus moodustakse sisestusvorm ja salvestakse sisestatud andmed faili 
	 * 
	 */
	public Sisestus() {
		
		// konteineri kirjeldamine
		this.setPreferredSize(new Dimension(Pealeht.L, Pealeht.P));
		
		//paneeli kirjeldus ja lisamine konteinerisse
		vormiPaneel.setLayout(new GridLayout(0, 1));
		this.add(vormiPaneel);
		
		//nimetuse sisestuse lisamine vormiPaneelile
		kusimus1 = new JLabel("Mis on selle riietuseseme nimetus?");
		vormiPaneel.add(kusimus1);
		vormiPaneel.add(sisNimetus);

		//liigi lisamine vormiPaneelile
		kusimus2 = new JLabel("Mis on lisatud riide liik?");
		vormiPaneel.add(kusimus2);
		vormiPaneel.add(liikideC);
		//esialgne valik on t�hi
		liikideC.setSelectedIndex(0);
		
		//hooaja lisamine vormiPaneelile
		kusimus3 = new JLabel("Mis aastaagadel seda saab kanda?");
		kevad = new JCheckBox("kevad");
		suvi = new JCheckBox("suvi");
		sygis = new JCheckBox("sygis");
		talv = new JCheckBox("talv");
		vormiPaneel.add(kusimus3);
		vormiPaneel.add(kevad);
		vormiPaneel.add(suvi);
		vormiPaneel.add(sygis);
		vormiPaneel.add(talv);
		
		//s�ndmuse lisamine vormiPaneelile
		kusimus4 = new JLabel("Milliseks puhuks on see riietus sobilik?");
		pidu = new JCheckBox("pidulik");
		vaba = new JCheckBox("vabaaeg");
		kontor = new JCheckBox("kontor-t��");
		vormiPaneel.add(kusimus4);
		vormiPaneel.add(pidu);
		vormiPaneel.add(vaba);
		vormiPaneel.add(kontor);
		
		//pildi lisamise nupu lisamine vormiPaneelile
		vormiPaneel.add(pilt);
		
		//nupuPaneeli lisamine vormiPaneelile
		vormiPaneel.add(nupuPaneel, new BorderLayout().SOUTH);
		
		//nuppude ja tagasiside lisamine nupuPaneelile
		nupuPaneel.add(salvesta);
		nupuPaneel.add(tagasi);
		tagasiside = new JLabel("Andmed salvestatud!");
		vormiPaneel.add(tagasiside);
		//esialgu on tagasiside n�htamatu
		tagasiside.setVisible(false);
		
		// nuppudele tegevuse m��ramine

		pilt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//failikataloogi avamine
				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new java.io.File("."));
				chooser.setDialogTitle("Pildi asukoha salvestamine:");
				chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				chooser.setAcceptAllFileFilterUsed(false);

				//Save vajutamisel, salvestatakse pildi asukoht Strigina ja kuvatakse vastav tekst
				if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
					riidePilt = ((chooser.getSelectedFile()).toString()).replaceAll("\\\\", "/");
					pilt.setText("Pilt salvestatud!");
				} 
				// kui ei salvestata pilti, siis kuvatakse nupul vastav tekst
				else {
					pilt.setText("Pilti ei salvestatud!");
				}
			}
		});

		salvesta.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Kontrollib, kas m�ni vormiv�ljadest on t�hjaks j�etud
				if (sisNimetus.getText().isEmpty()
						|| liikideC.getSelectedItem() == ""
						|| (kevad.isSelected() == false
								&& suvi.isSelected() == false
								&& sygis.isSelected() == false 
								&& talv.isSelected() == false)
						|| (pidu.isSelected() == false
								&& vaba.isSelected() == false 
								&& kontor.isSelected() == false)) {
					//kui on t�hi, siis kuvab hoiatuse
					JOptionPane.showMessageDialog(nupuPaneel,
							"Vasta palun k�igile k�simustele!", "T�helepanu!",
							JOptionPane.WARNING_MESSAGE);
				}
				
				// Kui k�ik k�ik v�ljad on t�idetud, siis lisab sisestatud andmed faili �he reana
				//komadega eraldatuna
				else {
					//loome kirjutaja
					FileWriter writer=Failid.writer(Esileht.fNimi);
					
					// lisab faili nimetuse
					String valitudNimetus = sisNimetus.getText();
					
					// lisab faili 1 (kui on �lemised riided), 
					//2(kui on alumised riided), 3 (kui on kleit)
					String valitudLiik = "";
					if (liikideC.getSelectedItem().equals(liigid[1])
							|| liikideC.getSelectedItem().equals(liigid[3])) {
						valitudLiik += "2";
					}
					if (liikideC.getSelectedItem().equals(liigid[2])
							|| liikideC.getSelectedItem().equals(liigid[5])) {
						valitudLiik += "1";
					}
					if (liikideC.getSelectedItem().equals(liigid[4])) {
						valitudLiik += "12";
					}

					// lisab faili aastaajad numbritena, vastavalt 1,2,3,4 
					//ning salvestamisel t�hjendab valitud ruudu(d)
					String aasta = "";
					if (kevad.isSelected() == true) {
						aasta += "1";
						kevad.setSelected(false);
					}
					if (suvi.isSelected() == true) {
						aasta += "2";
						suvi.setSelected(false);
					}
					if (sygis.isSelected() == true) {
						aasta += "3";
						sygis.setSelected(false);
					}
					if (talv.isSelected() == true) {
						aasta += "4";
						talv.setSelected(false);
					}

					// lisab faili sydmused vastavalt t�htedega P,V,T
					//ning salvestamisel t�hjendab valitud ruudu(d)
					String syndmus = "";
					if (pidu.isSelected() == true) {
						syndmus += "P";
						pidu.setSelected(false);
					}
					if (vaba.isSelected() == true) {
						syndmus += "V";
						vaba.setSelected(false);
					}
					if (kontor.isSelected() == true) {
						syndmus += "T";
						kontor.setSelected(false);
					}

					//kui k�ik korras, kirjutab faili kogutud info �hele reale
					//l�ppu lisatakse rea vahetus ning ekraanil kuvatakse tagaside �nnestumise kohta
					try {
						writer.write(valitudNimetus + "," + valitudLiik + ","
								+ aasta + "," + syndmus + "," + riidePilt
								+ "\r\n");
						writer.close();
						tagasiside.setVisible(true);
						
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					//taastatkse vormi t�hjad algseaded
					sisNimetus.setText("");
					liikideC.setSelectedIndex(0);
					pilt.setText("Lisa pilt");
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

	@Override
	public void actionPerformed(ActionEvent arg0) {
	}

}

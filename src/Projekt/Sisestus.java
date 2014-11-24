package Projekt;

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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

	/**
	 *Klass loob sisestusvormi
	 */
	public class Sisestus extends JPanel implements ActionListener{
		/**
	 	 *Nimetuse lisamine.
	 	 */ 
		public JLabel kusimus1;
		public JTextField sisNimetus = new JTextField(20);
		/**
	 	 *Liikide valik menyy loomine.
	 	 */ 
		public JLabel kusimus2;
		public String[] liigid = {"", "püksid", "pluus", "seelik", "kleit", "kampsun" };
		public JComboBox liikideC=new JComboBox(liigid);
		/**
	 	 *Hooaegade valik menyy loomine.
	 	 */ 
		public JLabel kusimus3;
		public JCheckBox kevad;
		public JCheckBox suvi;
		public JCheckBox sygis;
		public JCheckBox talv;
		/**
	 	 *Syndmuse valik menyy loomine.
	 	 */ 
		public JLabel kusimus4;
		public JCheckBox pidu;
		public JCheckBox vaba;
		public JCheckBox kontor;
		/**
	 	 *Salvestusnupu loomine.
	 	 */ 
	 	public JButton salvesta=new JButton("Salvesta");
	 	public JLabel tagasiside;
	 	
	 	public JButton tagasi=new JButton("Esilehele");

	 	
		
		public Sisestus(){
			
			//JDialog.setDefaultLookAndFeelDecorated(true);
			//final Container contentPane = this;
	        this.setPreferredSize(new Dimension (500,600));
	        //this.setLayout(new GridLayout(0,1));
			
	        JPanel paneel1=new JPanel();
	        paneel1.setLayout(new GridLayout(0,1));
			paneel1.setVisible(true);
			this.add(paneel1);
			
		
			kusimus1=new JLabel("Mis on selle riietuseseme nimetus?");
			paneel1.add(kusimus1); 
			paneel1.add(sisNimetus); 
			
			kusimus2=new JLabel("Mis on lisatud riide liik?");
		    paneel1.add(kusimus2);
			paneel1.add(liikideC); 
			liikideC.setSelectedIndex(0);//mitu valikut
			
			kusimus3=new JLabel("Mis aastaagadel seda saab kanda?");
			kevad = new JCheckBox("kevad");
			suvi = new JCheckBox("suvi");
			sygis = new JCheckBox("sygis");
			talv = new JCheckBox("talv");

			paneel1.add(kusimus3);
			paneel1.add(kevad);
			paneel1.add(suvi);
			paneel1.add(sygis);
			paneel1.add(talv);
			
			kusimus4=new JLabel("Milliseks puhuks on see riietus sobilik?");
			pidu = new JCheckBox("pidulik");
			vaba = new JCheckBox("vabaaeg");
			kontor = new JCheckBox("kontor-töö");
			paneel1.add(kusimus4);
			paneel1.add(pidu);
			paneel1.add(vaba);
			paneel1.add(kontor);
	
			JPanel paneel2=new JPanel();
	
			paneel2.add(salvesta); 
			paneel2.add(tagasi); 
			paneel1.add(paneel2, new BorderLayout().SOUTH);
			tagasiside=new JLabel("Andmed salvestatud!");
			paneel1.add(tagasiside);
			tagasiside.setVisible(false);
			
			
			salvesta.addActionListener(new ActionListener() {
			    //private String file;

				@Override
			    public void actionPerformed(ActionEvent e) {
					
				   //Kontrollib kas on tyhjad
					if(sisNimetus.getText().isEmpty() || liikideC.getSelectedItem()==""
					||(kevad.isSelected()==false && suvi.isSelected()==false && sygis.isSelected()==false && talv.isSelected()==false)
					||(pidu.isSelected()==false && vaba.isSelected()==false && kontor.isSelected()==false)){
				       JOptionPane.showMessageDialog(paneel2, "Vasta palun kõigile küsimustele!", "Tähelepanu!", JOptionPane.WARNING_MESSAGE);
				    }
					//Juhul kui kõik on täidetud siis:
				    else{
			    	FileWriter kirjutab = Failid.writer();// loome failikirjutaja
			    	String valitudNimetus=sisNimetus.getText();
			        String valitudLiik = "";
			        if(liikideC.getSelectedItem().equals(liigid[1])||liikideC.getSelectedItem().equals(liigid[3])){
			        	valitudLiik+="2";
			        }
			        if(liikideC.getSelectedItem().equals(liigid[2])||liikideC.getSelectedItem().equals(liigid[5])){
			        	valitudLiik+="1";
			        }
			        if(liikideC.getSelectedItem().equals(liigid[4])){
			        	valitudLiik+="12";
			        }
			        
			        //aastaajad
			        String aasta="";
			        if(kevad.isSelected()==true){
			        	aasta+="1";
			        	kevad.setSelected(false);
			        }
			        if(suvi.isSelected()==true){
			        	aasta+="2";
			        	suvi.setSelected(false);
			        }
			        if(sygis.isSelected()==true){
			        	aasta+="3";
			        	sygis.setSelected(false);
			        }
			        if (talv.isSelected()==true){
			        	aasta+="4";
			        	talv.setSelected(false);
			        }
			        
			        //syndmused
			        String syndmus="";
			        if(pidu.isSelected()==true){
			        	syndmus+="P";
			        	pidu.setSelected(false);
			        }
			        if(vaba.isSelected()==true){
			        	syndmus+="V";
			        	vaba.setSelected(false);
			        }
			        if(kontor.isSelected()==true){
			        	syndmus+="T";
			        	kontor.setSelected(false);
			        }
			        
			        	
			        try {
						kirjutab.write(valitudNimetus +","+valitudLiik+","+aasta+","+syndmus+"\r\n");
						kirjutab.close(); 
						tagasiside.setVisible(true);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			        sisNimetus.setText("");
			        liikideC.setSelectedIndex(0);
				    }
			    }  
		     });
			
			tagasi.addActionListener(new ActionListener() {
				@Override
			    public void actionPerformed(ActionEvent e) {
					if(e.getSource()==tagasi){
					Pealeht.Vahetus(new Esileht());
					}
				}	
				
			});
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
		}
	 
	}  

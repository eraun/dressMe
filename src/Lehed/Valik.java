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

public class Valik extends JPanel implements ActionListener {
	
	//public static ArrayList<Riided> rList=Failid.reader();
	public JLabel today;
	public JButton nupp1=new JButton("Vaata kappi");
	public JLabel kusimus1;
	public String[] liigid2 = {"", "Pidulik riietus", "Vabaaja riietus", "Töö riietus"};
	public JComboBox syndmusteC=new JComboBox(liigid2);
	public JLabel kusimus2;
	public JButton nupp2=new JButton("DressMe");
	public JLabel vastus;
	public String valitud;
	public JButton tagasi=new JButton("Esilehele");
	public JLabel pilt1;
	public JLabel pilt2;
	public Valik() throws IOException{
		    this.setPreferredSize(new Dimension (500,700));
		
	        int r=2;
	        int v=2;
		    JPanel [][] paneel1=new JPanel[r][v];
	        setLayout(new GridLayout(r,v));
			//paneel1.setVisible(true);
	        for (int f = 0; f < r; f++) {
				for (int g = 0; g < v; g++) {
					paneel1[f][g]=new JPanel();
					add(paneel1[f][g]);
				}
			}
	      
			
			kusimus1=new JLabel("Mis puhuks soovid riietust valida?");
			kusimus1.setFont(new Font("Serif", Font.BOLD, 14));
			//kusimus1.setForeground(Color.RED);
			paneel1[0][0].add(nupp1, nupp1.CENTER);
			paneel1[0][0].add(kusimus1);
			paneel1[0][0].add(syndmusteC); 
			syndmusteC.setSelectedIndex(0);//mitu valikut, 1 kuna muidu error
			paneel1[0][0].add(nupp2);
			kusimus2=new JLabel("Mis võiks selga panna?");
			kusimus2.setForeground(Color.BLUE);
			kusimus2.setFont(new Font("Serif", Font.BOLD, 15));
			paneel1[0][0].add(kusimus2);
			
			String aa []=aastaaeg();
			today=new JLabel("Praegu on "+aa[0].toUpperCase()+"  ("+aa[1]+")");
			today.setFont(new Font("Serif", Font.BOLD, 12));
			paneel1[0][1].add(today);
			//paneel1[0][1].add(nupp1, nupp1.CENTER);
			paneel1[0][1].add(tagasi);
			
			pilt1 = new JLabel();
			paneel1[1][0].add(pilt1); 
			pilt1.setVisible(false);
			
			pilt2 = new JLabel();    
			paneel1[1][1].add(pilt2); 
			pilt2.setVisible(false);
			
			nupp1.addActionListener(new ActionListener() {
				@Override
			    public void actionPerformed(ActionEvent e) {
					if(e.getSource()==nupp1){
						JOptionPane.showMessageDialog(paneel1[0][1],
							    kapiSisu(),
							    "Sinu RIIDEKAPP",
							    JOptionPane.PLAIN_MESSAGE);
					}
				}	
				
			});
			
			nupp2.addActionListener(new ActionListener() {
				@Override
			    public void actionPerformed(ActionEvent e) {
					if(e.getSource()==nupp2){
						if(syndmusteC.getSelectedItem()==""){
							JOptionPane.showMessageDialog(paneel1[0][0], "Palun vali riietuse liik!", "Tähelepanu!", JOptionPane.WARNING_MESSAGE);
						}
						else{
						String [] kombo=kombineeri();	
						kusimus2.setText(kombo[0]);
						pilt1.setIcon(new ImageIcon(((new ImageIcon(kombo[1])).getImage()).getScaledInstance(250, 250, java.awt.Image.SCALE_SMOOTH))); 
						pilt1.setVisible(true);
						pilt2.setIcon(new ImageIcon(((new ImageIcon(kombo[2])).getImage()).getScaledInstance(250, 250, java.awt.Image.SCALE_SMOOTH))); 
						pilt2.setVisible(true);
						syndmusteC.setSelectedIndex(0);
						
						}
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
	//riidekapi sisu
	public static String kapiSisu(){
		String nimi="";
		for (int i = 0; i < Esileht.rList.size(); i++) {
		nimi+=(Esileht.rList.get(i).getNimetus()+"\r\n");
	
		}
		return nimi;
	}

	public static String [] aastaaeg(){
		String seasons[] = {"talv", "talv", "kevad", "kevad", "kevad", "suvi", 
				  "suvi", "suvi", "sügis", "sügis", "sügis", "talv"};
		java.util.Date date = Calendar.getInstance().getTime();
		SimpleDateFormat sdf=new SimpleDateFormat("dd.MM.yyyy");
		String season=seasons[(Calendar.getInstance().get(Calendar.MONTH))];
		String kp=(""+sdf.format(date)+"");
		String nr;
		if(season.equals("kevad")){
			nr="1";
		}
		else if (season.equals("suvi")){
			nr="2";
		}
		else if (season.equals("sügis")){
			nr="3";
		}
		else nr="4";
		
		String aastaaeg[]={season,kp,nr};
		
		return aastaaeg;
		
	}
	
	public String [] kombineeri(){
	
		//Kombineerija
		if(syndmusteC.getSelectedItem()!=""){
		valitud=Character.toString(((String) syndmusteC.getSelectedItem()).charAt(0));}
		ArrayList<Integer> sobivad=new ArrayList<>();
		for (int i = 0; i < Esileht.rList.size(); i++) {
			if((Esileht.rList.get(i).getHooaeg().contains(Valik.aastaaeg()[2])==true) 
				&& (Esileht.rList.get(i).getSyndmus().contains(valitud)==true)){
				sobivad.add(i);
			}
		}
		Random ran=new Random();
		int random=sobivad.get(ran.nextInt(sobivad.size()));
		String ranLiik=Esileht.rList.get(random).getLiik();
		String [] komplekt = new String[3];
		boolean lopp=false;
		while(!lopp){
			switch(ranLiik){
			default:
			case "1":
				ranLiik="3";
				break;
			case "3":
				int random2=sobivad.get(ran.nextInt(sobivad.size()));
				if(Esileht.rList.get(random2).getLiik().equals("2")==true){
					komplekt[0]=("<html>"+Esileht.rList.get(random).getNimetus()+" ja <br> "+Esileht.rList.get(random2).getNimetus()+"</html>").toUpperCase();
					komplekt[1]=Esileht.rList.get(random).getPilt();
					komplekt[2]=Esileht.rList.get(random2).getPilt();
					lopp=true;
					break;
				}
				else{ 
					ranLiik="3";
					break;
				}
			case "2":
				ranLiik="4";
				break;
			case "4":
				int random3=sobivad.get(ran.nextInt(sobivad.size()));
				if(Esileht.rList.get(random3).getLiik().equals("1")==true){
					komplekt[0]=("<html>"+Esileht.rList.get(random).getNimetus()+" ja <br> "+Esileht.rList.get(random3).getNimetus()+"</html>").toUpperCase();
					komplekt[1]=Esileht.rList.get(random).getPilt();
					komplekt[2]=Esileht.rList.get(random3).getPilt();
					lopp=true;
					break;
				}
				else{ 
					ranLiik="4";
					break;
				}
				
			case "12":
				komplekt[0]=(Esileht.rList.get(random).getNimetus()).toUpperCase();
				komplekt[1]=Esileht.rList.get(random).getPilt();
				komplekt[2]="";
				lopp=true;
				break;	
				
			
			}
		}
		return komplekt;
		

		}


}

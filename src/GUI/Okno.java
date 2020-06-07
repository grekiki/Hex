package GUI;

import javax.swing.*;

import logika.Stanje;
import vodja.Vodja;
import logika.Igra;


import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JColorChooser;

@SuppressWarnings("serial") public class Okno extends JFrame implements ActionListener{

	protected Platno platno;

	//Statusna vrstica v spodnjem delu okna
	private JLabel status;

	// Izbire v meniju
	private JMenuItem igraClovekRacunalnik;
	private JMenuItem igraRacunalnikClovek;
	private JMenuItem igraClovekClovek;
	private JMenuItem igraRacunalnikRacunalnik;
	private JMenuItem velikostPlosce;
	private JMenuItem barvaPrvega;
	private JMenuItem barvaDrugega;
	private JMenuItem random;
	private JMenuItem minimax;
	private JMenuItem zamikNasprotnika;
	private JMenuItem ime1;
	private JMenuItem ime2;
	
	// igralca
	private String igralec1 = "Igralec 1";
	private String igralec2 = "Igralec 2";

	public Okno(String ime,Platno platno){
		this.setTitle(ime);
		this.platno=platno;
		this.setVisible(true);

		//meni

		JMenuBar jmb=new JMenuBar();
		JMenu novaIgra=new JMenu("Nova Igra");
		JMenu nastavitve=new JMenu("Nastavitve");
		this.setJMenuBar(jmb);
		jmb.add(novaIgra);
		jmb.add(nastavitve);

		igraClovekRacunalnik=new JMenuItem("Človek – računalnik");
		novaIgra.add(igraClovekRacunalnik);
		igraClovekRacunalnik.addActionListener(this);

		igraRacunalnikClovek=new JMenuItem("Računalnik – človek");
		novaIgra.add(igraRacunalnikClovek);
		igraRacunalnikClovek.addActionListener(this);

		igraClovekClovek=new JMenuItem("Človek – človek");
		novaIgra.add(igraClovekClovek);
		igraClovekClovek.addActionListener(this);

		igraRacunalnikRacunalnik=new JMenuItem("Računalnik – računalnik");
		novaIgra.add(igraRacunalnikRacunalnik);
		igraRacunalnikRacunalnik.addActionListener(this);

		JMenu imeIgralcev=new JMenu("Ime");
		nastavitve.add(imeIgralcev);

		ime1=new JMenuItem("Ime igralca 1");
		imeIgralcev.add(ime1);
		ime1.addActionListener(this);

		ime2=new JMenuItem("Ime igralca 2");
		imeIgralcev.add(ime2);
		ime2.addActionListener(this);

		JMenu nasprotnik=new JMenu("Nasprotnik");
		nastavitve.add(nasprotnik);
		
		JMenu pametNasprotnika=new JMenu("Algoritem");
		nasprotnik.add(pametNasprotnika);
		
		random = new JMenuItem("Naključno");
		pametNasprotnika.add(random);
		random.addActionListener(this);
		
		minimax = new JMenuItem("Minimax");
		pametNasprotnika.add(minimax);
		minimax.addActionListener(this);

		zamikNasprotnika=new JMenuItem("Zamik");
		nasprotnik.add(zamikNasprotnika);
		zamikNasprotnika.addActionListener(this);

		velikostPlosce=new JMenuItem("Velikost plošče");
		nastavitve.add(velikostPlosce);
		velikostPlosce.addActionListener(this);

		barvaPrvega=new JMenuItem("Barva igralca 1");
		nastavitve.add(barvaPrvega);
		barvaPrvega.addActionListener(this);

		barvaDrugega=new JMenuItem("Barva igralca 2");
		nastavitve.add(barvaDrugega);
		barvaDrugega.addActionListener(this);

		/////////////////////////////////////

		// igralno polje
		setLayout(new GridBagLayout());


		GridBagConstraints polje_layout=new GridBagConstraints();
		polje_layout.gridx=0;
		polje_layout.gridy=0;
		polje_layout.fill=GridBagConstraints.BOTH;
		polje_layout.weightx=1.0;
		polje_layout.weighty=1.0;
		this.add(platno,polje_layout);

		// statusna vrstica za sporočila
		status=new JLabel();
		status.setFont(new Font(status.getFont().getName(),status.getFont().getStyle(),20));
		GridBagConstraints status_layout=new GridBagConstraints();
		status_layout.gridx=0;
		status_layout.gridy=1;
		status_layout.anchor=GridBagConstraints.CENTER;
		getContentPane().add(status,status_layout);

		status.setText("Dobrodošli v HEX! Izberite igro!");

	}
	
	@Override public void actionPerformed(ActionEvent e){
		if (e.getSource() == barvaPrvega) { 
		Color novaBarva = JColorChooser.showDialog(this,
				"Izberite barvo", platno.barvaIgralca1);
		platno.barva1(novaBarva);
		}
		else if (e.getSource() == barvaDrugega) {
			Color novaBarva = JColorChooser.showDialog(this,
					"Izberite barvo", platno.barvaIgralca2);
			platno.barva2(novaBarva);
		}
		else if (e.getSource() == ime1) {
			String i = JOptionPane.showInputDialog("Spremenite ime igralca 1");
			preimenuj(true, i);
		}
		else if (e.getSource() == ime2) {
			String i = JOptionPane.showInputDialog("Spremenite ime igralca 2");
			preimenuj(false, i);
		}
		else if (e.getSource() == velikostPlosce) {
			String i = JOptionPane.showInputDialog("Izberite število vrstic in stolpičev.");
			if (i!=null && !i.equals("")) {
			try {
				   
				int n = Integer.parseInt(i);
				if (n > 0) {
				Igra nova = new Igra(n);
				this.platno.nastaviIgro(nova);
				Vodja.igramoNovoIgro(nova);}
				else {JOptionPane.showMessageDialog(this, "Neveljavna izbira!");}		   
				}
				catch (NumberFormatException ex)
				{
				   JOptionPane.showMessageDialog(this, "Neveljavna izbira!");
				}
		}}
		else if (e.getSource() == igraClovekRacunalnik) {
			Vodja.kdoIgra(true, false);
			Igra nova = new Igra(this.platno.igra.N);
			this.platno.nastaviIgro(nova);
			Vodja.igramoNovoIgro(nova);
		}
		else if (e.getSource() == igraClovekClovek) {
			Vodja.kdoIgra(true, true);
			Igra nova = new Igra(this.platno.igra.N);
			this.platno.nastaviIgro(nova);
			Vodja.igramoNovoIgro(nova);
		}
		else if (e.getSource() == igraRacunalnikRacunalnik) {
			Vodja.kdoIgra(false, false);
			Igra nova = new Igra(this.platno.igra.N);
			this.platno.nastaviIgro(nova);
			Vodja.igramoNovoIgro(nova);
		}
		else if (e.getSource() == igraRacunalnikClovek) {
			Vodja.kdoIgra(false, true);
			Igra nova = new Igra(this.platno.igra.N);
			this.platno.nastaviIgro(nova);
			Vodja.igramoNovoIgro(nova);
		}
		else if (e.getSource() == zamikNasprotnika) {
			String i = JOptionPane.showInputDialog("Izberite dolžino računalnikovega zamika v sekundah.");
			if (i!=null && !i.equals("")) {
			try {
				   
				int n = Integer.parseInt(i);
				if (n >= 0) {
				Vodja.nastaviZamik(n);}
				else {JOptionPane.showMessageDialog(this, "Neveljavna izbira!");}
				}
				catch (NumberFormatException ex)
				{
				   JOptionPane.showMessageDialog(this, "Neveljavna izbira!");
				}
		}}
		else if (e.getSource() == random) {
			Vodja.nasprotnikRandom();
		}
		else if (e.getSource() == minimax) {
			Vodja.nasprotnikMinimax();
		}

		osveziGUI();

	}
	public void preimenuj(boolean b, String niz) {
		if (niz != null && !niz.equals("")) {
		if (b) {
			this.igralec1 = niz;
		}
		else this.igralec2 = niz;
		}
		osveziGUI();
	}
	
	public void osveziGUI(){
		platno.repaint();
		if (this.platno.igra.getStanje() == Stanje.V_TEKU) {
			switch(this.platno.igra.naPotezi) {
			case PRVI:  
				status.setForeground(platno.barvaIgralca1); 
				status.setText("Na vrsti je " + igralec1 + ".");
				
			break;
			case DRUGI:  
				status.setForeground(platno.barvaIgralca2); 
				status.setText("Na vrsti je " + igralec2 + ".");
			break;
			}}
		else {
			switch(this.platno.igra.getZmagovalec()) {
			case PRVI: 
				status.setForeground(platno.barvaIgralca1);
				status.setText("Zmagal je " + igralec1 + "!");
			break;
			case DRUGI: 
				status.setForeground(platno.barvaIgralca2);
				status.setText("Zmagal je " + igralec2 + "!");
			break;
		}
			
		}

	}
}

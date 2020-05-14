package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

@SuppressWarnings("serial") public class Okno extends JFrame implements ActionListener{

	protected Platno platno;

	//Statusna vrstica v spodnjem delu okna
	private JLabel status;

	// Izbire v menujih
	private JMenuItem igraClovekRacunalnik;
	private JMenuItem igraRacunalnikClovek;
	private JMenuItem igraClovekClovek;
	private JMenuItem igraRacunalnikRacunalnik;
	private JMenuItem velikostPlosce;
	private JMenuItem barvaPrvega;
	private JMenuItem barvaDrugega;
	private JMenuItem pametNasprotnika;
	private JMenuItem zamikNasprotnika;
	private JMenuItem ime1;
	private JMenuItem ime2;

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

		pametNasprotnika=new JMenuItem("Algoritem");
		nasprotnik.add(pametNasprotnika);
		pametNasprotnika.addActionListener(this);

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
//		JPanel wrapperPanel = new JPanel(new GridBagLayout());
//		wrapperPanel.setBackground(Color.white);
//		wrapperPanel.add(platno);

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

		status.setText("Izberite igro!");

	}
	@Override public void actionPerformed(ActionEvent e){
		//TODO
	}
	public void osveziGUI(){
		platno.repaint();
	}

}

package vodja;

import javax.swing.SwingWorker;

import GUI.Okno;
import inteligenca.Inteligenca;
import logika.Igra;
import logika.Igralec;
import splosno.Koordinati;
import logika.Stanje;

public class Vodja{
	public static Okno okno;

	public static Igra igra=null;
	public static boolean prviJeClovek=false;
	public static boolean drugiJeClovek=true;

	public static boolean clovekNaVrsti=false;

	public static void igramoNovoIgro(){
		igra=new Igra();
		igramo();
	}

	public static void igramo(){
		okno.osveziGUI();
		System.out.println(igra.getStanje());
		switch(igra.getStanje()){
			case ZMAGA_PRVI:
				break;
			case ZMAGA_DRUGI:
				break;
			case V_TEKU:
				Igralec igralec=igra.naPotezi;
				VrstaIgralca vrstaNaPotezi=igralec==Igralec.PRVI?(prviJeClovek?VrstaIgralca.C:VrstaIgralca.R):(drugiJeClovek?VrstaIgralca.C:VrstaIgralca.R);
				switch(vrstaNaPotezi){
					case C:
						clovekNaVrsti=true;
						break;
					case R:
						clovekNaVrsti=false;
						igrajRacunalnikovoPotezo();
						break;
				}
		}
	}

	public static Inteligenca racunalnikovaInteligenca=new Inteligenca("Spam");

	public static void igrajRacunalnikovoPotezo(){
		SwingWorker<Koordinati,Void> worker=new SwingWorker<Koordinati,Void>(){
			@Override protected Koordinati doInBackground(){
				Koordinati poteza=racunalnikovaInteligenca.izberiPotezo(igra);
				return poteza;
			}
			@Override protected void done(){
				Koordinati poteza=null;
				try{
					poteza=racunalnikovaInteligenca.izberiPotezo(igra);
				}catch(Exception e){};
				System.out.println(poteza);
				igra.odigraj(poteza);
				igramo();
			}
		};
		worker.execute();
		okno.osveziGUI();
	}

	public static void igrajClovekovoPotezo(Koordinati poteza){
		if(igra.odigraj(poteza))
			clovekNaVrsti=false;
		igramo();
	}

}

package vodja;

import java.util.concurrent.TimeUnit;

import javax.swing.SwingWorker;

import GUI.Okno;
import inteligenca.Inteligenca;
import inteligenca.Minimax;
import logika.Igra;
import logika.Igralec;
import splosno.Koordinati;
import inteligenca.VrstaRacunalnika;


public class Vodja{
	public static Okno okno;
	
	public static Igra igra=null;
	public static boolean prviJeClovek=false;
	public static boolean drugiJeClovek=true;

	public static boolean clovekNaVrsti=false;
	
	public static int zamik = 2;
	
	public static  VrstaRacunalnika vrsta = VrstaRacunalnika.MINIMAX; 

	public static void igramoNovoIgro(Igra i){
		igra=i;
		igramo();
	}
	public static void igramoNovoIgro(){
		igra=new Igra();
		igramo();
	}

	public static void igramo(){
		okno.osveziGUI();
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

	public static Inteligenca racunalnikovaInteligenca=new Inteligenca();
	public static Minimax minimax = new Minimax(2);


	//odvisno od tega, kateri algoritem smo izbrali, računalnik drugače igra
	
	public static void igrajRacunalnikovoPotezo(){
		if (vrsta == VrstaRacunalnika.RANDOM) {
		SwingWorker<Koordinati,Void> worker=new SwingWorker<Koordinati,Void>(){
			@Override protected Koordinati doInBackground(){
				Koordinati poteza=racunalnikovaInteligenca.izberiPotezo(igra);

				try {TimeUnit.SECONDS.sleep(zamik);} catch (Exception e) {};
				return poteza;
			}
			@Override protected void done(){
				Koordinati poteza=null;
				try{
					poteza = get();
				}catch(Exception e){};
				igra.odigraj(poteza);
				igramo();

			}
		};
		worker.execute();}
		else  {
			SwingWorker<Koordinati,Void> worker=new SwingWorker<Koordinati,Void>(){
				@Override protected Koordinati doInBackground(){
					Koordinati poteza = minimax.izberiPotezo(igra);
					try {TimeUnit.SECONDS.sleep(zamik);} catch (Exception e) {};
					return poteza;
				}
				@Override protected void done(){
					Koordinati poteza=null;
					try{
						poteza = get();
					}catch(Exception e){};
					igra.odigraj(poteza);
					igramo();

				}
			};
			worker.execute();
		}
		okno.osveziGUI();
	}

	public static void igrajClovekovoPotezo(Koordinati poteza){
		if(igra.odigraj(poteza))
			clovekNaVrsti=false;
		igramo();
	}
public static void kdoIgra(boolean a, boolean b) {
	prviJeClovek = a;
	drugiJeClovek = b;
	}
public static void nastaviZamik(int n) {
	zamik = n;
}
public static void nasprotnikRandom() {
	vrsta = VrstaRacunalnika.RANDOM;
	}
public static void nasprotnikMinimax() {
	vrsta = VrstaRacunalnika.MINIMAX;
	}

}

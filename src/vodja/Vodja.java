package vodja;

import java.util.concurrent.TimeUnit;

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
	
	public static int zamik = 2;

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
		//System.out.println(igra.getStanje());
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
				try {TimeUnit.SECONDS.sleep(zamik);} catch (Exception e) {};
				//spremenil vrsti red
				return poteza;
			}
			@Override protected void done(){
				Koordinati poteza=null;
				try{
					//poteza=racunalnikovaInteligenca.izberiPotezo(igra);
					poteza = get();
				}catch(Exception e){};
				//System.out.println(poteza);
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
public static void kdoIgra(boolean a, boolean b) {
	prviJeClovek = a;
	drugiJeClovek = b;
	}
public static void nastaviZamik(int n) {
	zamik = n;
}
}

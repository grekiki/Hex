package inteligenca;

import java.util.ArrayList;
import java.util.List;
import logika.Igra;
import logika.Igralec;
import logika.Polje;
import splosno.KdoIgra;
import splosno.Koordinati;

public class Minimax extends KdoIgra {
		
		private static final int ZMAGA = Integer.MAX_VALUE; // vrednost zmage
		private static final int ZGUBA = -ZMAGA;  // vrednost izgube
	
		
		private int globina;
		
		public Minimax () {
			super("Skupina instruktorjev"); 
			this.globina = 9;
			//Razen prve poteze, alphabeta rabi manj kot 5 sekund
		}
		
		public Minimax (int globina) {
			super("alphabeta globina " + globina);
			this.globina = globina;
		}
		
		public Koordinati izberiPotezo (Igra igra) {
			// Na začetku alpha = ZGUBA in beta = ZMAGA
			System.out.println("izberi poteza...");
			return alphabetaPoteze(igra, this.globina, ZGUBA, ZMAGA, igra.naPotezi).poteza;
		}
		
		public static OcenjenaPoteza alphabetaPoteze(Igra igra, int globina, int alpha, int beta, Igralec jaz) {
			System.out.println("trentutno sem na globini " + globina);
			int ocena;
			// Če sem računalnik, maksimiramo oceno z začetno oceno ZGUBA
			// Če sem pa človek, minimiziramo oceno z začetno oceno ZMAGA
			if (igra.naPotezi == jaz) {ocena = ZGUBA;} else {ocena = ZMAGA;}
			ArrayList<Koordinati> moznePoteze = moznePoteze(igra);
			
			Koordinati kandidat = moznePoteze.get(0); // Možno je, da se ne spremini vrednost kanditata. Zato ne more biti null.
			
			for (Koordinati p: moznePoteze) {
				Igra kopijaIgre = new Igra(igra);
				System.out.println(kopijaIgre.naPotezi);
				kopijaIgre.odigraj(p);
				
				int ocenap;
				switch (kopijaIgre.getStanje()) {
				case ZMAGA_PRVI: ocenap = (jaz == Igralec.PRVI ? ZMAGA : ZGUBA); break;
				case ZMAGA_DRUGI: ocenap = (jaz == Igralec.DRUGI ? ZMAGA : ZGUBA); break;
				default:
					// Nekdo je na potezi
					if (globina == 1) ocenap = OceniPozicijo.oceniPozicijo(kopijaIgre, jaz);
					else ocenap = alphabetaPoteze (kopijaIgre, globina-1, alpha, beta, jaz).ocena;
				}
				
				if (igra.naPotezi == jaz) { // Maksimiramo oceno
					if (ocenap > ocena) { // mora biti > namesto >=
						ocena = ocenap;
						kandidat = p;
						alpha = Math.max(alpha,ocena);
						System.out.println(kandidat + " alpha: " + ocena + ";");
					}
				} else { // igra.naPotezi() != jaz, torej minimiziramo oceno
					if (ocenap < ocena) { // mora biti < namesto <=
						ocena = ocenap;
						kandidat = p;
						beta = Math.min(beta, ocena);
						System.out.println(kandidat + " beta: " + ocena + ";");
					}	
				}
				if (alpha >= beta) // Izstopimo iz "for loop", saj ostale poteze ne pomagajo
					System.out.println(kandidat + " koncno: " + ocena + ";");
					return new OcenjenaPoteza (kandidat, ocena);
			}
			System.out.println(kandidat + " koncno: " + ocena + ";");
			return new OcenjenaPoteza (kandidat, ocena);
		}

		private static ArrayList<Koordinati> moznePoteze(Igra igra){
			ArrayList<Koordinati> moznePoteze=new ArrayList<Koordinati>();
			for (int i=0;i<igra.N;i++) {
			for(int j=0;j<igra.N;j++) {
				if(igra.plosca[i][j]==Polje.PRAZNO) {
					moznePoteze.add(new Koordinati(i,j));
				}
			}
		}
			return moznePoteze;}
}

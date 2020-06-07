package inteligenca;

import java.util.ArrayList;


import logika.Igra;
import logika.Igralec;
import logika.Polje;
import splosno.KdoIgra;
import splosno.Koordinati;

// minimax oziroma alfabeta
public class Minimax extends KdoIgra {
		
		private static final int ZMAGA = Integer.MAX_VALUE; // vrednost zmage
		private static final int ZGUBA = -ZMAGA;  // vrednost zgube
	
		
		private int globina;
				
		public Minimax (int globina) {
			super("alphabeta globina " + globina);
			this.globina = globina;
		}
		
		public Koordinati izberiPotezo (Igra igra) {
			// Na začetku alpha = ZGUBA in beta = ZMAGA
			return alphabetaPoteze(igra, this.globina, ZGUBA, ZMAGA, igra.naPotezi).poteza;
		}
		
		public static OcenjenaPoteza alphabetaPoteze(Igra igra, int globina, int alpha, int beta, Igralec jaz) {
			int ocena;
			// Če sem računalnik, maksimiramo oceno z začetno oceno ZGUBA
			// Če sem pa človek, minimiziramo oceno z začetno oceno ZMAGA
			if (igra.naPotezi == jaz) {ocena = ZGUBA;} else {ocena = ZMAGA;}
			ArrayList<Koordinati> moznePoteze = moznePoteze(igra);
			
			Koordinati kandidat = moznePoteze.get(0);
			
			for (Koordinati p: moznePoteze) {
				Igra kopijaIgre = new Igra(igra);
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
					if (ocenap > ocena) { 
						ocena = ocenap;
						kandidat = p;
						alpha = Math.max(alpha,ocena);
					}
				} else { // igra.naPotezi() != jaz, torej minimiziramo oceno
					if (ocenap < ocena) { // mora biti < namesto <=
						ocena = ocenap;
						kandidat = p;
						beta = Math.min(beta, ocena);
					}	
				}
				if (alpha >= beta){ // Izstopimo iz "for loop", saj ostale poteze ne pomagajo
					return new OcenjenaPoteza (kandidat, ocena);
			}
			}
			return new OcenjenaPoteza (kandidat, ocena);
		}
		// seznam praznih polj
		private static ArrayList<Koordinati> moznePoteze(Igra igra){
			ArrayList<Koordinati> moznePoteze=new ArrayList<Koordinati>();
			for (int i=0;i<igra.N;i++) {
			for(int j=0;j<igra.N;j++) {
				if(igra.plosca[i][j]==Polje.PRAZNO) {
					moznePoteze.add(new Koordinati(i,j));
				}
			}
		}
			//sito zmanjšuje število pregledanih potez
			ArrayList<Koordinati> manjPotez=new ArrayList<Koordinati>();
			int sito;
			int velikost = moznePoteze.size();
			if (velikost > 100) sito = 15;
			else if (velikost > 50) sito = 6;
			else if (velikost > 15) sito = 3;
			else sito = 1;
			for (int i = 0; i<velikost;i++) {
				if (i % sito == 0) {
					manjPotez.add(moznePoteze.get(i));
				}
			}
			return manjPotez;}

}

package logika;

import java.util.*;

public class Igra {

	// Velikost igralne pološče je N x N.
	public static final int N = 6;

	// Igralno polje
	private Polje[][] plosca;

	// Igralec, ki je trenutno na potezi.
	// Vrednost je poljubna, če je igre konec (se pravi, lahko je napačna).
	public Igralec naPotezi;
	public boolean konecIgre;
	/**
	 * Nova igra, v začetni poziciji je prazna in na potezi je O.
	 */
	public Igra() {
		konecIgre = false;
		plosca = new Polje[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				plosca[i][j] = Polje.PRAZNO;
			}
		}
		naPotezi = Igralec.PRVI;
	}

	/**
	 * Odigraj potezo p.
	 * 
	 * @param p
	 * @return true, če je bila poteza uspešno odigrana
	 */
	public boolean odigraj(Lokacija p) {
		if (plosca[p.vrstica][p.stolpec] == Polje.PRAZNO) {
			plosca[p.vrstica][p.stolpec] = naPotezi.getPolje();
			naPotezi = naPotezi.nasprotnik();
			return true;
		} else {
			return false;
		}
	}
	public Igralec getZmagovalec() {
		// TODO
		return null;
	}
}

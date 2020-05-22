package inteligenca;

import java.util.HashMap;

import logika.Igra;
import logika.Igralec;
import logika.Polje;
import splosno.Koordinati;

public class OceniPozicijo {

private static int NAJDALJSA = 999;
	
	public static int oceniPozicijo(Igra igra, Igralec jaz) {
		int ocena = 0;
		if (jaz == Igralec.PRVI) {
			ocena = najkrajsaPotLevoDesno(igra) - najkrajsaPotGorDol(igra);
			return ocena;
		}else {
			ocena = najkrajsaPotGorDol(igra) - najkrajsaPotLevoDesno(igra);
			return ocena;
		}
	}
	
	
	public static int najkrajsaPotGorDol(Igra igra) {
		//HashMap<Koordinati, Integer> slovar = new HashMap<Koordinati, Integer>();
			int najkrajsa = NAJDALJSA;
			for (int col = 0; col < igra.N; col++) {
				boolean[][] pogledana = new boolean[igra.N][igra.N];
				for (int i = 0; i < igra.N;i++) {
					for (int j = 0; j<igra.N; j++) {
						if (i == 0) pogledana[i][j] = true;//
						else pogledana[i][j] = false;
					}
				}
				if (igra.plosca[0][col] != Polje.DRUGI) {//
				int nova = najkrajsaPotGorDol(igra, col, 0, pogledana);
				if (najkrajsa > nova) najkrajsa = nova;
			}}
			return najkrajsa;
	}
	
	public static int najkrajsaPotLevoDesno(Igra igra) {
		//HashMap<Koordinati, Integer> slovar = new HashMap<Koordinati, Integer>();
			int najkrajsa = NAJDALJSA;
			for (int row = 0; row < igra.N; row++) {
				boolean[][] pogledana = new boolean[igra.N][igra.N];
				for (int i = 0; i < igra.N;i++) {
					for (int j = 0; j<igra.N; j++) {
						if (j == 0) {
							pogledana[i][j] = true;
						}
						else pogledana[i][j] = false;
					}
				}
				if (igra.plosca[row][0] != Polje.PRVI) {
				int nova = najkrajsaPotLevoDesno(igra, 0, row, pogledana);
				if (najkrajsa > nova) najkrajsa = nova;
			}}
			return najkrajsa;
	}
	
	private static int najkrajsaPotGorDol(Igra igra, int col, int row, boolean[][] pogledana) {

		Koordinati noviKoordinati = new Koordinati(col, row);
//		if (slovar.containsKey(noviKoordinati)) {
//			return slovar.get(noviKoordinati);
//			}
		int najkrajsa = NAJDALJSA;
		if (row == igra.N - 1) {
			switch(igra.plosca[col][row]) { 
			case DRUGI:
//				slovar.put(noviKoordinati, NAJDALJSA);
				pogledana[row][col] = false;
				return NAJDALJSA;
			case PRVI:
//				slovar.put(noviKoordinati, 0);
				pogledana[row][col] = false;
				return 0;
			case PRAZNO:
//				slovar.put(noviKoordinati, 1);
				pogledana[row][col] = false;
				return 1;}
		}
			int[] dx={0,1,1,0,-1,-1};
			int[] dy={-1,-1,0,1,1,0};
			for (int i = 0; i < dx.length; i++) {
				int nx = col + dx[i];
				int ny = row + dy[i];
				try{
					if (!pogledana[ny][nx]) {
				
					pogledana[ny][nx] = true;
					int nova = najkrajsaPotGorDol(igra, nx, ny, pogledana);
					if (najkrajsa > nova) najkrajsa = nova;
					}}catch (Exception e) {};}
					switch(igra.plosca[row][col]) { 
					case DRUGI:
//						slovar.put(noviKoordinati, NAJDALJSA);
						pogledana[row][col] = false;
						return NAJDALJSA;
					case PRVI:
//						slovar.put(noviKoordinati, najkrajsa);
						pogledana[row][col] = false;
						return najkrajsa;
					case PRAZNO:
//						slovar.put(noviKoordinati, 1+ najkrajsa);
						pogledana[row][col] = false;
						return 1 + najkrajsa;}
					
			
		
	System.out.println("NapakaA");	
	return 999;}


	private static int najkrajsaPotLevoDesno(Igra igra, int col, int row, boolean[][] pogledana) {
		Koordinati noviKoordinati = new Koordinati(col, row);
//		if (slovar.containsKey(noviKoordinati)) {
//			return slovar.get(noviKoordinati);
//			}
		int najkrajsa = NAJDALJSA;
		if (col == igra.N - 1) {
			switch(igra.plosca[row][col]) { 
			case DRUGI:
//				slovar.put(noviKoordinati, 0);
				pogledana[row][col] = false;
				return 0;
			case PRVI:
//				slovar.put(noviKoordinati, NAJDALJSA);
				pogledana[row][col] = false;
				return NAJDALJSA;
			case PRAZNO:
//				slovar.put(noviKoordinati, 1);
				pogledana[row][col] = false;
				return 1;}
		}
			int[] dx={0,1,1,0,-1,-1};
			int[] dy={-1,-1,0,1,1,0};
			for (int i = 0; i < dx.length; i++) {
				int nx = col + dx[i];
				int ny = row + dy[i];
				try{
					if (!pogledana[ny][nx]) {
				
					pogledana[ny][nx] = true;
					int nova = najkrajsaPotLevoDesno(igra, nx, ny, pogledana);
					if (najkrajsa > nova) najkrajsa = nova;
					}}catch (Exception e) {};}
					switch(igra.plosca[row][col]) { 
					case DRUGI:
//						slovar.put(noviKoordinati, najkrajsa);
						pogledana[row][col] = false;
						return najkrajsa;
					case PRVI:
//						slovar.put(noviKoordinati, NAJDALJSA);
						pogledana[row][col] = false;
						return NAJDALJSA;
					case PRAZNO:
//						slovar.put(noviKoordinati, 1 + najkrajsa);
						pogledana[row][col] = false;
						return 1 + najkrajsa;}
					
				
				
			
				
	System.out.println("NapakaB");	
	return 999;}
}

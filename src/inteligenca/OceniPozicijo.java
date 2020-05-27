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
//		if (jaz == Igralec.PRVI) {
//			ocena = najkrajsaPotLevoDesno(igra) - najkrajsaPotGorDol(igra);
//			return ocena;
//		}else {
//			ocena = najkrajsaPotGorDol(igra) - najkrajsaPotLevoDesno(igra);
//			return ocena;
//		}
		int najkrajsaPrvi = INF;
		int najkrajsaDrugi = INF;
		int[][] grafPrvi = narediGraf(igra, true);
		int[][] grafDrugi = narediGraf(igra, false);
		for (int i = 0; i < igra.N;i++) {
			for (int j = 0; j<igra.N; j++){
				int novaPrvi = floydWarshall(grafPrvi, igra)[i*igra.N][j*igra.N+(igra.N-1)];
				int novaDrugi = floydWarshall(grafDrugi, igra)[i][igra.N * (igra.N-1) + j];
				if (novaPrvi < najkrajsaPrvi) najkrajsaPrvi = novaPrvi;
				if (novaDrugi < najkrajsaDrugi) najkrajsaDrugi = novaDrugi;
			}
		}
		if (jaz == Igralec.PRVI) {
			ocena = najkrajsaDrugi - najkrajsaPrvi;
			return ocena;}
			else {
				ocena = najkrajsaPrvi - najkrajsaDrugi;
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
	///////////////////////////////////////////////////////////////////
	final static int INF = 99999;
	  
    private static int[][] floydWarshall(int graph[][], Igra igra) 
    { 
        int dist[][] = new int[igra.N * igra.N][igra.N * igra.N]; 
        int i, j, k; 
  
        /* Initialize the solution matrix same as input graph matrix. 
           Or we can say the initial values of shortest distances 
           are based on shortest paths considering no intermediate 
           vertex. */
        for (i = 0; i < igra.N * igra.N; i++) 
            for (j = 0; j < igra.N * igra.N; j++) 
                dist[i][j] = graph[i][j]; 
  
        /* Add all vertices one by one to the set of intermediate 
           vertices. 
          ---> Before start of an iteration, we have shortest 
               distances between all pairs of vertices such that 
               the shortest distances consider only the vertices in 
               set {0, 1, 2, .. k-1} as intermediate vertices. 
          ----> After the end of an iteration, vertex no. k is added 
                to the set of intermediate vertices and the set 
                becomes {0, 1, 2, .. k} */
        for (k = 0; k < igra.N * igra.N; k++) 
        { 
            // Pick all vertices as source one by one 
            for (i = 0; i < igra.N * igra.N; i++) 
            { 
                // Pick all vertices as destination for the 
                // above picked source 
                for (j = 0; j < igra.N * igra.N; j++) 
                { 
                    // If vertex k is on the shortest path from 
                    // i to j, then update the value of dist[i][j] 
                    if (dist[i][k] + dist[k][j] < dist[i][j]) 
                        dist[i][j] = dist[i][k] + dist[k][j]; 
                } 
            } 
        } 
  
        // Print the shortest distance matrix 
        return dist; 
    } 
    private static int[][] narediGraf(Igra igra, boolean prvi){
    	int[][] graf = new int[igra.N*igra.N][igra.N * igra.N];
    	int[] dx={0,1,1,0,-1,-1};
		int[] dy={-1,-1,0,1,1,0};
		int i = -1;
    	if (prvi) {
    		for (int x1 = 0; x1< igra.N; x1++) {
    			for (int y1 = 0; y1 < igra.N; y1++) {
    				i++;
    				int j = -1;
    				for (int x2 = 0; x2<igra.N;x2++) {
    					for (int y2 = 0; y2<igra.N;y2++){
    						j++;
    						if (x1 == x2 && y1 == y2) graf[i][j] = 0;
    						else {
    							boolean jeBlizu = false;
    							for (int t = 0; t < dx.length; t++) {
    								int nx = x1 + dx[t];
    								int ny = y1 + dy[t];
    								if (nx == x2 && ny == y2) {
    									jeBlizu = true;
    									break;
    								}
    							}
    									if (jeBlizu) {
    										switch (igra.plosca[x2][y2]) {
    										case PRAZNO:
    											graf[i][j] = 1;
    											break;
    										case PRVI:
    											graf[i][j] = 0;
    											break;
    										case DRUGI:
    											graf[i][j] = INF;
    											break;
    										}
    									}
    									else graf[i][j] = INF;
    								}
    							}
    						
    						
    					
    				}
    			}
    		}
    	}
    	
    		else {
		for (int x1 = 0; x1< igra.N; x1++) {
			for (int y1 = 0; y1 < igra.N; y1++) {
				i++;
				int j = -1;
				for (int x2 = 0; x2<igra.N;x2++) {
					for (int y2 = 0; y2<igra.N;y2++){
						j++;
						if (x1 == x2 && y1 == y2) graf[i][j] = 0;
						else {
							boolean jeBlizu = false;
							for (int t = 0; t < dx.length; t++) {
								int nx = x1 + dx[t];
								int ny = y1 + dy[t];
								if (nx == x2 && ny == y2) {
									jeBlizu = true;
									break;
								}
							}
									if (jeBlizu) {
										switch (igra.plosca[x2][y2]) {
										case PRAZNO:
											graf[i][j] = 1;
											break;
										case PRVI:
											graf[i][j] = INF;
											break;
										case DRUGI:
											graf[i][j] = 0;
											break;
										}
									}
									else graf[i][j] = INF;
								}
							}
						
						
					
				}
			}
		}
	
    }
    	return graf;
    }
}

package inteligenca;

import logika.Igra;
import logika.Igralec;

public class OceniPozicijo {


	
	public static int oceniPozicijo(Igra igra, Igralec jaz) {

		int ocena = 0;


		
		int najkrajsaPrvi = INF;
		int najkrajsaDrugi = INF;
		int[][] grafPrvi = narediGraf(igra, true);
		int[][] grafDrugi = narediGraf(igra, false);
		for (int i = 0; i < igra.N;i++) {
			for (int j = 0; j<igra.N; j++){
				int novaPrvi = floydWarshall(grafPrvi, igra)[i*igra.N][j*igra.N+(igra.N-1)]; // zanimajo nas le poti od zgoraj do spodaj	
				int novaDrugi = floydWarshall(grafDrugi, igra)[i][igra.N * (igra.N-1) + j];  // oziroma od levo do desno
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
	
	

	final static int INF = 99999;
	 
	// Floyd-Warshall algoritem
	
    private static int[][] floydWarshall(int graph[][], Igra igra) { 
        int dist[][] = new int[igra.N * igra.N][igra.N * igra.N]; 
        int i, j, k; 
        for (i = 0; i < igra.N * igra.N; i++) 
            for (j = 0; j < igra.N * igra.N; j++) 
                dist[i][j] = graph[i][j]; 
        for (k = 0; k < igra.N * igra.N; k++) 
        { 
        	// začetno polje
            for (i = 0; i < igra.N * igra.N; i++) 
            { 

            	// končno polje
                for (j = 0; j < igra.N * igra.N; j++) 
                { 

                	// če je točka k na najkrajši poti med i in j, spremenimo dist[i][j]
                    if (dist[i][k] + dist[k][j] < dist[i][j]) 
                        dist[i][j] = dist[i][k] + dist[k][j]; 
                } 
            } 
        } 
  
        return dist; 
    } 
    
    // naredi graf, ki predstavlja osnovo za delovanje Floyd-Warshall algoritma
    private static int[][] narediGraf(Igra igra, boolean prvi){
    	int[][] graf = new int[igra.N*igra.N][igra.N * igra.N];
    	int[] dx={0,1,1,0,-1,-1};
		int[] dy={-1,-1,0,1,1,0};
		int i = -1;
    	if (prvi) {
    		for (int x1 = 0; x1< igra.N; x1++) {
    			for (int y1 = 0; y1 < igra.N; y1++) { // algoritem bi pospešili, če bi upoštevali, da je razdalja od A do B enaka razdalji od B do A
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
    	// ponovitev za drugega igralca
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

package inteligenca;

import strukture.hitra_igra;
import strukture.poteza;

class hitra_evaluacija{
	/**
	 * Rezultat je pozitiven, če zmaguje igralec 1, in negativen če zmaguje igralec 2
	 * 
	 * @param h
	 * @return
	 */
	public static int score(hitra_igra h){
		int score1=0;
		int score2=0;
		for(int x=0;x<11;x++){
			for(int y=0;y<11;y++){
				if(h.polje[x][y]!=0){
					int mi=h.polje[x][y];
					int score=0;
					int degSame=0;
					int degOpposite=0;
					for(poteza s:poteza.values()){
						int x2=x+s.x;
						int y2=y+s.y;
						if(0<=x2&&x2<11&&0<=y2&&y2<11){
							if(h.polje[x2][y2]==mi){
								degSame++;
							}else if(h.polje[x2][y2]==(mi==1?2:1)){
								degOpposite++;
							}
						}
					}
					score+=f(degSame,degOpposite);
					if(mi==1) {
						score1+=score;
					}else {
						score2+=score;
					}
				}
			}
		}
		return score1-score2;
	}

	private static int f(int degSame,int degOpposite){
		int sum=0;
		if(degSame>3) {
			return -1;
		}
		sum+=Math.min(4,2*degSame);
		sum-=degOpposite;
		return sum;
	}
}
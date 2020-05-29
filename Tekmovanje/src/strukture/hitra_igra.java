package strukture;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import logika.Igra;
import logika.Igralec;
import logika.Polje;

public class hitra_igra{
	public int[][] polje;
	public int naPotezi;
	public int prazno;
	public vector_set mozne_poteze;
	public hitra_igra(){
		polje=new int[11][11];
		naPotezi=1;
		prazno=11*11;
		mozne_poteze=new vector_set();
		for(int i=0;i<11;i++) {
			for(int j=0;j<11;j++) {
				mozne_poteze.add(new tocka(i,j));
			}
		}
	}
	public hitra_igra(Igra a) throws Exception{
		if(a.N!=11){
			throw new Exception();
		}
		polje=new int[11][11];
		prazno=0;
		mozne_poteze=new vector_set();
		for(int x=0;x<11;x++){
			for(int y=0;y<11;y++){
				polje[x][y]=a.plosca[x][y]==Polje.PRVI?1:(a.plosca[x][y]==Polje.DRUGI?2:0);
				if(polje[x][y]==0){
					prazno++;
					mozne_poteze.add(new tocka(x,y));
				}
			}
		}
		naPotezi=a.naPotezi==Igralec.PRVI?1:2;
	}
	public boolean odigraj(int x,int y){
		if(polje[x][y]!=0){
			return false;
		}
		mozne_poteze.remove(new tocka(x,y));
		polje[x][y]=naPotezi;
		naPotezi=(naPotezi==1?2:1);
		prazno--;
		return true;
	}
	public boolean odigraj(tocka t){
		return odigraj(t.x,t.y);
	}
	@Override public String toString(){
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<11;i++){
			for(int j=0;j<i;j++){
				sb.append(" ");
			}
			for(int j=0;j<11;j++){
				sb.append((polje[j][i]==0?".":polje[j][i]==1?"X":"O")+" ");
			}
			sb.append("\n");
		}
		return sb.toString();
	}
	public int done(){
		boolean[][] done=new boolean[11][11];
		Queue<tocka> bfs=new LinkedList<tocka>();
		for(int x=0;x<11;x++){
			if(polje[x][0]==1){
				bfs.add(new tocka(x,0));
				done[x][0]=true;
			}
		}
		while(!bfs.isEmpty()){
			tocka t=bfs.poll();
			if(t.y==10){
				return 1;
			}
			for(poteza d:poteza.values()){
				int x2=t.x+d.x;
				int y2=t.y+d.y;
				if(0<=x2&&x2<11&&0<=y2&&y2<11){
					if(polje[x2][y2]==1&&!done[x2][y2]){
						bfs.add(new tocka(x2,y2));
						done[x2][y2]=true;
					}
				}
			}
		}
		//Preverimo še za igralca 2. Na žalost moramo duplicirati kodo :(
		bfs=new LinkedList<tocka>();
		for(int y=0;y<11;y++){
			if(polje[0][y]==2){
				bfs.add(new tocka(0,y));
				done[0][y]=true;
			}
		}
		while(!bfs.isEmpty()){
			tocka t=bfs.poll();
			if(t.x==10){
				return 2;
			}
			int[] dx={0,1,1,0,-1,-1};
			int[] dy={-1,-1,0,1,1,0};
			for(int dir=0;dir<dx.length;dir++){
				int x2=t.x+dx[dir];
				int y2=t.y+dy[dir];
				if(0<=x2&&x2<11&&0<=y2&&y2<11){
					if(polje[x2][y2]==2&&!done[x2][y2]){
						bfs.add(new tocka(x2,y2));
						done[x2][y2]=true;
					}
				}
			}
		}
		return 0;
	}
	public void obrni(tocka t){
		if(polje[t.x][t.y]==0||polje[t.x][t.y]==(naPotezi==1?1:2)) {
			System.out.println("Napaka: obrnili smo potezo, ki ni bila odigrana, ali pa ni bila odigrana od igralca na potezi");
		}
		mozne_poteze.add(new tocka(t.x,t.y));
		polje[t.x][t.y]=0;
		prazno++;
		naPotezi=(naPotezi==1?2:1);
	}
	@Override public hitra_igra clone() {
		hitra_igra h2=new hitra_igra();
		for(int i=0;i<11;i++) {
			for(int j=0;j<11;j++) {
				h2.polje[i][j]=polje[i][j];
			}
		}
		h2.naPotezi=naPotezi;
		h2.prazno=prazno;
		return h2;
	}
}


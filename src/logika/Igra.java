package logika;

import java.util.*;

import koordinati.Koordinati;

public class Igra{

	// Velikost igralne polosce je N x N.
	public static int N=6;

	// Igralno polje
	private Polje[][] plosca;

	// Igralec, ki je trenutno na potezi.
	// Vrednost je poljubna, ce je igre konec (se pravi, lahko je napacna).
	public Igralec naPotezi;
	public boolean konecIgre;

	/**
	 * Nova igra. Na potezi je prvi igralec.
	 */
	public Igra(){
		inicializiraj_plosco();

	}
	public Igra(int velikost){
		N=velikost;
		inicializiraj_plosco();
	}
	private void inicializiraj_plosco(){
		konecIgre=false;
		plosca=new Polje[N][N];
		for(int i=0;i<N;i++){
			for(int j=0;j<N;j++){
				plosca[i][j]=Polje.PRAZNO;
			}
		}
		naPotezi=Igralec.PRVI;
	}
	/**
	 * Odigraj potezo p.
	 * 
	 * @param p
	 * @return true, ce je bila poteza uspeso odigrana
	 */
	public boolean odigraj(Koordinati p){
		if(plosca[p.getX()][p.getY()]==Polje.PRAZNO){
			plosca[p.getX()][p.getY()]=naPotezi.getPolje();
			naPotezi=naPotezi.nasprotnik();
			return true;
		}else{
			return false;
		}
	}

	public Igralec getZmagovalec(){
		//Preverimo èe je zgornji rob povezan s spodnjim
		//To bo za igralca_1 ki postavlja v stringu recimo X. 
		boolean[][]done=new boolean[N][N];
		Queue<Koordinati> bfs=new LinkedList<Koordinati>();
		for(int col=0;col<N;col++) {
			if(plosca[0][col]==Polje.PRVI) {
				bfs.add(new Koordinati(0,col));
				done[0][col]=true;
			}
		}
		while(!bfs.isEmpty()) {
			Koordinati k=bfs.poll();
			if(k.getX()==N-1) {
				return Igralec.PRVI;
			}
			int[]dx= {0,1,1,0,-1,-1};
			int[]dy= {1,0,-1,-1,-1,0};
			int x=k.getX();
			int y=k.getY();
			for(int dir=0;dir<dx.length;dir++) {
				int x2=x+dx[dir];
				int y2=y+dy[dir];
				try {
					if(plosca[x2][y2]==Polje.PRVI&&!done[x2][y2]) {
						bfs.add(new Koordinati(x2,y2));
						done[x2][y2]=true;
					}
				}catch(Exception e) {
					
				}
			}
		}
		//Preverimo še za igralca 2. Na žalost moramo duplicirati kodo :(
		bfs=new LinkedList<Koordinati>();
		for(int row=0;row<N;row++) {
			if(plosca[row][0]==Polje.DRUGI) {
				bfs.add(new Koordinati(row,0));
				done[row][0]=true;
			}
		}
		while(!bfs.isEmpty()) {
			Koordinati k=bfs.poll();
			if(k.getY()==N-1) {
				return Igralec.DRUGI;
			}
			int[]dx= {0,1,1,0,-1,-1};
			int[]dy= {1,0,-1,-1,-1,0};
			int x=k.getX();
			int y=k.getY();
			for(int dir=0;dir<dx.length;dir++) {
				int x2=x+dx[dir];
				int y2=y+dy[dir];
				try {
					if(plosca[x2][y2]==Polje.DRUGI&&!done[x2][y2]) {
						bfs.add(new Koordinati(x2,y2));
						done[x2][y2]=true;
					}
				}catch(Exception e) {
					
				}
			}
		}
		return null;
	}
	@Override public String toString(){
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<N;i++){
			for(int j=0;j<i;j++){
				sb.append(" ");
			}
			for(int j=0;j<N;j++){
				sb.append((plosca[i][j]==Polje.PRAZNO?".":plosca[i][j]==Polje.PRVI?"X":"O")+" ");
			}
			sb.append("\n");
		}
		return sb.toString();
	}
}

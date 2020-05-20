package logika;

import java.util.*;

import splosno.Koordinati;

public class Igra{

	// Velikost igralne polosce je N x N.
	public int N=11;

	// Igralno polje to sem spremenil na public, da sem lahko platno naredil
	public Polje[][] plosca;

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
	 * @return true, ce je bila poteza uspeso odigrana, drugace false
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

//	public final ArrayList<Koordinati> zmagovalnaPolja = new ArrayList<Koordinati>();
	public ArrayList<Koordinati> zmagovalnaPot() throws Exception{
		if(getStanje()==Stanje.V_TEKU){
			throw new Exception("Nihče ni zmagal, zmagovalna pot ne more obstajati");
		}else{
			Koordinati[][] prev=new Koordinati[N][N];
			Queue<Koordinati> bfs=new LinkedList<Koordinati>();
			for(int x=0;x<N;x++){
				if(plosca[x][0]==Polje.PRVI){
					bfs.add(new Koordinati(x,0));
				}
			}
			while(!bfs.isEmpty()){
				Koordinati k=bfs.poll();
				if(k.getY()==N-1){
//					urediPoljaCol();
					ArrayList<Koordinati> ans=new ArrayList<Koordinati>();
					while(true){
						ans.add(k);
						k=prev[k.getX()][k.getY()];
						if(k.getY()==0){
							ans.add(k);
							break;
						}
					}
					return ans;
				}
				int[] dx={0,1,1,0,-1,-1};
				int[] dy={-1,-1,0,1,1,0};
				int x=k.getX();
				int y=k.getY();
				for(int dir=0;dir<dx.length;dir++){
					int x2=x+dx[dir];
					int y2=y+dy[dir];
					try{
						if(plosca[x2][y2]==Polje.PRVI&&prev[x2][y2]==null){
							bfs.add(new Koordinati(x2,y2));

//							zmagovalnaPolja.add(new Koordinati(x2, x2));

							prev[x2][y2]=k;
						}
					}catch(Exception e){

					}
				}
			}
			prev=new Koordinati[N][N];
			bfs=new LinkedList<Koordinati>();
			for(int row=0;row<N;row++){
				if(plosca[0][row]==Polje.DRUGI){
					bfs.add(new Koordinati(0,row));
				}
			}
			while(!bfs.isEmpty()){
				Koordinati k=bfs.poll();
				if(k.getX()==N-1){
					ArrayList<Koordinati> ans=new ArrayList<Koordinati>();
					while(true){
						ans.add(k);
						k=prev[k.getX()][k.getY()];
						if(k.getX()==0){
							ans.add(k);
							break;
						}
					}
					return ans;
				}
				int[] dx={0,1,1,0,-1,-1};
				int[] dy={-1,-1,0,1,1,0};
				int x=k.getX();
				int y=k.getY();
				for(int dir=0;dir<dx.length;dir++){
					int x2=x+dx[dir];
					int y2=y+dy[dir];
					try{
						if(plosca[x2][y2]==Polje.DRUGI&&prev[x2][y2]==null){
							bfs.add(new Koordinati(x2,y2));
							prev[x2][y2]=k;
						}
					}catch(Exception e){

					}
				}
			}
		}
		System.out.println("Napaka pri analizi zmagovalca");
		return null;
	}
	public Igralec getZmagovalec(){

		//		zmagovalnaPolja.clear();

		//Preverimo ce je zgornji rob povezan s spodnjim
		//To bo za igralca_1 ki postavlja v stringu recimo X. 
		boolean[][] done=new boolean[N][N];
		Queue<Koordinati> bfs=new LinkedList<Koordinati>();
		for(int col=0;col<N;col++){
			if(plosca[col][0]==Polje.PRVI){
				bfs.add(new Koordinati(col,0));

//				zmagovalnaPolja.add(new Koordinati(0,col));

				done[col][0]=true;
			}
		}
		while(!bfs.isEmpty()){
			Koordinati k=bfs.poll();
			if(k.getY()==N-1){
//				urediPoljaCol();
				return Igralec.PRVI;
			}

			int[] dx={0,1,1,0,-1,-1};
			int[] dy={-1,-1,0,1,1,0};
			int x=k.getX();
			int y=k.getY();
			for(int dir=0;dir<dx.length;dir++){
				int x2=x+dx[dir];
				int y2=y+dy[dir];
				try{
					if(plosca[x2][y2]==Polje.PRVI&&!done[x2][y2]){
						bfs.add(new Koordinati(x2,y2));

//						zmagovalnaPolja.add(new Koordinati(x2, x2));

						done[x2][y2]=true;
					}
				}catch(Exception e){

				}
			}
		}
		//Preverimo še za igralca 2. Na žalost moramo duplicirati kodo :(
		bfs=new LinkedList<Koordinati>();
		for(int row=0;row<N;row++){
			if(plosca[0][row]==Polje.DRUGI){
				bfs.add(new Koordinati(0,row));
				done[0][row]=true;
			}
		}
		while(!bfs.isEmpty()){
			Koordinati k=bfs.poll();
			if(k.getX()==N-1){
				return Igralec.DRUGI;
			}

			int[] dx={0,1,1,0,-1,-1};
			int[] dy={-1,-1,0,1,1,0};
			int x=k.getX();
			int y=k.getY();
			for(int dir=0;dir<dx.length;dir++){
				int x2=x+dx[dir];
				int y2=y+dy[dir];
				try{
					if(plosca[x2][y2]==Polje.DRUGI&&!done[x2][y2]){
						bfs.add(new Koordinati(x2,y2));
						done[x2][y2]=true;
					}
				}catch(Exception e){

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
				sb.append((plosca[j][i]==Polje.PRAZNO?".":plosca[j][i]==Polje.PRVI?"X":"O")+" ");
			}
			sb.append("\n");
		}
		return sb.toString();
	}

	public Stanje getStanje(){
		Igralec zmagovalec=getZmagovalec();
//		System.out.println(zmagovalec);
		if(zmagovalec==Igralec.PRVI)
			return Stanje.ZMAGA_PRVI;
		else if(zmagovalec==Igralec.DRUGI)
			return Stanje.ZMAGA_DRUGI;
		else
			return Stanje.V_TEKU;
	}


}

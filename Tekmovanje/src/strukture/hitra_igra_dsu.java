package strukture;

import java.util.LinkedList;
import java.util.Queue;

import logika.Igra;
import logika.Igralec;
import logika.Polje;
import splosno.Koordinati;

public class hitra_igra_dsu{
	public int[][] polje;
	public int naPotezi;
	public int prazno;
	public final static poteza[]po=poteza.values();
	public DisjointUnionSets dsu;
	public vector_set mozne_poteze;
	public hitra_igra_dsu(){
		polje=new int[11][11];
		naPotezi=1;
		prazno=11*11;
		dsu=new DisjointUnionSets(121+4);
		mozne_poteze=new vector_set();
		for(int i=0;i<11;i++){
			for(int j=0;j<11;j++){
				mozne_poteze.add(new tocka(i,j));
			}
		}
	}
	public hitra_igra_dsu(hitra_igra a) throws Exception{
		polje=new int[11][11];
		prazno=11*11;
		mozne_poteze=new vector_set();
		for(int x=0;x<11;x++){
			for(int y=0;y<11;y++){
				mozne_poteze.add(new tocka(x,y));
			}
		}
		naPotezi=1;
		dsu=new DisjointUnionSets(121+4);
		while(true) {
			boolean worked=false;
			label:
			for(int i=0;i<11;i++) {
				for(int j=0;j<11;j++) {
					if(a.polje[i][j]==1&&polje[i][j]==0) {
						odigraj(new tocka(i,j));
						worked=true;
						break label;
					}
				}
			}
			if(!worked) {
				break;
			}
			boolean worked2=false;
			label2:
			for(int i=0;i<11;i++) {
				for(int j=0;j<11;j++) {
					if(a.polje[i][j]==2&&polje[i][j]==0) {
						odigraj(new tocka(i,j));
						worked2=true;
						break label2;
					}
				}
			}
			if(!worked2) {
				break;
			}
		}
	}
	public boolean odigraj(int x,int y){
		if(polje[x][y]!=0){
			return false;
		}
		mozne_poteze.remove(new tocka(x,y));
		polje[x][y]=naPotezi;
		if(y==0&&polje[x][y]==1){
			dsu.union(11*x+y,121+0);
		}
		if(y==10&&polje[x][y]==1){
			dsu.union(11*x+y,121+1);
		}
		if(x==0&&polje[x][y]==2){
			dsu.union(11*x+y,121+2);
		}
		if(x==10&&polje[x][y]==2){
			dsu.union(11*x+y,121+3);
		}
		for(poteza p:po){
			int x2=x+p.x;
			int y2=y+p.y;
			if(0<=x2&&x2<11&&0<=y2&&y2<11){
				if(polje[x][y]==polje[x2][y2]){
					dsu.union(11*x+y,11*x2+y2);
				}
			}
		}
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
		if(dsu.find(121+0)==dsu.find(121+1)){
			return 1;
		}
		if(dsu.find(121+2)==dsu.find(121+3)){
			return 2;
		}
		return 0;
	}
}
package inteligenca;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

import strukture.globalno;
import strukture.hitra_igra;
import strukture.hitra_igra_dsu;
import strukture.poteza;
import strukture.tocka;
public class mcts{
	public static final int runde=1000;
	public static tocka play(hitra_igra g,boolean console) throws Exception{
		long l=System.currentTimeMillis();
		node n=new node(g.naPotezi);
		int cikli=0;
		while(System.currentTimeMillis()-l<globalno.tlimit){
			for(int i=0;i<runde;i++){
				n.run(new hitra_igra_dsu(g));
			}
			cikli++;
		}
		if(console){
			System.out.println(cikli+"/ "+(System.currentTimeMillis()-l)+" ms");
			tocka t=n.best();
			double d=n.ch[11*t.x+t.y].wins/(0.0+n.ch[11*t.x+t.y].plays);
			d*=100;
			System.out.println(n.ch[11*t.x+t.y].wins+"/"+n.ch[11*t.x+t.y].plays+" "+(d+"").substring(0,Math.min(4,(d+"").length())));
		}
		return n.best();
	}

}
class node{
	public static final double c=0.3;
	int player;
	int opponent;
//	HashMap<tocka,node> ch;
	node[] ch;
	int wins;
	int plays;
	node(int pl){
		this.player=pl;
		this.opponent=pl==1?2:1;
		this.ch=new node[121];
//		this.ch=new HashMap<tocka,node>();
		wins=0;
		plays=0;
	}
	int run(hitra_igra_dsu g){
		if(g.done()!=0){
			plays+=1;
			if(g.done()==player){
				wins+=1;
			}
			return g.done();
		}
		plays++;
		boolean is_leaf=true;
		for(int i=0;i<121;i++){
			if(ch[i]!=null){
				is_leaf=false;
				break;
			}
		}
		if(is_leaf){
			tocka first=null;
			//simuliramo naključne poteze
			while(g.done()==0){
				ArrayList<tocka> opcije=new ArrayList<tocka>();
				for(int x=0;x<11;x++){
					for(int y=0;y<11;y++){
						if(g.polje[x][y]==0){
							opcije.add(new tocka(x,y));
						}
					}
				}
				Collections.shuffle(opcije);
				first=opcije.get(0);
				for(tocka t:opcije){
					g.odigraj(t);
					if(g.done()!=0){
						break;
					}
				}
			}
			int t=g.done();
			ch[11*first.x+first.y]=new node(opponent);
			if(t==player){
				wins++;
				ch[11*first.x+first.y].plays=1;
			}else{
				ch[11*first.x+first.y].plays=1;
				ch[11*first.x+first.y].wins=1;
			}
			return t;
		}else{
			double best=-1;
			tocka next=null;
			for(tocka t:g.mozne_poteze){
				if(ch[11*t.x+t.y]==null){
					next=t;
					ch[11*t.x+t.y]=new node(opponent);
					break;
				}
				node n=ch[11*t.x+t.y];
				//pazimo, ker če nasprotnik zmaga, mi izgubimo!.
				double score=((double)n.plays-n.wins)/n.plays+c*Math.sqrt(Math.log(plays)/n.plays);
				if(score>best){
					best=score;
					next=t;
				}
			}
			g.odigraj(next);
			int t=ch[11*next.x+next.y].run(g);
			if(t==player){
				wins++;
			}
			return t;
		}
	}
	tocka best(){
		double best=-1;
		tocka ans=null;
		for(int i=0;i<121;i++){
			if(ch[i]!=null){
				double curr=((double)ch[i].plays-ch[i].wins)/ch[i].plays;
				if(curr>best){
					best=curr;
					ans=new tocka(i/11,i%11);
				}
			}
		}
		return ans;
	}
	@Override public String toString(){
		return "igralec "+player+" "+wins+"/"+plays;
	}
}
package inteligenca;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import strukture.globalno;
import strukture.hitra_igra;
import strukture.hitra_igra_dsu;
import strukture.poteza;
import strukture.tocka;
import strukture.vector_set;

public class mcts_pattern_amaf{
	public static final int runde=1000;
	public static tocka play(hitra_igra g,boolean console) throws Exception{
		long l=System.currentTimeMillis();
		node_pattern_amaf n=new node_pattern_amaf(g.naPotezi,0);
		int cikli=0;
		while(System.currentTimeMillis()-l<globalno.tlimit){
			for(int i=0;i<runde;i++){
				n.run(new hitra_igra_dsu(g),new ArrayList<pkg>(100));
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
//		System.out.println("lol");
		return n.best();
	}
}
class pkg{
	tocka t;
	int igralec;
	public pkg(tocka t,int igralec){
		super();
		this.t=t;
		this.igralec=igralec;
	}
	@Override public String toString(){
		return igralec+" "+t;
	}

}
class node_pattern_amaf{
	public static final double c=0.3;
	public static final double alpha=0;
	int player;
	int opponent;
	node_pattern_amaf[] ch;
	int wins;
	int plays;
	int depth;
	node_pattern_amaf(int pl,int depth){
		this.player=pl;
		this.opponent=pl==1?2:1;
		this.ch=new node_pattern_amaf[121];
		wins=0;
		plays=0;
		this.depth=depth;
	}
	int simulate_random(hitra_igra_dsu g,tocka first,ArrayList<pkg> history){
		//simuliramo naključne poteze
		tocka prev=null;
		while(g.done()==0){
			vector_set poteze=g.mozne_poteze;
			tocka t=null;
			tocka option=checkBridge(prev,g);
			if(option!=null){
				t=option;
			}
			while(t==null){
				t=poteze.get((int)(Math.random()*poteze.size));
			}
			if(first.x==-1&&first.y==-1){
				first.x=t.x;
				first.y=t.y;
			}
			history.add(new pkg(t,g.naPotezi));
			g.odigraj(t);
			prev=t;
		}
		return g.done();
	}
	private tocka checkBridge(tocka prev,hitra_igra_dsu g){
		if(prev==null){
			return null;
		}
		int naPotezi=g.naPotezi;
		//preverimo okolico "prev" za mostove, ki bi jih drugače lahko postavili
		poteza[] q=poteza.values();
		for(int i=0;i<q.length;i++){
			poteza p=q[i];
			poteza p2=q[(i+2)%q.length];
			int x1=prev.x+p.x;
			int y1=prev.y+p.y;
			int x2=prev.x+p2.x;
			int y2=prev.y+p2.y;
			if(0<=x1&&x1<11&&0<=y1&&y1<11){
				if(0<=x2&&x2<11&&0<=y2&&y2<11){
					if(g.polje[x1][y1]==naPotezi&&g.polje[x2][y2]==naPotezi){
						tocka t=new tocka(prev.x+q[(i+1)%q.length].x,prev.y+q[(i+1)%q.length].y);
						if(g.polje[t.x][t.y]==0){
							return t;
						}
					}
				}
			}
		}
		return null;
	}
	int run(hitra_igra_dsu g,ArrayList<pkg> history){
		if(g.done()!=0){
			return g.done();
		}
		boolean is_leaf=check_for_leaf();
		if(is_leaf){
			tocka first=new tocka(-1,-1);
			int t=simulate_random(g,first,history);
			ch[11*first.x+first.y]=new node_pattern_amaf(opponent,depth+1);
			return t;
		}else{
			double best=-globalno.inf;
			tocka next=null;
			for(tocka t:g.mozne_poteze){
				if(ch[11*t.x+t.y]==null){
					next=t;
					ch[11*t.x+t.y]=new node_pattern_amaf(opponent,depth+1);
					break;
				}
				node_pattern_amaf n=ch[11*t.x+t.y];
				//izberemo nasprotnikovo potezo, ki mu da najmanj zmag
				double scoreUCB=((double)n.plays-n.wins)/n.plays+c*Math.sqrt(Math.log(plays+1)/n.plays);
				double scoreAMAF=((double)n.plays-n.wins)/n.plays;
				double score=alpha*scoreUCB+(1-alpha)*scoreAMAF;
				if(score>best){
					best=score;
					next=t;
				}
			}
			history.add(new pkg(next,g.naPotezi));
			g.odigraj(next);
			int won=ch[11*next.x+next.y].run(g,history);
			//posodobimo igre/zmage otrok. 
			for(int i=depth;i<history.size();i+=2){
				pkg a=history.get(i);
				if(a.igralec==player){
					tocka t=a.t;
					if(ch[11*t.x+t.y]==null){
						ch[11*t.x+t.y]=new node_pattern_amaf(opponent,depth+1);
					}
					plays++;
					ch[11*t.x+t.y].plays++;
					if(won!=player){
						ch[11*t.x+t.y].wins++;
					}else{
						wins++;
					}
				}
			}
			return won;
		}
	}
	private boolean check_for_leaf(){
		for(int i=0;i<121;i++){
			if(ch[i]!=null){
				return false;
			}
		}
		return true;
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
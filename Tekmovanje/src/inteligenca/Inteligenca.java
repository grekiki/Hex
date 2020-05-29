package inteligenca;

import java.util.Scanner;

import logika.Igra;
import splosno.Koordinati;
import strukture.globalno;
import strukture.hitra_igra;
import strukture.tocka;
public class Inteligenca extends splosno.KdoIgra{
	public int algoritem=102;//1- default, 2-nakljčna poteza, 3-MCTS,4- console, 101-mcts pattern,102-mcts pattern amaf
	public boolean console=true;
	public Inteligenca(){
		super("1234");
	}
	public Koordinati izberiPotezo(Igra igra) throws Exception{
		hitra_igra a=new hitra_igra(igra);
		return izberiPotezo(a).toKoordinati();
	}
	public tocka izberiPotezo(hitra_igra g) throws Exception{
		if(algoritem==1){
			return max.play(g);
		}else if(algoritem==2){
			while(true) {
				tocka t=g.mozne_poteze.get((int)(g.mozne_poteze.size*Math.random()));
				if(t!=null) {
					return t;
				}
			}
		}else if(algoritem==3) {
			return mcts.play(g,console);
		}else if(algoritem==4) {
			Scanner sc=new Scanner(System.in);
			int x=sc.nextInt();
			int y=sc.nextInt();
			return new tocka(x,y);
		}else if(algoritem==101) {
			return mcts_pattern.play(g,console);
		}else if(algoritem==102) {
			return mcts_pattern_amaf.play(g,console);
		}
		else{
			throw new Exception("Algoritem "+algoritem+" ni implementiran");
		}
	}
}

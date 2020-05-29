import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

import javax.swing.WindowConstants;

import GUI.Okno;
import GUI.Platno;
import inteligenca.Inteligenca;
import logika.Igra;
import strukture.globalno;
import strukture.hitra_igra;
import strukture.hitra_igra_dsu;
import strukture.tocka;
import vodja.Vodja;

public class Ogrodje{
	public static int run(Inteligenca a,Inteligenca b,boolean console) throws Exception{
		hitra_igra h=new hitra_igra();
		while(h.done()==0){
			if(console)
				System.out.println(h);
			if(h.naPotezi==1){
				tocka t=a.izberiPotezo(h);
				if(console)
					System.out.println(t);
				h.odigraj(t);
			}else{
				tocka t=b.izberiPotezo(h);
				if(console)
					System.out.println(t);
				h.odigraj(t);
			}
		}
		if(console){
			System.out.println(h);
			System.out.println(h.done());
		}
		return h.done();
	}
	public static void main(String[] args) throws Exception{
		boolean benchmark=false;
		if(benchmark){
			benchmark();
			return;
		}
		Inteligenca a=new Inteligenca();
		a.algoritem=101;
		a.console=false;
		Inteligenca b=new Inteligenca();
		b.algoritem=102;
		b.console=false;
		boolean console=true;
		if(console){
			globalno.tlimit=1*100;
			int wa=0;
			int wb=0;
			for(int i=0;i<100;i++){
				System.out.println(i+" "+wa+" "+wb);
				int t=run(a,b,false);
				if(t==1) {
					wa++;
				}else {
					wb++;
				}
			}
			System.out.println(wa+" "+wb);
		}else{
			globalno.tlimit=50*100;
			Igra d=new Igra(11);
			Platno platno=new Platno(900,600);
			platno.nastaviIgro(d);
			Okno okno1=new Okno("HEX",platno);
			okno1.pack();
			okno1.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			okno1.setVisible(true);
			Vodja.okno=okno1;
			Vodja.igra=d;
			Vodja.igramo();
		}
	}

	private static void benchmark(){
		long l=System.currentTimeMillis();

		System.out.println(System.currentTimeMillis()-l+" ms");
	}

}

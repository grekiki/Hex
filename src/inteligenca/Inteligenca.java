package inteligenca;

import java.util.ArrayList;

import logika.Igra;
import logika.Polje;
import splosno.Koordinati;

public class Inteligenca extends splosno.KdoIgra{
	String ime;
	public Inteligenca(String ime){
		super(ime);
		this.ime=ime;
		System.out.println("Narejena je inteligenca "+ime);
	}
	public Koordinati izberiPotezo(Igra igra){
		ArrayList<Koordinati> možnePoteze=new ArrayList<Koordinati>();
		for(int i=0;i<igra.N;i++) {
			for(int j=0;j<igra.N;j++) {
				if(igra.plosca[i][j]==Polje.PRAZNO) {
					možnePoteze.add(new Koordinati(i,j));
				}
			}
		}
		return možnePoteze.get((int)(možnePoteze.size()*Math.random()));
	}

}

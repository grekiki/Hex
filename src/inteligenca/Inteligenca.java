package inteligenca;

import logika.Igra;
import splosno.Koordinati;

public class Inteligenca extends splosno.KdoIgra{
	String ime;
	public Inteligenca(String ime){
		super(ime);
		this.ime=ime;
		System.out.println("Narejena je inteligenca "+ime);

	}
	public Koordinati izberiPotezo(Igra igra){
		return new Koordinati(0,0);
	}

}

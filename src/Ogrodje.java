import splosno.Koordinati;
import vodja.Vodja;
import logika.Igra;

import javax.swing.WindowConstants;

import GUI.*;

public class Ogrodje{

	public static void main(String[] args) throws Exception{
		Igra a=new Igra(3);
		a.odigraj(new Koordinati(0,0));
		a.odigraj(new Koordinati(0,1));
		a.odigraj(new Koordinati(1,0));
		a.odigraj(new Koordinati(1,1));
		a.odigraj(new Koordinati(2,0));
		a.odigraj(new Koordinati(0,2));
		a.odigraj(new Koordinati(2,1));
		a.odigraj(new Koordinati(2,2));
		a.odigraj(new Koordinati(1,2));
		System.out.println(a);
		if(a.getZmagovalec()!=null){
			System.out.println(a.getZmagovalec());
		}
		System.out.println(a.getStanje());
		Igra b=new Igra(6);
		for(int i=0;i<6;i++){
			b.odigraj(new Koordinati(i,0));
			b.odigraj(new Koordinati(i,1));
		}
		System.out.println(b);
		System.out.println(b.getStanje());
//		System.out.println(b.zmagovalnaPolja);

		Igra c=new Igra(4);
		c.odigraj(new Koordinati(0,0));
		c.odigraj(new Koordinati(1,0));
		c.odigraj(new Koordinati(0,1));
		c.odigraj(new Koordinati(2,0));
		c.odigraj(new Koordinati(1,1));
		c.odigraj(new Koordinati(3,0));
		c.odigraj(new Koordinati(2,1));
		c.odigraj(new Koordinati(3,3));
		c.odigraj(new Koordinati(2,2));
		c.odigraj(new Koordinati(0,3));
		c.odigraj(new Koordinati(2,3));
		System.out.println(c);
		System.out.println(c.getStanje());
		System.out.println(c.zmagovalnaPot());
//		System.out.println(c.zmagovalnaPolja);

		// zelimo si 
		//[Koordinati [x=0, y=2], Koordinati [x=1, y=1], Koordinati [x=2, y=1], Koordinati [x=2, y=2], Koordinati [x=3, y=2]]

		/////////////////////////////////////////////////////
//
//		Igra d=new Igra(3);
//
//		Platno platno=new Platno(900,600);
//		platno.nastaviIgro(d);
//		Okno okno1=new Okno("HEX",platno);
//		okno1.pack();
//		okno1.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//		okno1.setVisible(true);
//		Vodja.okno=okno1;
//		Vodja.igra=d;
//		Vodja.igramo();

	}
}

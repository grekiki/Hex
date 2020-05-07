import koordinati.Koordinati;
import logika.Igra;
import GUI.*;

public class Ogrodje{

	public static void main(String[] args){
//		Igra a=new Igra(6);
//		for(int i=0;i<6;i++) {
//			a.odigraj(new Koordinati(0,i));
//			a.odigraj(new Koordinati(1,i));
//		}
//		System.out.println(a);
//		if(a.getZmagovalec()!=null) {
//			System.out.println(a.getZmagovalec());
//		}
		Igra b=new Igra(6);
		for(int i=0;i<6;i++) {
			b.odigraj(new Koordinati(i,0));
			b.odigraj(new Koordinati(i,1));
		}
		System.out.println(b);
		if(b.getZmagovalec()!=null) {
			System.out.println(b.getZmagovalec());
		}
		System.out.println(b.getStanje());
//		System.out.println(b.zmagovalnaPolja);
		
	
		
		Igra c=new Igra(4);
		c.odigraj(new Koordinati(0,0));
		c.odigraj(new Koordinati(1,0));
		c.odigraj(new Koordinati(0,2));
		c.odigraj(new Koordinati(2,0));
		c.odigraj(new Koordinati(1,1));
		c.odigraj(new Koordinati(3,0));
		c.odigraj(new Koordinati(2,1));
		c.odigraj(new Koordinati(3,3));
		c.odigraj(new Koordinati(2,2));
		c.odigraj(new Koordinati(0,3));
		c.odigraj(new Koordinati(3,2));
		
		System.out.println(c.getStanje());
//		System.out.println(c.zmagovalnaPolja);
		
	// želimo si 
	//[Koordinati [x=0, y=2], Koordinati [x=1, y=1], Koordinati [x=2, y=1], Koordinati [x=2, y=2], Koordinati [x=3, y=2]]
		
		
	/////////////////////////////////////////////////////
		
		Igra d = new Igra(10);
		
		Platno platno = new Platno(900, 600);
		platno.nastaviIgro(d);
		Okno okno1 = new Okno("Poskus 1", platno);
		okno1.pack();
		okno1.setVisible(true);
	}
}

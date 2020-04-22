import koordinati.Koordinati;
import logika.Igra;

public class Ogrodje{

	public static void main(String[] args){
		Igra a=new Igra(6);
		for(int i=0;i<6;i++) {
			a.odigraj(new Koordinati(0,i));
			a.odigraj(new Koordinati(1,i));
		}
		System.out.println(a);
		if(a.getZmagovalec()!=null) {
			System.out.println(a.getZmagovalec());
		}
		Igra b=new Igra(6);
		for(int i=0;i<6;i++) {
			b.odigraj(new Koordinati(i,0));
			b.odigraj(new Koordinati(i,1));
		}
		System.out.println(b);
		if(b.getZmagovalec()!=null) {
			System.out.println(b.getZmagovalec());
		}
	}
}

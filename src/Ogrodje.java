import splosno.Koordinati;
import vodja.Vodja;
import logika.Igra;

import javax.swing.WindowConstants;

import GUI.*;

public class Ogrodje{

	public static void main(String[] args) throws Exception{
		Igra a=new Igra(3);
		a.odigraj(new Koordinati(0,0));
		a.odigraj(new Koordinati(1,0));
		a.odigraj(new Koordinati(0,1));
		a.odigraj(new Koordinati(1,1));
		a.odigraj(new Koordinati(0,2));
		a.odigraj(new Koordinati(1,2));
		System.out.println(a);
		if(a.getZmagovalec()!=null){
			System.out.println(a.getZmagovalec());
		}
		Igra b=new Igra(5);
		int[][]q= {{2,2,2,2,1},{2,1,2,2,2},{1,1,1,2,1},{1,1,1,1,1},{2,1,2,2,1}};
		while(true) {
			boolean worked=false;
			label:
			for(int i=0;i<q.length;i++) {
				for(int j=0;j<q[i].length;j++) {
					if(q[i][j]==1) {
						q[i][j]=0;
						b.odigraj(new Koordinati(j,i));
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
			for(int i=0;i<q.length;i++) {
				for(int j=0;j<q[i].length;j++) {
					if(q[i][j]==2) {
						q[i][j]=0;
						b.odigraj(new Koordinati(j,i));
						worked2=true;
						break label2;
					}
				}
			}
			if(!worked2) {
				break;
			}
		}
		System.out.println(b);
		System.out.println(b.getStanje());
		// zelimo si 
		//[Koordinati [x=0, y=2], Koordinati [x=1, y=1], Koordinati [x=2, y=1], Koordinati [x=2, y=2], Koordinati [x=3, y=2]]

		/////////////////////////////////////////////////////

		Igra d=new Igra(3);

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
// 

import vodja.Vodja;
import logika.Igra;
import javax.swing.WindowConstants;
import GUI.*;

public class Ogrodje{

	public static void main(String[] args) throws Exception{
		
		
		///////////////////// Glavo okno ////////////////////////////
		Igra hex=new Igra(11);

		Platno platno=new Platno(900,600);
		platno.nastaviIgro(hex);
		Okno glavnoOkno=new Okno("HEX",platno);
		glavnoOkno.pack();
		glavnoOkno.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		glavnoOkno.setVisible(true);
		Vodja.okno=glavnoOkno;}
		//////////////////////////////////////////////////////////////

}
 

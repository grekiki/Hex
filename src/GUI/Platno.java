package GUI;

import javax.swing.*;
import java.awt.*;
import logika.Igra;
import logika.Polje;

public class Platno extends JPanel {
	
	protected int sirina;
	protected int visina;
	protected Igra igra;

	
	protected Color barvaIgralca1;
	protected Color barvaIgralca2;
	protected Color barvaRoba;
	protected Color barvaPrazno;
	protected int debelinaRoba;
	
	public Platno(int sirina, int visina) {
		this.sirina = sirina;
		this.visina = visina;
		this.igra = null;
		this.barvaIgralca1 = Color.red;
		this.barvaIgralca2 = Color.blue;
		this.barvaRoba = Color.black;
		this.barvaPrazno = Color.white;
		this.setPreferredSize(getPrefferedSize());
		this.setBackground(Color.white);
	}
	
	public void nastaviIgro (Igra igra) {
		this.igra = igra;
		repaint();
	}

	
	public Dimension getPrefferedSize() {
		return new Dimension(this.sirina, this.visina);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		//sestkotnik(200, 200, 200, 10, g2, this.barvaRoba, this.barvaIgralca1);
		int enota = 90; //odvisna naj bo od velikosti platna
		int padding = 15;
		int debelinaRoba = 8;
		for (int i = 0; i < this.igra.plosca.length; i++) {
			for (int j = 0; j < this.igra.plosca[0].length; j++) {
				int y = (int) Math.round(padding + j * enota + (i+1) * enota/2); // ni še vredu, ampak približno
				int x = (int) Math.round(padding + i * enota + (i+1) * enota/2);
					if (igra.plosca[i][j] == Polje.PRVI) {
					sestkotnik(y, x, (int) Math.round(enota/2), debelinaRoba, g2, this.barvaRoba, this.barvaIgralca1);
				} else if (igra.plosca[i][j] == Polje.DRUGI) {
					sestkotnik(y, x, (int) Math.round(enota/2), debelinaRoba, g2, this.barvaRoba, this.barvaIgralca2);
				}else sestkotnik(y, x, (int) Math.round(enota/2), debelinaRoba, g2, this.barvaRoba, this.barvaPrazno);
			}
		}
		
	}
	
	
// nariše šestkotnik, če mu damo središče in polmer:	
	private void sestkotnik(int x, int y, int r, int debelinaRoba, Graphics2D g, Color rob, Color notranjost) {
		int[] xKoordinate = new int[6];
		int[] yKoordinate = new int[6];
		int[] xKoordinate2 = new int[6];
		int[] yKoordinate2 = new int[6];
		for (int i = 0; i < 6; i++) {
			   yKoordinate[i] = (int) Math.round(x + r * Math.cos(i * 2.0 * Math.PI / 6));
			   xKoordinate[i] = (int) Math.round(y + r * Math.sin(i * 2.0 * Math.PI / 6));
			   yKoordinate2[i] = (int) Math.round(x + (r - debelinaRoba) * Math.cos(i * 2.0 * Math.PI / 6));
			   xKoordinate2[i] = (int) Math.round(y + (r - debelinaRoba) * Math.sin(i * 2.0 * Math.PI / 6));
		}
		g.setColor(rob);
		//g.drawPolygon(xKoordinate, yKoordinate, 6);
		g.fillPolygon(xKoordinate, yKoordinate, 6);
		g.setColor(notranjost);
		g.fillPolygon(xKoordinate2, yKoordinate2, 6);
	}
	


}

package GUI;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class Okno extends JFrame {

	protected Platno platno;
	
	public Okno(String ime, Platno platno) {
		this.setTitle(ime);
		this.platno = platno;
		this.add(platno, BorderLayout.CENTER);
		this.setVisible(true);
	}
}

package GUI;

import javax.swing.*;

import java.awt.*;
import logika.Igra;
import logika.Polje;
import logika.Stanje;
import splosno.Koordinati;
import vodja.Vodja;

import java.awt.event.*;

@SuppressWarnings("serial") public class Platno extends JPanel implements MouseListener{
	protected int sirina;
	protected int visina;
	protected Igra igra;
	protected boolean konecIgre;
	protected Color barvaIgralca1;
	protected Color barvaIgralca2;
	protected Color barvaRoba;
	protected Color barvaPrazno;
	protected int debelinaRoba;
	protected int zunanjiPolmer;
	protected int notranjiPolmer;
	protected int paddingx;
	protected int paddingy;

	public Platno(int sirina,int visina){
		this.sirina=sirina;
		this.visina=visina;
		this.igra=null;
		this.konecIgre=false;
		this.barvaIgralca1=Color.red;
		this.barvaIgralca2=Color.blue;
		this.barvaRoba=Color.black;
		this.barvaPrazno=Color.white;
		this.setPreferredSize(getPrefferedSize());
		this.setBackground(Color.white);
		this.addMouseListener(this);
	}

	public void nastaviIgro(Igra igra){
		this.igra=igra;
		repaint();
	}

	public Dimension getPrefferedSize(){
		return new Dimension(this.sirina,this.visina);
	}

	@Override protected void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2=(Graphics2D)g;
		/////

		int priporocenaSirina=Math.min(getWidth(),(int)getHeight()*3/2);

		System.out.println("sirina: "+getWidth()+", visina: "+getHeight());
		int sx=getWidth()/2;
		int sy=getHeight()/2;
		//x0 = paddingx + notranji
		//y0 = paddingy + zunanji
		// samo zunanji, debelinaRoba in padding se spreminjata
		//int zunanji = 40; //odvisen naj bo od velikosti platna
		zunanjiPolmer=(priporocenaSirina/((this.igra.N*3/2)+4))/2;
		notranjiPolmer=(int)Math.round(zunanjiPolmer*Math.sqrt(3)/2);  // polmer šestkotniku očrtane in včrtane krožnice
		debelinaRoba=6;
		paddingx=sx-(this.igra.N*3/2)*notranjiPolmer;
		paddingy=sy-(this.igra.N/2)*(zunanjiPolmer+notranjiPolmer);

//		System.out.println("zunanji: "+zunanjiPolmer);
//		System.out.println("noranji: "+notranjiPolmer);
//		System.out.println("paddingx: "+paddingx);
//		System.out.println("paddingy: "+paddingx);
//		System.out.println("debelinaRoba: "+debelinaRoba);

		notranjiPolmer=(int)Math.round(zunanjiPolmer*Math.sqrt(3)/2);  // polmer šestkotniku očrtane in včrtane krožnice
		Color barva;

		/////////////// rob plošče

		int a=0;
		int b=this.igra.plosca.length-1;
		g2.setColor(Color.black);

		double[] v=new double[2];
		double norma=Math.sqrt(0.5*0.5+(Math.sqrt(3)/2)*(Math.sqrt(3)/2));
		v[0]=0.5/norma;
		v[1]=(Math.sqrt(3)/2)/norma;

		int x1=(int)Math.round(paddingx+(2*a+1)*notranjiPolmer+(a*notranjiPolmer));
		int y1=(int)Math.round(paddingy+zunanjiPolmer+a*1.5*zunanjiPolmer);
		//g2.fillOval(x1, y1, 10, 10);
		int x2=(int)Math.round(paddingx+(2*b+1)*notranjiPolmer+(a*notranjiPolmer));
		int y2=(int)Math.round(paddingy+zunanjiPolmer+a*1.5*zunanjiPolmer);
		//g2.fillOval(x2, y2, 10, 10);
		int x3=(int)Math.round(paddingx+(2*b+1)*notranjiPolmer+(b*notranjiPolmer));
		int y3=(int)Math.round(paddingy+zunanjiPolmer+b*1.5*zunanjiPolmer);
		//g2.fillOval(x3, y3, 10, 10);
		int x4=(int)Math.round(paddingx+(2*a+1)*notranjiPolmer+(b*notranjiPolmer));
		int y4=(int)Math.round(paddingy+zunanjiPolmer+b*1.5*zunanjiPolmer);
		//g2.fillOval(x4, y4, 10, 10);

		int x12=(int)Math.round(paddingx+(2*a+1)*notranjiPolmer+(a*notranjiPolmer)-2*notranjiPolmer*v[0]);
		int y12=(int)Math.round(paddingy+zunanjiPolmer+a*1.5*zunanjiPolmer-2*notranjiPolmer*v[1]);
		//g2.fillOval(x12, y12, 10, 10);
		int x13=(int)Math.round(paddingx+(2*a+1)*notranjiPolmer+(a*notranjiPolmer)-2*notranjiPolmer);
		int y13=(int)Math.round(paddingy+zunanjiPolmer+a*1.5*zunanjiPolmer);
		//g2.fillOval(x13, y13, 10, 10);
		int x14=(int)Math.round(paddingx+(2*a+1)*notranjiPolmer+(a*notranjiPolmer)-2*notranjiPolmer+notranjiPolmer*v[0]);
		int y14=(int)Math.round(paddingy+zunanjiPolmer+a*1.5*zunanjiPolmer-notranjiPolmer*v[1]);
		//g2.fillOval(x14, y14, 10, 10);

		int x21=(int)Math.round(paddingx+(2*b+1)*notranjiPolmer+(a*notranjiPolmer)+2*notranjiPolmer);
		int y21=(int)Math.round(paddingy+zunanjiPolmer+a*1.5*zunanjiPolmer);
		//g2.fillOval(x21, y21, 10, 10);
		int x23=(int)Math.round(paddingx+(2*b+1)*notranjiPolmer+(a*notranjiPolmer)+2*notranjiPolmer*v[0]);
		int y23=(int)Math.round(paddingy+zunanjiPolmer+a*1.5*zunanjiPolmer-2*notranjiPolmer*v[1]);
		//g2.fillOval(x23, y23, 10, 10);

		int x31=(int)Math.round(paddingx+(2*b+1)*notranjiPolmer+(b*notranjiPolmer)+2*notranjiPolmer*v[0]);
		int y31=(int)Math.round(paddingy+zunanjiPolmer+b*1.5*zunanjiPolmer+2*notranjiPolmer*v[1]);
		//g2.fillOval(x31, y31, 10, 10);		
		int x32=(int)Math.round(paddingx+(2*b+1)*notranjiPolmer+(b*notranjiPolmer)+2*notranjiPolmer);
		int y32=(int)Math.round(paddingy+zunanjiPolmer+b*1.5*zunanjiPolmer);
		//g2.fillOval(x32, y32, 10, 10);
		int x34=(int)Math.round(paddingx+(2*b+1)*notranjiPolmer+(b*notranjiPolmer)+2*notranjiPolmer*v[0]+notranjiPolmer*v[0]);
		int y34=(int)Math.round(paddingy+zunanjiPolmer+b*1.5*zunanjiPolmer+2*notranjiPolmer*v[1]-notranjiPolmer*v[1]);
		//g2.fillOval(x34, y34, 10, 10);

		int x41=(int)Math.round(paddingx+(2*a+1)*notranjiPolmer+(b*notranjiPolmer)-2*notranjiPolmer);
		int y41=(int)Math.round(paddingy+zunanjiPolmer+b*1.5*zunanjiPolmer);
		//g2.fillOval(x41, y41, 10, 10);
		int x42=(int)Math.round(paddingx+(2*a+1)*notranjiPolmer+(b*notranjiPolmer)-2*notranjiPolmer*v[0]);
		int y42=(int)Math.round(paddingy+zunanjiPolmer+b*1.5*zunanjiPolmer+2*notranjiPolmer*v[1]);
		//g2.fillOval(x42, y42, 10, 10);

		int r1=(int)Math.round(x12-debelinaRoba*v[0]);
		int l1=(int)Math.round(y12-debelinaRoba*v[1]);
		int r2=(int)Math.round(x23+debelinaRoba*v[0]);
		int l2=(int)Math.round(y23-debelinaRoba*v[1]);
		int r3=(int)Math.round(x32+debelinaRoba);
		int l3=(int)Math.round(y32);
		int r4=(int)Math.round(x31+debelinaRoba*v[0]);
		int l4=(int)Math.round(y31+debelinaRoba*v[1]);
		int r5=(int)Math.round(x42-debelinaRoba*v[0]);
		int l5=(int)Math.round(y42+debelinaRoba*v[1]);
		int r6=(int)Math.round(x13-debelinaRoba);
		int l6=(int)Math.round(y13);

		int[] iksi2={r1,r2,r3,r4,r5,r6};
		int[] ipsi2={l1,l2,l3,l4,l5,l6};
		g2.setColor(this.barvaRoba);
		g2.fillPolygon(iksi2,ipsi2,6);

		int[] okvirZgorajx={x12,x23,x2,x1,x14};
		int[] okvirZgorajy={y12,y23,y2,y1,y14};
		int[] okvirSpodajx={x4,x3,x34,x31,x42};
		int[] okvirSpodajy={y4,y3,y34,y31,y42};
		g2.setColor(this.barvaIgralca1);
		g2.fillPolygon(okvirZgorajx,okvirZgorajy,5);
		g2.fillPolygon(okvirSpodajx,okvirSpodajy,5);

		int[] okvirLevox={x13,x14,x1,x4,x42};
		int[] okvirLevoy={y13,y14,y1,y4,y42};
		int[] okvirDesnox={x2,x23,x32,x34,x3};
		int[] okvirDesnoy={y2,y23,y32,y34,y3};
		g2.setColor(this.barvaIgralca2);
		g2.fillPolygon(okvirLevox,okvirLevoy,5);
		g2.fillPolygon(okvirDesnox,okvirDesnoy,5);

		Stroke stroke=new BasicStroke(debelinaRoba);
		g2.setStroke(stroke);
		g2.setColor(barvaRoba);
		g2.drawLine(x14,y14,x1,y1);
		g2.drawLine(x2,y2,x23,y23);
		g2.drawLine(x3,y3,x34,y34);
		g2.drawLine(x4,y4,x42,y42);

		///////////////// polja plošče

		for(int i=0;i<this.igra.plosca.length;i++){
			for(int j=0;j<this.igra.plosca[0].length;j++){
				int x=(int)Math.round(paddingx+(2*j+1)*notranjiPolmer+(i*notranjiPolmer)); // vodoravno
				int y=(int)Math.round(paddingy+zunanjiPolmer+i*1.5*zunanjiPolmer); // navpično
				if(igra.plosca[j][i]==Polje.PRVI)
					barva=this.barvaIgralca1;
				else if(igra.plosca[j][i]==Polje.DRUGI)
					barva=this.barvaIgralca2;
				else
					barva=this.barvaPrazno; // dodati še za zmagovalno vrstico
				// test
				if(i==0&&j==0)
					System.out.println("x0 = "+y+", y0 = "+x+",");
				else if(i==0&&j==this.igra.plosca.length-1)
					System.out.println("x0 = "+y+", yN = "+x+",");
				else if(i==this.igra.plosca.length-1&&j==0)
					System.out.println("xN = "+y+", y0 = "+x+",");
				else if(i==this.igra.plosca.length-1&&j==this.igra.plosca.length-1)
					System.out.println("xN = "+y+", yN = "+x+",");

				sestkotnik(y,x,(int)Math.round(zunanjiPolmer+debelinaRoba),debelinaRoba,g2,this.barvaRoba,barva);
			}
		}
	}

// nariše šestkotnik, če mu damo središče in polmer:	
	private void sestkotnik(int x,int y,int r,int debelinaRoba,Graphics2D g,Color rob,Color notranjost){
		int[] xKoordinate=new int[6];
		int[] yKoordinate=new int[6];
		int[] xKoordinate2=new int[6];
		int[] yKoordinate2=new int[6];
		for(int i=0;i<6;i++){
			yKoordinate[i]=(int)Math.round(x+r*Math.cos(i*2.0*Math.PI/6));
			xKoordinate[i]=(int)Math.round(y+r*Math.sin(i*2.0*Math.PI/6));
			yKoordinate2[i]=(int)Math.round(x+(r-debelinaRoba)*Math.cos(i*2.0*Math.PI/6));
			xKoordinate2[i]=(int)Math.round(y+(r-debelinaRoba)*Math.sin(i*2.0*Math.PI/6));
		}
		g.setColor(rob);
		//g.drawPolygon(xKoordinate, yKoordinate, 6);
		g.fillPolygon(xKoordinate,yKoordinate,6);
		g.setColor(notranjost);
		g.fillPolygon(xKoordinate2,yKoordinate2,6);
	}

	private Polygon getHex(int x,int y,int r){
		int[] xKoordinate=new int[6];
		int[] yKoordinate=new int[6];
		for(int i=0;i<6;i++){
			yKoordinate[i]=(int)Math.round(x+r*Math.cos(i*2.0*Math.PI/6));
			xKoordinate[i]=(int)Math.round(y+r*Math.sin(i*2.0*Math.PI/6));
		}
		return new Polygon(xKoordinate,yKoordinate,6);
	}

	@Override public void mouseClicked(MouseEvent e){
		if(igra.getStanje()!=Stanje.V_TEKU) {
			konecIgre=true;
		}
		if(Vodja.clovekNaVrsti&&!konecIgre){
			int x=e.getX();
			int y=e.getY();
			for(int i=0;i<this.igra.plosca.length;i++){
				for(int j=0;j<this.igra.plosca[0].length;j++){
					int sx=(int)Math.round(paddingx+(2*j+1)*notranjiPolmer+(i*notranjiPolmer));
					int sy=(int)Math.round(paddingy+zunanjiPolmer+i*1.5*zunanjiPolmer);
					if(getHex(sy,sx,notranjiPolmer).contains(new Point(x,y))){
						System.out.println("zadel si "+j+", "+i+".");
						this.igra.odigraj(new Koordinati(j, i));
						break;
					}
				}
			}
			repaint();
		}
		Vodja.igramo();
	}

	@Override public void mousePressed(MouseEvent e){
	}

	@Override public void mouseReleased(MouseEvent e){
	}

	@Override public void mouseEntered(MouseEvent e){
	}

	@Override public void mouseExited(MouseEvent e){
	}

}

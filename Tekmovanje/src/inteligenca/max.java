package inteligenca;

import strukture.globalno;
import strukture.hitra_igra;
import strukture.tocka;

/**
 * Izbere najboljšo potezo
 * @author Gregor
 *
 */
public class max{
	public static tocka play(hitra_igra g) {
		int best=-globalno.inf;
		tocka ans=null;
		for(tocka t:g.mozne_poteze) {
			g.odigraj(t);
			int score=hitra_evaluacija.score(g);
			if(score>best) {
				ans=t;
				best=score;
			}
			g.obrni(t);
		}
		return ans;
	}
}

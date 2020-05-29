package strukture;

import java.util.Arrays;
import java.util.Iterator;

public class vector_set implements Iterable<tocka> {
	private boolean[][] grid;
	private tocka[] q;
	public int size = 0;
	private int cappacity = 121;
	private int load = 0;
	public vector_set() {
		q = new tocka[121];
		grid = new boolean[11][11];
	}

	public void add(tocka ml) {
		if (!grid[ml.x][ml.y]) {
			load++;
			grid[ml.x][ml.y] = true;
			q[size++] = ml;
			if (size == cappacity) {// Redek pojav

				// Ce je load factor precej velik potem kloniramo. Drugace cistimo
				// Problem: Duplikati bodo ostali v klonu...
				double limit = 0.7;
				if (load / (double) cappacity > limit) {
					q = Arrays.copyOf(q, 2 * cappacity);
					cappacity *= 2;
				} else {
					tocka[] q2 = new tocka[cappacity];
					int p = 0;
					for (int i = 0; i < size; i++) {
						if (grid[q[i].x][q[i].y]) {
							q2[p++] = q[i];
						}
					}
					q = q2;
					size = load;
				}
			}
		}

	}

	public tocka get(int a) {
		if (grid[q[a].x][q[a].y]) {
			return q[a];
		} else {
			return null;
		}
	}

	public void remove(tocka ml) {
		if (grid[ml.x][ml.y]) {
			load--;
			grid[ml.x][ml.y] = false;
		}
	}

	@Override public Iterator<tocka> iterator(){
		return new Iterator<tocka> () {
			int p=0;
			int done=0;
            @Override
            public boolean hasNext() {
                return done<load;
            }

            @Override
            public tocka next() {
               while(get(p)==null) {
            	   p++;
               }
               done++;
               return get(p++);
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("no changes allowed");
            }
        };
	}

}
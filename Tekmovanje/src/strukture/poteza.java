package strukture;

public enum poteza{
	desno(1,0),
	desno_dol(0,1),
	levo_dol(-1,1),
	levo(-1,0),
	levo_gor(0,-1),
	desno_gor(1,-1);

	public final int x,y;
	poteza(int x,int y){
		this.x=x;
		this.y=y;
	}
}
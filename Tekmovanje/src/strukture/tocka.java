package strukture;

import splosno.Koordinati;

public class tocka{
	public int x,y;
	public tocka(int x,int y){
		this.x=x;
		this.y=y;
	}
	public tocka(Koordinati k){
		this.x=k.getX();
		this.y=k.getY();
	}
	@Override public int hashCode(){
		final int prime=31;
		int result=1;
		result=prime*result+x;
		result=prime*result+y;
		return result;
	}
	@Override public boolean equals(Object obj){
		if(this==obj)
			return true;
		if(obj==null)
			return false;
		if(getClass()!=obj.getClass())
			return false;
		tocka other=(tocka)obj;
		if(x!=other.x)
			return false;
		if(y!=other.y)
			return false;
		return true;
	}
	
	public Koordinati toKoordinati() {
		return new Koordinati(x,y);
	}
	@Override public String toString(){
		return "("+x+", "+y+") ";
	}

}

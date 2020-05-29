package strukture;

public class DisjointUnionSets{
	int[] rank,parent;
	int n;
	public DisjointUnionSets(int n){
		rank=new int[n];
		parent=new int[n];
		this.n=n;
		for(int i=0;i<n;i++){
			parent[i]=i;
		}
	}
	public int find(int x){
		if(parent[x]!=x){
			parent[x]=find(parent[x]);
		}
		return parent[x];
	}
	public void union(int x,int y){
		int xRoot=find(x),yRoot=find(y);
		if(xRoot==yRoot)
			return;
		if(rank[xRoot]<rank[yRoot])
			parent[xRoot]=yRoot;
		else if(rank[yRoot]<rank[xRoot])
			parent[yRoot]=xRoot;
		else{
			parent[yRoot]=xRoot;
			rank[xRoot]=rank[xRoot]+1;
		}
	}
	public void print(){
		boolean[] done=new boolean[n];
		for(int i=0;i<n;i++){
			if(!done[i]&&rank[i]>0){
				for(int j=0;j<n;j++){
					if(find(i)==find(j)){
						if(j/11<11){
							System.out.print("("+j/11+", "+j%11+") ");
						}else {
							System.out.print(j%11==0?"U":(j%11==1?"D":(j%11==2?"L":"R"))+" ");
						}
						done[j]=true;
					}
				}
				System.out.println();
			}
		}
	}
}

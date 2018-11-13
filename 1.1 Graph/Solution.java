import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

interface Graph {
    public int V();
    public int E();
    public void addEdge(int v, int w);
    public Iterable<Integer> adj(int v);
    public boolean hasEdge(int v, int w);
}
class GraphList implements Graph
{
	private static final Object NEWLINE = null;
	int V;
	int E;
	String b[];
     Bag<Integer>[] adj;
//     GraphList()
//     {
//	 adj=(Bag<Integer>[])new Bag[V];
//	 for(int v=0;v<V;v++)
//		 adj[v]=new Bag<Integer>();
//     }
public int V()
{
	return V;
}
public int E()
{
	return E;
}
public boolean hasEdge(int v, int w)
{
	return false;
	
}
public Iterable<Integer> adj(int v)
{
	return adj[v];
}
GraphList(Scanner in)
{
this.V=(Integer.parseInt(in.nextLine()));
adj=(Bag<Integer>[])new Bag[V];
for(int v=0;v<V;v++)
	 adj[v]=new Bag<Integer>();
int E=Integer.parseInt(in.nextLine());
String a=in.nextLine();
StringTokenizer st=new StringTokenizer(a,",");
 b=new String[st.countTokens()];
int j=0;
while(st.hasMoreTokens())
{
	b[j++]=st.nextToken();
	//System.out.println(b[0]+","+b[1]);
}
for(int i=0;i<E;i++)
{
	String z=in.nextLine();
	String r[]=z.split(" ");
	int v=Integer.parseInt(r[0]);
	int w=Integer.parseInt(r[1]);
	addEdge(v,w);
}
}
public void addEdge(int v,int w)
{
	E++;
	adj[v].add(w);
	adj[w].add(v);
}
public String toString() {
    StringBuilder s = new StringBuilder();
    s.append(V + " vertices, " + E + " edges "+"\n");
   
    for (int v = 0; v < V; v++) {
    	//System.out.println(b[v]);
        s.append(b[v] + ": ");
        //System.out.println(v);
        for (int w : adj[v]) {
        	//System.out.println("v val:"+v);
       
         s.append(b[w] + " ");
        }
        s.append("\n");
    }
    return s.toString();
}

}

class GraphMatrix implements Graph
{
	int ver;
	int edg;
	int [][]matrix;
	String[] city;
	 public GraphMatrix(Scanner scan) {
		 this.ver = Integer.parseInt(scan.nextLine());
	        matrix =  new int[ver][ver];
	        int edge = Integer.parseInt(scan.nextLine());
	        city = scan.nextLine().split(",");
	        for (int i = 0; i < edge; i++) {
	            String[] inputs = scan.nextLine().split(" ");
	            addEdge(Integer.parseInt(inputs[0]),Integer.parseInt(inputs[1]));
	        }
	}
	public int V()
	 {
		return ver;
		 
	 }
	    public int E()
	    {
			return edg;
	    	
	    }
	    public void addEdge(int v, int w)
	    {
	    	if (v != w) {
	            if (!hasEdge(v, w)) {
	                matrix[v][w] = 1;
	                matrix[w][v] = 1;
	                edg++;
	            }
	        } else {
	            return;
	        }
	    }
	    public Iterable<Integer> adj(int v)
	    {
			return null;
	    	
	    }
	    public boolean hasEdge(int v, int w)
	    {
			if(matrix[v][w]==1)
			{
				return true;
			}
			return false;
	    	
	    }
	    public void output()
	    {
	    	int count=0;
	    	System.out.println(ver+" vertices, "+edg+" edges");
	    	for(int i=0;i<ver;i++)
	    	{
	    		int j=0;
	    		for(;j<ver;j++)
	    		{
	    			if(matrix[i][j]==0)
	    				count++;	
	    		}
	    	}
	    	//System.out.println("Matric"+matrix.length);
	    	if(count!=matrix.length)
	    	{
	    		int i=0; 
	    	for(i=0;i<ver-1;i++)
	    	{
	    		int j=0;
	    		for(;j<ver;j++)
	    		{
	    			System.out.print(matrix[i][j]+" ");
	    		}
	    		//System.out.print(matrix[i][j]);
	    		System.out.println();
	    	}
	    	int k=0;
	    	for(k=0;k<ver-1;k++)
    		{
    			System.out.print(matrix[i][k]+" ");
    		}
    		System.out.print(matrix[i][k]);
	    	}
	    	else
	    		System.out.println("No edges");
	    	
	    }
}
class Solution {
   
    public static void main(final String[] args) {
        Scanner scan = new Scanner(System.in);
        String s  = scan.nextLine();
        switch (s) {
        case "List":
           GraphList listObj = new GraphList(scan);
            System.out.println(listObj);
            break;
        case "Matrix":
            GraphMatrix matObj = new GraphMatrix(scan);
            matObj.output();
            break;
        default:
            break;
        }
    }
}

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

class Vertex implements Comparable<Vertex> 
{
	private int n;
	private List<Edge> adjacenciesList;
	private boolean visited;
	private Vertex predecessor;
	private int distance = Integer.MAX_VALUE;

	public Vertex(int n) {
		this.n  = n;
		this.adjacenciesList = new ArrayList<>();
	}

	public void addNeighbour(Edge edge) {
		this.adjacenciesList.add(edge);
	}

	public int getName() {
		return n;
	}

	public void setName(int n) {
		this.n = n;
	}

	public List<Edge> getAdjacenciesList() {
		return adjacenciesList;
	}

	public void setAdjacenciesList(List<Edge> adjacenciesList) {
		this.adjacenciesList = adjacenciesList;
	}

	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	public Vertex getPredecessor() {
		return predecessor;
	}

	public void setPredecessor(Vertex predecessor) {
		this.predecessor = predecessor;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}
	//public String toString() {
		//return this.n;
	//}
	

	@Override
	public int compareTo(Vertex otherVertex) {
		return Integer.compare(this.distance, otherVertex.getDistance());
	}
}
class Edge {
	private int weight;
	private Vertex startVertex;
	private Vertex targetVertex;
	
	public Edge(int weight, Vertex startVertex, Vertex targetVertex) {
		this.weight = weight;
		this.startVertex = startVertex;
		this.targetVertex = targetVertex;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public Vertex getStartVertex() {
		return startVertex;
	}

	public void setStartVertex(Vertex startVertex) {
		this.startVertex = startVertex;
	}

	public Vertex getTargetVertex() {
		return targetVertex;
	}

	public void setTargetVertex(Vertex targetVertex) {
		this.targetVertex = targetVertex;
	}
}
class DijkstraShortestPath 
{ 
	public void computeShortestPaths(Vertex sourceVertex)
	{
		sourceVertex.setDistance(0);
		PriorityQueue<Vertex> priorityQueue = new PriorityQueue<>();
		priorityQueue.add(sourceVertex);
		sourceVertex.setVisited(true);
 
		while( !priorityQueue.isEmpty() ){
            // Getting the minimum distance vertex from priority queue
			Vertex actualVertex = priorityQueue.poll();
			for(Edge edge : actualVertex.getAdjacenciesList()){
				Vertex v = edge.getTargetVertex();
				if(!v.isVisited())
				{
					int newDistance = actualVertex.getDistance() + edge.getWeight();
 
					if( newDistance < v.getDistance() ){
						priorityQueue.remove(v);
						v.setDistance(newDistance);
						v.setPredecessor(actualVertex);
						priorityQueue.add(v);
					}
				}
			}
			actualVertex.setVisited(true);
		}
	}
 
	public List<Vertex> getShortestPathTo(Vertex targetVertex){
		List<Vertex> path = new ArrayList<>();
 
		for(Vertex vertex=targetVertex;vertex!=null;vertex=vertex.getPredecessor()){
			path.add(vertex);
		}
 
		Collections.reverse(path);
		return path;
	}
}


public class Solution 
{
	public static void main(String[] args) 
	{
		Scanner sc = new Scanner(System.in);
		DijkstraShortestPath sp = new DijkstraShortestPath();
		int a = Integer.parseInt(sc.nextLine());
		int b = Integer.parseInt(sc.nextLine());
		Vertex v[] = new Vertex[b];
		for (int i = 0; i < a; i++) 
		{
			v[i] = new Vertex(i);
		}
		for (int j = 0; j < b; j++) {
			String s1 = sc.nextLine();
			String s2[] = s1.split(" ");
			Vertex v1 = getVertex(v,Integer.parseInt(s2[0]));
			Vertex v2 = getVertex(v,Integer.parseInt(s2[1]));
			v1.addNeighbour(new Edge(Integer.parseInt(s2[2]), v1, v2));
			v2.addNeighbour(new Edge(Integer.parseInt(s2[2]), v2, v1));
		}
		// Self loops are not allowed...
		// Parallel Edges are allowed...
		// Take the Graph input here...
		String caseToGo = sc.nextLine();
		switch (caseToGo) 
		{
		case "Graph":
			System.out.println(a+" vertices "+b+" edges");
			for(int i=0; i<a; i++)
			{
				Vertex v3 = v[i];
				v[i].hashCode();
				//System.out.println(s.getClass());
				System.out.print(i+":");
				System.out.println(v[i].getName());
				System.out.println(v[i].getPredecessor());
			
			}
			
			//Print the Graph Object.
			break;

		case "DirectedPaths":
			String s7 = sc.nextLine();
			String s8[] = s7.split(" ");
			DijkstraShortestPath shortestPath = new  DijkstraShortestPath();
			Vertex v1 = getVertex(v, Integer.parseInt(s8[0]));
			Vertex v2 = getVertex(v, Integer.parseInt(s8[1]));
			// System.out.println(v1);
			shortestPath.computeShortestPaths(v1);
			System.out.println(v2.getDistance());
			System.out.println(shortestPath.getShortestPathTo(v2));
			// Handle the case of DirectedPaths, where two integers are given.
			// First is the source and second is the destination.
			// If the path exists print the distance between them.
			// Other wise print "No Path Found."
			break;

		case "ViaPaths":
			// Handle the case of ViaPaths, where three integers are given.
			// First is the source and second is the via is the one where path should pass throuh.
			// third is the destination.
			// If the path exists print the distance between them.
			// Other wise print "No Path Found."
			break;

		default:
			break;
		}

	}

	private static Vertex getVertex(Vertex[] v, int parseInt) 
	{
		// TODO Auto-generated method stub
		for (int i = 0; i < v.length; i++)
		{
			return v[i];
		}	
		return null;
	}
}
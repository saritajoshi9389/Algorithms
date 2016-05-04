
import java.util.ArrayList;


/**
 *
 * @author sarita joshi
 */
class Vertex 
{
   int height;      // Height of node
   int excess_flow; // Excess Flow 
    Vertex(int h,int e_flow)
    {
        this.height = h;
        this.excess_flow = e_flow;
    }
}
class Edge 
{
   int u, v; // Edge is from u to v       
   int flow; // Current flow
   int capacity; // total flow capacity
   Edge(int flow, int capacity, int u, int v)
    {
        this.flow = flow;
        this.capacity = capacity;
        this.u = u;
        this.v = v;
    }
}

class Graph {
	int V;
	ArrayList<Vertex> verticesList;
	ArrayList<Edge> edgeList;
	Graph(int V)
	{
	    this.V = V;
	    // all vertices are initialized with 0 height
	    // and 0 excess flow
	    verticesList =new ArrayList<Vertex>();
	    edgeList=new ArrayList<Edge>();
	    for (int i = 0; i < V; i++){
	    	verticesList.add(new Vertex(0,0));
	    }
	        
	}
	
        // Function to add an edge to a graph
	void addEdge(int u, int v, int capacity)
	{
	    // flow is initialized with 0 for all edge
	    edgeList.add(new Edge(0, capacity, u, v));
	}
	
	void preflow(int s)
	{
	    // Making h of source Vertex equal to no. of vertices
	    // Height of other vertices is 0.
	    verticesList.get(s).height = verticesList.size();
	 
	    //
	    for (int i = 0; i < edgeList.size(); i++)
	    {
	        // If current edgeListe goes from source
	        if (edgeList.get(i).u == s)
	        {
	            // Flow is equal to capacity
	        	edgeList.get(i).flow = edgeList.get(i).capacity;
	 
	            // Initialize excess flow for adjacent v
	            verticesList.get(edgeList.get(i).v).excess_flow += edgeList.get(i).flow;
	 
	            // Add an edge from v to s in residual graph with
	            // capacity equal to 0
	            edgeList.add(new Edge(-edgeList.get(i).flow, 0, edgeList.get(i).v, s));
	        }
	    }
	}
	
        // This function updates the reverse edge flow
	void updateReverseEdgeFlow(int i, int flow)
	{
	    int u = edgeList.get(i).v, v = edgeList.get(i).u;
	 
	    for (int j = 0; j < edgeList.size(); j++)
	    {
	        if (edgeList.get(j).v == v && edgeList.get(j).u == u)
	        {
	            edgeList.get(j).flow -= flow;
	            return;
	        }
	    }
	 
	    // adding reverse Edge in residual graph
	    edgeList.add(new Edge(0, flow, u, v));
	}
        
        // returns index of overflowing Vertex
	int overFlowVertex(ArrayList<Vertex> verticesList)
	{
	    for (int i = 1; i < verticesList.size() - 1; i++)
	       if (verticesList.get(i).excess_flow > 0)
	            return i;
	 
	    // -1 if no overflowing Vertex
	    return -1;
	}
	
	boolean push(int u)
	{
	    // Traverse through all edges to find an adjacent (of u)
	    // to which flow can be pushed
	    for (int i = 0; i < edgeList.size(); i++)
	    {
	        // Checks u of current edge is same as given
	        // overflowing vertex
	        if (edgeList.get(i).u == u)
	        {
	            // if flow is equal to capacity then no push
	            // is possible
	            if (edgeList.get(i).flow == edgeList.get(i).capacity)
	                continue;
	 
	            // Push is only possible if height of adjacent
	            // is smaller than height of overflowing vertex
	            if (verticesList.get(u).height > verticesList.get(edgeList.get(i).v).height)
	            {
	                // Flow to be pushed is equal to minimum of
	                // remaining flow on edge and excess flow.
	                int flow = min(edgeList.get(i).capacity - edgeList.get(i).flow,
	                               verticesList.get(u).excess_flow);
	 
	                // Reduce excess flow for overflowing vertex
	                verticesList.get(u).excess_flow -= flow;
	 
	                // Increase excess flow for adjacent
	                verticesList.get(edgeList.get(i).v).excess_flow += flow;
	 
	                // Add residual flow (With capacity 0 and negative
	                // flow)
	                edgeList.get(i).flow += flow;
	 
	                updateReverseEdgeFlow(i, flow);
	 
	                return true;
	            }
	        }
	    }
	    return false;
	}
	
	void relabel(int u)
	{
	    // Initialize minimum height of an adjacent
	    int max = Integer.MAX_VALUE;
	 
	    // Find the adjacent with minimum height
	    for (int i = 0; i < edgeList.size(); i++)
	    {
	        if (edgeList.get(i).u == u)
	        {
	            // if flow is equal to capacity then no
	            // relabeling
	            if (edgeList.get(i).flow == edgeList.get(i).capacity)
	                continue;
	 
	            // Update minimum height
	            if (verticesList.get(edgeList.get(i).v).height < max)
	            {
	                max = verticesList.get(edgeList.get(i).v).height;
	 
	                // updating height of u
	                verticesList.get(u).height = max + 1;
	            }
	        }
	    }
	}
	
        int min(int a,int b){
                return ((a>=b) ? b : a);
	}
        
        // Function that returns the max flow
	int getMaxFlow(int s, int t)
	{
	    preflow(s); // initialization	 
	    // loop untill none of the Vertex is in overflow
	    while (overFlowVertex(verticesList) != -1)
	    {
	        int u = overFlowVertex(verticesList);
	        if (!push(u))
	            relabel(u);
	    }
	    // excess_flow will be final maximum flow
	    return verticesList.get(verticesList.size()-1).excess_flow;
	}

}

public class HW12 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int V = 6;
        Graph g = new Graph(V);
        // Creating above shown flow network. CRLS page 726 example
        // should return a max flow of 23
        g.addEdge(0, 1, 16);
        g.addEdge(0, 2, 13);
        g.addEdge(2, 1, 4);
        g.addEdge(1, 3, 12);
        g.addEdge(2, 4, 14);
        g.addEdge(3, 2, 9);
        g.addEdge(3, 5, 20);
        g.addEdge(4, 3, 7);
        g.addEdge(4, 5, 4);
        // Initialize source and sink i.e s and t
        int s = 0, t = 5;
        System.out.println("Max Flow is -> " + g.getMaxFlow(s, t));
    }
    
}

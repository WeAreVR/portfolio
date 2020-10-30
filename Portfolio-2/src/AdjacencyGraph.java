import java.util.ArrayList;
import java.util.PriorityQueue;

public class AdjacencyGraph {
    ArrayList<Vertex> vertices;
    public AdjacencyGraph(){
        vertices = new ArrayList<Vertex>();
    }

    public void addVertex(Vertex v){
        vertices.add(v);
    }


    public void addEdge(Vertex f, Vertex t, Integer w){
        if(!(vertices.contains(f) && vertices.contains(t)) ) {
            System.out.println("Vertex not in graph");
            return;
        }
        Edge e = new Edge( f,  t,  w);
        Edge ee = new Edge(t, f, w);
    }



    public void PrintGraph(){
        for (int i = 0; i<vertices.size(); i++){
            System.out.println("From Vertex: " + vertices.get(i).name);
            Vertex currentfrom= vertices.get(i);
            for (int j=0; j<currentfrom.outEdges.size(); j++){
                Edge currentEdge= currentfrom.outEdges.get(j);
                System.out.println("To " + currentEdge.to.name + " weight: " + currentEdge.weight);
            }
            System.out.println(" ");

        }
    }



    public void PrimsMST() {
        PriorityQueue<Vertex> Q = new PriorityQueue<Vertex>();

        if (vertices.size() > 0) {
            vertices.get(0).dist = 0;
            Q.offer(vertices.get(0));
        }

        int counter = 0;
        int MST = 0;

        while (!Q.isEmpty() && counter < vertices.size()) {
            Vertex u = Q.poll();

            if (!u.visited) {
                for (Edge e : u.outEdges) {
                    if (e.weight < e.to.dist) {
                        e.to.dist = e.weight;
                        e.to.pred = u;
                        Q.offer(e.to);
                    }
                }
                counter++;
                MST += u.dist;
                u.visited = true;
            }
        }
        System.out.println("\nMinimum Spanning Tree Distance: " + MST + " Km. Price: " + MST*1000000+ " DKK");
        printMST();
    }

    public void printMST() {
        for (Vertex vertex : vertices) {

            System.out.println(vertex.pred + " to: " + vertex + " Edge weight: " + vertex.dist );

        }
    }
}




class Vertex implements Comparable<Vertex> {
    String name;
    ArrayList<Edge> outEdges;
    Integer dist = Integer.MAX_VALUE;
    Vertex pred = null;
    Boolean visited = false;

    public Vertex(String id) {
        name = id;
        outEdges = new ArrayList<Edge>();
    }

    public void addOutEdge(Edge e) {
        outEdges.add(e);
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int compareTo(Vertex o) {
        if (this.dist < o.dist) {
            return -1;
        }
        if (this.dist > o.dist) {
            return 1;
        }
        return 0;
    }
}


class Edge {
    Integer weight;
    Vertex from;
    Vertex to;

    public Edge(Vertex from, Vertex to, Integer cost) {
        this.from = from;
        this.to = to;
        this.weight = cost;

        this.from.addOutEdge(this);
    }

    public int compareTo(Edge o) {
        if (this.weight < o.weight) {
            return -1;
        }
        if (this.weight > o.weight) {
            return 1;
        }
        return 0;
    }
}
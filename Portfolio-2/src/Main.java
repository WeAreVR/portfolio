public class Main {

    public static void main(String[] args) {

        // write your code here
        AdjacencyGraph myGraph = new AdjacencyGraph();

        Vertex eskildstrup = new Vertex("Eskildstrup");
        Vertex haslev = new Vertex("Haslev");
        Vertex holbæk = new Vertex("Holbæk");
        Vertex jægerspris = new Vertex("Jægerspris");
        Vertex kalundborg = new Vertex("Kalundborg");
        Vertex korsør = new Vertex("Korsør");
        Vertex køge = new Vertex("Køge");
        Vertex maribo = new Vertex("Maribo");
        Vertex næstved = new Vertex("Næstved");
        Vertex ringsted = new Vertex("Ringsted");
        Vertex slagelse = new Vertex("Slagelse");
        Vertex nykøbingF = new Vertex("NykøbingF");
        Vertex vordingborg = new Vertex("Vordingborg");
        Vertex sorø = new Vertex("Sorø");
        Vertex roskilde = new Vertex("Roskilde");
        Vertex nakskov = new Vertex("Nakskov");

        myGraph.addVertex(eskildstrup);
        myGraph.addVertex(haslev);
        myGraph.addVertex(holbæk);
        myGraph.addVertex(jægerspris);
        myGraph.addVertex(kalundborg);
        myGraph.addVertex(korsør);
        myGraph.addVertex(køge);
        myGraph.addVertex(maribo);
        myGraph.addVertex(næstved);
        myGraph.addVertex(ringsted);
        myGraph.addVertex(slagelse);
        myGraph.addVertex(nykøbingF);
        myGraph.addVertex(sorø);
        myGraph.addVertex(vordingborg);
        myGraph.addVertex(roskilde);
        myGraph.addVertex(nakskov);


        myGraph.addEdge(eskildstrup, maribo, 28);
        myGraph.addEdge(eskildstrup, nykøbingF, 13);
        myGraph.addEdge(eskildstrup, vordingborg, 24);
        myGraph.addEdge(haslev, korsør, 60);
        myGraph.addEdge(haslev, køge, 24);
        myGraph.addEdge(haslev, næstved, 25);
        myGraph.addEdge(haslev, ringsted, 19);
        myGraph.addEdge(haslev, roskilde, 47);
        myGraph.addEdge(haslev, slagelse, 48);
        myGraph.addEdge(haslev, sorø, 34);
        myGraph.addEdge(haslev, vordingborg, 40);
        myGraph.addEdge(holbæk, jægerspris, 34);
        myGraph.addEdge(holbæk, kalundborg, 44);
        myGraph.addEdge(holbæk, korsør, 66);
        myGraph.addEdge(holbæk, ringsted, 36);
        myGraph.addEdge(holbæk, roskilde, 32);
        myGraph.addEdge(holbæk, slagelse, 46);
        myGraph.addEdge(holbæk, sorø, 34);
        myGraph.addEdge(jægerspris, korsør, 95);
        myGraph.addEdge(jægerspris, køge, 58);
        myGraph.addEdge(jægerspris, ringsted, 56);
        myGraph.addEdge(jægerspris, roskilde, 33);
        myGraph.addEdge(jægerspris, slagelse, 74);
        myGraph.addEdge(jægerspris, sorø, 63);
        myGraph.addEdge(kalundborg, ringsted, 62);
        myGraph.addEdge(kalundborg, roskilde, 70);
        myGraph.addEdge(kalundborg, slagelse, 39);
        myGraph.addEdge(kalundborg, sorø, 51);
        myGraph.addEdge(korsør, slagelse, 20);
        myGraph.addEdge(køge, næstved, 45);
        myGraph.addEdge(køge, ringsted, 28);
        myGraph.addEdge(køge, roskilde, 25);
        myGraph.addEdge(køge, vordingborg, 60);
        myGraph.addEdge(maribo, nakskov, 27);
        myGraph.addEdge(maribo, nykøbingF, 26);
        myGraph.addEdge(næstved, roskilde, 57);
        myGraph.addEdge(næstved , slagelse, 37);
        myGraph.addEdge(næstved, sorø, 32);
        myGraph.addEdge(næstved, vordingborg, 28);
        myGraph.addEdge(ringsted, roskilde, 31);
        myGraph.addEdge(ringsted, sorø, 15);
        myGraph.addEdge(ringsted, vordingborg, 58);
        myGraph.addEdge(slagelse, sorø, 14);

       myGraph.PrintGraph();

       myGraph.PrimsMST();




    }
}
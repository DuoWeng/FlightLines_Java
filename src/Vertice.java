import java.util.ArrayList;

public class Vertice {
    String element;
    // a sequence of incident
    ArrayList<Edge> edges;
    //the status of vertice
    boolean hasVisited;
    Vertice path;
    int dist;
    Vertice(String s)
    {
        element = s;
        edges = new ArrayList<>();
        hasVisited = false;
        dist = 10000000;
        path = null;
    }

    public String getElement() {
        return element;
    }
    public int getDist(){return dist;}
    public void setDist(int i){dist = i;}
    public Vertice getPath(){return path;}
    public void setPath(Vertice v){path = v;}
    //get a sequence of incident
    public ArrayList<Edge> incidentEdges() {
        return edges;
    }
    //add an incident
    public void addEdge(Edge edge) {
        edges.add(edge);
    }
    //remove an incident
    public void removeEdge(Edge edge)
    {
        edges.remove(edge);
    }
    //set the vertice visited
    public void setStatus(boolean b)
    {hasVisited = b;}
    public boolean getStatus()
    {return hasVisited;}
    @Override
    public boolean equals(Object o)
    {
        Vertice v = (Vertice) o;
        return v.getElement().equals(element);
    }
    @Override
    public String toString()
    {return element;}
}

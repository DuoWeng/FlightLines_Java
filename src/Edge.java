public class Edge {
    Vertice origin,des;
    String status;
    int cost;
    //constructor
    Edge(Vertice o,Vertice d,int cost)
    {origin = o;des = d;status = "UNEXPLORED";this.cost = cost;}
    //change status of the edge
    public void setStatus(String status) {
        this.status = status;
    }
    //get the original vertice
    public Vertice getOrigin() {
        return origin;
    }
    //get the end vertice
    public Vertice getDes() {
        return des;
    }
    //get the status of this edge
    public String getStatus()
    {return status;}
    public int getCost(){return cost;}
    @Override
    public boolean equals(Object o)
    {
        Edge e = (Edge) o;
        return e.getOrigin().equals(origin) && e.getDes().equals(des);
    }
    @Override
    public String toString()
    {return "From "+ origin + " to "+ des;}
}

import PQ.HeapAdaptablePriorityQueue;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Scanner;
import java.util.Stack;
public class AirlineConnections {
    private ArrayList<Edge> edges;
    private ArrayList<Vertice> vertices;
    private Stack s = new Stack();
    private String path = "";
    //constructor
    AirlineConnections() throws FileNotFoundException {
        vertices = new ArrayList<>();
        edges = new ArrayList<>();
        createVertices();
        createEdges();
        addIncidenceToVertice();
    }

    /**
     * helper method to initialize vertices collection
     * @throws FileNotFoundException
     */
    private void createVertices() throws FileNotFoundException {
        File f = new File("/Users/duo_weng/Downloads/assignment4/src/routes with costs.csv");
        Scanner scan = new Scanner(f);
        while(scan.hasNext())
        {
            String line = scan.nextLine();
            String splt[] = line.split(",");
            Vertice v1 = new Vertice(splt[0]);
            Vertice v2 = new Vertice(splt[1]);
            if(!vertices.contains(v1)){
                if(!vertices.contains(v2))
                {vertices.add(v1);vertices.add(v2);}
                else vertices.add(v1);
            }
            else if (!vertices.contains(v2)) vertices.add(v2);
        }
    }

    /**
     * helper method to initialize edges collection
     * @throws FileNotFoundException
     */
    private void createEdges() throws FileNotFoundException {
        File f = new File("/Users/duo_weng/Downloads/assignment4/src/routes with costs.csv");
        Scanner scan = new Scanner(f);
        while(scan.hasNext())
        {
            String line = scan.nextLine();
            String splt[] = line.split(",");
            String start = splt[0];
            String end = splt[1];
            int cost = Integer.parseInt(splt[2].trim());
            Vertice departure = getVertice(start);
            Vertice dest = getVertice(end);
            if(departure != null && dest != null)
            {
                edges.add(new Edge(departure,dest,cost));
            }
        }
    }

    /**
     * helper method to add incidence sequence to each vertex
     */
    private void addIncidenceToVertice(){
       for(Edge e:edges){
           e.getOrigin().addEdge(e);
       }
    }
    /**
     * get a list of edges
     * @return an arraylist
     */
    public ArrayList<Edge> getEdges()
    {return edges;}

    /**
     * helper method
     * According to the name of airport, we find it from this graph
     * @param str the name of airport
     * @return a vertice in graph
     */
    private Vertice getVertice(String str)
    {
        Vertice tempt = new Vertice(str);
        for(Vertice v: vertices)
        {if(v.equals(tempt)) return v;}
        return null;
    }
    public String cheapestFlight(String start,String end)
    {
        if(start.equals(end))  return "The departure and destination can not be the same.\n";
        Vertice departure = getVertice(start);
        Vertice dest = getVertice(end);
        if(departure == null)
            return start + " doesn't exist. Try again.\n";
        if(dest == null)
            return end + " doesn't exist. Try again.\n";
        String shortestPath = "";
        shortestPath(departure);
        Stack<String> reverse = new Stack<>();
        reverse.push(end);
        int totalCost = dest.getDist();
        while(dest.getPath() != null)
        {
            reverse.push(dest.getPath().toString());
            dest = dest.getPath();
        }
        while(!reverse.isEmpty())
        {
            shortestPath+= reverse.pop()+" ";
        }
        return "The shortest path from "+ start + " to "+end +" is "+shortestPath +
                ".The total cost is "+ totalCost +".\n";
    }

    /**
     * helper method that perform Dijkstra's algorithm
     */
    private void shortestPath(Vertice d)
    {
        HeapAdaptablePriorityQueue<Integer,Vertice> pq = new HeapAdaptablePriorityQueue<>();
        //
        for(Vertice v:vertices){v.setDist(10000000);v.setPath(null);}
        d.setDist(0);
        for(Vertice v:vertices)
        {pq.insert(v.getDist(),v);}
        while(!pq.isEmpty()){
            Vertice start = pq.removeMin();
            for(Edge e: start.incidentEdges())
            {
                if(e.getDes().getDist() > start.getDist()+e.getCost())
                {e.getDes().setDist(start.getDist()+e.getCost());e.getDes().setPath(start);}
            }}
    }
    /**
     * return a path according to the departure and destination
     * @param start the name of departure airport
     * @param end the name of destination airport
     * @return a string of path
     */
    public String findFlight(String start,String end)
    {
        if(start.equals(end))  return "The departure and destination can not be the same.\n";
        Vertice departure = getVertice(start);
        Vertice dest = getVertice(end);
        if(departure == null)
        return start + " doesn't exist. Try again.\n";
        if(dest == null)
        return end + " doesn't exist. Try again.\n";
        flush();
        findPath(departure,dest);
        if(path.isEmpty())
            return "There is no route between "+ start + " and "+dest+".Try again.\n";
        else
            return "From "+ start + " to " + end +" the path is :"+path+".\n";
    }
    //helper method:perform path finding algorithm
    private void findPath(Vertice start,Vertice dest)
    {
        start.setStatus(true);
        s.push(start);
        if(start.equals(dest))
        {
            for (Enumeration<Vertice> v = s.elements(); v.hasMoreElements();)
                path += v.nextElement() + " ";
        }
        for(Edge e:start.incidentEdges())
        {
            if(e.getStatus().equals("UNEXPLORED"))
            {
                if(!e.getDes().getStatus())
                {
                    e.setStatus("DISCOVERY");
                    findPath(e.getDes(),dest);
                }
            }
        }
        s.pop();
    }
    /**
     * create a connection between two vertices if they are in graph
     * @param start a starting vertice in the graph
     * @param end  a ending vertice in the graph
     */
    public void addFlight(String start,String end,int i)
    {
        if(start.equals(end)) {System.out.println("The departure and destination can not be the same.\n");return;}
        Vertice departure = getVertice(start);
        Vertice dest = getVertice(end);
        if(departure == null)
        {System.out.println(start + " doesn't exist. Try again.\n");return;}
        if(dest == null)
        {System.out.println(end + " doesn't exist. Try again.\n");return;}

        Edge newEdge = new Edge(departure,dest,i);
        if(edges.contains(newEdge))
        {
            System.out.println("add from " + start + " to "+ end + " ,but from " + start + " to "+ end + " has existed.\n");return;
        }
        edges.add(newEdge);
        departure.addEdge(newEdge);
        System.out.println("add from " + start + " to "+ end + ".\n");
    }

    /**
     * remove a existing connection between two vertices
     * @param start an existing vertice as starting point in graph
     * @param end an existing vertice as ending point in graph
     */
    public void removeFlight(String start,String end)
    {
        if(start.equals(end)) {System.out.println("The departure and destination can not be the same.\n");return;}
        Vertice departure = getVertice(start);
        Vertice dest = getVertice(end);
        if(departure == null)
        {System.out.println(start + " doesn't exist. Try again.\n");return;}
        if(dest == null)
        {System.out.println(end + " doesn't exist. Try again.\n");return;}
        Edge tempt = new Edge(departure,dest,0);
        for(Edge e:edges)
        {
            if(e.equals(tempt)) {
                e.getOrigin().removeEdge(e);
                edges.remove(e);
                System.out.println("Have removed the flight from "+ start +" to " + end + ".");
                return;
            }
        }
        System.out.println("The flight from "+ start +" to " + end + " doesn't exist.");
    }
    //returns the number of single flights in the graph
    public int totalCount()
    {
        return edges.size();
    }
    //debug method
    public int numPort(){return vertices.size();}

    //debug method
    public void printGraph()
    {

        for(Vertice v:vertices){
            System.out.println(v);
            for(Edge e:v.incidentEdges()) {System.out.print(e+" ");}
            System.out.println();}
        for(Edge e:edges){
            System.out.println(e);
            System.out.println(e.getDes());
            System.out.println(e.getDes().incidentEdges());}
    }
    //helper method:flush the status of graph
    private void flush()
    {
        for(Vertice v:vertices){v.setStatus(false);}
        for(Edge e:edges){e.setStatus("UNEXPLORED");}
        path = "";
    }
    //helper method when flight is added or removed from the graph
    private void resetVerticeDist()
    {
        for(Vertice v:vertices){v.setDist(10000000);}
    }

}

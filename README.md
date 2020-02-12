# FlightLines_Java

 You are now given a file named “routes with costs.csv” which contains not only the flight routes but also their associated cost (also fictional) in a third column.
Given this information you need to add a method to your “AirlineConnections” class. That method will be named cheapestFlight() and will return the path with the lowest cost given two airport codes (including the overall cost). Of course, you may also need to modify your graph implementation to include the costs associated to each edge. The rest of the class can remain the same. Now the update AirlineConnections class should be as follows:


class AirlineConnections {
 AirlineConnections(filename) void addFlight(DEP, DES, cost) 
void removeFlight(DEP, DES)
String findFlight(DEP, DES)
String cheapestFlight (DEP, DES)
int totalCount()
}

In order to create this new method, you should implement Dijkstra’s algorithm.

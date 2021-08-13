package com.company;

import java.util.*;
import java.util.Queue;

public class WeightedGraph {
    static int totalmiles=0;
    static class Edge {
        int source;
        int destination;
        int weight;

        public Edge(int source, int destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }
    }
    static class Graph {
        int vertices;
        LinkedList<Edge>[] adjacencylist;
        //Constructor for Graph
        Graph(int vertices) {
            this.vertices = vertices;
            adjacencylist = new LinkedList[vertices];
            //initialize adjacency lists for all the vertices
            for (int i = 0; i < vertices; i++) {
                adjacencylist[i] = new LinkedList<>();
            }
        }
        //This Functions creates edges.
        public void addEdge(int source, int destination, int weight) {
            Edge edge = new Edge(source, destination, weight);
            Edge e1=new Edge(destination,source,weight);
            adjacencylist[source].addFirst(edge);
            adjacencylist[destination].add(e1);
        }

        //Finds the fastest route from one city to another city.
        public ArrayList<Integer> route(int start, int end){
            int startcheck=start;
            ArrayList<Integer >route= new ArrayList<>();
            ArrayList<Integer>vertex=new ArrayList<Integer>();
            ArrayList<Boolean>known = new ArrayList<Boolean>();
            ArrayList<Integer>path = new ArrayList<Integer>();
            ArrayList<Integer>cost = new ArrayList<Integer>();
            for(int v=0;v<vertices;v++){
                vertex.add(v);
                known.add(false);
                path.add(-1);
                cost.add(Integer.MAX_VALUE);
            }
            cost.set(start,0);
            for (int i = 0; i < vertices; i++)
            {
                int v=least_cost_unknown_vertex(known,cost);
                known.set(v,true);
                //checks closest adjacent vertices and sets path
                for(Edge e:adjacencylist[v]){
                    if(cost.get(e.destination) > cost.get(v)+edgeWeight(v,e.destination) ){
                        path.set(e.destination,v);
                        cost.set(e.destination, cost.get(v)+edgeWeight(v,e.destination));
                    }
                }
                if(known.get(end)) break;
            }
            int n=end;
            //adds all cities on fastest path to route
            while (n!=startcheck){
                route.add(0,path.get(n));
                n = path.get(n);
            }
            totalmiles+=cost.get(end);
            route.add(end);
            return route;
        }
        //This checks what the nearest attraction city is and runs the route function from the start to the nearest city. Then it sets start to the nearest city
        //and keeps on running until there are no more attractions on the list.
        public ArrayList<Integer>attractionRoute(ArrayList<Integer>attractionList,int start, int end){
            ArrayList<Integer>routeList= new ArrayList<>();
            if(attractionList.isEmpty()){
                return route(start,end);
            }
            int maxPlace=0;
            while (!attractionList.isEmpty()){
                int max=Integer.MAX_VALUE;
                for(int i:attractionList){
                    if(routeCost(start,i)<max){
                        max=routeCost(start,i);
                        maxPlace=i;
                    }
                }
                for(int s : route(start,maxPlace)){
                    if(!routeList.contains(s)){
                        routeList.add(s);
                    }
                }
                start=maxPlace;
                attractionList.remove((Integer) maxPlace);
            }
            for(int s:route(maxPlace,end)){
                if(!routeList.contains(s)){
                    routeList.add(s);
                }
            }
            return routeList;
        }
        //This calculates how many miles it is from one city to another. The least amount of miles.
        public int routeCost(int start, int end){
            int startcheck=start;
            ArrayList<Integer>vertex1=new ArrayList<Integer>();
            ArrayList<Boolean>known1 = new ArrayList<Boolean>();
            ArrayList<Integer>path1 = new ArrayList<Integer>();
            ArrayList<Integer>cost1 = new ArrayList<Integer>();
            for(int v=0;v<vertices;v++){
                vertex1.add(v);
                known1.add(false);
                path1.add(-1);
                cost1.add(Integer.MAX_VALUE);
            }
            cost1.set(start,0);
            for (int i = 0; i < vertices; i++)
            {
                int v=least_cost_unknown_vertex(known1,cost1);
                known1.set(v,true);
                for(Edge e:adjacencylist[v]){
                    if(cost1.get(e.destination) > cost1.get(v)+edgeWeight(v,e.destination) ){
                        path1.set(e.destination,v);
                        cost1.set(e.destination, cost1.get(v)+edgeWeight(v,e.destination));
                    }
                }
                if(known1.get(end)) break;
            }
            int n=end;
            int directMiles=0;
            while (n!=startcheck){
                n = path1.get(n);
            }
            directMiles+=cost1.get(end);
            return directMiles;
        }
        //This function calculates the weight of the connected edges.
        public int edgeWeight(int s, int y){
            for (int i = 0; i < vertices; i++) {
                LinkedList<Edge> list = adjacencylist[i];
                for (int j = 0; j < list.size(); j++) {
                    if((i==s && list.get(j).destination==y) || (i==y&&list.get(j).destination==s)){
                        int returnValue=list.get(j).weight;
                        return returnValue;
                    }
                }
            }
            return -1;
        }
        //This function finds out what the next least cost unknown vertex is.
        public int least_cost_unknown_vertex(ArrayList<Boolean>known,ArrayList<Integer>cost){
            int leastcostvertex=Integer.MAX_VALUE;
            int returnVertex=0;
            for(int i=0;i<known.size();i++){
                if(!known.get(i) && cost.get(i)<leastcostvertex){
                    returnVertex=i;
                    leastcostvertex=cost.get(i);
                }
            }
            return returnVertex;
        }
    }
}
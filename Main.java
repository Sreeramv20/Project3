package com.company;

import java.io.IOException;
import java.util.*;

public class Main extends WeightedGraph {
    public static void main(String[] args) throws Exception {
        Roads r = new Roads();
        r.read_csv_file(args[0]);
        Attractions a=new Attractions();
        a.readAttractions(args[1]);
        Hashtable<String, Integer> cityToNum = r.getCityToNum();
        Hashtable<Integer, String> numToCity = r.getNumToCity();
        Hashtable<String,String>attractionState=a.getAttractions();
        Scanner scnr = new Scanner(System.in);
        System.out.println("Name of starting city (or EXIT to quit): ");
        String startingCity = scnr.nextLine();
        if(startingCity.toLowerCase().equals("exit")){
            return;
        }
        System.out.println("Name of Ending City: ");
        String endingCity=scnr.nextLine();
        Boolean run=true;
        ArrayList<Integer> attractions= new ArrayList<>();
        while(run){
               System.out.println("List an Attraction along the way (or ENOUGH to stop listing): ");
               String attractionInput = scnr.nextLine();
               if(!attractionState.containsKey(attractionInput)&&!attractionInput.toLowerCase().equals("enough")){
                   System.out.println("Attraction '"+attractionInput+"' unknown");
               }
               if(attractionInput.toLowerCase().equals("enough")){
                   break;
               }
               if(attractionState.containsKey(attractionInput)) {
                   String acity = attractionState.get(attractionInput);
                   int attractionCityNum = cityToNum.get(acity);
                   attractions.add(attractionCityNum);
               }
        }
        int src = cityToNum.get(startingCity), dest = cityToNum.get(endingCity);
        WeightedGraph.Graph rs = r.returnGraph();
        ArrayList<Integer>testAttractions = rs.attractionRoute(attractions,src,dest);
        System.out.println("ROUTE:");
        for(int i:testAttractions){
            System.out.println(numToCity.get(i));
        }
        int totalmiles=r.getTotalMiles();
        System.out.println("Total Miles:"+totalmiles);
    }
}


